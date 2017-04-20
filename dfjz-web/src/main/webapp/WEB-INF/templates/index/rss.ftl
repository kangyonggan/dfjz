<#assign ctx="${(rca.contextPath)!''}">

<#assign ctx="${(rca.contextPath)!''}">

<h2 class="visit-title">全部RSS资源</h2>

<ol>
    <li><a href="${ctx}/rss/blog.xml" target="_blank">全部文章</a></li>
    <li><a href="${ctx}/rss/sitemap.xml" target="_blank">站点地图</a></li>
<#list rssMap?keys as key>
    <li><a href="${ctx}/rss/${rssMap[key]}" target="_blank">${key}</a></li>
</#list>
</ol>