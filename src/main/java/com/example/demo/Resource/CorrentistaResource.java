package com.example.demo.Resource;

import java.math.BigDecimal;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Domain.Correntista;
import com.example.demo.Servico.CorrentistaService;
import com.example.demo.Servico.dto.CadastroCorrentistaDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/correntista")
@RequiredArgsConstructor
public class CorrentistaResource {

	private final CorrentistaService correntistaService;

    @PostMapping(consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Correntista> save(@RequestBody CadastroCorrentistaDTO cadastroCorrentistaDTO) throws Exception{
        return ResponseEntity.ok(correntistaService.save(cadastroCorrentistaDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Correntista> findById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(correntistaService.findById(id));
    }

    @PutMapping("/transferir/{idTransferidor}/{idRecebedor}/{valor}")
    public ResponseEntity<Long> getDigitoUnicoById(@PathVariable Long idTransferidor, @PathVariable Long idRecebedor, @PathVariable BigDecimal valor) throws Exception {
    	correntistaService.transferir(idTransferidor, idRecebedor, valor);
    	return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteDigitoUnicoById(@PathVariable Long id){
        correntistaService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<BigDecimal> getSaldo(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(correntistaService.findSaldo(id));
    }

    @PutMapping("/depositar/{id}/{valor}")
    public ResponseEntity<Long> depositar(@PathVariable Long id, @PathVariable BigDecimal valor) throws Exception {
    	correntistaService.depositar(id, valor);
    	return ResponseEntity.ok().build();
    }
}
