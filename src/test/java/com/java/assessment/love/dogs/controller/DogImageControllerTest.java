package com.java.assessment.love.dogs.controller;

import com.java.assessment.love.dogs.rest.responseModel.DogImage;
import com.java.assessment.love.dogs.service.DogImageServiceImpl;
import com.java.assessment.love.dogs.utils.FileMockUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DogImageControllerTest {

    @InjectMocks
    DogImageController dogImageController;

    @Mock
    DogImageServiceImpl dogImageService;

    @Test
    void getImagesTest() {
        List<DogImage> dogImages = new ArrayList<>();
        DogImage dogImage = new DogImage();
        dogImage.setId("1");
        dogImages.add(dogImage);
        when(dogImageService.getImages(null, null, null)).thenReturn(dogImages);
        Assertions.assertEquals(HttpStatus.OK, dogImageController.getImages(null, null, null).getStatusCode() );
    }

    @Test
    void uploadImageTest() throws URISyntaxException, IOException {
        MockMultipartFile file = FileMockUtils.getMockMultipartFile("/mock/dog_mock.jpg", "dog_mock.jpg");
        Assertions.assertEquals(HttpStatus.OK, dogImageController.uploadImage(file, "264", "1").getStatusCode() );

    }

    @Test
    void deleteImageTest() {
        Assertions.assertEquals(HttpStatus.OK, dogImageController.deleteImage(any(), any()).getStatusCode() );
    }

}
