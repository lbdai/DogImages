package com.java.assessment.love.dogs.rest.responseModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class DogImage implements Serializable {
    private String id;
    private String url;
    private int width;
    private int height;
    private int pending;
    private int approved;
    @JsonProperty("original_filename")
    private String originFileName;

    @JsonProperty("sub_id")
    private String subId;
    private List<Breed> breeds;
}
