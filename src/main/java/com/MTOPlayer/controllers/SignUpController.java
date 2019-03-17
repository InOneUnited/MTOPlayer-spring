package com.MTOPlayer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SignUpController {
    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public String showSignUp(Model model){
        return "signUp";
    }
}
