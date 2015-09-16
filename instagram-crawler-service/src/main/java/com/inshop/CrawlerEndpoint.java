package com.inshop;


import com.inshop.dao.UserDao;
import com.inshop.entity.User;
import com.inshop.instagram.InstagramCrawler;
import com.inshop.instagram.InstagramFilter;
import org.jinstagram.entity.users.feed.MediaFeedData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by savetisyan on 17/09/15.
 */
@Component
public class CrawlerEndpoint {
    @Autowired
    private UserDao userDao;
    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(4);

    @PostConstruct
    public void init() {
        for (User user : userDao.findAll(User.class)) {
            InstagramCrawler instagramCrawler = new InstagramCrawler(user);
            executor.schedule(() -> {
                InstagramFilter filter = new InstagramFilter(user.getShop().getDomain());
                List<ParsedImage<MediaFeedData>> shopItems = instagramCrawler.getShopItems(filter);
                /*todo: update items in DB*/
            }, 1, TimeUnit.DAYS);
        }
    }
}
