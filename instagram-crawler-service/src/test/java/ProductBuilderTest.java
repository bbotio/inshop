import com.inshop.ProductFactory;
import com.inshop.entity.Price;
import com.inshop.entity.Product;
import junit.framework.Assert;
import org.jinstagram.entity.common.Caption;
import org.jinstagram.entity.users.feed.MediaFeedData;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static com.inshop.entity.Price.Currency.RUB;
import static com.inshop.entity.Price.Currency.USD;

/**
 * Created by savetisyan on 26/09/15.
 */
public class ProductBuilderTest {
    private URL url;
    private Caption caption;
    private MediaFeedData mediaFeedData;

    @Before
    public void setUp() {
        mediaFeedData = new MediaFeedData();
        caption = new Caption();
        caption.setCreatedTime("71267381628");
        mediaFeedData.setCaption(caption);
        mediaFeedData.setLink("link to image");
    }

    @Test
    public void testValid() throws MalformedURLException, URISyntaxException {
        url = new URL("http://sevak.inshop.com?category=shoes,fashion&size=xxl&count=2&" +
                "color=red&price=2&currency=rub");
        caption.setText("You must buy this shoes" + url.toString() + ". Visit our site www.example.com");
        mediaFeedData.setTags(Arrays.asList("123", "tah1", "as8"));

        List<Product> productList = ProductFactory.buildProducts(mediaFeedData, url);
        Assert.assertEquals(2, productList.size());
        Product product = productList.get(0);

        Assert.assertEquals(2, product.getCategories().size());
        product.getCategories()
                .forEach(x ->
                        Assert.assertTrue("Category should be 'shoes' and 'fashion', but found " + x.getName(),
                                x.getName().equals("shoes") || x.getName().equals("fashion")));

        Assert.assertEquals(2.0, product.getPrice().getPrice());
        Assert.assertEquals(RUB, product.getPrice().getCurrency());
        Assert.assertEquals("link to image", product.getImageUrl());
        Assert.assertEquals("You must buy this shoes. Visit our site www.example.com", product.getDescription());

        Assert.assertEquals(2, product.getAdditionalFields().size());
        product.getAdditionalFields()
                .forEach(x ->
                        Assert.assertTrue("Category should be 'shoes' and 'fashion', but found " + x.getName(),
                                x.getName().equals("color") && x.getValue().equals("red") ||
                                        x.getName().equals("size") && x.getValue().equals("xxl")));

        Assert.assertEquals(3, product.getTags().size());
        Assert.assertEquals(new HashSet<>(Arrays.asList("123", "tah1", "as8")), product.getTags());
    }

    @Test
    public void testValid2() throws Exception {
        url = new URL("http://sevak.inshop.com");
        caption.setText("You must buy this shoes! " + url.toString());
        mediaFeedData.setTags(new ArrayList<>());

        List<Product> productList = ProductFactory.buildProducts(mediaFeedData, url);
        System.out.println();

        Assert.assertEquals(1, productList.size());
        Product product = productList.get(0);

        Assert.assertEquals(1, product.getCategories().size());
        product.getCategories()
                .forEach(x ->
                        Assert.assertTrue("Category should be 'other', but found " + x.getName(),
                                x.getName().equals("other")));

        Assert.assertEquals("Price must be equals to default price (1 USD)", new Price(1, USD), product.getPrice());
        Assert.assertEquals("link to image", product.getImageUrl());
        Assert.assertEquals("You must buy this shoes!", product.getDescription());

        Assert.assertTrue("There should be no additional fields", product.getAdditionalFields().isEmpty());
        Assert.assertTrue("There should be no tags", product.getTags().isEmpty());
    }
}
