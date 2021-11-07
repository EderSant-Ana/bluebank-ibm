package com.santana.bluebank.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.santana.bluebank.enums.TipoRegimeEmpregaticio;
import com.santana.bluebank.utils.GerarNumeroConta;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_conta")
@NoArgsConstructor
@Data
public class Conta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_conta")
	private Integer id;

	private final String agencia = "0001";

	@Column(name = "numero_conta")
	private String numeroConta;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cliente_id", referencedColumnName = "cliente_id")
	private Cliente cliente;

	private BigDecimal limiteDisponivel;

	public Conta(Cliente cliente) {
//		this.id = id;
		this.cliente = cliente;
		this.numeroConta = GerarNumeroConta.gerarNumeroConta();
		this.limiteDisponivel = definirLimiteDisponivel(cliente);
	}

	public BigDecimal definirLimiteDisponivel(Cliente cliente) {

		if(cliente.getRendaMensalIndividual().compareTo(new BigDecimal("0")) == 0)
			this.limiteDisponivel = new BigDecimal(600.00);
		else if (cliente.getTipoRegimeEmpregaticio() == TipoRegimeEmpregaticio.ASSISTENCIAL)
			this.limiteDisponivel = new BigDecimal(600.00);
		else if (cliente.getTipoRegimeEmpregaticio() == TipoRegimeEmpregaticio.FORMAL)
			this.limiteDisponivel = cliente.getRendaMensalIndividual().multiply(new BigDecimal(0.80));
		else if (cliente.getTipoRegimeEmpregaticio() == TipoRegimeEmpregaticio.INFORMAL)
			this.limiteDisponivel = cliente.getRendaMensalIndividual().multiply(new BigDecimal(0.50));

		return this.limiteDisponivel.setScale(2, RoundingMode.HALF_EVEN);
	}
}
