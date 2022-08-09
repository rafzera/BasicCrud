package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.DemoApplication;
import com.example.demo.config.exception.ResourceNotFoundException;
import com.example.demo.entity.PagamentoEntity;
import com.example.demo.mock.PagamentoEntityMock;
import com.example.demo.service.PagamentoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

//configuração, tem que passar a application sempre
@SpringBootTest(classes = DemoApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class PagamentoControllerTest {

  //simula as chamadas da controller
  private MockMvc mockMvc;

  private ObjectMapper mapper;

  //emula um contexto de aplicação
  @Autowired
  private WebApplicationContext context;

  @MockBean
  private PagamentoService service;

  @BeforeEach
  public void setup() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    mapper = new ObjectMapper();
  }

  @Test
  public void save() throws Exception {
    PagamentoEntity entity = PagamentoEntityMock.build();

    when(service.save(any(PagamentoEntity.class))).thenReturn(entity);

    //
    mockMvc.perform(post("/v1/pagamentos")
            .contentType(APPLICATION_JSON)
            .content(mapper.writeValueAsString(entity))
            .characterEncoding(StandardCharsets.UTF_8))
        .andDo(print())
        .andExpect(status().isCreated());

  }

  @Test
  public void getById() throws Exception {
    PagamentoEntity entity = PagamentoEntityMock.build();

    when(service.findById(any())).thenReturn(entity);

    mockMvc.perform(get("/v1/pagamentos/{id}", 1L)
            .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void findAll() throws Exception {
    var expected = List.of(PagamentoEntityMock.build());

    when(service.findAll()).thenReturn(expected);

    mockMvc.perform(get("/v1/pagamentos")
            .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());

  }

  @Test
  public void update() throws Exception {
    var expected = PagamentoEntityMock.build2();

    when(service.update(any(), any(PagamentoEntity.class))).thenReturn(expected);

    mockMvc.perform(put("/v1/pagamentos/{id}", 1L)
            .contentType(APPLICATION_JSON)
            .content(mapper.writeValueAsString(expected)).characterEncoding(StandardCharsets.UTF_8))
        .andDo(print())
        .andExpect(status().isOk());

  }

  @Test
  public void testpatch() throws Exception {

    var expected = PagamentoEntityMock.build2();

    when(service.update(any(), any(PagamentoEntity.class))).thenReturn(expected);

    mockMvc.perform(patch("/v1/pagamentos/{id}", 1L)
            .contentType(APPLICATION_JSON)
            .content(mapper.writeValueAsString(expected)).characterEncoding(StandardCharsets.UTF_8))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void testdelete() throws Exception {

    mockMvc.perform(delete("/v1/pagamentos/{id}",1L)
        .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());

  }

  @Test
  public void testException() throws Exception {

    when(service.findById(any())).thenThrow(ResourceNotFoundException.class);

    mockMvc.perform(get("/v1/pagamentos/{id}", 8L)
            .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isNotFound());
  }


}
