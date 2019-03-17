package com.MTOPlayer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SongController {
    @RequestMapping(value = "/song/{songId}", method = RequestMethod.GET)
    public String showSong(@PathVariable int songId){
        return "player";
    }
}
