import com.inshop.instagram.InstagramFilter;
import org.jinstagram.entity.common.Caption;
import org.jinstagram.entity.users.feed.MediaFeedData;
import org.junit.Test;

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
}
