<#assign title="文件上传"/>
<#assign ctx="${(rca.contextPath)!''}">

<@override name="content">

<h2 class="visit-title">${title}</h2>

<div>
    <form action="${ctx}/file/upload" method="post" id="file-form" class="tools-form" enctype="multipart/form-data">
        <div id="files">
            <input type="file"/>
        </div>
        <br/>
        <a href="javascript:" id="add-file" class="file-add">添加新文件</a>
        <br/><br/>
        <button class="btn btn-success" data-loading-text="正在上传...">上传</button>
    </form>
</div>

<div class="mt-30">
    <div id="result" class="hidden">
        <h4>结果：</h4>
        <pre class="result"><code></code></pre>
    </div>
</div>

<script>
    /**
     * 添加新文件
     */
    $("#add-file").click(function () {
        $("#files").append("<input type='file'/>");

        return false;
    });

    /**
     * 异步提交
     */
    $("#file-form").submit(function () {
        $btn = $(this).find("button");
        $btn.text($btn.attr("data-loading-text")).attr("disabled", "disabled");

        var formData = new FormData();

        var filename = $(this).find("input[type='file']").val();
        if (!filename || filename == '') {
            alert("至少选择一个文件");
            $btn.text("上传").removeAttr("disabled");
            return false;
        }

        var files = $(this).find("input[type='file']");
        for (var i = 0; i < files.length; i++) {
            formData.append("files", files[i].files[0]);
        }

        $.ajax({
            url: $(this).attr("action"),
            type: "POST",
            data: formData,
            contentType: false,
            processData: false,
            success: function (data) {
                $("#result").removeClass("hidden").find("code").text(data);
                $btn.text("上传").removeAttr("disabled");
            },
            error: function () {
                $("#result").removeClass("hidden").find("code").text("网络错误，请稍后再试！");
                $btn.text("上传").removeAttr("disabled");
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