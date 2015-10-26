package com.inshop.instagram;

import com.inshop.Crawler;
import com.inshop.Filter;
import com.inshop.entity.Product;
import com.inshop.entity.User;
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
    public List<List<Product>> getShopItems(Filter<MediaFeedData> filter) {
        return getItems().stream()
                .filter(filter::filter)
                .map(image -> {
                    String domain = user.getShop().getDomain();
                    InstagramFilter instagramFilter = new InstagramFilter(domain);
                    Matcher matcher = instagramFilter.getCompiledPattern().matcher(image.getCaption().getText());
                    String url = matcher.group();
                    if (url.isEmpty()) {
                        return Collections.<Product>emptyList();
                    }
                    return buildProducts(image, url);
                })
                .collect(Collectors.toList());
    }
}
