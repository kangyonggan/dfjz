$(function () {
    $(".article-title").mouseover(function () {
        clearInterval(task);
        if ($line) {
            $line.addClass("article-title-line-hide").removeClass("article-title-line-show");
        }
        deg = 90;
        $line = $(this).parent("h1").find(".article-title-line");
        $line.css("width", $(this).width());
        $line.removeClass("article-title-line-hide").addClass("article-title-line-show");

        task = setInterval("mouseIn()", 10);
    });

    $(".article-title").mouseout(function () {
        clearInterval(task);
        task = setInterval("mouseOut()", 10);
    });

    $(".markdown a").attr("target", "_blank");

    if (q != '') {
        $("#header form input").val(q);
    }
});

var $line, deg = 90, task;
function mouseIn() {
    deg += 5;
    if (deg >= 180) {
        clearInterval(task);
        deg = 180;
    }

    var y = $line[0];
    y.style.transform = "rotateY(" + deg + "deg)";
    y.style.webkitTransform = "rotateY(" + deg + "deg)";
    y.style.OTransform = "rotateY(" + deg + "deg)";
    y.style.MozTransform = "rotateY(" + deg + "deg)";

}

function mouseOut() {
    deg -= 5;
    if (deg <= 90) {
        clearInterval(task);
        $line.addClass("article-title-line-hide").removeClass("article-title-line-show");
        $line = null;
        return;
    }

    var y = $line[0];
    y.style.transform = "rotateY(" + deg + "deg)";
    y.style.webkitTransform = "rotateY(" + deg + "deg)";
    y.style.OTransform = "rotateY(" + deg + "deg)";
    y.style.MozTransform = "rotateY(" + deg + "deg)";
}