import com.inshop.instagram.InstagramFilter;
import junit.framework.Assert;
import org.jinstagram.entity.common.Caption;
import org.jinstagram.entity.users.feed.MediaFeedData;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by savetisyan on 15/09/15.
 */
public class ValidatorTest {
    private String hostname = "inshop.com";
    private InstagramFilter instagramFilter = new InstagramFilter(hostname);
    private Caption caption;
    private MediaFeedData mediaFeedData;

    @Before
    public void setUp() throws Exception {
        mediaFeedData = new MediaFeedData();
        caption = new Caption();
        mediaFeedData.setCaption(caption);
    }

    @Test
    public void testValidUrl1() throws Exception {
        caption.setText("Some description of product and link to site: https://inshop.com");
        Assert.assertTrue("There should be an url with hostname " + hostname + " in image description",
                instagramFilter.filter(mediaFeedData));
    }

    @Test
    public void testValidUrl2() throws Exception {
        caption.setText("Some description of product and link to site: https://inshop.com/category=shoes " +
                "https://inshop.com/price=1");
        Assert.assertTrue("There should be an url with hostname " + hostname + " in image description",
                instagramFilter.filter(mediaFeedData));
    }

    @Test
    public void testInvalidUrl1() throws Exception {
        caption.setText("Some description of produst and link to site: https://inshsop.com");
        Assert.assertFalse("There shouldn't be url with hostname " + hostname + " in image description",
                instagramFilter.filter(mediaFeedData));
    }

    @Test
    public void testInvalidUrl2() throws Exception {
        caption.setText("Some description of produst and link to site: http://anothersite.com");
        Assert.assertFalse("There shouldn't be url with hostname " + hostname + " in image description",
                instagramFilter.filter(mediaFeedData));
    }

    @Test
    public void testInvalidUrl3() throws Exception {
        caption.setText("Some description of produst and link to site: <ups, where is the link?>");
        Assert.assertFalse("There shouldn't be url with hostname " + hostname + " in image description",
                instagramFilter.filter(mediaFeedData));
    }
}
