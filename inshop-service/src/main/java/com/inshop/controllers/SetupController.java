package com.inshop.controllers;

import com.inshop.PayPalFactory;
import com.inshop.dao.ShopDao;
import com.inshop.dao.UserDao;
import com.inshop.entity.*;
import com.paypal.svcs.types.perm.GetAccessTokenResponse;
import com.paypal.svcs.types.perm.RequestPermissionsResponse;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by savetisyan on 09/09/15.
 */
@Controller
@Scope("session")
@RequestMapping("/setup")
public class SetupController {
    private static final Logger LOGGER = LoggerFactory.logger(SetupController.class);

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PayPalFactory payPalFactory;

    @Autowired
    @Qualifier("grantPermissionsUrl")
    private String grantPermissionsUrl;

    @RequestMapping(method = GET)
    public String profile(ModelMap params, HttpSession session) {
        final User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }

        Shop userShop = shopDao.getUserShop(user);
        if(userShop.getPickUpAddress() == null) {
            userShop.setPickUpAddress(new Address());
        }
        shopDao.update(userShop);

        List<String> checkedCheckBoxes = new ArrayList<>();
        Set<ShopDelivery> shopDelivery = userShop.getShopDelivery();
        if(shopDelivery != null && !shopDelivery.isEmpty()) {
            checkedCheckBoxes.addAll(shopDelivery.stream()
                    .map(delivery -> delivery.getDeliveryType().getName())
                    .collect(Collectors.toList()));
        }

        params.addAttribute("user", user);
        params.addAttribute("shop", userShop);
        params.addAttribute("checkedCheckBoxes", checkedCheckBoxes);
        return "setup";
    }

    @RequestMapping(value = "/payment", method = GET)
    public String requestPaymentPermissions() {
        RequestPermissionsResponse requestPermissionsResponse = payPalFactory.getPermissionsRequest().requestPermissions();
        if (requestPermissionsResponse != null) {
            if (requestPermissionsResponse.getToken() != null) {
                return "redirect:" + grantPermissionsUrl + requestPermissionsResponse.getToken();
            }

            requestPermissionsResponse.getError().forEach(x -> LOGGER.warn(x.getMessage()));
        }

        return "redirect:/setup";
    }

    @RequestMapping(value = "/handlePermissions", method = GET)
    public String handlePaymentPermissions(HttpServletRequest request, HttpSession session) {
        String requestToken = request.getParameter("request_token");
        String verificationCode = request.getParameter("verification_code");

        if (requestToken != null && verificationCode != null) {
            GetAccessTokenResponse accessToken = payPalFactory.getPermissionsRequest().getAccessToken(requestToken, verificationCode);

            if (accessToken.getToken() != null && accessToken.getTokenSecret() != null) {
                final User user = (User) session.getAttribute("user");
                if (user == null) {
                    return "redirect:/";
                }

                Token token = new Token(accessToken.getToken(), accessToken.getTokenSecret());
                user.setPaypalToken(token);
                userDao.update(user);

                /*TokenAuthorization authorization = new TokenAuthorization(accessToken.getToken(), accessToken.getSecret());
                SignatureCredential cred = new SignatureCredential(paypalUsername, paypalPassword, paypalSignature);
                cred.setApplicationId(paypalAppId);
                cred.setThirdPartyAuthorization(authorization);*/
            }
        }

        return "redirect:/setup";
    }
}
