package com.github.starter.controller.dto;

import com.github.starter.domain.Product;
import lombok.Getter;

@Getter
public class ProductResponseDto {

  private final Long id;
  private final String name;

  public ProductResponseDto(Product product) {
    this.id = product.getId();
    this.name = product.getName();
  }

}
