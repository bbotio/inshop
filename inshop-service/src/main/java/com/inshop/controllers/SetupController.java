package com.inshop.controllers;

import com.inshop.dao.ShopDao;
import com.inshop.dao.UserDao;
import com.inshop.entity.User;
import org.jinstagram.Instagram;
import org.jinstagram.exceptions.InstagramException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by savetisyan on 09/09/15.
 */
@Controller
@Scope("session")
@RequestMapping("/setup")
public class SetupController {

    @Autowired
    private ShopDao shopDao;

    @RequestMapping(method = GET)
    public String profile(ModelMap params, HttpSession session) {
        final User user = (User) session.getAttribute("user");
        if(user == null) {
            return "redirect:/";
        }

        params.addAttribute("user", user);
        params.addAttribute("shop", shopDao.getUserShop(user));
        return "setup";
    }
}
