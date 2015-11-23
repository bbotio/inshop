package com.inshop;

import com.paypal.core.credential.SignatureCredential;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import urn.ebay.api.PayPalAPI.*;
import urn.ebay.apis.CoreComponentTypes.BasicAmountType;
import urn.ebay.apis.eBLBaseComponents.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by savetisyan on 09/10/15.
 */
public class ExpressCheckoutRequest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExpressCheckoutRequest.class);

    private Properties sdkProperties;
    private SignatureCredential signatureCredential;
    private List<PaymentDetailsItemType> items;
    private SellerDetailsType sellerDetails;
    private AddressType shipToAddress;
    private BasicAmountType orderTotal;

    public ExpressCheckoutRequest(List<PaymentDetailsItemType> items, SellerDetailsType sellerDetails,
                                  AddressType shipToAddress, BasicAmountType orderTotal,
                                  SignatureCredential signatureCredential, Properties sdkProperties) {
        this.signatureCredential = signatureCredential;
        this.sdkProperties = sdkProperties;
        this.items = items;
        this.sellerDetails = sellerDetails;
        this.shipToAddress = shipToAddress;
        this.orderTotal = orderTotal;
    }

    public SetExpressCheckoutResponseType setExpressCheckout(String returnUrl, String cancelUrl) {
        SetExpressCheckoutRequestDetailsType expressCheckoutRequestDetails = new SetExpressCheckoutRequestDetailsType();
        expressCheckoutRequestDetails.setReturnURL(returnUrl);
        expressCheckoutRequestDetails.setCancelURL(cancelUrl);

        List<PaymentDetailsType> paymentDetails = new ArrayList<>();

        PaymentDetailsType paymentDetail = new PaymentDetailsType();
        paymentDetail.setOrderTotal(orderTotal);
        paymentDetail.setPaymentAction(PaymentActionCodeType.SALE);
        paymentDetail.setSellerDetails(sellerDetails);
        paymentDetail.setPaymentRequestID("request_id");

        paymentDetail.setPaymentDetailsItem(items);
        paymentDetail.setShipToAddress(shipToAddress);
        paymentDetails.add(paymentDetail);

        expressCheckoutRequestDetails.setPaymentDetails(paymentDetails);

        SetExpressCheckoutReq setExpressCheckoutReq = new SetExpressCheckoutReq();
        SetExpressCheckoutRequestType setExpressCheckoutRequest = new SetExpressCheckoutRequestType(expressCheckoutRequestDetails);
        setExpressCheckoutReq.setSetExpressCheckoutRequest(setExpressCheckoutRequest);

        PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(sdkProperties);
        SetExpressCheckoutResponseType setExpressCheckoutResponse;
        try {
            setExpressCheckoutResponse = service.setExpressCheckout(setExpressCheckoutReq, signatureCredential);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return setExpressCheckoutResponse;
    }

    public DoExpressCheckoutPaymentResponseType doExpressCheckout(String token, String payerId) {
        DoExpressCheckoutPaymentReq doExpressCheckoutPaymentReq = new DoExpressCheckoutPaymentReq();

        DoExpressCheckoutPaymentRequestDetailsType doExpressCheckoutPaymentRequestDetails = new DoExpressCheckoutPaymentRequestDetailsType();
        doExpressCheckoutPaymentRequestDetails.setToken(token);
        doExpressCheckoutPaymentRequestDetails.setPayerID(payerId);

        List<PaymentDetailsType> paymentDetails = new ArrayList<>();

        PaymentDetailsType paymentDetail = new PaymentDetailsType();
        paymentDetail.setOrderTotal(orderTotal);
        paymentDetail.setPaymentAction(PaymentActionCodeType.SALE);
        paymentDetail.setSellerDetails(sellerDetails);
        paymentDetail.setPaymentRequestID("request_id");

        paymentDetail.setPaymentDetailsItem(items);
        paymentDetail.setShipToAddress(shipToAddress);
        paymentDetails.add(paymentDetail);

        doExpressCheckoutPaymentRequestDetails.setPaymentDetails(paymentDetails);

        DoExpressCheckoutPaymentRequestType doExpressCheckoutPaymentRequest = new DoExpressCheckoutPaymentRequestType(doExpressCheckoutPaymentRequestDetails);
        doExpressCheckoutPaymentReq.setDoExpressCheckoutPaymentRequest(doExpressCheckoutPaymentRequest);

        PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(sdkProperties);
        DoExpressCheckoutPaymentResponseType doExpressCheckoutPaymentResponse;
        try {
            doExpressCheckoutPaymentResponse = service.doExpressCheckoutPayment(doExpressCheckoutPaymentReq, signatureCredential);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return doExpressCheckoutPaymentResponse;
    }

    public GetExpressCheckoutDetailsResponseType getExpressCheckoutDetails(String token) {
        GetExpressCheckoutDetailsReq getExpressCheckoutDetailsReq = new GetExpressCheckoutDetailsReq();
        GetExpressCheckoutDetailsRequestType getExpressCheckoutDetailsRequest = new GetExpressCheckoutDetailsRequestType(token);

        getExpressCheckoutDetailsReq.setGetExpressCheckoutDetailsRequest(getExpressCheckoutDetailsRequest);
        PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(sdkProperties);
        GetExpressCheckoutDetailsResponseType getExpressCheckoutDetailsResponse;
        try {
            getExpressCheckoutDetailsResponse =
                    service.getExpressCheckoutDetails(getExpressCheckoutDetailsReq, signatureCredential);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return getExpressCheckoutDetailsResponse;
    }


}
