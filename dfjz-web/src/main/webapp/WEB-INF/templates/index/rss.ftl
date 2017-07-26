<#assign title="订阅"/>
<#assign ctx="${(rca.contextPath)!''}">

<@override name="content">

<h2 class="visit-title">全部RSS资源</h2>

<div class="tools-list">
    <div class="item">
        <a href="${ctx}/rss/blog.xml" target="_blank">
            <img src="${ctx}/static/app/images/article.png"/>
            <p>全部文章</p>
        </a>
    </div>
    <div class="item">
        <a href="${ctx}/rss/sitemap.xml" target="_blank">
            <img src="${ctx}/static/app/images/sitemap.png"/>
            <p>网站地图</p>
        </a>
    </div>
    <#list rssMap?keys as key>
        <div class="item">
            <a href="${ctx}/rss/${rssMap[key]}" target="_blank">
                <#if key?length gte 4>
                    <#if key?substring(0,4)=='不死武尊'>
                        <img src="${ctx}/static/app/images/1337.jpg" class="book-pic"/>
                        <p class="book-name">${key}</p>
                    <#elseif key?substring(0,4)=='逆天邪神'>
                        <img src="${ctx}/static/app/images/2722.jpg" class="book-pic"/>
                        <p class="book-name">${key}</p>
                    <#elseif key?substring(0,4)=='绝世战魂'>
                        <img src="${ctx}/static/app/images/18612.jpg" class="book-pic"/>
                        <p class="book-name">${key}</p>
                    <#elseif key?substring(0,4)=='嫡女重生'>
                        <img src="${ctx}/static/app/images/14141.jpg" class="book-pic"/>
                        <p class="book-name">${key}</p>
                    <#else>
                        <img src="${ctx}/static/app/images/book.png"/>
                        <p>${key}</p>
                    </#if>
                <#else>
                    <img src="${ctx}/static/app/images/nmhn.jpg" class="book-pic"/>
                    <p class="book-name">农门悍女掌家小厨娘(${(key?number - 1) * 100 + 1}-${key?number * 100}章)</p>
                </#if>
            </a>
        </div>
    </#list>
</div>

</@override>

<@extends name="../content-layout.ftl"/>

