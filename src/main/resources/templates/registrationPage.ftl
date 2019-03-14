<#import "parts/common.ftl" as c>

<@c.page>
    <body class="bodyMain">
        <div>
            <form id="registerForm" action="/registration" method="post">
                <#--<h4>One:<input type="radio" value="one" name="radioDel"/></h4>-->
                <#--<h4>Two:<input type="radio" value="two" name="radioDel" checked = ""/></h4>-->
                <#--<h4>Three:<input type="radio" value="three" name="radioDel"/></h4>-->
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            </form>
            <form id="toLoginPage" action="/login" method="get"></form>
            <table class="tableLoginPage">
                <tr class="trLoginPage">
                    <td>
                        <div align="center">
                            <b>User Name:</b>
                        </div>
                        <input form="registerForm" class="inpTextLog" type="text" name="username"/>
                        <#if usernameError??><div align="center" class="invalid-feedback">${usernameError}</div></#if>
                    </td>
                    <td>
                        <div align="center">
                            <b>Employee:</b>
                        </div>
                        <input form="registerForm" type="radio" class="inpRadioLog" value="Employee" name="radioDel"/>
                    </td>
                </tr>
                <tr class="trLoginPage">
                    <td>
                        <div align="center">
                            <b>Password:</b>
                        </div>
                        <input form="registerForm" class="inpTextLog" type="password" name="password"/>
                        <#if passwordError??><div align="center" class="invalid-feedback">${passwordError}</div></#if>
                    </td>
                    <td>
                        <div align="center">
                            <b>Chief:</b>
                        </div>
                        <input form="registerForm" type="radio" class="inpRadioLog" value="Chief" name="radioDel"/>
                    </td>
                </tr>
                <tr class="trLoginPage">
                    <td>
                        <div align="center">
                            <b>First Name:</b>
                        </div>
                        <input form="registerForm" class="inpTextLog" type="text" name="firstName"/>
                        <#--<#if passwordError??><div align="center" class="invalid-feedback">${passwordError}</div></#if>-->
                    </td>
                    <td>
                        <div align="center">
                            <b>Admin:</b>
                        </div>
                        <input form="registerForm" type="radio" class="inpRadioLog" value="Admin" name="radioDel"/>
                    </td>
                </tr>
                <tr class="trLoginPage">
                    <td colspan="2">
                        <div class="stringLog">
                            <b>Last Name:</b>
                        </div>
                        <input form="registerForm" class="inpTextLog" type="text" name="lastName"/>
                        <#--<#if passwordError??><div align="center" class="invalid-feedback">${passwordError}</div></#if>-->
                    </td>
                </tr>
                <tr class="trLoginPage">
                    <td colspan="2">
                        <div class="stringLog">
                            <b>Department Name:</b>
                        </div>
                        <input form="registerForm" class="inpTextLog" type="text" name="departmentName"/>
                        <#--<#if passwordError??><div align="center" class="invalid-feedback">${passwordError}</div></#if>-->
                    </td>
                </tr>
                <tr class="trLoginPage">
                    <td width="119px">
                        <button form="toLoginPage" class="buttLogPage">Back</button>
                    </td>
                    <td width="119px">
                        <button form="registerForm" class="buttLogPage">Register</button>
                    </td>
                </tr>
            </table>
        </div>
    </body>
</@c.page>