package com.java.assessment.love.dogs.util;

import java.util.Map;

public class StringUtils {
  private StringUtils() {}

  public static String buildUrl(String baseUrl, Map<String, Object> queryParams) {
    StringBuilder urlBuilder = new StringBuilder(baseUrl);
    final boolean[] hasQuery = {false};

    if (queryParams != null) {
      queryParams.forEach(
          (key, value) -> {
            if (value != null) {
              urlBuilder.append(hasQuery[0] ? "&" : "?");
              urlBuilder.append(key).append("=").append(value);
              hasQuery[0] = true;
            }
          });
    }
    return urlBuilder.toString();
  }
}
