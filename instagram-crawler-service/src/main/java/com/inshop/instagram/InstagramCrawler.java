package com.inshop.instagram;

import com.inshop.Crawler;
import com.inshop.Filter;
import com.inshop.entity.Product;
import com.inshop.entity.User;
import org.apache.commons.lang3.tuple.Pair;
import org.jinstagram.Instagram;
import org.jinstagram.auth.model.Token;
import org.jinstagram.entity.users.feed.MediaFeedData;
import org.jinstagram.exceptions.InstagramException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

import static com.inshop.ProductFactory.buildProducts;

/**
 * Created by savetisyan on 14/09/15.
 */
public class InstagramCrawler implements Crawler<MediaFeedData> {
    private static final Logger LOGGER = LoggerFactory.getLogger(InstagramCrawler.class);
    public static final Pair<Product, Integer> EMPTY_PRODUCT = Pair.of(new Product(), 0);
    private User user;

    public InstagramCrawler(User user) {
        this.user = user;
    }

    @Override
    public List<MediaFeedData> getItems() {
        try {
            com.inshop.entity.Token tokenEntity = user.getInstagramToken();
            Token instagramToken = new Token(tokenEntity.getToken(), tokenEntity.getSecret());
            Instagram instagram = new Instagram(instagramToken);
            return instagram.getUserFeeds().getData();
        } catch (InstagramException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Pair<Product, Integer>> getShopItems(Filter<MediaFeedData> filter) {
        return getItems().stream()
                .filter(filter::filter)
                .map(image -> {
                    InstagramFilter instagramFilter = (InstagramFilter) filter;
                    Matcher matcher = instagramFilter.getMatcher(image);
                    matcher.find();
                    String url = matcher.group();
                    if (url.isEmpty()) {
                        return EMPTY_PRODUCT;
                    }
                    return buildProducts(image, url);
                })
                .collect(Collectors.toList());
    }
}
