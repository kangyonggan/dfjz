<#assign ctx="${(rca.contextPath)!''}">

<h2 class="visit-title">常用导航</h2>

<div class="tools-list">
    <#list navs as nav>
        <div class="item">
            <a href="${nav.name}" target="_blank">
                <img src="${ctx}/static/app/images/nav/${nav.pcode}"/>
                <p>${nav.code}</p>
            </a>
        </div>
    </#list>
</div>

<script>
    window.document.title = "常用导航 | 东方娇子";
</script>


