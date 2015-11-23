package com.inshop.controllers;

import com.inshop.dao.ProductDao;
import com.inshop.entity.Product;
import com.inshop.entity.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 *
 */
@Controller
@SessionAttributes({"shop"})
public class IndexController {

    @Autowired
    private ProductDao productDao;

    @RequestMapping(value = {"*", "/category-grid"}, method = GET)
    public String categoryGrid(@ModelAttribute final Shop shop, final ModelMap params) {
        List<Product> uniqueProductsByShop = productDao.getUniqueProductsByShop(shop);
        params.addAttribute("products", uniqueProductsByShop);
        return shop.getTheme().getName() + "/category-grid";
    }

    @RequestMapping(value = "/category-list", method = GET)
    public String categoryList(@ModelAttribute final Shop shop, final ModelMap params) {
        List<Product> uniqueProductsByShop = productDao.getUniqueProductsByShop(shop);
        params.addAttribute("products", uniqueProductsByShop);
        return shop.getTheme().getName() + "/category-list";
    }
}
