package com.MTOPlayer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProfileController {
    @RequestMapping(value = "/profile/info", method = RequestMethod.GET)
    public String showUserInfo() {
        return "userInfo";
    }

    @RequestMapping(value = "/profile/password", method = RequestMethod.GET)
    public String showPasswordChange() {
        return "changePassword";
    }

    @RequestMapping(value = "/profile/apps", method = RequestMethod.GET)
    public String showConnectedApps() {
        return "connectedApps";
    }
}
