package html;

import com.google.gson.Gson;
import dao.Data;
import daw.BattleManager;
import daw.SpringSingle;
import db.ItemsList;
import db.ItemsTable;
import db.UsersTable;
import java.util.List;

public class BuildUser {

    public String buildUserFailPage() {
        return "<h2>Login fail. Please re-login</h2>"
                + "<a href=\"/daw/main\"><input type=\"submit\" value=\"Main\" class=\"button\"/></a>"
                + "<a href=\"/daw/login\"><input type=\"submit\" value=\"Login\" class=\"button\"/></a>";
    }

    public String buildUserContent(UsersTable user, List<Integer> itemsOn, Integer[] power, List<Integer> items) {
        Data d = (Data)SpringSingle.getContext().getBean("data");
        String content = "<h1 align=\"center\">" + user.getName() + ":</h1>"
                + "<div id=\"userInfo\" align=\"center\">Register date: " + user.getDate() + "<br>"
                + "Level: " + user.getLvl() + "<br>"
                + "Points: " + user.getPoints() + "<br>"
                + "Money: " + user.getMoney() + "</div>"
                + "<div align=\"center\">"
                + "<a href=\"/daw/battle\" ><input type=\"submit\" value=\"Battle with bot\" class=\"button\"/></a>"
                + " <a href=\"/daw/items\"><input type=\"submit\" value=\"Shop\" class=\"button\"/></a>"
                + " <a href=\"/daw/main?exit=true\"><input type=\"submit\" value=\"Exit\" class=\"button\"/></a>"
                + "</div>"
                + "<table align=\"center\" border=\"1\"><tr>"
                + "<td>"
                + "<div id=\"td\"><table><tr><td>Items</td></tr><tr><td>on</td></tr><tr><td>you:</td></tr></table></div>"
                + "</td>";
        ItemsTable item;
        for (int i = 0; i < itemsOn.size(); i++) {
            item = d.getItemByID(itemsOn.get(i));
            content += "<td>"
                    + "<div style=\"'height:100%;'\" id=\"td\"><table>"
                    + "<tr height=\"5px\"><td align=\"center\">" + item.getName() + "</td></tr>"
                    + "<tr><td align=\"center\"><a href=\"/daw/item?id=" + item.getId() + "\"><img src=\"" + item.getSmallimg() + "\"/></a></td></tr>"
                    + "<tr><td align=\"center\"><pre><h5 id=\"tO\" class=\"" + item.getId() + "\"><a href=\"/daw/user?rem=" + item.getId() + "\">Take off</a></h5></pre></td></tr>"
                    + "</table></div>"
                    + "</td>";
        }
        content += "</tr></table>"
                + "<div id=\"userTotal\" align=\"center\">Total Power: " + power[0] + "<br>"
                + "Total Protection: " + power[1] + "</div>"
                + "<table align=\"center\" border=\"1\"><tr>"
                + "<td>"
                + "<div style=\"height:100%;\" id=\"td\"><table ><tr><td>Your</td></tr><tr><td>items</td></tr></table></div>"
                + "</td>";
        for (int i = 0; i < items.size(); i++) {
            item = d.getItemByID(items.get(i));
            content += "<td>"
                    + "<div style=\"'height:100%;'\" id=\"td\"><table>"
                    + "<tr><td align=\"center\">" + item.getName() + "</td></tr>"
                    + "<tr><td align=\"center\"><a href=\"/daw/item?id=" + item.getId() + "\"><img src=\"" + item.getSmallimg() + "\"/></a></td></tr>"
                    + "<tr><td align=\"center\"><pre><h5><a href=\"/daw/user?add=" + item.getId() + "\">Wear</a>  "
                    + "<a href=\"/daw/user?sell=" + item.getId() + "\" onclick=\"return confirm('Are you shure?');\">Sell</a></h5></pre></td></tr>"
                    + "</table></div>"
                    + "</td>";
        }
        content += "</tr></table>";
        return content;
    }
}
