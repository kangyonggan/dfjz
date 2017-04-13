<#assign ctx="${(rca.contextPath)!''}">

<div id="header">
    <div>
        <a href="#index" class="logo">东方娇子</a>
        <ul class="hidden-sm">
            <li><a href="#index">首页</a></li>
            <li><a href="#category">分类</a></li>
            <li><a href="#archives">归档</a></li>
            <li><a href="#rss" target="_blank">订阅</a></li>
            <li><a href="${ctx}/upload/rss/sitemap.xml">网站地图</a></li>
            <li><a href="#stat">统计分析</a></li>
            <li><a href="#about">关于</a></li>
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
                <li><a href="#index">首页</a></li>
                <li><a href="#categories">分类</a></li>
                <li><a href="#archives">归档</a></li>
                <li><a href="#rss" target="_blank">订阅</a></li>
                <li><a href="${ctx}/upload/rss/sitemap.xml">网站地图</a></li>
                <li><a href="#stat">统计分析</a></li>
                <li><a href="#about">关于</a></li>
            </ul>
        </div>
    </div>
</div>

