package com.goomo.hplus.controllers;
import com.goomo.hplus.beans.Login;
import com.goomo.hplus.beans.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String getHome()
    {
        System.out.println("In Home controller");
        return "index";
    }

    @GetMapping("/goToSearch")
    public String goToSearch()
    {
        System.out.println("Going to Search page");
        return "search";
    }
    @GetMapping("/goToLogin")
    public String goToLogin()
    {
        System.out.println("Going to Login Page ");
        return "login";
    }

    @GetMapping("/goToRegistration")
    public String goToRegistration()
    {
        System.out.println("Going to Register Page");
        return "register";
    }

    //Empty object- just to render correctly
   /* @ModelAttribute("newUser")
    public User getDefaultUser()
    {
        return new User();
    }
    //Attaching default Objects to our JSP pages-> In HomeController
    @ModelAttribute("genderItems")
    public List<String> getGenderItems()
    {
        return Arrays.asList(new String[]{"Male","Female","Others"});
    }
    @ModelAttribute("login")
    public Login getDefaultLogin()
    {
        return new Login();
    }*/
}
