<#assign title="HTML转义字符"/>
<#assign ctx="${(rca.contextPath)!''}">

<@override name="content">

<h2 class="visit-title">${title}</h2>

<h2 class="visit-title">HTML转义字符</h2>

<#list 0..3 as i>
<table class="table ascll-table">
    <thead>
    <tr>
        <th>字符</th>
        <th>转义字符</th>
    </tr>
    </thead>

    <tbody>
        <#list (i * 23)..(i * 23 + 22) as j>
        <#if j lt htmls?size>
        <tr>
            <td>${htmls[j].code}</td>
            <td>${htmls[j].name?html}</td>
        </tr>
        </#if>
        </#list>
    </tbody>
</table>
</#list>

<script>
    window.document.title = $(".visit-title").text() + " | 东方娇子";
</script>

</@override>

<@extends name="../content-layout.ftl"/>