package com.goomo.hplus.controllers;

import com.goomo.hplus.beans.Login;
import com.goomo.hplus.beans.User;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class DefaultModelAttributeController {

    @ModelAttribute("newUser")
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
    }
}
