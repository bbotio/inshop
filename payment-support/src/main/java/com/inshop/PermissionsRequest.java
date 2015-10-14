package com.inshop;

import com.paypal.svcs.services.PermissionsService;
import com.paypal.svcs.types.perm.GetAccessTokenRequest;
import com.paypal.svcs.types.perm.GetAccessTokenResponse;
import com.paypal.svcs.types.perm.RequestPermissionsRequest;
import com.paypal.svcs.types.perm.RequestPermissionsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by savetisyan on 10/10/15.
 */
public class PermissionsRequest {
    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionsRequest.class);
    private String callback;
    private Properties sdkProperties;

    public PermissionsRequest(String callback, Properties sdkProperties) {
        this.callback = callback;
        this.sdkProperties = sdkProperties;
    }

    public RequestPermissionsResponse requestPermissions() {
        List<String> scopeList = Arrays.asList("EXPRESS_CHECKOUT");

        RequestPermissionsRequest requestPermissionsRequest = new RequestPermissionsRequest(scopeList, callback);
        PermissionsService service = new PermissionsService(sdkProperties);
        RequestPermissionsResponse requestPermissionsResponse;
        try {
            requestPermissionsResponse = service.requestPermissions(requestPermissionsRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return requestPermissionsResponse;
    }

    public GetAccessTokenResponse getAccessToken(String token, String verifier) {
        GetAccessTokenRequest getAccessTokenRequest = new GetAccessTokenRequest();
        getAccessTokenRequest.setToken(token);
        getAccessTokenRequest.setVerifier(verifier);

        PermissionsService service = new PermissionsService(sdkProperties);
        GetAccessTokenResponse getAccessTokenResponse;
        try {
            getAccessTokenResponse = service.getAccessToken(getAccessTokenRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return getAccessTokenResponse;
    }
}
