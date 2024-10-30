package com.scm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;




@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    //user dashborad page
    @RequestMapping(value = "/dashboard")
    public String UserDashboard() {
        System.out.println("User Dashboard");
        return "user/dashboard";
    }
     // user profile page

     @RequestMapping(value = "/profile")
     public String userProfile(Model model, Authentication authentication) {
 
         return "user/profile";
     }
    

    //user add contacts page

    //user view contacts

    //user edit contact

    //user delete contact


}
