import com.inshop.ProductFactory;
import com.inshop.entity.Price;
import com.inshop.entity.Product;
import junit.framework.Assert;
import org.apache.commons.lang3.tuple.Pair;
import org.jinstagram.entity.common.Caption;
import org.jinstagram.entity.users.feed.MediaFeedData;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static com.inshop.entity.Price.Currency.RUB;
import static com.inshop.entity.Price.Currency.USD;
import static java.net.URLEncoder.encode;

/**
 * Created by savetisyan
 */
public class ProductBuilderTest {
    private String url;
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
    public void testValid() throws MalformedURLException, URISyntaxException, UnsupportedEncodingException {
        url = "http://sevak.inshop.com?category=shoes,fashion&size=xxl&count=2&" +
                "color=red&price=2&currency=rub";
        caption.setText("You must buy this shoes" + url + ". Visit our site www.example.com");
        mediaFeedData.setTags(Arrays.asList("123", "tah1", "as8"));

        Pair<Product, Integer> productList = ProductFactory.buildProducts(mediaFeedData, url);
        Assert.assertEquals(Integer.valueOf(2), productList.getValue());
        Product product = productList.getKey();

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
        url = "http://sevak.inshop.com";
        caption.setText("You must buy this shoes! " + url);
        mediaFeedData.setTags(new ArrayList<>());

        Pair<Product, Integer> productList = ProductFactory.buildProducts(mediaFeedData, url);
        Assert.assertEquals(Integer.valueOf(1), productList.getValue());
        Product product = productList.getKey();

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

    @Test
    public void testValid3() throws Exception {
        url = "http://sevak.inshop.com?category=book,книга&price=1500&currency=rub&count=1&name=Алиса в стране чудес&" +
                "seller=Студия Артемия Лебедева";

        caption.setText("You must buy this book! " + url);
        mediaFeedData.setTags(new ArrayList<>());

        Pair<Product, Integer> productList = ProductFactory.buildProducts(mediaFeedData, url);
        Assert.assertEquals(Integer.valueOf(1), productList.getValue());
        Product product = productList.getKey();

        Assert.assertEquals(2, product.getCategories().size());
        product.getCategories()
                .forEach(x ->
                        Assert.assertTrue("Category should be 'книга' or 'book', but found " + x.getName(),
                                x.getName().equals("книга") || x.getName().equals("book")));

        Assert.assertEquals("Price must be equals to 1500 Rub", new Price(1500, RUB), product.getPrice());
        Assert.assertEquals("link to image", product.getImageUrl());
        Assert.assertEquals("You must buy this book!", product.getDescription());
        Assert.assertTrue("There should be no tags", product.getTags().isEmpty());
    }
}
