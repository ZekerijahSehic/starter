package com.github.starter.testing.util;

import com.github.starter.domain.Product;
import com.github.starter.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductUtil {

  private final ProductRepository productRepository;

  public Product createProduct() {
    return productRepository.save(generateProduct());
  }

  public Product generateProduct() {
    return Product.builder()
            .name("name")
            .build();
  }
}
