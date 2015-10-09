package com.inshop.controllers;

import com.inshop.dao.ShopDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by akornev on 07/10/15.
 */
@Controller
@Scope("session")
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private ShopDao shopDao;

    @RequestMapping(value = "/details", method = POST, produces="application/json")
    public Response saveShopDetails(final ModelMap params, final HttpSession session) {
        return Response.ok();
    }
}
