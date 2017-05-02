<#assign title="非法请求"/>
<#assign ctx="${(rca.contextPath)!''}">

<@override name="content">
<h2 class="align-center">为防止灌水，3分钟内只能评论一次！</h2>

<div class="align-center">
    <a href="javascript:window.location.reload();" class="color-grey">返回文章</a>
</div>
</@override>

<@extends name="../content-layout.ftl"/>