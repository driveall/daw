<html>
    <head>
        <title>ITEM - DriveAllWars - free online game</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css"/>
    </head>
    <body>
        <div id="buyResult" align="center">
            ${buy}
        </div>
        <div align="center">
            <h1 align="center">${name}</h1>
            <table align="center" border="1" width="60%">
                <tr height="300px">
                    <td>
                        <h3>About: ${about}</h3>
                        <h3>Protect: ${protect}</h3>
                        <h3>Damage: ${damage}</h3>
                        <h3>Level: ${level}</h3>
                        <h3>Price: ${price}</h3>
                    </td>
                    <td width="300px">
                        <img src="${image}"/>
                    </td>
                </tr>
            </table>
            <a href="/daw/item?id=${id}&buy=true">
                <input type="submit" onclick="return confirm('Are you shure?');" value="Buy" class="button"/>
            </a><br>
            <a href="/daw/items">
                <input type="submit" value="Items" class="button"/>
            </a>
            <a href="/daw/user">
                <input type="submit" value="User" class="button"/>
            </a>
        </div>
    </body>
</html>
