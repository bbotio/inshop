package com.inshop.instagram;

import com.inshop.Filter;
import org.jinstagram.entity.users.feed.MediaFeedData;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.net.URLEncoder.encode;

/**
 * Created by savetisyan on 16/09/15.
 */
public class InstagramFilter implements Filter<MediaFeedData> {
    private static final String pattern = "(http|https):\\/\\/?(www.)?%s\\S*";

    private Pattern compiledPattern;

    public InstagramFilter(String domain) {
        compiledPattern = Pattern.compile(String.format(pattern, domain));
    }

    @Override
    public boolean filter(MediaFeedData mediaFeedData) {
        return mediaFeedData != null &&
                mediaFeedData.getCaption() != null &&
                mediaFeedData.getCaption().getText() != null && getMatcher(mediaFeedData).find();
    }

    public Matcher getMatcher(MediaFeedData mediaFeedData) {
        return compiledPattern.matcher(mediaFeedData.getCaption().getText());
    }
}
