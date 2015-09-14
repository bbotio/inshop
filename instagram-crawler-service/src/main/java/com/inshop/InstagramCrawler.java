package com.inshop;

import com.inshop.entity.User;
import org.jinstagram.Instagram;
import org.jinstagram.auth.model.Token;
import org.jinstagram.entity.users.feed.MediaFeed;
import org.jinstagram.entity.users.feed.MediaFeedData;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by savetisyan on 14/09/15.
 */
public class InstagramCrawler {
    public static void crawl(User user) throws IOException {
        Token instagramToken = user.getInstagramToken();
        Instagram instagram = new Instagram(instagramToken);
        MediaFeed userFeeds = instagram.getUserFeeds();

        userFeeds.getData().stream()
                .parallel()
                .map(x -> {
                    URL url = parseDescription(user, x);
                    x.setId(null);
                    if (url != null) {
                        x.setId(url.getPath());
                    }
                    return x;
                })
                .filter(x -> x.getId() != null)
                .forEach(x -> {
                    /*todo: do something*/
                });
    }

    public static URL parseDescription(User user, MediaFeedData image) {
        String description = image.getCaption().getText();
        String domain = user.getShop().getDomain();
        return parseDescription(description, domain);
    }

    /**
     * Description should contain only one URL to shop.
     *
     * @param description
     * @param domain
     * @return
     */
    public static URL parseDescription(String description, String domain) {
        String[] words = description.split("\\s+");

        URL ansUrl = null;
        for (String word : words) {
            try {
                URL url = new URL(word);
                if (url.getHost().equals(domain)) {
                    if (ansUrl == null) {
                        ansUrl = url;
                    } else {
                        return null;
                    }
                }
            } catch (MalformedURLException e) {
            }
        }

        return ansUrl;
    }
}
