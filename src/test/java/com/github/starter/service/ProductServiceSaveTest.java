package com.github.starter.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.starter.domain.Product;
import com.github.starter.testing.util.ProductUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ProductServiceSaveTest extends IntegrationTest {

    @Autowired
    private ProductUtil productUtil;
    @Autowired
    private ProductService productService;

    @Test
    void whenValidProduct_thenSaveProduct() {
        // Given
        final Product product = productUtil.generateProduct();

        // When
        final Product result = productService.save(product);

        // Then
        assertThat(result).isNotNull();
    }

}
