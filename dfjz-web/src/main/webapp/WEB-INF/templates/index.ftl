<#assign ctx="${(rca.contextPath)!''}">

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
                <span>发表于 ${article.createdTime?date}</span>
                <span>分类于 <a href="#category/${article.categoryCode}">${article.categoryName}</a></span>
                <span>访问量 <a href="#">${article.visitCount}</a></span>
                <span>评论数 <a href="#">${article.commentCount}</a></span>
            </div>

            <div class="article-summary markdown">
            ${article.content}
            </div>

            <a class="article-more" href="#article/${article.id}">阅读全文 »</a>
        </div>
    </#list>

    <#if (page.list)?? && page.pages gt 1>
        <div class="pagination hidden-sm">
            <a href="#">&lt;</a>
            <a href="#">1</a>
            <a href="javascript:" class="active">2</a>
            <a href="#">3</a>
            <a href="#">4</a>
            <a href="#">5</a>
            <a href="#">&gt;</a>
        </div>

        <div class="pagination hidden-lg">
            <a href="#">&lt;</a>
            <a href="#">1</a>
            <a href="javascript:" class="active">2</a>
            <a href="#">3</a>
            <a href="#">&gt;</a>
        </div>
    </#if>
</div>
</#if>

<script src="${ctx}/static/app/js/index.js"></script>