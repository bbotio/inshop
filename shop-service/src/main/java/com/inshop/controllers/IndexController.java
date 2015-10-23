package com.inshop.controllers;

import com.inshop.entity.Shop;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 *
 */
@Controller
@SessionAttributes({"shop"})
public class IndexController {

    @RequestMapping(value = "/", method = GET)
    public String index(final ModelMap params, @ModelAttribute final Shop shop) {
        return "fashion-shop/category-grid";
    }

    @RequestMapping(value = "/category-list", method = GET)
    public String categoryList() {
        return "fashion-shop/category-list";
    }

    @RequestMapping(value = "/category-grid", method = GET)
    public String categoryGrid() {
        return "fashion-shop/category-grid";
    }

    @RequestMapping(value = "/cart", method = GET)
    public String cart() {
        return "fashion-shop/cart";
    }

    @RequestMapping(value = "/about", method = GET)
    public String about() {
        return "fashion-shop/about";
    }
}
