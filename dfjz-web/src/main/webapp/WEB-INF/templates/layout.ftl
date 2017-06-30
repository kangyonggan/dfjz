<#assign ctx="${(rca.contextPath)!''}">

<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="description" content="代码片段,学习笔记,技术分享,解决方案,架构设计"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>

    <title>博客 | 东方娇子</title>

    <link rel="shortcut icon" href="${ctx}/static/app/images/favicon.ico" type="image/x-icon">

    <link rel="stylesheet" href="${ctx}/static/app/css/app.css"/>

    <script src="${ctx}/static/app/js/jquery.min.js"></script>
    <script>var ctx = '${ctx}';</script>
</head>
<body>

<#include "navbar.ftl">

<div class="height-170"></div>

<div class="sim-page"></div>

<div class="height-80"></div>

<#include "footer.ftl">

<div class="scroll-bar hidden">
    <a href="javascript:scroll(0,0)" class="top">&uarr;</a>
    <a href="javascript:scroll(0, 9999999999)" class="bottom">&darr;</a>
</div>

<script src="${ctx}/static/app/js/simpage.js"></script>
<script src="${ctx}/static/app/js/app.js"></script>
</body>
</html>