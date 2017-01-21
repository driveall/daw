package controllers;

import daw.SpringSingle;
import daw.UserManager;
import html.BuildLog;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Login {

    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest req) {
        ModelAndView model = new ModelAndView("login");
        BuildLog b = (BuildLog)SpringSingle.getContext().getBean("buildLog");
        String name = req.getParameter("name");
        String pass = req.getParameter("pass");
        String content = "";
        if(name == null && pass == null){
            model.addObject("title", "LOGIN - ");
            content += b.buildLogContent();
        }else if(name != null && pass != null){
            UserManager u = (UserManager)SpringSingle.getContext().getBean("user");
            HttpSession s = req.getSession();
            if(u.logUser(name, pass)){
                model.addObject("title", "LOGIN OK - ");
                s.setAttribute("log", true);
                s.setAttribute("user", name);
                content += b.buildLogOkContent(name);
            }else{
                model.addObject("title", "LOGIN FAIL - ");
                s.setAttribute("log", false);
                content += b.buildLogFailContent(name);
            }
        }       
        model.addObject("content", content);
        return model;
    }
}
