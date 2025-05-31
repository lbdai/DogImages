package com.java.assessment.love.dogs.controller;

import com.java.assessment.love.dogs.rest.responseModel.DogImage;
import com.java.assessment.love.dogs.service.IDogImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("${api.version}/images")
public class DogImageController {

    private final IDogImageService dogImageService;

    @Autowired
    public DogImageController(IDogImageService dogImageService) {
        this.dogImageService = dogImageService;
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<DogImage>> getImages(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit, @RequestParam(required = false) String order) {
        List<DogImage> dogImages = dogImageService.getImages(page, limit, order);
        return ResponseEntity.ok(dogImages);
    }

    @PostMapping(value= "/upload/bread/{bread-id}/sub/{sub-id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Boolean> uploadImage(@RequestParam("file") MultipartFile file,@PathVariable(value = "bread-id") String breedId,@PathVariable(value = "sub-id") String subId) throws IOException {
        dogImageService.uploadImage(file, breedId, subId);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping(value = "/delete/{image-id}/breeds/{breed-id}")
    public ResponseEntity<Boolean> deleteImage(@PathVariable(value = "image-id") String imageId, @PathVariable(value = "breed-id") String breedId) {
        dogImageService.deleteImagesBreedId(imageId, breedId);
        return ResponseEntity.ok(true);
    }

    @GetMapping(value = "/download/{image-id}")
    public ResponseEntity<String> downloadImage(@PathVariable(value = "image-id") String imageId) {
        String res = dogImageService.getImageById(imageId);
        return  ResponseEntity.ok(res);
    }
}
