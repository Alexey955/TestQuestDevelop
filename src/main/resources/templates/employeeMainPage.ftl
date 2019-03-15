<#import "parts/common.ftl" as c>

<@c.page>
    <body class="bodyMain">
    <table class="mainTable" align="center">
        <tr class="bordBottBreak trMainTable">
            <td>
                <form action="/logout" method="post">
                    <input type="submit" value="Sign Out" class="buttons"/>
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                </form>
            </td>
            <td class="bordBottBreak tdFrame" rowspan="7" align="middle">
                <iframe name="mainFrame" class="mainFrameId">Alternative text</iframe>
            </td>
        </tr>
        <tr class="trMainTable">
            <td>
                <form method="get">
                    <button class="buttons">Locked</button>
                </form>
            </td>
        </tr>
        <tr class="trMainTable">
            <td>
                <form method="get">
                    <button class="buttons">Out of service</button>
                </form>
            </td>
        </tr>
        <tr class="trMainTable">
            <td>
                <form method="get">
                    <button class="buttons">Doesn't work</button>
                </form>
            </td>
        </tr>
        <tr class="trMainTable">
            <td>
                <form method="get">
                    <button class="buttons">Forbidden</button>
                </form>
            </td>
        </tr>
        <tr class="trMainTable">
            <td>
                <form method="get">
                    <button class="buttons">Banned</button>
                </form>
            </td>
        </tr>
        <tr class="trMainTable">
            <td>
                <form target="mainFrame" method="get" action="/lastChance">
                    <button class="buttons">MAYBE!!!</button>
                </form>
            </td>
        </tr>
    </table>
    </body>
</@c.page>