package controllers;

import daw.BattleManager;
import daw.SpringSingle;
import daw.UserManager;
import db.ItemsTable;
import db.UsersTable;
import html.BuildBattle;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Battle {

    @RequestMapping("/battle")
    public ModelAndView user(HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView model = new ModelAndView("user");
        BattleManager bat = (BattleManager) SpringSingle.getContext().getBean("battle");
        HttpSession s = req.getSession();
        UserManager m = (UserManager) SpringSingle.getContext().getBean("user");
        String userName = (String) s.getAttribute("user");
        Integer[] power = bat.getPower(m.getUserByName(userName));
        String strike = req.getParameter("strike");
        String defend = req.getParameter("defend");
        Boolean[] mas = null;
        if (strike != null && defend != null) {
            mas = bat.moveAuto(strike, defend);
        }
        UsersTable user = m.getUserByName(userName);
        String[] results = bat.setHPAttributes(strike, defend, s, user.getLvl(), mas, power);
        String content = "";

        Integer myHP = (Integer) s.getAttribute("hp");
        Integer oppHP = (Integer) s.getAttribute("oppHP");

        List<ItemsTable> items = m.getUserItemsList(user);
        BuildBattle b = (BuildBattle)SpringSingle.getContext().getBean("buildBattle");
        content += b.buildBattleContent(user, myHP, oppHP, mas, results, s);
        model.addObject("content", content);

        return model;
    }
}
