package com.java.assessment.love.dogs.advise;

import com.java.assessment.love.dogs.exception.CommonException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CommonExceptionAdvise extends ResponseEntityExceptionHandler {

  @ExceptionHandler(CommonException.class)
  public ResponseEntity<String> commonExceptionHandler(CommonException exception) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
  }
}
