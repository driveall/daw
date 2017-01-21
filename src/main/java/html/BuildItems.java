package html;

import db.ItemsTable;
import java.util.List;

public class BuildItems {
    public String buildItemsPage(List<List<ItemsTable>> i) {
        String s = "<table align=\"center\" border=\"1\" width=\"900px\">"
                + "<tr>"
                + "<th width=\"20%\">Helmets:</th>"
                + "<th width=\"20%\">Armors:</th>"
                + "<th width=\"20%\">Boots:</th>"
                + "<th width=\"20%\">Pocket:</th>"
                + "<th width=\"20%\">Weapons:</th>"
                + "</tr>";

        for (int j = 0; j < i.get(4).size(); j++) {
            s += "<tr>";
            for (int jj = 0; jj < i.size(); jj++) {
                ItemsTable itemN = null;
                try {
                    itemN = i.get(jj).get(j);
                } catch (Exception e) {
                }
                if (itemN != null) {
                    s += "<td>"
                            + "<a href=\"/daw/item?id=" + i.get(jj).get(j).getId() + "\">" + i.get(jj).get(j).getName() + "</a>"
                            + "<div id=\"td\"><table>"
                            + "<tr>"
                            + "<td>"
                            + "Protect: " + i.get(jj).get(j).getArmor() + "<br>Damage: " + i.get(jj).get(j).getDamage() + "<br>"
                            + "Min.Lvl: " + i.get(jj).get(j).getLvl() + "<br>Price: " + i.get(jj).get(j).getPrice()
                            + "</td>"
                            + "<td width=\"40px\">"
                            + "<a href=\"/daw/item?id=" + i.get(jj).get(j).getId() + "\"><img src=\"" + i.get(jj).get(j).getSmallimg() + "\"/></a>"
                            + "</td>"
                            + "</tr>"
                            + "</table></div>"
                            + "</td>";
                } else {
                    s += "<td></td>";
                }
            }
            s += "</tr>";
        }
        s += "</table><br>";
        return s;
    }
    
    
}
