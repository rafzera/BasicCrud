package com.example.demo.controller;

import com.example.demo.dto.PagamentoDTO;
import com.example.demo.entity.PagamentoEntity;
import com.example.demo.mapper.PagamentoMapper;
import com.example.demo.service.PagamentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/pagamentos")
@AllArgsConstructor
@Api(value = "pagamentos")
public class PagamentoController {

  private final PagamentoService pagamentoService;

  @ApiOperation(value = "criar novo pagamento")
  @PostMapping
  public ResponseEntity<PagamentoEntity> save(@RequestBody PagamentoDTO dto){
   return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoService.save(PagamentoMapper.toEntity(dto)));
  }

  @ApiOperation(value = "retorna todos os pagamentos")
  @GetMapping
  public ResponseEntity<List<PagamentoEntity>> findall(){
    return ResponseEntity.ok(pagamentoService.findAll());
  }
  @ApiOperation(value = "retorna um pagamento especifico")
  @GetMapping("/{id}")
  public ResponseEntity<PagamentoEntity> findById(@PathVariable Long id){
    return ResponseEntity.ok(pagamentoService.findById(id));
  }

  @ApiOperation(value = "atualiza um pagamento")
  @PutMapping("/{id}")
  public ResponseEntity<PagamentoEntity> update(@PathVariable Long id,@RequestBody PagamentoDTO dto){
    return ResponseEntity.ok(pagamentoService.update(id,PagamentoMapper.toEntity(dto)));
  }

  @ApiOperation(value = "atualiza parcialmente um pagamento")
  @PatchMapping("/{id}")
  public ResponseEntity<PagamentoEntity> patch(@PathVariable Long id, @RequestBody PagamentoDTO dto){
    return ResponseEntity.ok(pagamentoService.update(id,PagamentoMapper.toEntity(dto)));
  }

  @ApiOperation(value = "deleta um pagamento em especifico")
  @DeleteMapping("/{id}")
  public  ResponseEntity<Long> delete(@PathVariable Long id){
    pagamentoService.delete(id);
    return new ResponseEntity<>(id, HttpStatus.OK);
  }

}
