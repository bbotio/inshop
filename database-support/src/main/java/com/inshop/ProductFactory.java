package com.inshop;

import com.inshop.entity.AdditionalField;
import com.inshop.entity.Category;
import com.inshop.entity.Price;
import com.inshop.entity.Product;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.jinstagram.entity.users.feed.MediaFeedData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.inshop.entity.Price.Currency.USD;

/**
 * Created by savetisyan on 25/09/15.
 */
public class ProductFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductFactory.class);

    public static List<Product> buildProducts(MediaFeedData image, URL url) throws URISyntaxException {
        Product product = new Product();

        Date ts = new Date(Long.parseLong(image.getCaption().getCreatedTime()));
        Instant instant = ts.toInstant();
        product.setDate(LocalDateTime.ofInstant(instant, ZoneId.systemDefault()));

        product.setDescription(image.getCaption().getText().replace(url.toString(), "").trim());
        product.setImageUrl(image.getLink());
        product.setTags(new HashSet<>(image.getTags()));
        product.setCategories(new HashSet<>());
        product.setAdditionalFields(new HashSet<>());

        Price price = new Price(1, USD);
        product.setPrice(price);

        List<NameValuePair> params = URLEncodedUtils.parse(url.toURI(), "UTF-8");
        AtomicInteger count = new AtomicInteger(1);

        params.forEach(param -> {
            if (param.getName().equals("category")) {
                String[] categories = param.getValue().split(",");
                for (String categoryName : categories) {
                    Category category = new Category();
                    category.setName(categoryName);
                    category.setProduct(product);
                    product.getCategories().add(category);
                }
            } else if (param.getName().equals("price")) {
                price.setPrice(Double.parseDouble(param.getValue()));
            } else if (param.getName().equals("currency")) {
                try {
                    price.setCurrency(Price.Currency.valueOf(param.getValue().toUpperCase()));
                } catch (IllegalArgumentException e) {
                    LOGGER.warn("Unknown currency " + param.getValue());
                }
            } else if (param.getName().equals("count")) {
                count.set(Integer.parseInt(param.getValue()));
            } else {
                product.getAdditionalFields().add(new AdditionalField(param.getName(), param.getValue()));
            }
        });

        if(product.getCategories().isEmpty()) {
            Category category = new Category();
            category.setProduct(product);
            category.setName("other");
            product.getCategories().add(category);
        }

        return generate(product, count.get());
    }

    private static List<Product> generate(Product product, int n) {
        List<Product> generatedList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            try {
                generatedList.add(product.clone());
            } catch (CloneNotSupportedException e) {
                LOGGER.warn("Can't clone product");
            }
        }

        return generatedList;
    }
}
