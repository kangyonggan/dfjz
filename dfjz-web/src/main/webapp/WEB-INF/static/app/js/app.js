$(function () {
    $(".sim-page").simPage();

    $(window).scroll(function () {
        if ($(window).scrollTop() >= 100) { //向下滚动像素大于这个值时，即出现浮窗~
            $('.scroll-bar').fadeIn(1000);
            $("#header").css("height", "60px");
            $("#header > div > a.logo").css("margin-top", "10px");
            $("#header > div > ul").css("margin-top", "18px");
            $("#header > div > form > input").css("margin-top", "17px");
            $("#header > div > a.menu-button").css("margin-top", "10px");
        } else {
            $('.scroll-bar').fadeOut(1000);
            $("#header").css("height", "90px");
            $("#header > div > a.logo").css("margin-top", "25px");
            $("#header > div > ul").css("margin-top", "30px");
            $("#header > div > form > input").css("margin-top", "28px");
            $("#header > div > a.menu-button").css("margin-top", "20px");
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
