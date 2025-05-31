package com.java.assessment.love.dogs.util;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class StringUtils {
    private StringUtils() {

    }

    public static String buildUrl(String baseUrl, Integer limit, Integer page, String order) {
        StringBuilder urlBuilder = new StringBuilder(baseUrl);
        boolean hasQuery = false;

        if (limit != null) {
            urlBuilder.append(hasQuery ? "&" : "?");
            urlBuilder.append("limit=").append(limit);
            hasQuery = true;
        }

        if (page != null) {
            urlBuilder.append(hasQuery ? "&" : "?");
            urlBuilder.append("page=").append(page);
            hasQuery = true;
        }

        if (order != null && !order.isEmpty()) {
            urlBuilder.append(hasQuery ? "&" : "?");
            urlBuilder.append("order=").append(URLEncoder.encode(order, StandardCharsets.UTF_8));
        }

        return urlBuilder.toString();
    }
}
