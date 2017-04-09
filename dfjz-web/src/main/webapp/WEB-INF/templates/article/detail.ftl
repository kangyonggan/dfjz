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
        文章目录、 热评、推荐
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

<div class="detail-bottom-line"></div>

<div class="detail-nears">
    <a href="#" title="从零开始搭建NexT主题的Hexo博客">< 从零开始搭建NexT主题的Hexo博客</a>
    <a href="#" title="MySQL报错：数据库结构错误">MySQL报错：数据库结构错误 ></a>
</div>

<div class="detail-comment">
    <form action="${ctx}/article/12/comment" method="post">
        <input type="text" name="username" placeholder="姓名..."/>
        <input type="email" name="email" placeholder="电子邮箱..."/>
        <textarea name="content" placeholder="评论...（markdown语法）"></textarea>
        <input type="submit" value="提交评论"/>
    </form>
</div>

<div class="detail-comment-list">
    <div class="detail-comment-info">最新评论</div>

    <div class="detail-comment-item">
        <div class="left">
            康
        </div>
        <div class="right">
            <div class="username-time">
                <span>康永敢[kangyonggan@gmail.com]</span>
                <span>刚刚</span>
            </div>
            <div class="content">会不会影响性能啊？</div>
            <div class="tool">
                <a href="#">赞(4)</a>
                <a href="#">踩(6)</a>
            </div>
        </div>
    </div>

    <div class="detail-comment-item">
        <div class="left">
            马
        </div>
        <div class="right">
            <div class="username-time">
                <span>马小跳[@maxiaotiaogmail.com]</span>
                <span>一小时前</span>
            </div>
            <div class="content">肯定会的，只是不知道会有多大影响</div>
            <div class="tool">
                <a href="#">赞(43)</a>
                <a href="#">踩(0)</a>
            </div>
        </div>
    </div>
</div>

<script src="${ctx}/static/app/js/detail.js"></script>
