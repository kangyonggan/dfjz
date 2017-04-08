$(function () {
    $(".sim-page").simPage();

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
});
