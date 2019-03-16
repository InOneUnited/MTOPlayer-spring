package com.MTOPlayer.servlets;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeServlet {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printHello(Model model){
        return "index";
    }
}