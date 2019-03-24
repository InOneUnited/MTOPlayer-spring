package com.MTOPlayer.controllers;

import com.MTOPlayer.models.TemporaryPassword;
import com.MTOPlayer.models.User;
import com.MTOPlayer.service.BasicLoginService;
import com.MTOPlayer.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@Controller
public class SignInController {
    @GetMapping("/login")
    public String showLogin(Model userModel, Model passwordModel){
        userModel.addAttribute("user", new User());
        passwordModel.addAttribute("temporaryPassword", new TemporaryPassword());
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(Model model, @ModelAttribute User user, @ModelAttribute TemporaryPassword password) throws IOException, SQLException {
        LoginService loginService = new BasicLoginService();
        if(loginService.isEmailInDB(user.getEmail())){
            if(loginService.isPasswordCorrect(user.getEmail(), password.getPassword())){
                return "redirect:/library";
            } else {
                model.addAttribute("passwordNotCorrectError", "true");
                return "login";
            }
        } else {
            model.addAttribute("emailDoesNotExistsError", "true");
            return "login";
        }
    }
}
