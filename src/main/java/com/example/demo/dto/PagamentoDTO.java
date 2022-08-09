package com.example.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PagamentoDTO {

  private String type;
  private Double ammount;
  private String currency;
  private String personType;

}
