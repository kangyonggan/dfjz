<#assign title="Markdown编辑器"/>
<#assign ctx="${(rca.contextPath)!''}">
<#assign data = RequestParameters.data!'' />

<@override name="content">

<h2 class="visit-title">${title}</h2>

<div>
    <h4>把markdown转成html：</h4>
    <form action="#tools/markdown" method="post" id="markdown-fromat-form">
        <textarea class="textarea mb-10" name="data" placeholder="请输入需要转化的markdown" required>${data}</textarea>
        <button class="btn btn-success" data-loading-text="正在转化...">转成html</button>
    </form>
</div>

<div class="mt-30">
<#if result??>
    <h4>Html：</h4>
    <pre class="result"><code>${result?html}</code></pre>
</#if>
</div>

<div class="mt-30">
<#if result??>
    <h4>预览：</h4>
    ${result}
</#if>
</div>

<script>
    $("#markdown-fromat-form").submit(function () {
        $btn = $(this).find("button");
        $btn.text($btn.attr("data-loading-text")).attr("disabled", "disabled");
    });
</script>

<script>
    window.document.title = $(".visit-title").text() + " | 东方娇子";
</script>

</@override>

<@extends name="../content-layout.ftl"/>