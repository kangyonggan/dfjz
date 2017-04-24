<#assign ctx="${(rca.contextPath)!''}">
<#assign data = RequestParameters.data!'' />
<#assign size = RequestParameters.size!'200' />

<h2 class="visit-title">生成二维码</h2>

<div>
    <h4>输入URL或其他文本：</h4>
    <form action="#tools/qr" method="post" id="qr-fromat-form" class="tools-form">
        <input class="input" name="data" placeholder="https://kangyonggan.com" value="${data}" required/><br/>

        <div class="mt-10"></div>

        <select name="size" class="select">
            <option value="100" <#if size=='100'>selected</#if>>100*100</option>
            <option value="200" <#if size=='200'>selected</#if>>200*200</option>
            <option value="300" <#if size=='300'>selected</#if>>300*300</option>
            <option value="400" <#if size=='400'>selected</#if>>400*400</option>
            <option value="500" <#if size=='500'>selected</#if>>500*500</option>
        </select>
        <button class="btn btn-success" data-loading-text="正在生成...">生成</button>
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