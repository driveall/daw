package controllers;

import com.google.gson.Gson;
import dao.Data;
import daw.BattleManager;
import daw.SpringSingle;
import daw.UserManager;
import db.ItemsList;
import db.UsersTable;
import html.BuildUser;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

//@Controller
public class User extends AbstractController{

    //@RequestMapping("/user")
    //public ModelAndView user(HttpServletRequest req, HttpServletResponse resp) {
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ModelAndView model = new ModelAndView("user");        
        UserManager m = (UserManager) SpringSingle.getContext().getBean("user");
        BuildUser b = (BuildUser)SpringSingle.getContext().getBean("buildUser");
        HttpSession s = req.getSession();
        model.addObject("title", (String) s.getAttribute("user")+" - ");
        UsersTable user = m.getUserByName((String) s.getAttribute("user"));
        Boolean isLog = (Boolean) s.getAttribute("log");
        String content = "";
        if (isLog == null || !isLog) {
            content += b.buildUserFailPage();
        } else {
            if (req.getParameter("sell") != null) {
                m.sellItem(req, resp, user);
            }
            if (req.getParameter("add") != null) {
                m.addItemToHands(req, user);
            }
            if (req.getParameter("rem") != null) {
                m.remItemFromHands(req, user);
            }
            Data d = (Data)SpringSingle.getContext().getBean("data");
            Gson gson = new Gson();
            BattleManager bat = (BattleManager)SpringSingle.getContext().getBean("battle");
            List<Integer> itemsOn = gson.fromJson(user.getItems(), ItemsList.class).getItemsIDsList();
            Integer[] power = bat.getPower(user);
            List<Integer> items = gson.fromJson(user.getItemsAll(), ItemsList.class).getItemsIDsList();
            content += b.buildUserContent(user, itemsOn, power, items);            
        }
        model.addObject("content", content);

        return model;
    }
}
