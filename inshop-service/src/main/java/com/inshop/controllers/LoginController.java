package com.inshop.controllers;

import com.inshop.dao.UserDao;
import com.inshop.entity.Address;
import com.inshop.entity.Shop;
import com.inshop.entity.User;
import com.inshop.utils.Consts;
import org.jinstagram.Instagram;
import org.jinstagram.auth.model.Token;
import org.jinstagram.auth.model.Verifier;
import org.jinstagram.auth.oauth.InstagramService;
import org.jinstagram.entity.users.basicinfo.UserInfoData;
import org.jinstagram.exceptions.InstagramException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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

    @RequestMapping(value = "/handleToken", method = GET)
    public String handleToken(HttpServletRequest request, HttpSession session) throws InstagramException {
        final String code = request.getParameter("code");
        if (code == null) {
            return "redirect:/";
        }

        final Verifier verifier = new Verifier(code);
        final Token accessToken = service.getAccessToken(null, verifier);
        final Instagram instagram = new Instagram(accessToken);

        com.inshop.entity.Token tokenEntity =
                new com.inshop.entity.Token(accessToken.getToken(), accessToken.getSecret());

        final UserInfoData userInfo = instagram.getCurrentUserInfo().getData();
        final String userId = userInfo.getId();
        User user = userDao.getByInstagramUserId(userId);

        //TODO: move it to some sort of service
        if (user == null) {
            user = new User();
            user.setAddress(new Address());
            user.setInstagramUserId(userId);
            user.setInstagramToken(tokenEntity);

            Shop shop = new Shop();
            shop.setState(Shop.State.STOPED);
            //TODO: maybe we should use user website instead of making domain
            final String domainName = userInfo.getUsername();
            if (!StringUtils.isEmpty(domainName)) {
                shop.setDomain(domainName.replaceAll("[^a-zA-Z0-9]*","") + ".inshop.com");
            }
            shop.setTitle(userInfo.getFullName());
            shop.setDescription(userInfo.getBio());
            shop.setPickUpAddress(new Address());
            shop.setOwner(user);
            user.setShop(shop);

            userDao.save(user);
        } else {
            user.setInstagramToken(tokenEntity);
            userDao.update(user);
        }

        session.setAttribute(Consts.USER_SESSION_ATTRIBUTE, user);
        return "redirect:/setup";
    }
}
