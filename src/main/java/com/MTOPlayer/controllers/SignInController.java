package com.MTOPlayer.controllers;

import com.MTOPlayer.models.TemporaryPassword;
import com.MTOPlayer.models.User;
import com.MTOPlayer.service.BasicLoginService;
import com.MTOPlayer.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.sql.SQLException;

@Controller
public class SignInController {
    private LoginService loginService;

    @Autowired
    SignInController(BasicLoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String showLogin(Model userModel, Model passwordModel) {
        userModel.addAttribute("user", new User());
        passwordModel.addAttribute("temporaryPassword", new TemporaryPassword());
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(Model model, @ModelAttribute User user, @ModelAttribute TemporaryPassword password) throws IOException, SQLException {
        if (loginService.isEmailInDB(user.getEmail())) {
            if (loginService.isPasswordCorrect(user.getEmail(), password.getPassword())) {
                return "redirect:/library";
            } else {
                System.out.println("password not correct");
                model.addAttribute("passwordNotCorrectError", "true");
                return "login";
            }
        } else {
            System.out.println("email does not exists");
            model.addAttribute("emailDoesNotExistsError", "true");
            return "login";
        }
    }

}
