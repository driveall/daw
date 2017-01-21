package daw;

import com.google.gson.Gson;
import dao.Data;
import db.ItemsList;
import db.ItemsTable;
import db.UsersTable;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpSession;

public class BattleManager {

    public Boolean[] moveAuto(String strike, String defend) {
        Boolean[] mas = new Boolean[2];
        Integer oppStrike = new Random().nextInt(3);
        Integer oppDefend = new Random().nextInt(3);
        mas[0] = createMoveAuto(oppDefend).equals(strike);
        mas[1] = createMoveAuto(oppStrike).equals(defend);
        return mas;
    }
    private String createMoveAuto(Integer move){
        String oppMove = null;
        switch (move) {
            case 0:
                oppMove = "left";
                break;
            case 1:
                oppMove = "center";
                break;
            case 2:
                oppMove = "right";
                break;
        }
        return oppMove;
    }
    public Integer[] getPower(UsersTable user) {
        Data d = (Data)SpringSingle.getContext().getBean("data");
        Gson gson = new Gson();
        List<Integer> itemsOn = gson.fromJson(user.getItems(), ItemsList.class).getItemsIDsList();
        Integer dam = 0, arm = 0;
        for (Integer i : itemsOn) {
            ItemsTable item = d.getItemByID(i);
            dam += item.getDamage();
            arm += item.getArmor();
        }
        Integer[] power = new Integer[2];
        power[0] = dam;
        power[1] = arm;
        return power;
    }

    public String[] setHPAttributes(String strike, String defend, HttpSession s, Integer userLvl, Boolean[] mas, Integer[] power){
        String[] results = new String[2];
        Integer hp;
        Integer oppHP;
        if(strike == null && defend == null && (s.getAttribute("inBattle") == null || !(Boolean)s.getAttribute("inBattle"))){
            hp = userLvl*10 + 10;
            oppHP = hp;
            s.setAttribute("hp", hp);
            s.setAttribute("oppHP", oppHP);
            s.setAttribute("inBattle", true);
        }else{
            hp = (Integer)s.getAttribute("hp");
            oppHP = (Integer)s.getAttribute("oppHP");
        }
        String my = null;
        String opp = null;
        Integer oppArmor = power[1];
        Integer oppDamage = power[0];
        if(mas != null){
            if(mas[0]){
                Integer opHit = power[0]-oppArmor;
                if(opHit>1){
                    oppHP = oppHP - opHit;
                }else oppHP = oppHP - 1;                
                my = "hit";
            }else my = "miss";
            if(mas[1]){
                Integer myHit = oppDamage - power[1];
                if(myHit>1){
                    hp = hp - myHit;
                }else hp = hp - 1;                
                opp = "hit";
            }else opp = "miss";
            s.setAttribute("hp", hp);
            s.setAttribute("oppHP", oppHP);
        }
        results[0] = my;
        results[1] = opp;
        return results;
    }
}
