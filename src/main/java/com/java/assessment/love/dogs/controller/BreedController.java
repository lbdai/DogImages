package com.java.assessment.love.dogs.controller;

import com.java.assessment.love.dogs.rest.responseModel.Breed;
import com.java.assessment.love.dogs.service.IBreedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.version}/breed")
@Tag(name = "Breed API", description = "Manage breeds")
public class BreedController {
    private final IBreedService breedService;

    @Autowired
    public BreedController(IBreedService breedService) {
        this.breedService = breedService;
    }

    @Operation(summary = "get all breeds")
    @ApiResponse(responseCode = "200", description = "retrieve all breeds")
    @GetMapping("/getAll")
    public ResponseEntity<List<Breed>> getAllBreeds() {
        List<Breed> breeds = breedService.getAllBreeds();
        return ResponseEntity.ok(breeds);
    }

    @Operation(summary = "get breed by id")
    @ApiResponse(responseCode = "200", description = "retrieve detail of breed")
    @GetMapping("/{breed-id}")
    public ResponseEntity<Breed> getBreedById(@PathVariable(name ="breed-id") String breedId) {
        Breed breed = breedService.getBreedById(breedId);
        return ResponseEntity.ok(breed);
    }
}
