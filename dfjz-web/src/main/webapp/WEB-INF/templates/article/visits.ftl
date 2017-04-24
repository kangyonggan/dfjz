<#assign ctx="${(rca.contextPath)!''}">

<h2>
    <a href="#article/${article.id}" class="visit-title">${article.title}</a>
</h2>

<table class="block">
    <thead>
    <tr>
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

<@c.pagination url="#article/${article.id}/visits"/>

<script>
    window.document.title = "所有访问 | ${article.title}| 东方娇子";
</script>