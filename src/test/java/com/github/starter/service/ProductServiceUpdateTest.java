package com.github.starter.service;

import static com.github.starter.domain.exception.ErrorCode.PRODUCT_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.starter.domain.Product;
import com.github.starter.domain.exception.StarterException;
import com.github.starter.testing.util.ProductUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ProductServiceUpdateTest extends IntegrationTest {

  @Autowired private ProductUtil productUtil;

  @Autowired private ProductService productService;

  @Test
  void whenProductExists_thenUpdate() {
    // Given
    final Product existing = productUtil.createProduct();

    final Product updateExisting = Product.builder().id(existing.getId()).name("Updetan").build();

    // When
    final Product result = productService.update(updateExisting);

    // Then
    assertThat(updateExisting).isEqualTo(result);
  }

  @Test
  void whenProductDoesNotExists_thenThrowException() {
    // Given
    final Product product = Product.builder().id(123L).build();

    // When
    final StarterException exception =
        assertThrows(StarterException.class,
            () -> productService.update(product));

    // Then
    assertThat(exception.getErrorCode()).isEqualTo(PRODUCT_NOT_FOUND);
  }
}
