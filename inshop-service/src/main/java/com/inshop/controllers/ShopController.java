package com.inshop.controllers;

import com.inshop.dao.ShopDao;
import com.inshop.entity.Shop;
import com.inshop.entity.ShopAnalytics;
import com.inshop.entity.User;
import com.inshop.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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
    public static final String SHOP_TITLE = "shop-title";
    public static final String SHOP_DETAILS = "shop-details";
    public static final String GOOGLE_ANALYTICS_ID = "google-analytics-id";
    public static final String SHOP_TAGS = "shop-tags";


    @Autowired
    private ShopDao shopDao;

    @RequestMapping(value = "/domain", method = POST, produces = "application/json")
    public Response saveShopDomain(final HttpServletRequest request, final HttpSession session) {
        final User user = (User) session.getAttribute("user");
        if (user == null) {
            return Response.error("Need to login");
        }
        final Map<String, String[]> params = request.getParameterMap();
        if (params.containsKey(DOMAIN_PARAM)) {
            final String newDomainName = params.get(DOMAIN_PARAM)[0];
            final Shop shop = shopDao.getUserShop(user);
            final Shop shopByDomain = shopDao.getShopByDomain(newDomainName);
            if ((shopByDomain == null) || (shop.equals(shopByDomain))) {
                shop.setDomain(newDomainName);
                shopDao.update(shop);
                return Response.ok();
            }
            return Response.error("Domain already exists.");
        }
        return Response.error("Need to pass domain");
    }

    @RequestMapping(value = "/details", method = POST, produces = "application/json")
    public Response saveShopDetails(final HttpServletRequest request, final HttpSession session) {
        final User user = (User) session.getAttribute("user");
        if (user == null) {
            return Response.error("Need to login");
        }

        final Map<String, String[]> params = request.getParameterMap();
        if (params.containsKey(SHOP_TITLE)) {
            final String shopTitle = params.get(SHOP_TITLE)[0];
            if (!StringUtils.isEmpty(shopTitle)) {
                final Shop shop = shopDao.getUserShop(user);
                final String shopDetails = params.getOrDefault(SHOP_DETAILS, new String[]{shop.getDescription()})[0];

                shop.setTitle(shopTitle);
                shop.setDescription(shopDetails);

                shopDao.update(shop);
                return Response.ok();
            }
        }

        return Response.error("Need to pass information about your shop");
    }

    @RequestMapping(value = "/analytics", method = POST, produces = "application/json")
    public Response saveAnalytics(final HttpServletRequest request, final HttpSession session) {
        final User user = (User) session.getAttribute("user");
        if (user == null) {
            return Response.error("Need to login");
        }

        final Map<String, String[]> params = request.getParameterMap();
        final Shop shop = shopDao.getUserShop(user);

        ShopAnalytics analytics = shop.getShopAnalytics();
        if (analytics == null) {
            analytics = new ShopAnalytics();
        }
        analytics.setGoogleAnalyticsId(params.getOrDefault(GOOGLE_ANALYTICS_ID,
                new String[]{analytics.getGoogleAnalyticsId()})[0]);
        analytics.setTags(params.getOrDefault(SHOP_TAGS,
                new String[]{analytics.getTags()})[0]);

        shop.setShopAnalytics(analytics);

        shopDao.update(shop);

        return Response.ok();
    }

}
