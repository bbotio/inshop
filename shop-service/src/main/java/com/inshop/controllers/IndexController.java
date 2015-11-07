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
        return shop.getTheme().getName() + "/category-grid";
    }

    @RequestMapping(value = "/category-list", method = GET)
    public String categoryList(@ModelAttribute final Shop shop) {
        return shop.getTheme().getName() + "/category-list";
    }

    @RequestMapping(value = "/category-grid", method = GET)
    public String categoryGrid(@ModelAttribute final Shop shop) {
        return shop.getTheme().getName() + "/category-grid";
    }

    @RequestMapping(value = "/cart", method = GET)
    public String cart(@ModelAttribute final Shop shop) {
        return shop.getTheme().getName() + "/cart";
    }

    @RequestMapping(value = "/about", method = GET)
    public String about(@ModelAttribute final Shop shop) {
        return shop.getTheme().getName() + "/about";
    }

    @RequestMapping(value = "/product", method = GET)
    public String product(@ModelAttribute final Shop shop) {
        return shop.getTheme().getName() + "/product";
    }

    @RequestMapping(value = "/product-full", method = GET)
    public String productFull(@ModelAttribute final Shop shop) {
        return shop.getTheme().getName() + "/product-full";
    }
}
