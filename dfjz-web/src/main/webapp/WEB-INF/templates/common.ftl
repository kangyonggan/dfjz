<#assign ctx="${(rca.contextPath)!''}">

<#--截取字符串, 超出部分用省略号代替-->
<#macro substring str len default=''>
    <#if str?? && str != ''>
        <#if str?length gt len>
        ${str?substring(0, len)}...
        <#else>
        ${str}
        </#if>
    <#else>
    ${default}
    </#if>
</#macro>

<#--分页组件-->
<#macro pagination url param="">
    <#if (page.list)?? && page.pages gt 1>
    <div class="pagination hidden-sm">
        <#if page.hasPreviousPage>
            <a href="${url}?p=${page.prePage}<#if param?has_content>&${param}</#if>">&lt;</a>
        </#if>

        <#list page.navigatepageNums as nav>
            <#if nav_index==0 && nav gt 2>
                <a href="${url}?p=1<#if param?has_content>&${param}</#if>">1</a>
                <a href="javascript:">...</a>
            </#if>
            <#if nav == page.pageNum>
                <a href="javascript:" class="active">${nav}</a>
            <#else>
                <a href="${url}?p=${nav}<#if param?has_content>&${param}</#if>">${nav}</a>
            </#if>
            <#if !nav_has_next && nav lt page.pages-1>
                <a href="javascript:">...</a>
                <a href="${url}?p=${page.pages}<#if param?has_content>&${param}</#if>">${page.pages}</a>
            </#if>
        </#list>

        <#if page.hasNextPage>
            <a href="${url}?p=${page.nextPage}<#if param?has_content>&${param}</#if>">&gt;</a>
        </#if>
    </div>

    <div class="pagination hidden-lg">
        <#if page.hasPreviousPage>
            <a href="${url}?p=${page.prePage}<#if param?has_content>&${param}</#if>">&lt;</a>
        </#if>

        <#if page.pageNum == page.pages>
            <#if page.pageNum-2 gt 0>
                <a href="${url}?p=${page.pageNum-2}<#if param?has_content>&${param}</#if>">${page.pageNum-2}</a>
            </#if>
        </#if>

        <#if page.pageNum gt 1>
            <a href="${url}?p=${page.pageNum-1}<#if param?has_content>&${param}</#if>">${page.pageNum-1}</a>
        </#if>

        <a href="javascript:" class="active">${page.pageNum}</a>

        <#if page.pageNum lt page.pages>
            <a href="${url}?p=${page.pageNum+1}<#if param?has_content>&${param}</#if>">${page.pageNum+1}</a>
        </#if>

        <#if page.pageNum == 1>
            <#if page.pageNum+2 lte page.pages>
                <a href="${url}?p=${page.pageNum+2}<#if param?has_content>&${param}</#if>">${page.pageNum+2}</a>
            </#if>
        </#if>

        <#if page.hasNextPage>
            <a href="${url}?p=${page.nextPage}<#if param?has_content>&${param}</#if>">&gt;</a>
        </#if>
    </div>
    </#if>
</#macro>

<#--计算时间-->
<#macro relative_date datetime=.now>
    <#assign ct = (.now?long-datetime?long)/1000>
    <#if ct gte 31104000><#--n年前-->${(ct/31104000)?int}年前
        <#t><#elseif ct gte 2592000><#--n月前-->${(ct/2592000)?int}个月前
        <#t><#elseif ct gte 86400*2><#--n天前-->${(ct/86400)?int}天前
        <#t><#elseif ct gte 86400><#--1天前-->昨天
        <#t><#elseif ct gte 3600><#--n小时前-->${(ct/3600)?int}小时前
        <#t><#elseif ct gte 60><#--n分钟前-->${(ct/60)?int}分钟前
        <#t><#elseif ct gt 0><#--n秒前-->${ct?int}秒前
        <#t><#else>刚刚
    </#if>
</#macro>

<#--目录-->
<#macro toc toc p=''>
    <#list toc.childrens as t>
    <div class="toc-line level-${t.level}">
        <#if t.level gt 2>
            <#list 2..t.level as deep>
                <span class="toc-space"></span>
            </#list>
        </#if>
    ${p}${t.sort+1}
        <a href="javascript:" title="${t.name}">${t.name}</a></div>
        <@c.toc toc=t p='${p}${t.sort+1}.'/>
    </#list>
</#macro>
