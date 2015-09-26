package com.inshop.controllers;

import org.jinstagram.Instagram;
import org.jinstagram.exceptions.InstagramException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by savetisyan on 09/09/15.
 */
@Controller
@Scope("session")
@RequestMapping("/profile")
public class ProfileController {

    @RequestMapping(method = GET)
    public String profile(ModelMap params, HttpSession session) throws InstagramException {
        Instagram instagram = (Instagram) session.getAttribute("instagram");
        if(instagram == null) {
            return "redirect:/login";
        }

        params.addAttribute("user", instagram.getCurrentUserInfo().getData());
        return "/profile";
    }
}
