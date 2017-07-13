<#assign title="八字、五行"/>
<#assign ctx="${(rca.contextPath)!''}">
<#assign lunar = RequestParameters.lunar!'1' />
<#assign year = RequestParameters.year!'' />
<#assign month = RequestParameters.month!'' />
<#assign day = RequestParameters.day!'' />
<#assign hour = RequestParameters.hour!'' />

<@override name="content">

<h2 class="visit-title">${title}</h2>

<div>
    <h4>待推算的出生日期：</h4>
    <form action="#tools/bazi" method="post" id="bazi-form">
        <div class="label">阴/阳历：</div>
        <select name="lunar" class="select input" required>
            <option value="0" <#if lunar=='0'>selected</#if>>阴历</option>
            <option value="1" <#if lunar=='1'>selected</#if>>阳历</option>
        </select>

        <div class="label">出生年份</div>
        <input type="number" class="input" name="year" value="${year}" placeholder="1991" max="2049" min="1900" required/>

        <div class="label">出生月份</div>
        <input type="number" class="input" name="month" value="${month}" placeholder="12" max="12" min="1" required/>

        <div class="label">出生日期</div>
        <input type="number" class="input" name="day" value="${day}" placeholder="27" max="31" min="1" required/>

        <div class="label">出生时辰</div>
        <input type="number" class="input" name="hour" value="${hour}" placeholder="15" max="23" min="0" required/>

        <br/>
        <br/>
        <button class="btn btn-success" data-loading-text="正在推算...">推算</button>
    </form>
</div>

<div class="mt-30">
<#if result??>
    <pre class="result"><code>${result?html}</code></pre>
</#if>
</div>

<script>
    $("#bazi-form").submit(function () {
        $btn = $(this).find("button");
        $btn.text($btn.attr("data-loading-text")).attr("disabled", "disabled");
    });
</script>

<script>
    window.document.title = $(".visit-title").text() + " | 东方娇子";
</script>

</@override>

<@extends name="../content-layout.ftl"/>