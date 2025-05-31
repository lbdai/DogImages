package com.java.assessment.love.dogs.rest;

import com.java.assessment.love.dogs.exception.CommonException;
import java.io.IOException;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class RestemplateServiceImpl {
  private final RestTemplate restTemplate;

  @Autowired
  public RestemplateServiceImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public <T> T get(String baseUrl, Map<String, Object> queryParams, Class<T> responseType) {
    String url = buildUrl(baseUrl, queryParams);
    return restTemplate.getForObject(url, responseType);
  }

  public <T, R> R post(String url, T requestBody, Class<R> responseType) {
    return restTemplate.postForObject(url, requestBody, responseType);
  }

  public ResponseEntity<Void> postFile(
      String url, MultipartFile file, Map<String, Object> queryParams) throws IOException {
    try {
      HttpHeaders headers = new HttpHeaders();

      headers.setContentType(MediaType.MULTIPART_FORM_DATA);

      MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
      if (queryParams != null) {
        queryParams.forEach(
            (key, value) -> {
              if (value != null) {
                body.add(key, value);
              }
            });
      }

      ByteArrayResource fileAsResource =
          new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
              return file.getOriginalFilename() != null
                  ? file.getOriginalFilename()
                  : "upload.png"; // important to set filename!
            }
          };
      body.add("file", fileAsResource);

      HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
      return restTemplate.exchange(
          url, HttpMethod.POST, requestEntity, Void.class // No response body expected
          );

    } catch (Exception e) {
      throw new CommonException(e);
    }
  }

  public void delete(String baseUrl, Map<String, Object> queryParams) {
    String url = buildUrl(baseUrl, queryParams);
    restTemplate.delete(url);
  }

  private String buildUrl(String baseUrl, Map<String, Object> queryParams) {
    if (queryParams == null || queryParams.isEmpty()) return baseUrl;

    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl);
    queryParams.forEach(
        (key, value) -> {
          if (value != null) {
            builder.queryParam(key, value);
          }
        });
    return builder.toUriString();
  }
}
