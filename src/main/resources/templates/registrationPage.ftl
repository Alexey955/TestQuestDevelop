<#import "parts/common.ftl" as c>

<@c.page>
    <body class="bodyMain">
        <div>
            <form id="registerForm" action="/registration" method="post">
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
                        <input form="registerForm" type="radio" class="inpRadioLog" value="Employee" name="role" checked = ""/>
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
                        <input form="registerForm" type="radio" class="inpRadioLog" value="Chief" name="role"/>
                    </td>
                </tr>
                <tr class="trLoginPage">
                    <td>
                        <div align="center">
                            <b>First Name:</b>
                        </div>
                        <input form="registerForm" class="inpTextLog" type="text" name="firstName"/>
                        <#if firstNameError??><div align="center" class="invalid-feedback">${firstNameError}</div></#if>
                    </td>
                    <td>
                        <div align="center">
                            <b>Admin:</b>
                        </div>
                        <input form="registerForm" type="radio" class="inpRadioLog" value="Admin" name="role"/>
                    </td>
                </tr>
                <tr class="trLoginPage">
                    <td>
                        <div class="stringLog">
                            <b>Last Name:</b>
                        </div>
                        <input form="registerForm" class="inpTextLog" type="text" name="lastName"/>
                        <#if lastNameError??><div align="center" class="invalid-feedback">${lastNameError}</div></#if>
                    </td>
                </tr>
                <tr class="trLoginPage">
                    <td>
                        <div class="stringLog">
                            <b>Department Name:</b>
                        </div>
                        <input form="registerForm" class="inpTextLog" type="text" name="departmentName" placeholder="&#34;No one&#34; if department is missing" value="No one"/>
                        <#if departmentNameError??><div align="center" class="invalid-feedback">${departmentNameError}</div></#if>
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