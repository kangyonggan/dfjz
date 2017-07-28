<#assign title="身份证查询"/>
<#assign ctx="${(rca.contextPath)!''}">
<#assign data = RequestParameters.data!'' />

<@override name="content">

<h2 class="visit-title">${title}</h2>

<div>
    <h4>待查询的身份证：</h4>
    <form action="#tools/idcard" method="post" id="xml-fromat-form">
        <input class="input" name="data" placeholder="请输入待查询的身份证" value="${data}" required/>
        <button class="btn btn-success mt-10" data-loading-text="正在查询...">查询</button>
    </form>
</div>

<div class="mt-30">
    <#if isIdCard??>
        <#if !isIdCard>
            <pre class="result"><code>您输入的身份证号码无效</code></pre>
        <#else>
            <pre class="result"><code>省份: ${province!'不存在的省'}<br/>性别: ${(sex==0)?string('男', '女')}<br/>生日: ${year}年${month}月${day}日<br/>年龄: ${age}周岁<br/>地区: ${area!'不存在的地区'}<br/><#if data?length==15>转成18位<#else>转成15位</#if>: <#if data?length==15>${to18}<#else>${to15}</#if><br/>生肖: ${shengXiao}<br/>出生年份: ${ganZhi}年<br/>运势: ${yunshi}</code></pre>
        </#if>
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