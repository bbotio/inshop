import com.inshop.Utils;
import com.inshop.entity.Product;
import com.inshop.instagram.InstagramFilter;
import org.jinstagram.entity.common.Caption;
import org.jinstagram.entity.users.feed.MediaFeedData;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

/**
 * Created by savetisyan on 15/09/15.
 */
public class ValidatorTest {
    @Test
    public void testValidUrl() throws Exception {
        InstagramFilter instagramFilter = new InstagramFilter("inshop.com");
        MediaFeedData mediaFeedData = new MediaFeedData();
        Caption caption = new Caption();
        caption.setText("https://inshop.com");
        mediaFeedData.setCaption(caption);

        System.out.println(instagramFilter.filter(mediaFeedData));
    }

    @Test
    public void parse() throws MalformedURLException {
        URL url = new URL("http://sevak.inshop.com?category=shoes,fashion&size=xxl&count=1&color=red&price=1&currency=EU");
        MediaFeedData mediaFeedData = new MediaFeedData();
        Caption caption = new Caption();
        caption.setText("https://inshop.com");
        caption.setCreatedTime("71267381628");
        mediaFeedData.setCaption(caption);
        mediaFeedData.setTags(Arrays.asList("123", "tah1", "as8"));
        mediaFeedData.setLink("link to image");

        Product product = Utils.buildProduct(mediaFeedData, url);
        System.out.println(product);

    }
}
