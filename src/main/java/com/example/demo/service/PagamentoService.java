package com.example.demo.service;

import static java.util.Objects.isNull;

import com.example.demo.config.exception.ResourceNotFoundException;
import com.example.demo.entity.PagamentoEntity;
import com.example.demo.repository.PagamentoRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PagamentoService {

  private final PagamentoRepository pagamentoRepository;

  public PagamentoEntity save(PagamentoEntity entity) {
   return pagamentoRepository.save(entity);
  }

  public List<PagamentoEntity> findAll() {
    return pagamentoRepository.findAll();
  }

  public PagamentoEntity findById(Long id) {
    return pagamentoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("deu ruim, entidade nao encontrada"));
  }

  public PagamentoEntity update(Long id,PagamentoEntity entity){

    PagamentoEntity actualEntity = this.findById(id);
    actualEntity = PagamentoEntity.builder()
        .id(actualEntity.getId())
        .type(isNull(entity.getType()) ? actualEntity.getType()  : entity.getType())
        .personType(isNull(entity.getPersonType()) ? actualEntity.getPersonType()  : entity.getPersonType() )
        .currency(isNull(entity.getCurrency()) ? actualEntity.getCurrency()  : entity.getCurrency())
        .ammount(isNull(entity.getAmmount()) ? actualEntity.getAmmount()  : entity.getAmmount())
        .build();

    return pagamentoRepository.save(actualEntity);
  }

  public void delete(Long id){
    PagamentoEntity pagamento = this.findById(id);
    pagamentoRepository.delete(pagamento);

  }

}
