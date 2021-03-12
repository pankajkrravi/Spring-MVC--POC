package com.goomo.hplus.controllers;

import com.goomo.hplus.beans.User;
import com.goomo.hplus.repository.UserRepository;
import org.hibernate.dialect.SybaseAnywhereDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class RegistrationController {
        //convert String value to Date
        @InitBinder
        public void initBinder(WebDataBinder binder)
        {
            binder.registerCustomEditor(Date.class,"dateOfBirth",new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),true));

        }

    @Autowired
    UserRepository userRepository;
    @PostMapping("/registeruser")
    public String registerUser(@Valid @ModelAttribute("newUser")User user, BindingResult result, Model model)
    {
        System.out.println(" In Register Controller");
        System.out.println("date Of Birth ---> "+ user.getDateOfBirth());
        if(result.hasErrors())
        {
            return "register";
        }
        userRepository.save(user);
        model.addAttribute("dataSaved","User Registered Successfully");
        return  "login";
    }
}
