<#import "parts/common.ftl" as c>

<@c.page>
<body>
    <h3 align="center">Users list:</h3>
    <#if personList??>
        <#list personList as personList>
            <div>
                First name: ${personList.firstName},
                Last name: ${personList.lastName},
                Department name: ${personList.departmentName},
                Role: ${personList.role}
                <a target="_top" href="/pickOperatUser/${personList.id}">pick</a>
                <br/>
            </div>
        </#list>
        <#else >
        List is empty.
    </#if>

</body>
</@c.page>