package com.example.demo.service;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.config.exception.ResourceNotFoundException;
import com.example.demo.entity.PagamentoEntity;
import com.example.demo.mock.PagamentoEntityMock;
import com.example.demo.repository.PagamentoRepository;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PagamentoServiceTest {

  @InjectMocks
  PagamentoService pagamentoService;

  @Mock
  PagamentoRepository pagamentoRepository;


  @Test
  void save() {
    //dado que retornara expected quando repository.save for chamado
    PagamentoEntity expected = PagamentoEntityMock.build();
    when(pagamentoRepository.save(any())).thenReturn(expected);

    //quando
    PagamentoEntity result = pagamentoService.save(expected);

    //deve
    assertEquals(expected,result);
    verify(pagamentoRepository,Mockito.times(1)).save(any());

  }

  @Test
  void findAll() {
    var expected = List.of(PagamentoEntityMock.build());
    when(pagamentoRepository.findAll()).thenReturn(expected);

    List<PagamentoEntity> result = pagamentoService.findAll();

    assertEquals(expected, result);
    verify(pagamentoRepository,Mockito.times(1)).findAll();
  }

  @Test
  void findById() {
    var expected = PagamentoEntityMock.build();
    when(pagamentoRepository.findById(any())).thenReturn(Optional.of(expected));

    PagamentoEntity result = pagamentoService.findById(any());

    assertEquals(expected,result);
    verify(pagamentoRepository,Mockito.times(1)).findById(any());
  }

  @Test
  void update() {
    var pagamento = PagamentoEntityMock.build();
    var expected = PagamentoEntityMock.build2();
    when(pagamentoRepository.findById(any())).thenReturn(Optional.of(pagamento));
    when(pagamentoRepository.save(any())).thenReturn(expected);

    PagamentoEntity result = pagamentoService.update(anyLong(), expected);

    assertNotEquals(expected,pagamento);
    assertEquals(expected,result);
    verify(pagamentoRepository,Mockito.times(1)).save(any());

  }

  @Test
  void delete() {
    var expected = PagamentoEntityMock.build();
    when(pagamentoRepository.findById(any())).thenReturn(Optional.of(expected));

    pagamentoService.delete(anyLong());

    verify(pagamentoRepository,Mockito.times(1)).delete(any());

  }

  @Test
  void testException(){
    when(pagamentoRepository.findById(any())).thenReturn(Optional.empty());

    Throwable throwable = catchThrowable(() -> pagamentoService.findById(anyLong()));

    Assertions.assertThat(throwable).isInstanceOf(ResourceNotFoundException.class);

  }

}