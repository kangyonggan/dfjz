<#assign title="全部工具"/>
<#assign ctx="${(rca.contextPath)!''}">
<#assign ctx="${(rca.contextPath)!''}">

<@override name="content">

<h2 class="visit-title">${title}</h2>

<div class="tools-list">
    <div class="item">
        <a href="#tools/qr">
            <img src="${ctx}/static/app/images/tools/qr.png"/>
            <p>生成二维码</p>
        </a>
    </div>
    <div class="item">
        <a href="#tools/ascll">
            <img src="${ctx}/static/app/images/tools/table.png"/>
            <p>ASCLL对照表</p>
        </a>
    </div>
    <div class="item">
        <a href="#tools/http">
            <img src="${ctx}/static/app/images/tools/http.png"/>
            <p>HTTP状态码详解</p>
        </a>
    </div>
    <div class="item">
        <a href="#tools/html">
            <img src="${ctx}/static/app/images/tools/html.png"/>
            <p>HTML转义字符</p>
        </a>
    </div>
    <div class="item">
        <a href="#tools/xml">
            <img src="${ctx}/static/app/images/tools/xml.png"/>
            <p>XML格式化</p>
        </a>
    </div>
    <div class="item">
        <a href="#tools/sql">
            <img src="${ctx}/static/app/images/tools/sql.png"/>
            <p>SQL格式化</p>
        </a>
    </div>
    <div class="item">
        <a href="#tools/json">
            <img src="${ctx}/static/app/images/tools/json.png"/>
            <p>JSON格式化</p>
        </a>
    </div>
    <div class="item">
        <a href="#tools/markdown">
            <img src="${ctx}/static/app/images/tools/markdown.png"/>
            <p>Markdown编辑器</p>
        </a>
    </div>
    <div class="item">
        <a href="#tools/js">
            <img src="${ctx}/static/app/images/tools/js.png"/>
            <p>JS代码压缩</p>
        </a>
    </div>
    <div class="item">
        <a href="#tools/css">
            <img src="${ctx}/static/app/images/tools/css.png"/>
            <p>CSS代码压缩</p>
        </a>
    </div>
    <div class="item">
        <a href="#tools/idcard">
            <img src="${ctx}/static/app/images/tools/idcard.png"/>
            <p>身份证查询</p>
        </a>
    </div>
</div>

<script>
    window.document.title = "工具 | 东方娇子";
</script>

</@override>

<@extends name="../content-layout.ftl"/>