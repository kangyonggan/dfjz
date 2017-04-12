<#assign ctx="${(rca.contextPath)!''}">

<table>
    <thead>
    <tr>
        <th>被访问的文章</th>
        <th>访问者IP</th>
        <th>国家</th>
        <th>地区</th>
        <th>省份</th>
        <th>城市</th>
        <th>运营商</th>
        <th>访问时间</th>
    </tr>
    </thead>

    <tbody>
    <#list page.list as visit>
    <tr>
        <td><a href="#article/${visit.articleId}" target="_blank">${visit.articleTitle}</a></td>
        <td>${visit.ip}</td>
        <td>${visit.country!''}</td>
        <td>${visit.area!''}</td>
        <td>${visit.region!''}</td>
        <td>${visit.city!''}</td>
        <td>${visit.isp!''}</td>
        <td>${visit.createdTime?datetime}</td>
    </tr>
    </#list>
    </tbody>
</table>

<div class="clear-float"></div>

<@c.pagination url="#article/${id}/visits"/>