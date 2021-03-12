package com.goomo.hplus.controllers;

import com.goomo.hplus.beans.Login;
import com.goomo.hplus.beans.User;
import com.goomo.hplus.exceptions.ApplicationException;
import com.goomo.hplus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("login")
public class LoginController {
    @Autowired
     private UserRepository userRepository;

       /* @ModelAttribute("login")
        public Login getDefaultLogin()
        {
            return  new Login();
        }*/

    @PostMapping("/login")
    public String login(@ModelAttribute("login")Login login, HttpSession session)
    {
        //You can provide full session management
        //with session object
        //session.setAttribute("","");
        //session.setMaxInactiveInterval();
        User user = userRepository.searchByName(login.getUsername());
        if(user==null)
        {
            throw new ApplicationException("User Not Found");
        }
        return "forward:/userprofile";
    }
        /*@ExceptionHandler(ApplicationException.class)
        public String handleException()
        {
            System.out.println("In Exception Handler of Login Controller");
            return "error";
        }*/
}
