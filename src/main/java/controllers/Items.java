package controllers;

import daw.ItemsManager;
import daw.SpringSingle;
import daw.UserManager;
import db.ItemsTable;
import html.BuildItems;
import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Items {

    @RequestMapping("/items")
    public ModelAndView items(HttpServletRequest req) {
        ModelAndView model = new ModelAndView("items");
        BuildItems b = (BuildItems) SpringSingle.getContext().getBean("buildItems");
        ItemsManager m = (ItemsManager) SpringSingle.getContext().getBean("items");
        String content = b.buildItemsPage(m.getListCatLists());   
        model.addObject("content", content);
        return model;
    }
    @RequestMapping("/item")
    public ModelAndView item(HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView model = new ModelAndView("item");
        String id = req.getParameter("id");
        String buy = req.getParameter("buy");
        HttpSession s = req.getSession();
        String userName = (String)s.getAttribute("user");
        ItemsManager m = (ItemsManager) SpringSingle.getContext().getBean("items");
        UserManager um = (UserManager)SpringSingle.getContext().getBean("user");
        ItemsTable item = m.getItemByID(Integer.parseInt(id));
        model.addObject("name", item.getName());
        model.addObject("about", item.getAbout());
        model.addObject("protect", item.getArmor());
        model.addObject("damage", item.getDamage());
        model.addObject("level", item.getLvl());
        model.addObject("price", item.getPrice());
        model.addObject("image", item.getImage());
        model.addObject("id", item.getId());
        if(buy != null && userName != null){
            try {
                resp.sendRedirect("/daw/item?id="+id+"&b="+um.buyItem(userName, item));
            } catch (IOException ex) {}         
        }
        String b = req.getParameter("b");
        model.addObject("buy", b);
                
        return model;
    }
    
}
