<#assign title="properties文件对比"/>
<#assign ctx="${(rca.contextPath)!''}">

<@override name="content">

<h2 class="visit-title">${title}</h2>

<div>
    <form action="${ctx}/tools/compare" method="post" id="compare-form" class="tools-form"
          enctype="multipart/form-data">
        <div>文件一：</div>
        <input type="file" id="fileLeft" name="fileLeft"/><br/><br/>
        <div class="mt-10"></div>
        <div>文件二：</div>
        <input type="file" id="fileRight" name="fileRight"/><br/><br/>

        <button class="btn btn-success" data-loading-text="正在对比...">对比</button>
    </form>
</div>

<div class="mt-30">
    <div id="result" class="hidden">
        <h4>结果：</h4>
        <pre class="result"><code></code></pre>
    </div>
</div>

<script>
    $("#compare-form").submit(function () {
        $btn = $(this).find("button");
        $btn.text($btn.attr("data-loading-text")).attr("disabled", "disabled");

        var formData = new FormData();

        var filename = $("#fileLeft").val();
        if (!filename || filename == '') {
            alert("请选择第一个properties文件");
            $btn.text("对比").removeAttr("disabled");
            return false;
        }

        filename = $("#fileRight").val();
        if (!filename || filename == '') {
            alert("请选择第二个properties文件");
            $btn.text("对比").removeAttr("disabled");
            return false;
        }

        formData.append("fileLeft", document.getElementById("fileLeft").files[0]);
        formData.append("fileRight", document.getElementById("fileRight").files[0]);

        $.ajax({
            url: $(this).attr("action"),
            type: "POST",
            data: formData,
            contentType: false,
            processData: false,
            success: function (data) {
                $("#result").removeClass("hidden").find("code").text(data);
                $btn.text("对比").removeAttr("disabled");
            },
            error: function () {
                $("#result").removeClass("hidden").find("code").text("网络错误，请稍后再试！");
                $btn.text("对比").removeAttr("disabled");
            }
        });

        return false;
    });

</script>

<script>
    window.document.title = $(".visit-title").text() + " | 东方娇子";
</script>

</@override>

<@extends name="../content-layout.ftl"/>