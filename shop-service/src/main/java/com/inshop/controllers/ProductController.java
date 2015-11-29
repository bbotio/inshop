package com.inshop.controllers;

import com.inshop.dao.ProductDao;
import com.inshop.entity.Product;
import com.inshop.entity.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by savetisyan on 15/11/15
 */
@Controller
@SessionAttributes({"shop"})
public class ProductController {
    @Autowired
    private ProductDao productDao;

    @RequestMapping(value = "/product/{productId}", method = GET)
    public String product(@ModelAttribute final Shop shop, final ModelMap params,
                          @PathVariable("productId") Integer productId) {
        params.addAttribute("product", productDao.get(Product.class, productId));
        return shop.getTheme().getName() + "/product";
    }

    @RequestMapping(value = "/product-full/{productId}", method = GET)
    public String productFull(@ModelAttribute final Shop shop, final ModelMap params,
                              @PathVariable("productId") Integer productId) {
        params.addAttribute("product", productDao.get(Product.class, productId));
        return shop.getTheme().getName() + "/product-full";
    }
}
