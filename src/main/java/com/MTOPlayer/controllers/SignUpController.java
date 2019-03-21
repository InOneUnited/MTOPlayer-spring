package com.MTOPlayer.controllers;

import com.MTOPlayer.models.Password;
import com.MTOPlayer.models.User;
import com.MTOPlayer.models.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class SignUpController {
    @GetMapping("/signUp")
    public String showSignUp(Model userModel, Model infoModel, Model passwordModel){
        System.out.println("HELLOGETTER");
        userModel.addAttribute("user", new User());
        infoModel.addAttribute("userInfo", new UserInfo());
        passwordModel.addAttribute("userPassword", new Password());
        return "signUp";
    }

    @PostMapping("/signUp")
    public String processNewUser(@ModelAttribute User user, @ModelAttribute UserInfo userInfo, @ModelAttribute Password userPassword){
        return "userInfo";
    }
}
