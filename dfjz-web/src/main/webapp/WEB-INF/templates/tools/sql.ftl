<#assign title="SQL格式化"/>
<#assign ctx="${(rca.contextPath)!''}">
<#assign data = RequestParameters.data!'' />
<#assign dialect = RequestParameters.dialect!'' />

<@override name="content">

<h2 class="visit-title">${title}</h2>

<div>
    <h4>待格式化的SQL：</h4>
    <form action="#tools/sql" method="post" id="sql-fromat-form">
        <textarea class="textarea mb-10" name="data" placeholder="请输入需要格式化的sql" required>${data}</textarea>
        <select name="dialect" class="select">
            <option value="MySQL" <#if dialect=='MySQL'>selected</#if>>MySQL</option>
            <option value="SQLServer" <#if dialect=='SQLServer'>selected</#if>>SQLServer</option>
            <option value="Oracle" <#if dialect=='Oracle'>selected</#if>>Oracle</option>
        </select>
        <button class="btn btn-success" data-loading-text="正在格式化...">格式化</button>
    </form>
</div>

<div class="mt-30">
<#if result??>
    <pre class="result"><code>${result?html}</code></pre>
</#if>
</div>

<script>
    $("#sql-fromat-form").submit(function () {
        $btn = $(this).find("button");
        $btn.text($btn.attr("data-loading-text")).attr("disabled", "disabled");
    });
</script>

<script>
    window.document.title = $(".visit-title").text() + " | 东方娇子";
</script>

</@override>

<@extends name="../content-layout.ftl"/>