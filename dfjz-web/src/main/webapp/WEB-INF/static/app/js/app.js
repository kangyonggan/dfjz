$(function () {
    $(".sim-page").simPage();

    $(window).scroll(function () {
        if ($(window).scrollTop() >= 100) { //向下滚动像素大于这个值时，即出现浮窗~
            $('.scroll-bar').fadeIn(1000);
        } else {
            $('.scroll-bar').fadeOut(1000);
        }
    });

    var showMenuList = false;
    $(".menu-button").click(function () {
        var $this = $(this);

        if (showMenuList) {
            $(".menu-list").addClass("hidden");
            $(".menu-button div").removeClass("line-border-radius");
        } else {
            $(".menu-list").removeClass("hidden");
            $(".menu-button div").addClass("line-border-radius");
        }

        showMenuList = !showMenuList;

        return false;
    });

    $(".menu-list ul li a").click(function () {
        $(".menu-list").addClass("hidden");
        $(".menu-button div").removeClass("line-border-radius");

        showMenuList = false;
        return true;
    });

    $("#header form input").keypress(function (e) {
        if (e.keyCode == 13) {// 回车

            var $form = $(this).parent("form");

            var val = $(this).val();
            if ($.trim(val).length < 2) {
                return false;
            }

            $form.submit();

            $(".menu-list").addClass("hidden");
            $(".menu-button div").removeClass("line-border-radius");

            showMenuList = false;
            return false;
        }
    });

});
