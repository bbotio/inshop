package com.inshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 *
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/", method = GET)
    public String index() {
        return "redirect:/login";
    }
}
