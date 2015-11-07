package com.inshop.controllers;

import com.inshop.dao.DeliveryTypeDao;
import com.inshop.dao.ShopDao;
import com.inshop.dao.ThemeDao;
import com.inshop.dao.UserDao;
import com.inshop.entity.*;
import com.inshop.utils.Response;
import org.apache.commons.validator.routines.EmailValidator;
import org.omg.CORBA.RepositoryIdHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

import static org.springframework.http.MediaType.*;
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
    public static final String EMAIL_PARAM = "email";
    public static final String NAME_PARAM = "name";
    public static final String PHONE_PARAM = "phone";

    public static final String COUNTRY_PARAM = "country";
    public static final String STATE_PARAM = "state";
    public static final String CITY_PARAM = "city";
    public static final String ADDRESS_PARAM = "address";
    public static final String ZIP_PARAM = "zip";

    public static final String PICKUP_COUNTRY_PARAM = "country-pickup";
    public static final String PICKUP_STATE_PARAM = "state-pickup";
    public static final String PICKUP_CITY_PARAM = "city-pickup";
    public static final String PICKUP_ADDRESS_PARAM = "address-pickup";
    public static final String PICKUP_ZIP_PARAM = "zip-pickup";

    public static final String CHECKBOX_DHL = "DHL";
    public static final String CHECKBOX_EMS = "EMS";
    public static final String CHECKBOX_SELF_SERVICE = "SelfService";
    public static final String SHOP_THEME = "shop-theme";

    @Autowired
    private UserDao userDao;

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private ThemeDao themeDao;

    @Autowired
    private DeliveryTypeDao deliveryTypeDao;

    @RequestMapping(value = "/domain", method = POST, produces = APPLICATION_JSON_VALUE)
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

    @RequestMapping(value = "/details", method = POST, produces = APPLICATION_JSON_VALUE)
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

    @RequestMapping(value = "/analytics", method = POST, produces = APPLICATION_JSON_VALUE)
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

    @RequestMapping(value = "/contacts", method = POST, produces = APPLICATION_JSON_VALUE)
    public Response saveUserContacts(final HttpServletRequest request, final HttpSession session) {
        final User user = (User) session.getAttribute("user");

        if (user == null) {
            return Response.error("Need to login");
        }

        if(user.getAddress() == null) {
            user.setAddress(new Address());
        }

        if(user.getShop().getPickUpAddress() == null) {
            user.getShop().setPickUpAddress(new Address());
        }

        final Map<String, String[]> params = request.getParameterMap();
        if(params.containsKey(NAME_PARAM)) {
            user.setName(params.get(NAME_PARAM)[0]);
        }

        if(params.containsKey(PHONE_PARAM)) {
            user.setPhone(params.get(PHONE_PARAM)[0]);
        }

        if(params.containsKey(COUNTRY_PARAM)) {
            String country = params.get(COUNTRY_PARAM)[0];
            if (!country.isEmpty() || user.getAddress().getCountry() == null || user.getAddress().getCountry().isEmpty()) {
                user.getAddress().setCountry(country);
            }

            if(StringUtils.isEmpty(user.getShop().getPickUpAddress().getCountry())) {
                user.getShop().getPickUpAddress().setCountry(country);
            }
        }

        if(params.containsKey(STATE_PARAM)) {
            user.getAddress().setStateOrProvince(params.get(STATE_PARAM)[0]);
            if(StringUtils.isEmpty(user.getShop().getPickUpAddress().getStateOrProvince())) {
                user.getShop().getPickUpAddress().setStateOrProvince(params.get(STATE_PARAM)[0]);
            }
        }

        if(params.containsKey(CITY_PARAM)) {
            user.getAddress().setCity(params.get(CITY_PARAM)[0]);
            if(StringUtils.isEmpty(user.getShop().getPickUpAddress().getCity())) {
                user.getShop().getPickUpAddress().setCity(params.get(CITY_PARAM)[0]);
            }
        }

        if(params.containsKey(ADDRESS_PARAM)) {
            user.getAddress().setAddress(params.get(ADDRESS_PARAM)[0]);
            if(StringUtils.isEmpty(user.getShop().getPickUpAddress().getAddress())) {
                user.getShop().getPickUpAddress().setAddress(params.get(ADDRESS_PARAM)[0]);
            }
        }

        if(params.containsKey(ZIP_PARAM)) {
            user.getAddress().setZip(params.get(ZIP_PARAM)[0]);
            if(StringUtils.isEmpty(user.getShop().getPickUpAddress().getZip())) {
                user.getShop().getPickUpAddress().setZip(params.get(ZIP_PARAM)[0]);
            }
        }
        userDao.update(user);

        if(params.containsKey(EMAIL_PARAM)) {
            String email = params.get(EMAIL_PARAM)[0];
            if(!EmailValidator.getInstance().isValid(email)) {
                return Response.error("Passed email is invalid: " + email);
            }

            user.setEmail(email);
        }

        userDao.update(user);
        return Response.ok();
    }

    @RequestMapping(value = "/delivery", method = POST, produces = APPLICATION_JSON_VALUE)
    public Response saveDelivery(final HttpServletRequest request, final HttpSession session) {
        final User user = (User) session.getAttribute("user");
        if (user == null) {
            return Response.error("Need to login");
        }

        final Map<String, String[]> params = request.getParameterMap();
        Set<ShopDelivery> shopDeliveries = new HashSet<>();

        Address pickUpAddress = user.getShop().getPickUpAddress();
        boolean isSelfServiceEnabled = false;

        for(String delivery: Arrays.asList(CHECKBOX_DHL, CHECKBOX_EMS, CHECKBOX_SELF_SERVICE)) {
            if(!params.containsKey(delivery) || !params.get(delivery)[0].equals("on")) {
                continue;
            }

            DeliveryType deliveryType = deliveryTypeDao.getByName(delivery);

            if(deliveryType == null) {
                deliveryType = new DeliveryType();
                deliveryType.setName(delivery);
                deliveryType.setDescription(delivery + " delivery type");
            }

            if(delivery.equals("SelfService")) {
                isSelfServiceEnabled = true;

                if(params.containsKey(PICKUP_COUNTRY_PARAM)) {
                    String country = params.get(PICKUP_COUNTRY_PARAM)[0];

                    if (!country.isEmpty() || pickUpAddress.getCountry() == null || pickUpAddress.getCountry().isEmpty()) {
                        pickUpAddress.setCountry(country);
                    }
                }

                if(params.containsKey(PICKUP_STATE_PARAM)) {
                    pickUpAddress.setStateOrProvince(params.get(PICKUP_STATE_PARAM)[0]);
                }

                if(params.containsKey(PICKUP_CITY_PARAM)) {
                    pickUpAddress.setCity(params.get(PICKUP_CITY_PARAM)[0]);
                }

                if(params.containsKey(PICKUP_ADDRESS_PARAM)) {
                    pickUpAddress.setAddress(params.get(PICKUP_ADDRESS_PARAM)[0]);
                }

                if(params.containsKey(PICKUP_ZIP_PARAM)) {
                    pickUpAddress.setZip(params.get(PICKUP_ZIP_PARAM)[0]);
                }
            }

            ShopDelivery shopDelivery = new ShopDelivery();
            shopDelivery.setDeliveryType(deliveryType);
            shopDeliveries.add(shopDelivery);
        }

        if(!isSelfServiceEnabled) {
            shopDeliveries.removeIf(x -> x.getDeliveryType().getName().equals("SelfService"));
        }

        user.getShop().setShopDelivery(shopDeliveries);
        user.getShop().setPickUpAddress(pickUpAddress);
        userDao.update(user);

        if(shopDeliveries.isEmpty()) {
            return Response.error("You must choose at least one delivery type for your shop.");
        }

        return Response.ok();
    }

    @RequestMapping(value = "/launch", method = POST)
    public String launchShop(final HttpServletRequest request, final HttpSession session) {
        // TODO: validate shop here
        final User user = (User) session.getAttribute("user");
        final Shop shop = shopDao.getUserShop(user);

        shop.setState(Shop.State.LAUNCHED);

        shopDao.update(shop);
        return "redirect:/dashboard";
    }

    @RequestMapping(value = "/theme", method = POST, produces = APPLICATION_JSON_VALUE)
    public Response saveShopTheme(final HttpServletRequest request, final HttpSession session) {
        final User user = (User) session.getAttribute("user");
        final Shop shop = shopDao.getUserShop(user);
        final Map<String, String[]> params = request.getParameterMap();

        if (params.containsKey(SHOP_THEME)) {
            final String themeName = params.get(SHOP_THEME)[0];
            if (!StringUtils.isEmpty(themeName)) {
                final Theme theme = themeDao.getThemeByName(themeName);

                shop.setTheme(theme);

                shopDao.update(shop);

                return Response.ok();
            }
        }



        return Response.error("Shop view was not specified");
    }
}
