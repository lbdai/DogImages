package com.java.assessment.love.dogs.config;

import com.java.assessment.love.dogs.util.Constant;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
public class ResTemplateConfig {

  @Value("${dogs.api.key}")
  private String apiKey;

  @Bean(name = "restTemplate")
  public RestTemplate restTemplate() {

    RestTemplate restTemplate = new RestTemplate();

    List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
    interceptors.add(new HeaderRequestInterceptor(Constant.API_HEADER, apiKey));
    interceptors.add(new HeaderRequestInterceptor("Content-Type", Constant.CONTENT_TYPE_JSON));
    restTemplate.setInterceptors(interceptors);
    return restTemplate;
  }

  @Bean(name = "fileRestTemplate")
  public RestTemplate fileRestTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
    interceptors.add(new HeaderRequestInterceptor(Constant.API_HEADER, apiKey));
    restTemplate.setInterceptors(interceptors);
    return restTemplate;
  }
}
