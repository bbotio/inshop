package com.inshop.controllers;

import com.inshop.entity.Shop;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 *
 */
@Controller
@SessionAttributes({"shop"})
public class IndexController {

    @RequestMapping(value = "/", method = GET)
    public String index(final ModelMap params,
                        @ModelAttribute final Shop shop) {

        params.addAttribute("host", shop.getDomain());
        return "index";
    }
}
