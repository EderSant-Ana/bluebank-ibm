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

import com.santana.bluebank.enums.TipoRegimeEmpregaticio;

import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_conta")
@NoArgsConstructor
public class Conta implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_conta")
	private Integer id;
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id", referencedColumnName = "cliente_id")
	private Cliente cliente;
	
	private BigDecimal limiteDisponivel;

	public Conta(Integer id, Cliente cliente) {
		this.id = id;
		this.cliente = cliente;
		this.limiteDisponivel = setLimiteDisponivel(cliente);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public BigDecimal getLimiteDisponivel() {
		return limiteDisponivel;
	}

	public BigDecimal setLimiteDisponivel(Cliente cliente) {
		if(cliente.getTipoRegimeEmpregaticio() == TipoRegimeEmpregaticio.Desempregado) {
			this.limiteDisponivel = new BigDecimal(600.00);
		}
		else if(cliente.getTipoRegimeEmpregaticio() == TipoRegimeEmpregaticio.Formal) {
			this.limiteDisponivel = cliente.getRendaMensalIndividual().multiply(new BigDecimal(0.80));
		}
		else if(cliente.getTipoRegimeEmpregaticio() == TipoRegimeEmpregaticio.Informal) {
			this.limiteDisponivel = cliente.getRendaMensalIndividual().multiply(new BigDecimal(0.50));
		}	
		return this.limiteDisponivel.setScale(2, RoundingMode.HALF_EVEN);
	}



	
	
	
	
}
