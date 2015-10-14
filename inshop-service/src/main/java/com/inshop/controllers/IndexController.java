package com.inshop.controllers;

import org.jinstagram.Instagram;
import org.jinstagram.auth.oauth.InstagramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 *
 */
@Controller
@Scope("session")
public class IndexController {
    @Autowired
    private InstagramService service;

    @RequestMapping(value = "/", method = GET)
    public String index(HttpSession session) {
        Instagram instagram = (Instagram) session.getAttribute("instagram");
        if (instagram != null) {
            return "redirect:/setup";
        }

        return "index";
    }

    @RequestMapping(value = "/authorize", method = POST)
    public String auth(HttpSession session) {
        Instagram instagram = (Instagram) session.getAttribute("instagram");
        if (instagram != null) {
            return "redirect:/setup";
        }

        return "redirect:" + service.getAuthorizationUrl(null);
    }
}
