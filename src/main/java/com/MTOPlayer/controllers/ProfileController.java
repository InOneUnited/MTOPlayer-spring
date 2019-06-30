package com.MTOPlayer.controllers;

import com.MTOPlayer.models.TemporaryPassword;
import com.MTOPlayer.models.User;
import com.MTOPlayer.models.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String showUserInfo(Model model, Model userModel, Model infoModel, Model passwordModel) {
        String pageName = "Profile";
        String songName = "hello";
        String artistName = "world";
        String email = "test@mail.com";
        String firstName = "John";
        String lastName = " Straight";
        String gender = "male";
        String date = "27.04.1998";
        model.addAttribute("pageName", pageName);
        model.addAttribute("songName", songName);
        model.addAttribute("playerInformation", artistName);
        model.addAttribute("email", email);
        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("gender", gender);
        model.addAttribute("date", date);
        userModel.addAttribute("user", new User());
        infoModel.addAttribute("userInfo", new UserInfo());
        return "userInfo";
    }

    @RequestMapping(value = "/password", method = RequestMethod.GET)
    public String showPasswordChange(Model oldTemporaryPassword, Model newTemporaryPassword) {
        oldTemporaryPassword.addAttribute("oldTemporaryPassword", new TemporaryPassword());
        newTemporaryPassword.addAttribute("newTemporaryPassword", new TemporaryPassword());
        return "changePassword";
    }

    @RequestMapping(value = "/apps", method = RequestMethod.GET)
    public String showConnectedApps() {
        return "connectedApps";
    }
}
