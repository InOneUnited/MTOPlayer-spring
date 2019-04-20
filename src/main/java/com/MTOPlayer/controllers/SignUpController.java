package com.MTOPlayer.controllers;

import com.MTOPlayer.models.TemporaryPassword;
import com.MTOPlayer.models.User;
import com.MTOPlayer.models.UserInfo;
import com.MTOPlayer.service.BasicLoginService;
import com.MTOPlayer.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.sql.SQLException;

@Controller
public class SignUpController {
    @GetMapping("/signUp")
    public String showSignUp(Model userModel, Model infoModel, Model passwordModel) {
        userModel.addAttribute("user", new User());
        infoModel.addAttribute("userInfo", new UserInfo());
        passwordModel.addAttribute("temporaryPassword", new TemporaryPassword());
        return "signUp";
    }

    @PostMapping("/signUp")
    public String processNewUser(Model model, @ModelAttribute User user, @ModelAttribute UserInfo userInfo, @ModelAttribute TemporaryPassword password) {
        LoginService loginService = new BasicLoginService();
        try {
            if (!loginService.isUserNew(user)) {
                model.addAttribute("error", "true");
                return "signUp";
            }

            loginService.addNewUser(user, userInfo, password.getPassword());
        } catch (IOException | SQLException e) {
            System.out.println("db error");
            e.printStackTrace();
            model.addAttribute("error2", "true");
            return "signUp";
        }

        return "redirect:/profile";
    }
}
