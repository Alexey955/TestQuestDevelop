<#import "parts/common.ftl" as c>

<@c.page>
    <body class="bodyMain">
    <table class="mainTable" align="center">
        <tr class="bordBottBreak trMainTable">
            <td>
                <form action="/adminMain" method="get">
                    <button class="buttons">Back</button>
                </form>
            </td>
            <td class="bordBottBreak tdFrame" rowspan="6" align="middle">
                <iframe name="mainFrame" class="mainFrameId">Alternative text</iframe>
            </td>
        </tr>
        <tr class="trMainTable">
            <td>
                <form target="mainFrame" action="/changeUser" method="get">
                    <button class="buttons">Change the user</button>
                </form>
            </td>
        </tr>
        <tr class="trMainTable">
            <td>
                <form target="mainFrame" action="/deleteUserOrNot" method="get">
                    <button class="buttons">Delete the user</button>
                </form>
            </td>
        </tr>
    </table>
    </body>
</@c.page>