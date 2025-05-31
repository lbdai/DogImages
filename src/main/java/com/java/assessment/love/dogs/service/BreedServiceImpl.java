package com.java.assessment.love.dogs.service;

import com.java.assessment.love.dogs.rest.RestemplateServiceImpl;
import com.java.assessment.love.dogs.rest.responseModel.Breed;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;

@Service
public class BreedServiceImpl implements IBreedService {


    private final RestemplateServiceImpl restTemplate;

    @Value("${dogs.api.url}")
    private String apiUrl;

    private final String BREEDS_ENDPOINT = "/breeds";

    @Autowired
    public BreedServiceImpl(RestemplateServiceImpl restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Breed> getAllBreeds() {
        String url = apiUrl + BREEDS_ENDPOINT;
        Breed[] response = restTemplate.get(url,null, Breed[].class);
        if(ObjectUtils.isEmpty(response)) {
            return new ArrayList<>();
        }
        return Arrays.asList(response);
    }

    @Override
    public Breed getBreedById(@NonNull String breadId) {
        String url = apiUrl + BREEDS_ENDPOINT + "/" + breadId;
        Map<String, Object> params = new HashMap<>();
        params.put("breadId", breadId);
        Breed response = restTemplate.get(url,params, Breed.class);
        if(ObjectUtils.isEmpty(response)) {
            return null;
        }
        return response;
    }
}
