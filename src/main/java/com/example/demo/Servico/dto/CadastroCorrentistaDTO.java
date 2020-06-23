package com.example.demo.Servico.dto;

import java.math.BigDecimal;

import com.example.demo.Domain.ContaCorrente;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CadastroCorrentistaDTO {

	private Long id;
	
	private String nome;
	
	private String cpf;
	
	private String telefone;
	
	private String endereco;
	
	private BigDecimal limiteCredito;

}
