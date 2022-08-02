package com.example.demo.mock;

import com.example.demo.entity.PagamentoEntity;

public class PagamentoEntityMock {

  public static PagamentoEntity build() {
    return PagamentoEntity.builder()
        .ammount(2.0)
        .id(1L)
        .currency("a")
        .type("PIX")
        .personType("pj")
        .build();
  }

  public static PagamentoEntity build2() {
    return PagamentoEntity.builder()
        .ammount(3.0)
        .id(1L)
        .currency("b")
        .type("TED")
        .personType("Fisica")
        .build();
  }
}
