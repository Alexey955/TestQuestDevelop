<#import "parts/common.ftl" as c>

<@c.page>
    <body>
        <#if myDate??>
            <#list myDate as x>
                ${x.username}
            </#list>
            <#else>
            Nothing
        </#if>
    </body>
</@c.page>