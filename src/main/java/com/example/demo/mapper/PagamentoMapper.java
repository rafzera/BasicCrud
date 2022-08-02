package com.example.demo.mapper;

import com.example.demo.dto.PagamentoDTO;
import com.example.demo.entity.PagamentoEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PagamentoMapper {

  public static PagamentoEntity toEntity(PagamentoDTO dto) {
    return PagamentoEntity.builder()
        .ammount(dto.getAmmount())
        .currency(dto.getCurrency())
        .personType(dto.getPersonType())
        .type(dto.getType())
        .build();
  }
}
