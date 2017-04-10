<#assign ctx="${(rca.contextPath)!''}">

<div class="list-line">
    <div class="title">
    ${category.name}(${category.articleCount})<small>分类</small>
    </div>
    <#list page.list as article>
        <div>
            <a href="#article/${article.id}">${article.title}</a>
        </div>
    </#list>
</div>

<div class="clear-float"></div>

<@c.pagination url="#category/${category.code}"/>
