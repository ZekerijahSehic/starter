package com.github.starter.service;

import static com.github.starter.domain.exception.ErrorCode.PRODUCT_NOT_FOUND;

import com.github.starter.domain.Product;
import com.github.starter.domain.exception.StarterException;
import com.github.starter.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  @Transactional
  public Product save(Product product) {
    return productRepository.save(product);
  }

  @Transactional
  public Product update(Product update) {
    final Product product = getById(update.getId());
    product.setName(update.getName());
    return productRepository.save(product);
  }

  public Product getById(Long id) {
    return productRepository.findById(id)
        .orElseThrow(() -> new StarterException(PRODUCT_NOT_FOUND));
  }
}
