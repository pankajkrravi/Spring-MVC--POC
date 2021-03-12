package com.goomo.hplus.controllers;

import com.goomo.hplus.beans.Login;
import com.goomo.hplus.beans.User;
import com.goomo.hplus.exceptions.LoginfailureException;
import com.goomo.hplus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginRestController {

    @Autowired
    private UserRepository userRepository;
    @PostMapping("/hplus/rest/loginuser")
    public ResponseEntity loginUser(@RequestBody Login login) throws LoginfailureException
    {
    System.out.println("username :" +login.getUsername()+" password : "+login.getPassword());
        User user=userRepository.searchByName(login.getUsername());
        if(user==null)
        {
            //return ResponseEntity.status(404).build();
            return new ResponseEntity<>("User Not found : ",HttpStatus.NOT_FOUND);
        }
        if(user.getUsername().equals(login.getUsername()) && user.getPassword().equals(login.getPassword()))
        {
        return new ResponseEntity<>("Welcome : "+user.getUsername(),HttpStatus.OK);
        }
        else {
            //throw new Exception
            throw new LoginfailureException("Invalid username or Password");
        }
    }
}
