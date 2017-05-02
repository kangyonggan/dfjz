<#assign title="JSON格式化"/>
<#assign ctx="${(rca.contextPath)!''}">
<#assign data = RequestParameters.data!'' />

<@override name="content">

<h2 class="visit-title">${title}</h2>

<div>
    <h4>待格式化的JSON：</h4>
    <form action="#tools/json" method="post" id="json-fromat-form">
        <textarea class="textarea" name="data" placeholder="请输入需要格式化的json" required>${data}</textarea>
        <button class="btn btn-success mt-10" data-loading-text="正在格式化...">格式化</button>
    </form>
</div>

<div class="mt-30">
<#if result??>
    <pre class="result"><code>${result?html}</code></pre>
</#if>
</div>

<script>
    $("#json-fromat-form").submit(function () {
        $btn = $(this).find("button");
        $btn.text($btn.attr("data-loading-text")).attr("disabled", "disabled");
    });
</script>

<script>
    window.document.title = $(".visit-title").text() + " | 东方娇子";
</script>

</@override>

<@extends name="../content-layout.ftl"/>