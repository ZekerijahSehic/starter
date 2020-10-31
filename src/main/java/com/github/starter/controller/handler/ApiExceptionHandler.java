package com.github.starter.controller.handler;

import static com.github.starter.domain.exception.ErrorCode.BAD_REQUEST_CODE;
import static com.github.starter.domain.exception.ErrorCode.INTERNAL_ERROR;
import static java.util.Collections.singletonList;

import com.github.starter.domain.exception.ApiError;
import com.github.starter.domain.exception.ErrorCode;
import com.github.starter.domain.exception.StarterException;
import com.github.starter.domain.exception.ViolationError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler({Exception.class, RuntimeException.class})
  public ResponseEntity<ApiError> handleAll(Exception ex) {
    log.error("Unexpected exception", ex);
    return createResponseEntity(ex, INTERNAL_ERROR);
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<ApiError> handleMissingServletRequestParameterException(
      MissingServletRequestParameterException ex) {
    log.error("Request param couldn't be bound", ex);
    return createResponseEntity(ex, BAD_REQUEST_CODE);
  }

  @ExceptionHandler(StarterException.class)
  protected ResponseEntity<ApiError> handleStarterException(StarterException ex) {
    log.debug("Starter exception occurred {}", ex.getMessage());
    return createResponseEntity(ex, ex.getErrorCode());
  }

  private ResponseEntity<ApiError> createResponseEntity(Exception ex, ErrorCode errorCode) {
    return new ResponseEntity<>(
        ApiError.builder()
            .code(errorCode.getCode())
            .message(errorCode.getMessage())
            .violationErrors(
                singletonList(new ViolationError(errorCode.getCode(), ex.getMessage())))
            .build(),
        new HttpHeaders(),
        errorCode.getStatus());
  }
}
