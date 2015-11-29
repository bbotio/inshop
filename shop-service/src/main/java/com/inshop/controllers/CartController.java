package com.inshop.controllers;

import com.inshop.Converter;
import com.inshop.ExpressCheckoutRequest;
import com.inshop.PayPalFactory;
import com.inshop.entity.*;
import com.paypal.core.credential.SignatureCredential;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentResponseType;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutResponseType;
import urn.ebay.apis.CoreComponentTypes.BasicAmountType;
import urn.ebay.apis.eBLBaseComponents.AddressType;
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsItemType;
import urn.ebay.apis.eBLBaseComponents.SellerDetailsType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static urn.ebay.apis.eBLBaseComponents.AckCodeType.SUCCESS;

/**
 * Created by savetisyan on 15/11/15
 */
@Controller
@SessionAttributes({"shop"})
public class CartController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CartController.class);

    private static final String FIRST_NAME = "inputFname";
    private static final String LAST_NAME = "inputLname";
    private static final String EMAIL = "inputEmail";
    private static final String PHONE = "inputPhone";
    private static final String ADDRESS_1 = "inputAddress1";
    private static final String ADDRESS_2 = "inputAddress2";
    private static final String CITY = "inputCity";
    private static final String POSTAL_CODE = "inputPostCode";
    private static final String COUNTRY = "inputCountry";
    private static final String REGION = "inputRegion";

    private static final String ATTRIBUTE_PRODUCTS = "products";
    private static final String ATTRIBUTE_TOTAL = "total";
    private static final String ATTRIBUTE_EXPRESS_CHECKOUT = "express_checkout";
    private static final String ATTRIBUTE_CUSTOMER = "customer";

    private static final String EXPRESS_CHECKOUT_OK = "/cart/checkout/ok";
    private static final String EXPRESS_CHECKOUT_CANCEL = "/cart/checkout/cancel";

    private static final String PAYMENT_COMPLETE = "checkoutOkNotification";
    private static final String PAYMENT_CANCEL = "checkoutCancelNotification";
    private static final String EMPTY_CART = "cartEmptyNotification";
    private static final String NOT_FILLED_CUSTOMER_INFO = "customerInfoNotification";

    @Autowired
    private PayPalFactory payPalFactory;

    @Autowired
    private Converter converter;

    @RequestMapping(value = "/cart", method = GET)
    public String cart(@ModelAttribute final Shop shop, ModelMap params, final HttpSession session) {
        List<Product> products = (List<Product>) session.getAttribute(ATTRIBUTE_PRODUCTS);
        if(products == null) {
            products = new ArrayList<>();
        }

        double total = 0.0;
        Price.Currency currency = null;

        for (Product product : products) {
            total += product.getQuantity() * product.getPrice().getPrice();

            if (currency == null) {
                currency = product.getPrice().getCurrency();
            } else {
                if (product.getPrice().getCurrency() != currency) {
                    throw new RuntimeException("Multi currency payments are not supported!");
                }
            }
        }

        if(currency == null) {
            currency = Price.Currency.USD;
        }

        Price totalPrice = new Price(total, currency);
        params.addAttribute(ATTRIBUTE_PRODUCTS, products);
        params.addAttribute(ATTRIBUTE_TOTAL, totalPrice);

        session.setAttribute(ATTRIBUTE_TOTAL, totalPrice);

        return shop.getTheme().getName() + "/cart";
    }

    @RequestMapping(value = "/cart/checkout", method = GET)
    public String checkout(@ModelAttribute final Shop shop,
                           final HttpSession session,
                           ModelMap params) {

        if (session.getAttribute(ATTRIBUTE_PRODUCTS) == null) {
            params.addAttribute(EMPTY_CART, true);
            return shop.getTheme().getName() + "/cart";
        }

        Token accessToken = shop.getOwner().getPaypalToken();
        SignatureCredential cred = payPalFactory.signatureCredential(accessToken);

        Customer customer = (Customer) session.getAttribute(ATTRIBUTE_CUSTOMER);
        if (customer == null) {
            params.addAttribute(NOT_FILLED_CUSTOMER_INFO, true);
            return shop.getTheme().getName() + "/cart";
        }

        AddressType shipToAddress = converter.convertAddress(customer);

        SellerDetailsType sellerDetails = new SellerDetailsType();
        sellerDetails.setPayPalAccountID(shop.getOwner().getEmail());

        List<Product> products = (List<Product>) session.getAttribute(ATTRIBUTE_PRODUCTS);
        Price orderTotal = (Price) session.getAttribute(ATTRIBUTE_TOTAL);

        List<PaymentDetailsItemType> detailsItemTypes =
                products.stream().map(converter::convertProduct).collect(Collectors.toList());

        BasicAmountType orderTotalAmount = converter.convertAmount(orderTotal);
        ExpressCheckoutRequest expressCheckoutRequest =
                payPalFactory.getExpressCheckoutRequest(detailsItemTypes,
                        sellerDetails, shipToAddress,
                        orderTotalAmount, cred);
        session.setAttribute(ATTRIBUTE_EXPRESS_CHECKOUT, expressCheckoutRequest);

        SetExpressCheckoutResponseType response = expressCheckoutRequest.setExpressCheckout(
                shop.getDomain() + EXPRESS_CHECKOUT_OK,
                shop.getDomain() + EXPRESS_CHECKOUT_CANCEL);

        return "redirect:" + payPalFactory.getExpressCheckoutUrl(response.getToken());
    }

    @RequestMapping(value = EXPRESS_CHECKOUT_OK, method = GET)
    public String checkoutOk(@ModelAttribute final Shop shop,
                             HttpSession session,
                             ModelMap params,
                             @RequestParam("token") String token,
                             @RequestParam("PayerID") String payerId) {
        ExpressCheckoutRequest expressCheckout = (ExpressCheckoutRequest) session.getAttribute(ATTRIBUTE_EXPRESS_CHECKOUT);
        if (expressCheckout != null) {
            /**
             * TODO: use express_checkout_details for showing user final checkout page (optional)
             * important: do_express_checkout commits transaction
             */
            //GetExpressCheckoutDetailsResponseType expressCheckoutDetails = expressCheckout.getExpressCheckoutDetails(token);
            DoExpressCheckoutPaymentResponseType doExpressCheckout = expressCheckout.doExpressCheckout(token, payerId);

            if (doExpressCheckout.getAck() == SUCCESS) {
                session.removeAttribute(ATTRIBUTE_PRODUCTS);
                session.removeAttribute(ATTRIBUTE_TOTAL);

                params.addAttribute(PAYMENT_COMPLETE, true);
            } else {
                /**
                 * TODO: what we should write to log?
                 * see available fields in doExpressCheckout.getDoExpressCheckoutPaymentResponseDetails()
                 */

                LOGGER.warn("DoExpressCheckout call failed");
            }

            // remove express_checkout object from session anyway
            session.removeAttribute(ATTRIBUTE_EXPRESS_CHECKOUT);
        }

        return shop.getTheme().getName() + "/cart";
    }

    @RequestMapping(value = EXPRESS_CHECKOUT_CANCEL, method = GET)
    public String checkoutCancel(@ModelAttribute final Shop shop,
                                 @RequestParam("token") String token,
                                 ModelMap params, HttpSession session) {

        params.addAttribute(PAYMENT_CANCEL, true);
        // do nothing if customer cancelled the order
        return shop.getTheme().getName() + "/cart";
    }

    @RequestMapping(value = "/cart/saveCustomer", method = POST)
    public String saveCustomerInfo(@ModelAttribute final Shop shop,
                                   final HttpServletRequest request,
                                   final HttpSession session) {
        Customer customer = (Customer) session.getAttribute(ATTRIBUTE_CUSTOMER);
        if (customer == null) {
            customer = new Customer();
        }

        final Map<String, String[]> params = request.getParameterMap();
        if (params.containsKey(FIRST_NAME)) {
            customer.setFirstName(params.get(FIRST_NAME)[0]);
        }

        if (params.containsKey(LAST_NAME)) {
            customer.setLastName(params.get(LAST_NAME)[0]);
        }

        if (params.containsKey(EMAIL)) {
            String email = params.get(EMAIL)[0];

            if (EmailValidator.getInstance().isValid(email)) {
                customer.setEmail(email);
            }
        }

        if (params.containsKey(PHONE)) {
            customer.setPhone(params.get(PHONE)[0]);
        }

        if (customer.getAddress() == null) {
            customer.setAddress(new Address());
        }

        if (params.containsKey(ADDRESS_1)) {
            customer.getAddress().setAddress1(params.get(ADDRESS_1)[0]);
        }

        if (params.containsKey(ADDRESS_2)) {
            customer.getAddress().setAddress2(params.get(ADDRESS_2)[0]);
        }

        if (params.containsKey(CITY)) {
            customer.getAddress().setCity(params.get(CITY)[0]);
        }

        if (params.containsKey(POSTAL_CODE)) {
            customer.getAddress().setZip(params.get(POSTAL_CODE)[0]);
        }

        if (params.containsKey(COUNTRY)) {
            customer.getAddress().setCountry(params.get(COUNTRY)[0]);
        }

        if (params.containsKey(REGION)) {
            customer.getAddress().setStateOrProvince(params.get(REGION)[0]);
        }

        session.setAttribute(ATTRIBUTE_CUSTOMER, customer);
        return "redirect:/cart";
    }
}
