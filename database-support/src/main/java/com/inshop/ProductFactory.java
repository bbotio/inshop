package com.inshop;

import com.inshop.entity.AdditionalField;
import com.inshop.entity.Category;
import com.inshop.entity.Price;
import com.inshop.entity.Product;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.http.NameValuePair;
import org.jinstagram.entity.users.feed.MediaFeedData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.inshop.entity.Price.Currency.USD;
import static java.net.URLEncoder.encode;
import static java.nio.charset.Charset.forName;
import static org.apache.http.client.utils.URLEncodedUtils.parse;

/**
 * Created by savetisyan on 25/09/15
 */
public class ProductFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductFactory.class);

    public static Pair<Product, Integer> buildProducts(MediaFeedData image, String url) {
        Product product = new Product();

        Date ts = new Date(Long.parseLong(image.getCaption().getCreatedTime()));
        Instant instant = ts.toInstant();
        product.setDate(LocalDateTime.ofInstant(instant, ZoneId.systemDefault()));

        product.setDescription(image.getCaption().getText().replace(url, "").trim());
        product.setImageUrl(image.getImages().getStandardResolution().getImageUrl());
        product.setTags(new HashSet<>(image.getTags()));
        product.setCategories(new HashSet<>());
        product.setAdditionalFields(new HashSet<>());

        Price price = new Price(1, USD);
        product.setPrice(price);

        // TODO: avoid multiple replace
        // maybe, it would be better to get query params manually and not to encode/decode
        // string and replace escaped substrings to unescaped equivalents

        String query = url.substring(url.indexOf("?") + 1);
        try {
            query = encode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.warn("Unsupported encoding (UTF-8), ");
            throw new RuntimeException(e);
        }
        query = query.replace("%3D", "=").replace("%26", "&");

        List<NameValuePair> params = parse(query, forName("UTF-8"));
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
            } else if (param.getName().equals("name")) {
                product.setName(param.getValue());
            } else {
                product.getAdditionalFields().add(new AdditionalField(param.getName(), param.getValue()));
            }
        });

        if (product.getCategories().isEmpty()) {
            Category category = new Category();
            category.setProduct(product);
            category.setName("other");
            product.getCategories().add(category);
        }

        return Pair.of(product, count.get());
    }
}