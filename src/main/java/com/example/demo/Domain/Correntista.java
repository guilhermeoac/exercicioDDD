package com.example.demo.Domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.example.demo.Util.ValidaCPF;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Entity(name = "TB_CORRENTISTA")
@Getter
@Setter
@Slf4j
@NoArgsConstructor
public class Correntista {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_correntista")
    @SequenceGenerator(name = "seq_correntista", sequenceName = "seq_correntista", allocationSize = 1 )
    @Column(name = "ID_correntista")
	private Long id;
	
    @Column(name = "ds_nome")
	private String nome;
	
    @Column(name = "ds_cpf")
	private String cpf;
	
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_CONTA_CORRENTE")
	private ContaCorrente contaCorrente;
	
    @Column(name = "ds_telefone")
	private String telefone;
	
    @Column(name = "ds_endereco")
	private String endereco;
	
	public void setCpf(String cpf) throws Exception {
		if(!ValidaCPF.isCPF(cpf)) {
			throw new Exception("CPF inválido!");
		}
		this.cpf = cpf;
	}
	
	public Correntista(String nome, String cpf, String telefone, String endereco, BigDecimal limiteCredito) throws Exception {
		setCpf(cpf);
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.contaCorrente = new ContaCorrente(limiteCredito);
	}
	
	

}
