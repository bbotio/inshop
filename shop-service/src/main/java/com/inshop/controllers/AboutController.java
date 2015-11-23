package com.inshop.controllers;

import com.inshop.entity.Shop;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by savetisyan on 15/11/15
 */
@Controller
@SessionAttributes({"shop"})
public class AboutController {

    @RequestMapping(value = "/about", method = GET)
    public String about(@ModelAttribute final Shop shop) {
        return shop.getTheme().getName() + "/about";
    }
}
