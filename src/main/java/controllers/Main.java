package controllers;

import daw.SpringSingle;
import daw.UserManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Main {
    @RequestMapping("/main")
    public ModelAndView login(HttpServletRequest req) {
        ModelAndView model = new ModelAndView("main");
        UserManager m = (UserManager)SpringSingle.getContext().getBean("user");
        HttpSession s = req.getSession();
        if(req.getParameter("exit")!=null){
            m.clearAllAttributes(s);
        }
        return model;
    }
}
