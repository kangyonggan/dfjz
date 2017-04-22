<#assign ctx="${(rca.contextPath)!''}">
<#assign q = RequestParameters.q!'' />

<#if !page?? || !page.list?? || page.list?size lte 0>
<div class="article-list-empty">
    没有符合条件的文章
</div>
<#else>
<div class="article-list">
    <#list page.list as article>
        <div class="article-item">
            <h1>
                <a href="#article/${article.id}" class="article-title">${article.title}</a>
                <div class="article-title-line article-title-line-hide"></div>
            </h1>

            <div class="article-info">
                <span><img class="list-icon" src="${ctx}/static/app/images/calendar.png"/>发表于 ${article.createdTime?date}</span>
                <span><img class="list-icon" src="${ctx}/static/app/images/folder.png"/>分类于 <a href="#category/${article.categoryCode}">${article.categoryName}</a></span>
                <span><img class="list-icon" src="${ctx}/static/app/images/visit.png"/>访问量 <a href="#article/${article.id}/visits">${article.visitCount}</a></span>
                <span><img class="list-icon" src="${ctx}/static/app/images/comment.png"/>评论数 <a href="#article/${article.id}">${article.commentCount}</a></span>
            </div>

            <div class="article-summary markdown">
            ${article.content}
            </div>

            <a class="article-more" href="#article/${article.id}">阅读全文 »</a>
        </div>
    </#list>

    <#if q!=''>
        <@c.pagination url="#search" param="q=${q}"/>
    <#else>
        <@c.pagination url="#index"/>
    </#if>
</div>
</#if>

<script>var q = '${q!''}';</script>
<script src="${ctx}/static/app/js/index.js"></script>