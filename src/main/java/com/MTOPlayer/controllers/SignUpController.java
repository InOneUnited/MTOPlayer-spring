package com.MTOPlayer.controllers;

import com.MTOPlayer.models.Password;
import com.MTOPlayer.models.User;
import com.MTOPlayer.models.UserInfo;
import com.MTOPlayer.service.BasicLoginService;
import com.MTOPlayer.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@Controller
public class SignUpController {
    @GetMapping("/signUp")
    public String showSignUp(Model userModel, Model infoModel, Model passwordModel){
        userModel.addAttribute("user", new User());
        infoModel.addAttribute("userInfo", new UserInfo());
        passwordModel.addAttribute("password", new Password());
        return "signUp";
    }

    @PostMapping("/signUp")
    public String processNewUser(Model model, @ModelAttribute User user, @ModelAttribute UserInfo userInfo, @ModelAttribute Password password){
        LoginService loginService = new BasicLoginService();
        try {
            if(!loginService.isUserNew(user))
            {
                System.out.println("user exists");
                model.addAttribute("error", "true");
                System.out.println("model added time to return");
                return "signUp";
            }

            loginService.addNewUser(user, userInfo, password.getPasswordValue());
        } catch (IOException|SQLException e) {
            System.out.println("db error");
            e.printStackTrace();
            model.addAttribute("error2", "true");
            return "signUp";
        }

        return "userInfo";
    }
}
