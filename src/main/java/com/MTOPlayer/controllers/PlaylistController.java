package com.MTOPlayer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PlaylistController {
    @RequestMapping(value = "/playlist/{playlistId}", method = RequestMethod.GET)
    public String showPlaylist(@PathVariable int playlistId) {
        return "playlist";
    }
}
