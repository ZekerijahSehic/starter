package com.github.starter.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.github.starter.controller.dto.ProductRequestDto;
import com.github.starter.domain.Product;
import com.github.starter.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;

class ProductControllerSaveTest extends WebLayerTest {

    private static final String URL = "/api/products";

    @MockBean
    private ProductService productService;

    @Test
    void whenValidRequest_thenSaveProduct() {
        // Given
        final ProductRequestDto request = new ProductRequestDto("Novi");

        when(productService.save(any(Product.class)))
                .thenReturn(Product.builder().name(request.getName()).build());
        // When
        final MockHttpServletResponse response = doPost(URL, request);

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        verify(productService).save(any(Product.class));
    }

}
