<#assign title="ASCLL码对照表"/>
<#assign ctx="${(rca.contextPath)!''}">

<@override name="content">

<h2 class="visit-title">${title}</h2>

<#list 0..3 as i>
<table class="table ascll-table">
    <thead>
    <tr>
        <th>ASCLL值</th>
        <th>控制字符</th>
    </tr>
    </thead>

    <tbody>
        <#list (i * 32)..(i * 32 + 31) as j>
        <tr>
            <td>${asclls[j].code}</td>
            <td>${asclls[j].name}</td>
        </tr>
        </#list>
    </tbody>
</table>
</#list>
</@override>

<@extends name="../content-layout.ftl"/>