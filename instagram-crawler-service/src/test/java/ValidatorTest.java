import com.inshop.InstagramCrawler;
import org.junit.Test;

import java.net.URL;

/**
 * Created by savetisyan on 15/09/15.
 */
public class ValidatorTest {
    @Test
    public void testValidUrl() throws Exception {
        URL url = InstagramCrawler.parseDescription(
                "http://insho.as?count=1&size=xxl\n" +
                        "http://inshop.com?count=1&size=xxl\n" +
                        "and something else", "inshop.com");
        System.out.println(url);
    }
}
