package controllers;

import daw.SpringSingle;
import daw.UserManager;
import html.BuildReg;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Register {
    @RequestMapping("/register")
    public ModelAndView register(HttpServletRequest req) {
        ModelAndView model = new ModelAndView("register");
        BuildReg b = (BuildReg)SpringSingle.getContext().getBean("buildReg");
        String name = req.getParameter("name");
        String pass = req.getParameter("pass");
        String sex = req.getParameter("sex");
        String message = "";
        String content = "";
        if(name == null && pass == null && sex == null){
            model.addObject("title", "REGISTER - ");
            message += "";
            content += b.buildRegContent();
        }else if(name != null && pass != null && sex != null){
            UserManager u = (UserManager)SpringSingle.getContext().getBean("user");
            if(u.regNewUser(name, pass, sex)){
                model.addObject("title", "REGISTER OK - ");
                message += b.buildRegOkMessage(name);
                content += b.buildRegOkContent(name);
            }else{
                model.addObject("title", "REGISTER FAIL - ");
                message += b.buildRegFailMessage(name);
                content += b.buildRegFailContent(name);
            }
        }else{
            model.addObject("title", "REGISTER NOT COMPLETE - ");
            message += b.buildRegNotFullMessage();
            content += b.buildRegFailContent(name);
        }
        model.addObject("content", content);
        model.addObject("message", message);
        return model;
    }
}
