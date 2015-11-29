package com.inshop;

import com.inshop.entity.Customer;
import com.inshop.entity.Price;
import com.inshop.entity.Product;
import org.springframework.stereotype.Component;
import urn.ebay.apis.CoreComponentTypes.BasicAmountType;
import urn.ebay.apis.eBLBaseComponents.AddressType;
import urn.ebay.apis.eBLBaseComponents.CountryCodeType;
import urn.ebay.apis.eBLBaseComponents.CurrencyCodeType;
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsItemType;

/**
 * Created by savetisyan on 19/11/15
 */
@Component
public class Converter {
    public PaymentDetailsItemType convertProduct(Product product) {
        PaymentDetailsItemType result = new PaymentDetailsItemType();
        result.setAmount(convertAmount(product.getPrice()));
        result.setDescription(product.getDescription());
        result.setItemURL(product.getImageUrl());
        result.setName(product.getName());
        result.setQuantity(product.getQuantity());
        return result;
    }

    public BasicAmountType convertAmount(Price price) {
        BasicAmountType amount = new BasicAmountType();
        amount.setCurrencyID(CurrencyCodeType.fromValue(price.getCurrency().name()));
        amount.setValue(String.valueOf(price.getPrice()));
        return amount;
    }

    public AddressType convertAddress(Customer customer) {

        AddressType shipToAddress = new AddressType();
//        shipToAddress.setCountryName(customer.getAddress().getCountry());
        shipToAddress.setCountry(CountryCodeType.US);
        if(customer.getAddress() != null) {
            shipToAddress.setStateOrProvince(customer.getAddress().getStateOrProvince());
            shipToAddress.setCityName(customer.getAddress().getCity());
            shipToAddress.setStreet1(customer.getAddress().getAddress1());
            shipToAddress.setStreet2(customer.getAddress().getAddress2());
            shipToAddress.setPostalCode(customer.getAddress().getZip());
        }

        if(!customer.getFirstName().isEmpty() && !customer.getLastName().isEmpty()) {
            shipToAddress.setName(customer.getFirstName() + " " + customer.getLastName());
        }
        shipToAddress.setPhone(customer.getPhone());
        return shipToAddress;
    }

}
