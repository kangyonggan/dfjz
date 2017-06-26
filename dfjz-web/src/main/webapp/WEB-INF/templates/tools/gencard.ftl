<#assign title="生成身份证"/>
<#assign ctx="${(rca.contextPath)!''}">
<#assign prov = RequestParameters.prov!'' />
<#assign startAge = RequestParameters.startAge!'1' />
<#assign endAge = RequestParameters.endAge!'100' />
<#assign sex = RequestParameters.sex!'' />
<#assign len = RequestParameters.len!'18' />
<#assign size = RequestParameters.size!'10' />

<@override name="content">

<h2 class="visit-title">${title}</h2>

<div>
    <h4>生成条件：</h4>
    <form action="#tools/gencard" method="post" id="xml-fromat-form">
        <div class="label">省份：</div>
        <select name="prov" class="select input">
            <option value="">随机</option>
            <#list cityCodes?keys as key>
                <option value="${key}" <#if key==prov>selected</#if>>${cityCodes["${key}"]}</option>
            </#list>
        </select>

        <div class="label">年龄段(周岁)</div>
        <input type="number" class="small-input" name="startAge" value="${startAge}"/> -
        <input type="number" class="small-input" name="endAge" value="${endAge}"/>

        <div class="label">性别</div>
        <select name="sex" class="select input">
            <option value="-1">随机</option>
            <option value="0" <#if sex=="0">selected</#if>>男</option>
            <option value="1" <#if sex=="1">selected</#if>>女</option>
        </select>

        <div class="label">位数</div>
        <select name="len" class="select input">
            <option value="-1">随机</option>
            <option value="15" <#if len=="15">selected</#if>>15位</option>
            <option value="18" <#if len=="18">selected</#if>>18位</option>
        </select>

        <div class="label">生成数量（1~100）</div>
        <input type="number" class="input" name="size" placeholder="生成数量" value="${size}" required />

        <br/>
        <button class="btn btn-success mt-10" data-loading-text="正在生成...">生成</button>
    </form>
</div>

<div class="mt-30">
    <#if result??>
        <pre class="result"><code>${result?html}</code></pre>
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