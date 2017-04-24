<#assign ctx="${(rca.contextPath)!''}">
<#assign data = RequestParameters.data!'' />

<h2 class="visit-title">生成二维码</h2>

<div>
    <h4>URL或其他文本：</h4>
    <form action="#tools/qr" method="post" id="qr-fromat-form" class="tools-form">
        <input class="input" name="data" placeholder="https://kangyonggan.com" value="${data}" required/><br/>
        <button class="btn btn-success mt-10" data-loading-text="正在生成...">生成</button>
    </form>
</div>

<div class="mt-30">
<#if result??>
    <img src="${ctx}/upload/${result}"/>
</#if>
</div>

<script>
    $("#qr-fromat-form").submit(function () {
        $btn = $(this).find("button");
        $btn.text($btn.attr("data-loading-text")).attr("disabled", "disabled");
    });
</script>

<script>
    window.document.title = $(".visit-title").text() + " | 东方娇子";
</script>