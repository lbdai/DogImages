package com.java.assessment.love.dogs.rest.responseModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.io.Serializable;

@Data
public class Breed implements Serializable {
    private Weight weight;
    private String id;
    private String name;
    private String temperament;
    private String origin;
    @JsonProperty("country_codes")
    private String country_codes;

    @JsonProperty("country_code")
    private String country_code;

    @JsonProperty("life_span")
    private String lifeSpan;

    @JsonProperty("wikipedia_url")
    private String wikipediaUrl;
}
