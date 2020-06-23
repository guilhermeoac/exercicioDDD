package com.example.demo.Servico;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.Domain.ContaCorrente;
import com.example.demo.Repository.ContaCorrenteRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class ContaCorrenteService {
	
	private final ContaCorrenteRepository contaCorrenteRepository;
	
	public void creditar(Long id, BigDecimal valor) throws Exception {
		ContaCorrente contaCorrente = contaCorrenteRepository.findById(id).orElseThrow(() -> new Exception("Conta inválida!"));
		contaCorrente.creditar(valor);
		contaCorrenteRepository.save(contaCorrente);
	}
	
	public void debitar(Long id, BigDecimal valor) throws Exception {
		ContaCorrente contaCorrente = contaCorrenteRepository.findById(id).orElseThrow(() -> new Exception("Conta inválida!"));
		contaCorrente.debitar(valor);
		contaCorrenteRepository.save(contaCorrente);
	}

}
