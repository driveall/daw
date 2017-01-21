package controllers;

import dao.Data;
import daw.SpringSingle;
import db.UsersTable;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Check {
    @RequestMapping("/check")
    public @ResponseBody String login(HttpServletRequest req) {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        if(password != null){
            if(password.length()>0 && password.length()<5){
                return "LIGHT PASSWORD";
            }else if(password.length()>=5 && password.length()<8){
                return "MIDDLE PASSWORD";
            }else if(password.length()>=8){
                return "GOOD PASSWORD";
            }else return "";
        }
        if(name != null){
            Data d = (Data)SpringSingle.getContext().getBean("data");
            List<UsersTable> users = d.getAllUsers();
            for(UsersTable u:users){
                if(u.getName().equalsIgnoreCase(name)){
                    return "User already exists";
                }
            }
        }
        return "";
    }
}
