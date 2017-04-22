<#assign ctx="${(rca.contextPath)!''}">

<div id="header">
    <div>
        <a href="#index" class="logo">东方娇子</a>
        <ul class="hidden-sm">
        <#include "categories.ftl">
        </ul>

        <form action="#search" method="get" class="hidden-sm" novalidate>
            <input type="text" name="q" placeholder="搜索..." autocomplete="off"/>
        </form>

        <a class="menu-button hidden-lg" href="javascript:">
            <div class="line"></div>
            <div class="line"></div>
            <div class="line"></div>
        </a>

        <div class="menu-list hidden-lg hidden">
            <form action="#search" method="get" novalidate>
                <input type="text" name="q" placeholder="搜索..." autocomplete="off"/>
            </form>

            <ul>
            <#include "categories.ftl">
            </ul>
        </div>
    </div>
</div>

