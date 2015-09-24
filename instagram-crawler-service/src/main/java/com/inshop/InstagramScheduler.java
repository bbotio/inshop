package com.inshop;


import com.inshop.dao.GenericDao;
import com.inshop.dao.UserDao;
import com.inshop.entity.AdditionalField;
import com.inshop.entity.Product;
import com.inshop.entity.ProductPackage;
import com.inshop.entity.User;
import com.inshop.instagram.InstagramCrawler;
import com.inshop.instagram.InstagramFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by savetisyan on 17/09/15.
 */
@Component
public class InstagramScheduler {
    @Autowired
    private UserDao userDao;

    @Autowired
    @Qualifier("genericDaoImpl")
    private GenericDao productDao;

    @Value("${scheduler.threads}")
    private int schedulerThreadsCount;

    @Value("${threadpool.threads}")
    private int threadPoolThreadsCount;

    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(schedulerThreadsCount);
    private ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(threadPoolThreadsCount);

    public void init() {
        executor.schedule(() -> {
            for (User user : userDao.findAll(User.class)) {
                threadPoolExecutor.submit(() -> {
                    InstagramCrawler instagramCrawler = new InstagramCrawler(user);
                    InstagramFilter filter = new InstagramFilter(user.getShop().getDomain());
                    List<List<Product>> shopItems = instagramCrawler.getShopItems(filter);

                    for (List<Product> shopItem : shopItems) {
                        ProductPackage productPackage = new ProductPackage();

                        for (Product product : shopItem) {
                            product.setShop(user.getShop());
                            product.setProductPackage(productPackage);
                            productDao.persist(product);
                        }
                    }
                });
            }
        }, 1, TimeUnit.DAYS);
    }
}
