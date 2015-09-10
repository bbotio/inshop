package com.inshop.controllers;

import org.jinstagram.Instagram;
import org.jinstagram.exceptions.InstagramException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by savetisyan on 09/09/15.
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {

    @RequestMapping(method = RequestMethod.GET)
    public String profile(ModelMap params, HttpSession session) throws InstagramException {
        Instagram instagram = (Instagram) session.getAttribute("instagram");
        params.addAttribute("user", instagram.getCurrentUserInfo().getData());
        return "/profile";
    }
}
