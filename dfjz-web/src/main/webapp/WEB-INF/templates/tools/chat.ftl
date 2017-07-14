<#assign title="智能小胖"/>
<#assign ctx="${(rca.contextPath)!''}">

<@override name="content">

<div>
    <a href="javascript:" id="chat">开始聊天</a>
</div>

<script>

    $("#chat").click(function () {
        var webSocket = new WebSocket("ws://127.0.0.1:8080/chat/server");

        webSocket.onerror = function (e) {
            console.log("error" + e);
        };
        webSocket.onopen = function (e) {
            console.log("open" + e);
        };
        webSocket.onmessage = function (e) {
            console.log("message:" + e.data);
        };
        return false;
    });


</script>

<script>
    window.document.title = $(".visit-title").text() + " | 东方娇子";
</script>

</@override>

<@extends name="../content-layout.ftl"/>