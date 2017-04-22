package com.kangyonggan.app.dfjz.biz.service.impl;

import com.kangyonggan.app.dfjz.biz.service.DictionaryService;
import com.kangyonggan.app.dfjz.model.annotation.LogTime;
import com.kangyonggan.app.dfjz.model.constants.AppConstants;
import com.kangyonggan.app.dfjz.model.vo.Dictionary;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author kangyonggan
 * @since 2017/4/22 0022
 */
@Service
public class DictionaryServiceImpl extends BaseService<Dictionary> implements DictionaryService {

    @Override
    @LogTime
    public void saveDictionary(Dictionary dictionary) {
        super.insertSelective(dictionary);
    }

    @Override
    @LogTime
    public List<Dictionary> findDictionariesByType(String type) {
        Example example = new Example(Dictionary.class);
        example.createCriteria().andEqualTo("isDeleted", AppConstants.IS_DELETED_NO).andEqualTo("type", type);

        example.setOrderByClause("sort asc");
        return super.selectByExample(example);
    }
}
