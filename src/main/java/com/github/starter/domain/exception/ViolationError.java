package com.github.starter.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ViolationError {
  private int code;
  private String message;
}
