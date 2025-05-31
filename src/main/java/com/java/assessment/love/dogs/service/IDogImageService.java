package com.java.assessment.love.dogs.service;


import com.java.assessment.love.dogs.rest.responseModel.DogImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IDogImageService {

    void uploadImage(MultipartFile file, String breedId, String subId) throws IOException;

    void deleteImagesBreedId(String imageId, String breedId);

    List<DogImage> getImages(Integer page, Integer limit, String order);

    String getImageById(String id);
}
