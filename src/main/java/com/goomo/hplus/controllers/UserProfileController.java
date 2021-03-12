package com.goomo.hplus.controllers;

import com.goomo.hplus.beans.Login;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class UserProfileController {
    @PostMapping("/userprofile")
    public String getUserProfile(@SessionAttribute("login")Login login, Model model)
    {
        System.out.println("In User profile controller :");
        System.out.println("Username from session object : "+login.getUsername());
        model.addAttribute("username",login.getUsername());
        //call to userrepository to get all the user information fom db
        return "profile";
    }
}
