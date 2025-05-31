package com.java.assessment.love.dogs.service;

import static org.mockito.Mockito.when;

import com.java.assessment.love.dogs.FileMockUtils;
import com.java.assessment.love.dogs.rest.RestemplateServiceImpl;
import com.java.assessment.love.dogs.rest.responseModel.DogImage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class DogImageServiceImplTest {

  @InjectMocks DogImageServiceImpl dogImageService;

  @Mock RestemplateServiceImpl restTemplate;

  String apiKey = "demo-key";
  String apiUrl = "http://localhost-demo";
  private final String IMAGE_ENDPOINT = "/images";

  @BeforeEach
  void init() {
    ReflectionTestUtils.setField(dogImageService, "apiKey", apiKey);
    ReflectionTestUtils.setField(dogImageService, "apiUrl", apiUrl);
  }

  @Test
  void uploadImageTest() throws URISyntaxException, IOException {
    MockMultipartFile file =
        FileMockUtils.getMockMultipartFile("/mock/dog_mock.jpg", "dog_mock.jpg");

    Map<String, Object> queryParams = new LinkedHashMap<>();
    queryParams.put("breed_ids", "1");
    queryParams.put("sub_id", "1");
    String url = apiUrl + IMAGE_ENDPOINT + "/upload";
    Assertions.assertDoesNotThrow(() -> dogImageService.uploadImage(file, "1", "1"));
  }

  @Test
  void deleteImagesBreedIdTest() {
    Assertions.assertDoesNotThrow(() -> dogImageService.deleteImagesBreedId("1", "2"));
  }

  @Test
  void getImagesTest() {
    DogImage[] dogImages = new DogImage[1];
    DogImage dogImage = new DogImage();
    dogImage.setId("1");
    dogImages[0] = dogImage;
    String url = apiUrl + IMAGE_ENDPOINT;
    when(restTemplate.get(url, null, DogImage[].class)).thenReturn(dogImages);
    List<DogImage> result = dogImageService.getImages(null, null, null);
    Assertions.assertEquals("1", result.getFirst().getId());
  }

  @Test
  void getRandomImagesTest() {
    DogImage[] dogImages = new DogImage[1];
    DogImage dogImage = new DogImage();
    dogImage.setId("1");
    dogImages[0] = dogImage;
    String url = apiUrl + IMAGE_ENDPOINT + "/search?limit=" + 1;
    when(restTemplate.get(url, null, DogImage[].class)).thenReturn(dogImages);
    List<DogImage> result = dogImageService.getRandomImages(1);
    Assertions.assertEquals("1", result.getFirst().getId());
  }

  @Test
  void getImageByIdTest() {
    DogImage dogImage = new DogImage();
    dogImage.setId("1");
    dogImage.setUrl("https://cdn2.thecatapi.com/images/xxBaNrfM0.jpg");
    String url = apiUrl + IMAGE_ENDPOINT + "/1";
    when(restTemplate.get(url, null, DogImage.class)).thenReturn(dogImage);
    when(restTemplate.get(dogImage.getUrl(), null, byte[].class)).thenReturn(new byte[100]);
    Assertions.assertNotNull(dogImageService.getImageById("1"));
  }
}
