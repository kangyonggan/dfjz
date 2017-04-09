<#assign ctx="${(rca.contextPath)!''}">

<div class="detail-header">
    <h1>${article.title}</h1>
    <div class="detail-info">
        <span>发表于 ${article.createdTime?date}</span>
        <span>分类于 <a href="#category/${article.categoryCode}">${article.categoryName}</a></span>
        <span>访问量 <a href="#">${article.visitCount}</a></span>
        <span>评论数 <a href="#">${article.commentCount}</a></span>
    </div>
</div>

<div class="detail-main">
    <div class="detail-toc">
        <@c.toc toc=toc/>
    </div>
    <div class="detail-content markdown">
    ${article.content}
    </div>
</div>

<div class="clear-float"></div>

<div class="detail-pay">
    <a href="javascript:">赏</a>
    <div class="hidden">
        <img src="${ctx}/static/app/images/alipay.png"/>
        <div>支付宝打赏</div>
    </div>
</div>

<div class="detail-bottom-line"></div>

<div class="detail-nears">
<#if provArticle??>
    <a class="left" href="#article/${provArticle.id}" title="${provArticle.title}">< ${provArticle.title}</a>
</#if>
<#if nextArticle??>
    <a class="right" href="#article/${nextArticle.id}" title="${nextArticle.title}">${nextArticle.title} ></a>
</#if>
</div>

<div class="detail-comment">
    <form action="${ctx}/article/${article.id}/comment" method="post">
        <input type="text" name="username" placeholder="姓名..."/>
        <input type="email" name="email" placeholder="电子邮箱..."/>
        <textarea name="content" placeholder="不要吝啬你的表扬..."></textarea>
        <input type="submit" value="提交评论"/>
    </form>
</div>

<#if comments?? && comments?size gt 0>
<div class="detail-comment-list">
    <div class="detail-comment-info">最新评论</div>

    <#list comments as comment>
        <div class="detail-comment-item">
            <div class="left">
                ${comment.username?substring(0, 1)}
            </div>
            <div class="right">
                <div class="username-time">
                    <span>${comment.username}[${comment.email}]</span>
                    <span><@c.relative_date datetime=comment.createdTime/></span>
                </div>
                <div class="content">${comment.content}</div>
                <div class="tool">
                    <a href="#">赞(4)</a>
                    <a href="#">踩(6)</a>
                </div>
            </div>
        </div>
    </#list>
</div>
</#if>

<script src="${ctx}/static/app/js/detail.js"></script>
