package com.example.demo.Domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Entity(name = "TB_CONTA_CORRENTE")
@Getter
@Setter
@Slf4j
@NoArgsConstructor
public class ContaCorrente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_conta_corrente")
    @SequenceGenerator(name = "seq_conta_corrente", sequenceName = "seq_conta_corrente", allocationSize = 1 )
    @Column(name = "ID_conta_corrente")
	private Long id;
	
    @Column(name = "nu_saldo")
	private BigDecimal saldo;
	
    @Column(name = "nu_limite")
	private BigDecimal limiteCredito;
	
	public ContaCorrente(BigDecimal limiteCredito) {
		this.saldo = new BigDecimal(0);
		this.limiteCredito = limiteCredito;
	}
	
	public void setLimiteCredito(BigDecimal limiteCredito) throws Exception {
		if(limiteCredito.compareTo(new BigDecimal(0)) == 1) {
			throw new Exception("Limite Negativo");
		}
		this.limiteCredito = limiteCredito;
		
	}
	
	
	public void creditar(BigDecimal valor) {
		verificaCreditar(valor);
		this.saldo = this.saldo.add(valor);
	}
	
	
	public void debitar(BigDecimal valor) throws Exception {
		verificaDebitar(valor);
		this.saldo = this.saldo.subtract(valor);
	}
	
	private void verificaDebitar(BigDecimal valor) throws Exception {
		BigDecimal saldoLimite = this.saldo;
		saldoLimite = saldoLimite.add(this.limiteCredito);
		if(saldoLimite.compareTo(valor) == -1) {
			throw new Exception("Saldo Insuficiente");
		}
	}
	
	private void verificaCreditar(BigDecimal valor){
		if(valor.compareTo(new BigDecimal(50000)) == 1) {
			log.info("Operação maior que 50000, valor creditado de {}", valor);
		}
	}

}
	
