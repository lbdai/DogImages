package com.java.assessment.love.dogs.exception;

public class CommonException extends RuntimeException {
  public CommonException(Exception e) {
    super(e);
  }
}
