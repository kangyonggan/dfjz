<#assign ctx="${(rca.contextPath)!''}">

<div class="list-line">
    <div class="total">好! 目前共计 ${page.total} 篇日志。 继续努力。</div>
    <div class="tag">2017</div>
<#list page.list as article>
    <div>
        <a href="#article/${article.id}"><span>${article.createdTime?string("MM-dd")}</span>${article.title}</a>
    </div>
</#list>
</div>

<div class="clear-float"></div>

<@c.pagination url="#archives"/>
