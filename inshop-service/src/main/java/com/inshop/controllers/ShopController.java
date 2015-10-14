package com.inshop.controllers;

import com.inshop.dao.ShopDao;
import com.inshop.entity.Shop;
import com.inshop.entity.User;
import com.inshop.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by akornev on 07/10/15.
 */
@Controller
@Scope("session")
@RequestMapping("/shop")
public class ShopController {

    public static final String DOMAIN_PARAM = "domain";
    @Autowired
    private ShopDao shopDao;

    @RequestMapping(value = "/details", method = POST, produces = "application/json")
    public Response saveShopDetails(final ModelMap params, final HttpSession session) {
        return Response.ok();
    }


    @RequestMapping(value = "/domain", method = POST, produces = "application/json")
    public Response saveShopDomain(final HttpServletRequest request, final HttpSession session) {
        final User user = (User) session.getAttribute("user");
        if (user == null) {
            return Response.error("Need to login");
        }
        final Map<String, String[]> params = request.getParameterMap();
        if (params.containsKey(DOMAIN_PARAM)) {
            final String newDomainName = params.get(DOMAIN_PARAM)[0];
            if (shopDao.getShopByDomain(newDomainName) == null) {
                final Shop shop = shopDao.getUserShop(user);
                shop.setDomain(newDomainName);
                shopDao.update(shop);
                return Response.ok();
            }
            return Response.error("Domain already exists.");
        }
        return Response.error("Need to pass domain");
    }

}
