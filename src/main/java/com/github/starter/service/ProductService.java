package com.github.starter.service;

import static com.github.starter.domain.exception.ErrorCode.PRODUCT_NOT_FOUND;

import com.github.starter.domain.Product;
import com.github.starter.domain.exception.StarterException;
import com.github.starter.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  @Transactional(readOnly = true)
  public Product getById(Long id) {
    return productRepository.findById(id)
        .orElseThrow(() -> new StarterException(PRODUCT_NOT_FOUND));
  }

  @Transactional
  public Product save(Product product) {
    return productRepository.save(product);
  }
}
