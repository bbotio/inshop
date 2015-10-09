package com.inshop.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by akornev on 07/10/15.
 */
public class Response {

    public Status status;
    public List<String> errors = Collections.emptyList();

    public static Response ok() {
        final Response response = new Response();
        response.status = Status.OK;
        return response;
    }

    public static Response error(String... errors) {
        final Response response = new Response();
        response.status = Status.OK;

        response.errors = Arrays.asList(errors);
        return response;
    }

    enum Status {
        OK,
        ERROR
    }
}
