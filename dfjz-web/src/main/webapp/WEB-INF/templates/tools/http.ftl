<#assign ctx="${(rca.contextPath)!''}">

<h2 class="visit-title">HTTP状态码</h2>

<table class="table">
    <thead>
    <tr>
        <th width="60">状态码</th>
        <th>含义</th>
    </tr>
    </thead>

    <tbody>
    <#list https as http>
    <tr>
        <td>${http.code}</td>
        <td>${http.name}</td>
    </tr>
    </#list>
    </tbody>
</table>

<script>
    window.document.title = $(".visit-title").text() + " | 东方娇子";
</script>