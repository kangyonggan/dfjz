<#assign ctx="${(rca.contextPath)!''}">

<div class="categories">
    <h1>全部分类</h1>

    <p>目前共计 ${categories?size} 个分类</p>

    <ul>
        <#list categories as category>
            <li><a href="#category/${category.code}">${category.name}<span>(${category.articleCount})</span></a></li>
        </#list>
    </ul>
</div>

