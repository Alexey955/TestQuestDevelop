<#import "parts/common.ftl" as c>

<@c.page>
    <body>
    <div>
        <form id="registerForm" action="/changeUser" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>
        <table>
            <tr class="trLoginPage">
                <td colspan="2">
                    <div  class="stringLog">
                        <b>First Name:</b>
                    </div>
                    <input form="registerForm" class="inpTextLog" type="text" name="firstName"/>
                    <#if firstNameError??><div align="center" class="invalid-feedback">${firstNameError}</div></#if>
                </td>
            </tr>
            <tr class="trLoginPage">
                <td colspan="2">
                    <div class="stringLog">
                        <b>Last Name:</b>
                    </div>
                    <input form="registerForm" class="inpTextLog" type="text" name="lastName"/>
                    <#if lastNameError??><div align="center" class="invalid-feedback">${lastNameError}</div></#if>
                </td>
            </tr>
            <tr class="trLoginPage">
                <td colspan="2" width="119px">
                    <button form="registerForm" class="buttLogPage">Create</button>
                </td>
            </tr>
        </table>
    </div>
    </body>
</@c.page>