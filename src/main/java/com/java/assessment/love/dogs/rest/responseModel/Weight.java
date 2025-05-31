package com.java.assessment.love.dogs.rest.responseModel;

import java.io.Serializable;
import lombok.Data;

@Data
public class Weight implements Serializable {
  private String imperial;
  private String metric;
}
