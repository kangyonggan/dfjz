<#assign ctx="${(rca.contextPath)!''}">

<div class="about">
    <h1>关于本站</h1>

    <h2>一、建站原由：</h2>
    <p>
        我之前是用的【hexo】搭建的博客系统，主题使用的是【next】。
    </p>
    <p>
        另外还集成了【统计插件】、【评论插件】、【分享插件】、【分页插件】、【RSS插件】、【sitemap插件】、【搜索插件】、【分类插件】、【标签插件】等。
    </p>
    <p>
        这些插件全部都是第三方js，虽然有cdn加速，但加载速度还是慢的跟屎一样。于是我决定：不再使用hexo+next+插件，而是自己手动实现上面这些功能。
    </p>

    <h2>二、现实需求：</h2>
    <p>
        服务器内存1G、带宽1M。 因此而产生如下需求：
        <ul>
            <li>不搞分布式和集群，不要做大，否则没那么多机器和内存。</li>
            <li>尽量让前端“轻巧”，尽量把计算和功能点放在后台做，因为这样可以提高前端加载速度，毕竟带宽有限。</li>
            <li>不使用第三方插件，提高加载速度。</li>
            <li>前端使用单页面，但又不想使用第三方单页面路由，因此自己写一个轻巧的单页面路由（模拟ace admin的单页面路由）。</li>
        </ul>
    </p>
    <p>
        需要实现如下功能：
        <ul>
            <li>文章列表界面可以预览文章的摘要，效果同next主题类似。</li>
            <li>文章摘要需要自动截取，以“&lt;!-- more --&gt;”作为截取点，同next原理一样。</li>
            <li>鼠标经过标题，要有类似next主题的效果。</li>
            <li>要有分页功能，并且效果同next。</li>
            <li>可以回到顶部和底部。</li>
            <li>要有搜索功能，并把搜索结果中的关键字变红。</li>
            <li>文章详情界面的侧栏要有目录、评论排行榜、阅读排行榜和推荐排行榜。</li>
            <li>文章详情界面的底部要有上一篇和下一篇。</li>
            <li>文章详情界面的底部要有支付宝打赏功能，并且还有特效，同next。</li>
            <li>文章详情界面的底部要有评论框和评论列表。</li>
            <li>可以指定文章需不需要评论，同next。</li>
            <li>文章评论中要显示：评论人姓名、邮箱、内容、时间和所在城市。</li>
            <li>分类中要显示文章的数量，并以时间线的方式展现，同next。</li>
            <li>要有归档功能，同next。</li>
            <li>需要生成rss以供订阅，同next。</li>
            <li>需要生成网站地图让网站更容易被收录，同next。</li>
            <li>要有分享功能。</li>
            <li>再附加一个统计分析，从中可以看出有哪些ip，在什么地方什么时间访问了哪篇文章等。</li>
        </ul>
    </p>

    <h2>三、遗憾之处：</h2>
    <p>
        由于使用的单页面，在url中出现了#，而这也正是锚点，是页内跳转所需要的，也就是说我的“单页面”和“页内跳转”这两个功能冲突了，不能两个一起实现，想了半天也没想出个解决办法。
    </p>
    <p>
        所以，在文章详情界面的“文章目录”那块，点击目录是跳转不到对应的地方的，求大神给个思路。
    </p>

    <h2>四、作者简介：</h2>
    <ul>
        <li>姓名：康永敢</li>
        <li>学校：淮北师范大学</li>
        <li>籍贯：安徽蚌埠</li>
        <li>现住址：上海市嘉定区江桥镇</li>
        <li>办公地点：上海市南京西路399号明天广场21楼华信证券</li>
        <li>从事行业：互联网+金融</li>
        <li>个人邮箱：java@kangyonggan.com</li>
        <li>微信号：Brave_Kang</li>
        <li>办公QQ：2825176081</li>
    </ul>
</div>

<script>
    window.document.title = "关于 | 东方娇子";
</script>
