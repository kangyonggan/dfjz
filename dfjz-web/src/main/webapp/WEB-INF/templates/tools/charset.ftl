<#assign title="编码转换"/>
<#assign ctx="${(rca.contextPath)!''}">
<#assign data = RequestParameters.data!'' />
<#assign charset = RequestParameters.charset!'' />

<@override name="content">

<h2 class="visit-title">${title}</h2>

<div>
    <h4>待转码的字符串：</h4>
    <form action="#tools/charset" method="post" id="xml-fromat-form">
        <textarea name="data" class="textarea" placeholder="请输入待转码的字符串" required>${data}</textarea>
        <div class="mt-10"></div>
        <select name="charset" class="select">
            <option value="1" <#if charset=='1'>selected</#if>>UTF-8 转 GBK</option>
            <option value="2" <#if charset=='2'>selected</#if>>UTF-8 转 IOS-8859-1</option>
            <option value="3" <#if charset=='3'>selected</#if>>GBK 转 UTF-8</option>
            <option value="4" <#if charset=='4'>selected</#if>>GBK 转 IOS-8859-1</option>
            <option value="5" <#if charset=='5'>selected</#if>>IOS-8859-1 转 UTF-8</option>
            <option value="6" <#if charset=='6'>selected</#if>>IOS-8859-1 转 GBK</option>
            <option value="7" <#if charset=='7'>selected</#if>>字符串 转 unicode</option>
            <option value="8" <#if charset=='8'>selected</#if>>unicode 转 字符串</option>
        </select>
        <div class="mt-10"></div>
        <button class="btn btn-success mt-10" data-loading-text="正在转码...">转码</button>
    </form>
</div>

<div class="mt-30">
    <#if result??>
        <pre class="result"><code>${result}</code></pre>
    </#if>
</div>

<script>
    $("#xml-fromat-form").submit(function () {
        $btn = $(this).find("button");
        $btn.text($btn.attr("data-loading-text")).attr("disabled", "disabled");
    });
</script>

<script>
    window.document.title = $(".visit-title").text() + " | 东方娇子";
</script>

</@override>

<@extends name="../content-layout.ftl"/>