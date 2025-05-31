package com.java.assessment.love.dogs.service;

import com.java.assessment.love.dogs.rest.responseModel.Breed;

import java.util.List;

public interface IBreedService {


    List<Breed> getAllBreeds();

    Breed getBreedById(String breadId);


}
