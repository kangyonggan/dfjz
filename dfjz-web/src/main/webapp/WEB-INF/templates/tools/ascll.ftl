<#assign ctx="${(rca.contextPath)!''}">

<h2 class="visit-title">ASCLL码对照表</h2>

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

<script>
    window.document.title = $(".visit-title").text() + " | 东方娇子";
</script>
