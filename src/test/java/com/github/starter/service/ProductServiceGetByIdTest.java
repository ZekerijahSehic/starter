package com.github.starter.service;

import static com.github.starter.domain.exception.ErrorCode.PRODUCT_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.starter.domain.Product;
import com.github.starter.domain.exception.StarterException;
import com.github.starter.testing.util.ProductUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

class ProductServiceGetByIdTest extends IntegrationTest {

  @Autowired
  private ProductUtil productUtil;
  @Autowired
  private ProductService productService;

  @Test
  void whenProductExists_thenReturn() {
    // Given
    final Product product = productUtil.createProduct();

    // When
    final Product result = productService.getById(product.getId());

    // Then
    assertThat(result)
        .isNotNull();
    assertThat(result.getId())
        .isEqualTo(product.getId());
  }

  @Test
  void whenProductDoesNotExists_thenThrowException() {
    // When
    final StarterException exception = assertThrows(StarterException.class,
        () -> productService.getById(250L));

    // Then
    assertThat(exception.getErrorCode()).isEqualTo(PRODUCT_NOT_FOUND);
  }

}
