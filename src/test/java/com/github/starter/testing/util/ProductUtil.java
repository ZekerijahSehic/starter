package com.github.starter.testing.util;

import com.github.starter.domain.Product;
import com.github.starter.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductUtil {

  @Autowired
  private ProductRepository productRepository;

  public Product createProduct() {
    return productRepository.save(Product.builder().name("test").build());
  }

  public Product generateProduct() {
    return Product.builder()
            .name("name")
            .build();
  }

}
