package com.inshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 *
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/", method = GET)
    public String index(@RequestHeader(value = "Host") final String host, final ModelMap params, final HttpSession session) {

        params.addAttribute("host", host);
        return "index";
    }
}
