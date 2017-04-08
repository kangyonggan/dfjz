<#assign ctx="${(rca.contextPath)!''}">

<div class="detail-header">
    <h1>从零开始搭建NexT主题的Hexo博客</h1>
    <div class="detail-info">
        <span>发表于 2017-4-8</span>
        <span>分类于 <a href="#">综合</a></span>
        <span>评论 <a href="#">5</a></span>
    </div>
</div>

<div class="detail-main">
    <div class="detail-toc">
        文章目录
    </div>
    <div class="detail-content">
        文章的内容
    </div>
</div>

<div class="detail-pay">
    <a href="javascript:">赏</a>
    <div class="hidden">
        <img src="${ctx}/static/app/images/alipay.png"/>
        <div>支付宝打赏</div>
    </div>
</div>

<div class="detail-bottom-line">
</div>

<script>
    var isHiddenPay = true;
    var $text = $(".detail-pay > div > div");
    var temp = 10, task;

    $(".detail-pay > a").click(function () {
        if (isHiddenPay) {
            $(".detail-pay > div").removeClass("hidden");
        } else {
            $(".detail-pay > div").addClass("hidden");
        }

        isHiddenPay = !isHiddenPay;

        return false;
    });

    $(".detail-pay > div > img").mouseover(function () {
        payOut();
        task = setInterval("payIn()", 5);
    }).mouseout(function () {
        payOut();
    });

    function payOut() {
        clearInterval(task);
        deg = 0;
        var t = $text[0];
        t.style.transform = "rotate(" + deg + "deg)";
        t.style.webkitTransform = "rotate(" + deg + "deg)";
        t.style.OTransform = "rotate(" + deg + "deg)";
        t.style.MozTransform = "rotate(" + deg + "deg)";
    }

    function payIn() {
        deg += temp;
        if (deg >= 50 || deg <= -50) {
            temp *= -1;
        }

        var t = $text[0];
        t.style.transform = "rotate(" + deg + "deg)";
        t.style.webkitTransform = "rotate(" + deg + "deg)";
        t.style.OTransform = "rotate(" + deg + "deg)";
        t.style.MozTransform = "rotate(" + deg + "deg)";

    }
</script>
