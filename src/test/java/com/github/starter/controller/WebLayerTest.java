package com.github.starter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest
public class WebLayerTest {

  @Autowired
  protected MockMvc mockMvc;

  @Autowired protected ObjectMapper objectMapper;

  @SneakyThrows
  protected MockHttpServletResponse doGet(String url) {
    return mockMvc.perform(
            get(url).contentType(MediaType.APPLICATION_JSON))
            .andReturn()
            .getResponse();

  }

  @SneakyThrows
  protected <T> MockHttpServletResponse doPost(String url, T body) {
    return mockMvc.perform(
            post(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(body)))
            .andReturn()
            .getResponse();

  }

  @SneakyThrows
  protected <T> MockHttpServletResponse doPut(String url, T body) {
    return mockMvc.perform(
            put(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(body)))
            .andReturn()
            .getResponse();
  }

  @SneakyThrows
  protected MockHttpServletResponse doDelete(String url) {
    return mockMvc.perform(
            delete(url)
                    .contentType(MediaType.APPLICATION_JSON))
            .andReturn()
            .getResponse();
  }
}
