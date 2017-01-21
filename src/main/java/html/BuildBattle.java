package html;

import daw.SpringSingle;
import daw.UserManager;
import db.ItemsTable;
import db.UsersTable;
import java.util.List;
import javax.servlet.http.HttpSession;

public class BuildBattle {

    public String buildBattleContent(UsersTable user, Integer myHP, Integer oppHP, Boolean[] mas, String[] results, HttpSession s) {
        String content = "";
        UserManager m = (UserManager) SpringSingle.getContext().getBean("user");
        List<ItemsTable> items = m.getUserItemsList(user);
        if (myHP > 0 && oppHP > 0) {
            content += "<div id='batLeft' align='left'>"
                    + "<h1>" + user.getName() + "</h1>"
                    + "<h1>HP: " + myHP + "</h1>";
            for (ItemsTable i : items) {
                content += "<h5>" + i.getName() + "</h5>"
                        + "<img src=\"" + i.getSmallimg() + "\"/>";
            }
            content += "</div>"
                    + "<div id='batRight' align='right'>"
                    + "<h1>Bot</h1>"
                    + "<h1>HP: " + oppHP + "</h1>";
            for (ItemsTable i : items) {
                content += "<h5>" + i.getName() + "</h5>"
                        + "<img src=\"" + i.getSmallimg() + "\"/>";
            }
            content += "</div>";
            content += "";

            content += "<div id='batCent' align='center'>"
                    + "<h1>Battle:</h1><hr>"
                    + "<form action=\"/daw/battle\" method=\"POST\"> "
                    + "<h3>Strike:</h3>"
                    + "<input type=\"radio\" name=\"strike\" value=\"left\"> Left<br>"
                    + "<input type=\"radio\" name=\"strike\" value=\"center\">Center<br>"
                    + "<input type=\"radio\" name=\"strike\" value=\"right\"> Right<br><hr>"
                    + "<h3>Defend:</h3>"
                    + "<input type=\"radio\" name=\"defend\" value=\"left\"> Left<br>"
                    + "<input type=\"radio\" name=\"defend\" value=\"center\">Center<br>"
                    + "<input type=\"radio\" name=\"defend\" value=\"right\"> Right<br><hr>"
                    + "<input type=\"submit\" value=\"Go!\" class=\"button\"/>"
                    + "</form>";
            if (mas != null) {
                content += "<h2>You " + results[0] + ";</h2>"
                        + "<h2>Opponent " + results[1] + ";</h2>";
            }
            content += "</div>";
        } else {
            Integer points = user.getPoints();
            Integer money = user.getMoney();
            if (myHP > 0 && oppHP <= 0) {
                Integer tPoints = 2 + (user.getLvl() * 2);
                Integer tMoney = (user.getLvl() * 20) + 10;
                content += "<h1>You WIN!!! +" + tPoints + " points, +" + tMoney + " money!</h1>";
                points += tPoints;
                money += tMoney;
                s.setAttribute("inBattle", false);
            } else if (oppHP > 0 && myHP <= 0) {
                content += "<h1>You LOSE((( no points, no money.</h1>";
                s.setAttribute("inBattle", false);
            } else if (myHP <= 0 && oppHP <= 0) {
                content += "<h1>DRAW +2 points, +10 money!</h1>";
                points += 2;
                money += 10;
                s.setAttribute("inBattle", false);
            }
            user.setPoints(points);
            user.setMoney(money);
            user = m.updateLevel(user);
            m.updateUser(user);
            content += "<a href=\"/daw/user\"><input type=\"submit\" value=\"OK\"/></a>";
            if (mas != null) {
                content += "<h2>You " + results[0] + ";</h2>"
                        + "<h2>Opponent " + results[1] + ";</h2>";
            }
        }
        return content;
    }
}
