package com.inshop.instagram;

import com.inshop.Filter;
import org.jinstagram.entity.users.feed.MediaFeedData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String text = mediaFeedData.getCaption().getText();
        return text != null && compiledPattern.matcher(text).find();
    }

    public Pattern getCompiledPattern() {
        return compiledPattern;
    }
}
