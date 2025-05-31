package com.java.assessment.love.dogs.service;

import com.java.assessment.love.dogs.rest.responseModel.DogImage;
import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface IDogImageService {

  void uploadImage(MultipartFile file, String breedId, String subId) throws IOException;

  void deleteImagesBreedId(String imageId, String breedId);

  List<DogImage> getImages(Integer page, Integer limit, String order);

  List<DogImage> getRandomImages(Integer counter);

  String getImageById(String id);
}
