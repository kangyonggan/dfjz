package com.kangyonggan.app.dfjz.biz.service.impl;

import com.kangyonggan.app.dfjz.biz.service.ArticleService;
import com.kangyonggan.app.dfjz.biz.service.CommentService;
import com.kangyonggan.app.dfjz.biz.service.MailService;
import com.kangyonggan.app.dfjz.biz.service.SmsService;
import com.kangyonggan.app.dfjz.biz.util.PropertiesUtil;
import com.kangyonggan.app.dfjz.common.IPUtil;
import com.kangyonggan.app.dfjz.mapper.CommentMapper;
import com.kangyonggan.app.dfjz.model.annotation.LogTime;
import com.kangyonggan.app.dfjz.model.constants.AppConstants;
import com.kangyonggan.app.dfjz.model.dto.CommentCountDto;
import com.kangyonggan.app.dfjz.model.vo.Article;
import com.kangyonggan.app.dfjz.model.vo.Comment;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @author kangyonggan
 * @since 2017/4/9 0009
 */
@Service
public class CommentServiceImpl extends BaseService<Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private MailService mailService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private SmsService smsService;

    @Override
    @LogTime
    public List<Comment> findCommentsByArticleId(Long articleId) {
        Example example = new Example(Comment.class);
        example.createCriteria().andEqualTo("isDeleted", AppConstants.IS_DELETED_NO).andEqualTo("articleId", articleId);

        example.setOrderByClause("id desc");
        return super.selectByExample(example);
    }

    @Override
    @LogTime
    public void saveComment(Comment comment) {
        super.insertSelective(comment);
    }

    @Override
    @LogTime
    public List<CommentCountDto> findArticlesCommentCount() {
        return commentMapper.selectArticlesCommentCount();
    }

    @Override
    public void updateCommitCity(Long id, String ip) {
        Map<String, String> resultMap = IPUtil.getIpInfo(ip);

        String city = resultMap.get("city");
        if (StringUtils.isEmpty(city)) {
            city = "未知地";
        }
        city += "网友";

        Comment comment = new Comment();
        comment.setId(id);
        comment.setCity(city);

        super.updateByPrimaryKeySelective(comment);

        comment = super.selectByPrimaryKey(id);
        Article article = articleService.findArticleById(comment.getArticleId());

        smsService.sendSms(PropertiesUtil.getProperties("app.mobile"), city, ip, article.getTitle(), comment.getContent());

        mailService.send(PropertiesUtil.getProperties("mail.receiver"),
                article.getTitle() + " - 评论通知",
                comment.getContent() + "\r\n\r\n来自: " + comment.getCity() + "(" + ip + ")");

    }
}
