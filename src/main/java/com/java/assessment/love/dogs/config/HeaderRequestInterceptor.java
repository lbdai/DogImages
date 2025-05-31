package com.java.assessment.love.dogs.config;

import java.io.IOException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class HeaderRequestInterceptor implements ClientHttpRequestInterceptor {
  private final String header;
  private final String value;

  public HeaderRequestInterceptor(String header, String value) {
    this.header = header;
    this.value = value;
  }

  @Override
  public ClientHttpResponse intercept(
      HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
    request.getHeaders().set(header, value);
    return execution.execute(request, body);
  }
}
