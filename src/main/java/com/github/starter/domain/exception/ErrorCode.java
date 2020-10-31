package com.github.starter.domain.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
  EXCEPTION(40001, "Exception occurred", BAD_REQUEST),
  BAD_REQUEST_CODE(40002, "Bad request", BAD_REQUEST),
  PRODUCT_NOT_FOUND(40003, "Could not find product", BAD_REQUEST),
  INTERNAL_ERROR(50001, "Internal Server Error", INTERNAL_SERVER_ERROR);

  private final int code;
  private final String message;
  private final HttpStatus status;
}
