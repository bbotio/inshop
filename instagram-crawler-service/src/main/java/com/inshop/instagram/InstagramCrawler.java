package com.inshop.instagram;

import com.inshop.Crawler;
import com.inshop.Filter;
import com.inshop.entity.Product;
import com.inshop.entity.User;
import org.jinstagram.Instagram;
import org.jinstagram.auth.model.Token;
import org.jinstagram.entity.users.feed.MediaFeedData;
import org.jinstagram.exceptions.InstagramException;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

import static com.inshop.ProductFactory.buildProducts;

/**
 * Created by savetisyan on 14/09/15.
 */
public class InstagramCrawler implements Crawler<MediaFeedData> {
    private User user;

    public InstagramCrawler(User user) {
        this.user = user;
    }

    @Override
    public List<MediaFeedData> getItems() {
        try {
            Token instagramToken = user.getInstagramToken();
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
                    try {
                        String domain = user.getShop().getDomain();
                        InstagramFilter instagramFilter = new InstagramFilter(domain);
                        Matcher matcher = instagramFilter.getCompiledPattern().matcher(image.getCaption().getText());
                        return buildProducts(image, new URL(matcher.group()));
                    } catch (MalformedURLException | URISyntaxException e) {
                    }
                    return new ArrayList<Product>();
                })
                .collect(Collectors.toList());
    }
}
