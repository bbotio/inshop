package com.inshop;

import com.inshop.entity.Token;
import com.paypal.core.credential.SignatureCredential;
import com.paypal.core.credential.TokenAuthorization;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import urn.ebay.apis.CoreComponentTypes.BasicAmountType;
import urn.ebay.apis.eBLBaseComponents.AddressType;
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsItemType;
import urn.ebay.apis.eBLBaseComponents.SellerDetailsType;

import java.util.List;
import java.util.Properties;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
public class PayPalFactory {
    private Properties sdkProperties = new Properties();

    @Value("${paypal.username}")
    private String paypalUsername;

    @Value("${paypal.password}")
    private String paypalPassword;

    @Value("${paypal.signature}")
    private String paypalSignature;

    @Value("${paypal.appid}")
    private String paypalAppId;

    @Value("${service.RedirectURL}")
    private String redirectUrl;

    @Value("${service.EndPoint.PayPalAPIAA}")
    private String paypalAPIUrl;

    @Value("${service.EndPoint.Permissions}")
    private String paypalAPIPermissionsUrl;

    @Value("${paypal.handle_permissions_url:/fix/permissions/url}")
    private String handlePermissionsUrl;

    @Value("${paypal.express_checkout_url:/fix/checkout/url}")
    private String expressCheckoutUrl;

    public String getExpressCheckoutUrl(String token) {
        return expressCheckoutUrl + token;
    }

    public void init() {
        sdkProperties.setProperty("acct1.UserName", paypalUsername);
        sdkProperties.setProperty("acct1.Password", paypalPassword);
        sdkProperties.setProperty("acct1.Signature", paypalSignature);
        sdkProperties.setProperty("acct1.AppId", paypalAppId);

        sdkProperties.setProperty("service.RedirectURL", redirectUrl);
        sdkProperties.setProperty("service.EndPoint.PayPalAPIAA", paypalAPIUrl);
        sdkProperties.setProperty("service.EndPoint.Permissions", paypalAPIPermissionsUrl);
    }

    public ExpressCheckoutRequest getExpressCheckoutRequest(List<PaymentDetailsItemType> items, SellerDetailsType sellerDetails,
                                                            AddressType shipToAddress, BasicAmountType orderTotal,
                                                            SignatureCredential signatureCredential) {
        return new ExpressCheckoutRequest(items, sellerDetails, shipToAddress, orderTotal, signatureCredential, sdkProperties);
    }

    public PermissionsRequest getPermissionsRequest() {
        return new PermissionsRequest(handlePermissionsUrl, sdkProperties);
    }

    public SignatureCredential signatureCredential(Token accessToken) {
        TokenAuthorization authorization = new TokenAuthorization(accessToken.getToken(), accessToken.getSecret());
        SignatureCredential cred = new SignatureCredential(paypalUsername, paypalPassword, paypalSignature);
        cred.setApplicationId(paypalAppId);
        cred.setThirdPartyAuthorization(authorization);
        return cred;
    }
}