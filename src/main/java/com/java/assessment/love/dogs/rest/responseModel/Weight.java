package com.java.assessment.love.dogs.rest.responseModel;

import lombok.Data;

import java.io.Serializable;

@Data
public class Weight implements Serializable {
    private String imperial;
    private String metric;
}
