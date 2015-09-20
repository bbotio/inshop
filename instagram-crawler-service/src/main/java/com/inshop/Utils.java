package com.inshop;

import com.inshop.entity.AdditionalField;
import com.inshop.entity.Category;
import com.inshop.entity.Price;
import com.inshop.entity.Price.Currency;
import com.inshop.entity.Product;
import org.jinstagram.entity.users.feed.MediaFeedData;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static com.inshop.entity.Price.Currency.USD;


/**
 * Created by Avetisyan Sevak
 * Date: 20.09.2015
 * Time: 23:28
 */
public class Utils {
    public static Map<String, String> parse(URL url) {
        String[] params = url.getQuery().split("&");
        Map<String, String> query = new HashMap<>();

        for (String param : params) {
            int i = param.indexOf("=");
            query.put(param.substring(0, i), param.substring(i + 1));
        }

        return query;
    }

    public static Product buildProduct(MediaFeedData image, URL url) {
        Product product = new Product();

        Date ts = new Date(Long.parseLong(image.getCaption().getCreatedTime()));
        Instant instant = ts.toInstant();
        product.setDate(LocalDateTime.ofInstant(instant, ZoneId.systemDefault()));

        product.setDescription(image.getCaption().getText());
        product.setImageUrl(image.getLink());
        product.setTags(new HashSet<>(image.getTags()));

        Map<String, String> params = parse(url);

        if (params.containsKey("category")) {
            String[] categories = params.get("category").split(",");
            product.setCategories(new HashSet<>());

            for (String categoryName : categories) {
                Category category = new Category();
                category.setName(categoryName);
                category.setProduct(product);
                product.getCategories().add(category);
            }
            params.remove("category");
        } else {
            Category category = new Category();
            category.setProduct(product);
            category.setName("other");
            product.getCategories().add(category);
        }

        if (params.containsKey("price")) {
            double price = Double.parseDouble(params.get("price"));

            Currency currency = USD;
            if (params.containsKey("currency")) {
                currency = Currency.valueOf(params.get("currency"));
                params.remove("currency");
            }

            product.setPrice(new Price(price, currency));
            params.remove("price");
        } else {
            product.setPrice(new Price(1, USD));
        }

        if (params.containsKey("count")) {
            //todo: store multiple products in product packages
        } else {
            // count = 1
        }

        product.setAdditionalFields(new HashSet<>());
        params.forEach((key, value) -> {
            AdditionalField additionalField = new AdditionalField();
            additionalField.setName(key);
            additionalField.setValue(value);

            product.getAdditionalFields().add(additionalField);
        });

        return product;
    }
}
