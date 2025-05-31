package com.java.assessment.love.dogs.service;

import com.java.assessment.love.dogs.rest.RestemplateServiceImpl;
import com.java.assessment.love.dogs.rest.responseModel.DogImage;
import com.java.assessment.love.dogs.util.StringUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class DogImageServiceImpl implements IDogImageService {

  private final RestemplateServiceImpl restTemplate;

  @Value("${dogs.api.key}")
  private String apiKey;

  @Value("${dogs.api.url}")
  private String apiUrl;

  @Autowired IBreedService breedService;

  private final String IMAGE_ENDPOINT = "/images";

  @Autowired
  public DogImageServiceImpl(RestemplateServiceImpl restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public void uploadImage(MultipartFile file, String breedId, String subId) throws IOException {
    String url = apiUrl + IMAGE_ENDPOINT + "/upload";
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("breed_ids", breedId);
    body.put("sub_id", subId);
    restTemplate.postFile(url, file, body);
  }

  @Override
  public void deleteImagesBreedId(String imageId, String breedId) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("imageId", imageId);
    body.put("breedId", breedId);
    String url = apiUrl + IMAGE_ENDPOINT + "/{imageId}/breeds/{breedId}";
    restTemplate.delete(url, body);
  }

  @Override
  public List<DogImage> getImages(Integer page, Integer limit, String order) {
    Map<String, Object> params = new LinkedHashMap<>();
    params.put("limit", limit);
    params.put("page", page);
    params.put("order", order);
    String url = StringUtils.buildUrl(apiUrl + IMAGE_ENDPOINT, params);
    DogImage[] response = restTemplate.get(url, null, DogImage[].class);
    return Arrays.asList(response);
  }

  @Override
  public List<DogImage> getRandomImages(Integer counter) {
    String url = apiUrl + IMAGE_ENDPOINT + "/search?limit=" + counter;
    DogImage[] response = restTemplate.get(url, null, DogImage[].class);
    if (response == null || response.length < 1) {
      return new ArrayList<>();
    }
    return Arrays.asList(response);
  }

  @Override
  public String getImageById(String imageId) {
    String url = apiUrl + IMAGE_ENDPOINT + "/" + imageId;
    DogImage dogImage = restTemplate.get(url, null, DogImage.class);
    byte[] bytes = restTemplate.get(dogImage.getUrl(), null, byte[].class);
    return Base64.getEncoder().encodeToString(bytes);
  }
}
