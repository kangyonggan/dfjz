<#assign title="身份证查询"/>
<#assign ctx="${(rca.contextPath)!''}">
<#assign data = RequestParameters.data!'340321199112273095' />

<@override name="content">

<h2 class="visit-title">${title}</h2>

<div>
    <h4>待查询的身份证：</h4>
    <form action="#tools/idcard" method="post" id="xml-fromat-form">
        <input class="input" name="data" placeholder="请输入需要格式化的xml" value="${data}" required />
        <button class="btn btn-success mt-10" data-loading-text="正在查询...">查询</button>
    </form>
</div>

<div class="mt-30">
<#if isIdCard??>
    <#if !isIdCard>
        <pre class="result"><code>您输入的身份证号码无效</code></pre>
    <#else>
        <pre class="result"><code><table>
                    <thead><tr><th>省份</th><th>性别</th><th>生日</th><th>年龄</th><th><#if data?length==15>转成18位<#else>转成15位</#if></th></tr></thead>
                    <tbody>
                    <tr><td>${province}</td><td>${(sex==0)?string('男', '女')}</td><td>${year}年${month}月${day}日</td><td>${age}周岁</td><td><#if data?length==15>${to18}<#else>${to15}</#if></td></tr>
                    </tbody>
                </table></code></pre>
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