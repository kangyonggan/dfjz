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
        <a href="#tools/qr2">
            <img src="${ctx}/static/app/images/tools/qr2.png"/>
            <p>解析二维码</p>
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
    <div class="item">
        <a href="#tools/gencard">
            <img src="${ctx}/static/app/images/tools/gencard.png"/>
            <p>生成身份证</p>
        </a>
    </div>
    <div class="item">
        <a href="#tools/charset">
            <img src="${ctx}/static/app/images/tools/charset.png"/>
            <p>编码转换</p>
        </a>
    </div>
    <div class="item">
        <a href="#tools/compare">
            <img src="${ctx}/static/app/images/tools/compare.png"/>
            <p>properties文件对比</p>
        </a>
    </div>
    <div class="item">
        <a href="#tools/bazi">
            <img src="${ctx}/static/app/images/tools/bazi.png"/>
            <p>八字、五行</p>
        </a>
    </div>
    <div class="item">
        <a href="#tools/chat">
            <img src="${ctx}/static/app/images/tools/chat.png"/>
            <p>智能小胖</p>
        </a>
    </div>
</div>

<script>
    window.document.title = "工具 | 东方娇子";
</script>

</@override>

<@extends name="../content-layout.ftl"/>