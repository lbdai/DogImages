package com.java.assessment.love.dogs.rest;

import com.java.assessment.love.dogs.config.ResTemplateConfig;
import com.java.assessment.love.dogs.exception.CommonException;
import java.io.IOException;
import java.util.Map;

import com.java.assessment.love.dogs.rest.responseModel.DogImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
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
  @Qualifier("restTemplate")
  private final RestTemplate restTemplate;

  @Autowired
  @Qualifier("fileRestTemplate")
  private RestTemplate fileRestTemplate;

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

  public <T, R> R  postFile(
      String url, MultipartFile file, Map<String, Object> queryParams, Class<R> responseType) throws IOException {
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

        Resource fileAsResource = new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename(); // provide filename explicitly
            }
        };

      HttpHeaders fileHeaders = new HttpHeaders();
      fileHeaders.setContentDispositionFormData("file", file.getOriginalFilename());
      HttpEntity<Resource> filePart = new HttpEntity<>(fileAsResource, fileHeaders);
      body.add("file", filePart);

      HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
      return fileRestTemplate.exchange(
          url, HttpMethod.POST, requestEntity, responseType ).getBody();

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
