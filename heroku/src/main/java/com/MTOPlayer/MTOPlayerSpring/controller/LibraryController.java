package com.MTOPlayer.MTOPlayerSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LibraryController {
    @RequestMapping(value = "/library", method = RequestMethod.GET)
    public String showLibrary(Model model){
        return "";
    }

    @RequestMapping(value = "/library/search?={keywords}", method = RequestMethod.GET)
    public String showSearchResults(Model model){
        return "";
    }
}
