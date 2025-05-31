package com.java.assessment.love.dogs.controller;

import com.java.assessment.love.dogs.rest.responseModel.DogImage;
import com.java.assessment.love.dogs.service.IDogImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("${api.version}/images")
@Tag(name = "Dog Image API", description = "Manage Dog images")
public class DogImageController {

  private final IDogImageService dogImageService;

  @Autowired
  public DogImageController(IDogImageService dogImageService) {
    this.dogImageService = dogImageService;
  }

  @Operation(summary = "get all previous uploaded images")
  @ApiResponse(responseCode = "200", description = "get all previous uploaded images")
  @GetMapping(value = "/")
  public ResponseEntity<List<DogImage>> getImages(
      @RequestParam(required = false) Integer page,
      @RequestParam(required = false) Integer limit,
      @RequestParam(required = false) String order) {
    List<DogImage> dogImages = dogImageService.getImages(page, limit, order);
    return ResponseEntity.ok(dogImages);
  }

  @Operation(summary = "upload image")
  @ApiResponse(responseCode = "200", description = "return true if upload image successfully")
  @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<Boolean> uploadImage(
      @RequestParam("file") MultipartFile file,
      @RequestParam(value = "breed-id", required = false) String breedId,
      @RequestParam(value = "sub-id", required = false) String subId)
      throws IOException {
    dogImageService.uploadImage(file, breedId, subId);
    return ResponseEntity.ok(true);
  }

  @Operation(summary = "delete image")
  @ApiResponse(responseCode = "200", description = "delete an image")
  @DeleteMapping(value = "/delete/{image-id}/breeds/{breed-id}")
  public ResponseEntity<Boolean> deleteImage(
      @PathVariable(value = "image-id") String imageId,
      @PathVariable(value = "breed-id") String breedId) {
    dogImageService.deleteImagesBreedId(imageId, breedId);
    return ResponseEntity.ok(true);
  }

  @Operation(summary = "get image view")
  @ApiResponse(responseCode = "200", description = "response a String base64 as image")
  @GetMapping(value = "/download/{image-id}")
  public ResponseEntity<String> downloadImage(@PathVariable(value = "image-id") String imageId) {
    String res = dogImageService.getImageById(imageId);
    return ResponseEntity.ok(res);
  }

  @Operation(summary = "get random number of images")
  @ApiResponse(responseCode = "200", description = "response a List of random Images")
  @GetMapping(value = "/random-images/{counter}")
  public ResponseEntity<List<DogImage>> getRandomImages(
      @PathVariable(value = "counter") Integer counter) {
    List<DogImage> res = dogImageService.getRandomImages(counter);
    return ResponseEntity.ok(res);
  }
}
