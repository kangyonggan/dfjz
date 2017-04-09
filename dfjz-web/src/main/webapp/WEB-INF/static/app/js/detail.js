$(function () {
    var isHiddenPay = true;

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
        taskPay = setInterval("payIn()", 5);
    }).mouseout(function () {
        payOut();
    });


});

var taskPay, degPay = 0;
var $text = $(".detail-pay > div > div");
var temp = 10;

function payOut() {
    clearInterval(taskPay);
    degPay = 0;
    var t = $text[0];
    t.style.transform = "rotate(" + degPay + "deg)";
    t.style.webkitTransform = "rotate(" + degPay + "deg)";
    t.style.OTransform = "rotate(" + degPay + "deg)";
    t.style.MozTransform = "rotate(" + degPay + "deg)";
}

function payIn() {
    degPay += temp;
    if (degPay >= 50 || degPay <= -50) {
        temp *= -1;
    }

    var t = $text[0];
    t.style.transform = "rotate(" + degPay + "deg)";
    t.style.webkitTransform = "rotate(" + degPay + "deg)";
    t.style.OTransform = "rotate(" + degPay + "deg)";
    t.style.MozTransform = "rotate(" + degPay + "deg)";

}