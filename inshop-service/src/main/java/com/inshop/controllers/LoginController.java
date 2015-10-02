package com.inshop.controllers;

import com.inshop.dao.ShopDao;
import com.inshop.dao.UserDao;
import com.inshop.entity.Shop;
import com.inshop.entity.User;
import org.jinstagram.Instagram;
import org.jinstagram.auth.model.Token;
import org.jinstagram.auth.model.Verifier;
import org.jinstagram.auth.oauth.InstagramService;
import org.jinstagram.entity.users.basicinfo.UserInfoData;
import org.jinstagram.exceptions.InstagramException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by savetisyan on 09/09/15.
 */
@Controller
@Scope("session")
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private InstagramService service;

    @Autowired
    private UserDao userDao;

    @RequestMapping(method = GET)
    public String login(ModelMap params, HttpSession session) {
        Instagram instagram = (Instagram) session.getAttribute("instagram");
        if (instagram != null) {
            return "redirect:/setup";
        }

        String authorizationUrl = service.getAuthorizationUrl(null);
        params.addAttribute("authorizationUrl", authorizationUrl);
        return "/login";
    }

    @RequestMapping(value = "/handleToken", method = GET)
    public String handleToken(HttpServletRequest request, HttpSession session) throws InstagramException {
        String code = request.getParameter("code");
        if (code == null) {
            return "redirect:/login";
        }

        Verifier verifier = new Verifier(code);
        Token accessToken = service.getAccessToken(null, verifier);
        Instagram instagram = new Instagram(accessToken);
        session.setAttribute("instagram", instagram);

        UserInfoData userInfo = instagram.getCurrentUserInfo().getData();

        String userId = userInfo.getId();
        User user = userDao.getById(userId);

        if (user == null) {
            user = new User();
            user.setUserId(userId);
            user.setInstagramToken(accessToken);

            Shop shop = new Shop();
            shop.setDomain(userInfo.getUsername() + ".inshop.com");
            shop.setTitle(userInfo.getFullName());
            shop.setDescription(userInfo.getBio());

            user.setShop(shop);
            userDao.persist(user);
        } else {
            user.setInstagramToken(accessToken);
            userDao.update(user);
        }

        return "redirect:/setup";
    }
}
