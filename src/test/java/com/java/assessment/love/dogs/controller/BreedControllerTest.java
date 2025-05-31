package com.java.assessment.love.dogs.controller;

import static org.mockito.Mockito.when;

import com.java.assessment.love.dogs.rest.responseModel.Breed;
import com.java.assessment.love.dogs.service.BreedServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class BreedControllerTest {

  @InjectMocks BreedController breedController;

  @Mock BreedServiceImpl breedService;

  @Test
  void getAllBreedsTest() {
    List<Breed> breeds = new ArrayList<>();
    Breed breed = new Breed();
    breed.setId("1");
    breeds.add(breed);

    when(breedService.getAllBreeds()).thenReturn(breeds);
    Assertions.assertEquals(HttpStatus.OK, breedController.getAllBreeds().getStatusCode());
  }

  @Test
  void getBreedByIdTest() {
    Breed breed = new Breed();
    breed.setId("1");
    when(breedService.getBreedById("1")).thenReturn(breed);
    Assertions.assertEquals(HttpStatus.OK, breedController.getBreedById("1").getStatusCode());
  }
}
