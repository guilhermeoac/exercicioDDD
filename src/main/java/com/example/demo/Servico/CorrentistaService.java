package com.example.demo.Servico;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.Domain.Correntista;
import com.example.demo.Repository.CorrentistaRepository;
import com.example.demo.Servico.dto.CadastroCorrentistaDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class CorrentistaService {
	
	private final CorrentistaRepository correntistaRepository;
	
	private final ContaCorrenteService contaCorrenteService;
	
	public Correntista save(CadastroCorrentistaDTO correntista) throws Exception {
		return correntistaRepository.save(new Correntista(correntista.getNome(), correntista.getCpf(), correntista.getTelefone(), correntista.getEndereco(), correntista.getLimiteCredito()));
	}
	
	public void delete(Long id) {
		correntistaRepository.deleteById(id);
	}
	
	public Correntista findById(Long id) throws Exception {
		return correntistaRepository.findById(id).orElseThrow(() -> new Exception("Correntista não existe!"));
	}
	
	public void transferir(Long idTransferidor, Long idRecebedor, BigDecimal valor) throws Exception {
		Correntista transferidor = findById(idTransferidor);
		Correntista recebedor = findById(idRecebedor);
		contaCorrenteService.debitar(transferidor.getContaCorrente().getId(), valor);
		contaCorrenteService.creditar(recebedor.getContaCorrente().getId(), valor);
	}
	
	public BigDecimal findSaldo(Long id) throws Exception {
		return correntistaRepository.findById(id).orElseThrow(() -> new Exception("Correntista não existe!")).getContaCorrente().getSaldo();
	}
	
	public void depositar(Long id, BigDecimal valor) throws Exception {
		Correntista transferidor = findById(id);
		contaCorrenteService.creditar(transferidor.getContaCorrente().getId(), valor);
	}

}
