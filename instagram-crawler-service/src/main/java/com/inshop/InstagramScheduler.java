package com.inshop;


import com.inshop.dao.ProductDao;
import com.inshop.dao.UserDao;
import com.inshop.entity.Category;
import com.inshop.entity.Product;
import com.inshop.entity.ProductPackage;
import com.inshop.entity.User;
import com.inshop.instagram.InstagramCrawler;
import com.inshop.instagram.InstagramFilter;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.*;

/**
 * Created by savetisyan on 17/09/15.
 */
@Component
public class InstagramScheduler {
    @Autowired
    private UserDao userDao;

    @Autowired
    private ProductDao productDao;

    @Value("${crawler.scheduler.threads:1}")
    private int schedulerThreadsCount;

    @Value("${crawler.threadpool.threads:4}")
    private int threadPoolThreadsCount;

    private ScheduledFuture<?> scheduledFuture;


    public void init() {
        final ScheduledExecutorService executor = Executors.newScheduledThreadPool(schedulerThreadsCount);
        final ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(threadPoolThreadsCount);
        scheduledFuture = executor.scheduleAtFixedRate(() -> {
            for (User user : userDao.findAll(User.class)) {
                threadPoolExecutor.submit(() -> {
                    InstagramCrawler instagramCrawler = new InstagramCrawler(user);
                    InstagramFilter filter = new InstagramFilter(user.getShop().getDomain());
                    List<Pair<Product, Integer>> shopItems = instagramCrawler.getShopItems(filter);

                    for (Pair<Product, Integer> shopItem : shopItems) {
                        ProductPackage productPackage = new ProductPackage();

                        Product product = shopItem.getKey();
                        product.setShop(user.getShop());
                        product.setProductPackage(productPackage);

                        /**
                         * TODO: don't save category to db, if it exists
                         * e.g. the two products have category 'book'
                         * try to find category by name, if it doesn't exist - save!
                         */
                        for (Category category : product.getCategories()) {
                            category.setProduct(product);
                        }

                        Product productByUrl = productDao.getByImageUrl(product.getImageUrl());
                        if (productByUrl == null) {
                            for (int i = 0; i < shopItem.getValue(); i++) {
                                product.setId(null);
                                productDao.save(product);
                            }
                        }
                    }
                });
            }
        }, 0, 15, TimeUnit.SECONDS);
    }

    public void destroy() {
        scheduledFuture.cancel(false);
    }

    public void restart() {
        scheduledFuture.cancel(false);
        init();
    }
}
