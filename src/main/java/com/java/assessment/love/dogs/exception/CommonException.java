package com.java.assessment.love.dogs.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonException extends RuntimeException {
  public CommonException(Exception e) {
    super(e);
    log.error("error upload", e);
  }
}
