package com.github.starter.controller;

import com.github.starter.controller.dto.ProductRequestDto;
import com.github.starter.controller.dto.ProductResponseDto;
import com.github.starter.domain.Product;
import com.github.starter.service.ProductService;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @GetMapping(value = "/{id}")
  public ProductResponseDto getById(@PathVariable @NotNull Long id) {
    final Product product = productService.getById(id);
    return new ProductResponseDto(product);
  }

  @PostMapping
  public ProductResponseDto save(@RequestBody @Valid ProductRequestDto request) {
    final Product product = Product.builder().name(request.getName()).build();
    return new ProductResponseDto(productService.save(product));
  }

  @PutMapping("/{id}")
  public ProductResponseDto update(@PathVariable @NotNull Long id,
      @RequestBody @Valid ProductRequestDto request) {
    final Product product = Product.builder().id(id).name(request.getName()).build();
    return new ProductResponseDto(productService.update(product));
  }

}
