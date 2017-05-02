<#assign ctx="${(rca.contextPath)!''}">
<#assign title="CSS压缩"/>
<#assign data = RequestParameters.data!'' />

<@override name="content">

<h2 class="visit-title">${title}</h2>

<div>
    <h4>待压缩的CSS：</h4>
    <form action="#tools/css" method="post" id="css-fromat-form">
        <textarea class="textarea" name="data" placeholder="请输入需要压缩的css" required>${data}</textarea>
        <button class="btn btn-success mt-10" data-loading-text="正在压缩...">压缩</button>
    </form>
</div>

<div class="mt-30">
<#if result??>
    <pre class="result"><code>${result?html}</code></pre>
</#if>
</div>

<script>
    $("#css-fromat-form").submit(function () {
        $btn = $(this).find("button");
        $btn.text($btn.attr("data-loading-text")).attr("disabled", "disabled");
    });
</script>

<script>
    window.document.title = $(".visit-title").text() + " | 东方娇子";
</script>

</@override>

<@extends name="../content-layout.ftl"/>