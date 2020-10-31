package com.github.starter.controller;

import static com.github.starter.domain.exception.ErrorCode.PRODUCT_NOT_FOUND;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.github.starter.domain.Product;
import com.github.starter.domain.exception.ApiError;
import com.github.starter.domain.exception.StarterException;
import com.github.starter.service.ProductService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.Collections;

@WebMvcTest(ProductController.class)
class ProductControllerGetByIdTest extends WebLayerTest {

  @MockBean
  private ProductService productService;

  @Test
  @SneakyThrows
  void whenProductExists_thenReturnStatusOk() {
    // Given
    final Long id = 12L;
    final String url = format("/api/products/%d", id);

    final Product product = Product.builder().build();

    when(productService.getById(anyLong()))
        .thenReturn(product);

    // When
    final MockHttpServletResponse response = doGet(url);

    // Then
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    verify(productService, times(1)).getById(id);
  }


  @Test
  @SneakyThrows
  void whenProductDoesNotExists_thenReturnStatusBadRequest() {
    // Given
    final Long id = 12L;
    final String url = format("/api/products/%d", id);

    doThrow(new StarterException(PRODUCT_NOT_FOUND))
        .when(productService).getById(anyLong());

    // When
    final MockHttpServletResponse response = doGet(url);

    // Then
    assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
  }
}
