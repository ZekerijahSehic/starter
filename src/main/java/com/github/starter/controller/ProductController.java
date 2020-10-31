package com.github.starter.controller;

import com.github.starter.controller.dto.ProductRequestDto;
import com.github.starter.controller.dto.ProductResponseDto;
import com.github.starter.domain.Product;
import com.github.starter.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @GetMapping("/{id}")
  public ProductResponseDto getById(@PathVariable @NotNull Long id) {
    final Product product = productService.getById(id);
    return new ProductResponseDto(product);
  }

  @PostMapping
  public ProductResponseDto save(@RequestBody @Valid ProductRequestDto request) {
    final Product product = Product.builder().name(request.getName()).build();
    return new ProductResponseDto(productService.save(product));
  }

}
