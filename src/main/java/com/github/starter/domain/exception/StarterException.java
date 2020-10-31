package com.github.starter.domain.exception;

import lombok.Getter;

@Getter
public class StarterException extends RuntimeException {

  private final ErrorCode errorCode;

  public StarterException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }

  public StarterException(ErrorCode errorCode, Throwable cause) {
    super(errorCode.getMessage(), cause);
    this.errorCode = errorCode;
  }

  @Override
  public String toString() {
    return "StarterException { "
        + "errorCode="
        + errorCode.getCode()
        + " , description="
        + errorCode.getMessage()
        + '}';
  }
}
