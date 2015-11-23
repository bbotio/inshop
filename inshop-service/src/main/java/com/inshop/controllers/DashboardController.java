package com.inshop.controllers;

import com.inshop.dao.ShopDao;
import com.inshop.entity.Shop;
import com.inshop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 *
 */
@Controller
@Scope("session")
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private ShopDao shopDao;

    @RequestMapping(method = GET)
    public String index(HttpSession session) {
        final User user = (User) session.getAttribute("user");
        final Shop shop = shopDao.getUserShop(user);

        if (shop.getState() == Shop.State.LAUNCHED) {
            return "dashboard";
        }
        return "redirect:/setup";
    }

}
