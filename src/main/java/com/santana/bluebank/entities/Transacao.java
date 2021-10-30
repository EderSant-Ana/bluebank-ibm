package com.santana.bluebank.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.santana.bluebank.enums.TipoOperacao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_transacoes")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transacao implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String banco;
	private String agencia;
	private String numeroContaOrigem;
	private String numeroContaDestino;
	
	@Enumerated(EnumType.STRING)
	private TipoOperacao operacao;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date data;
	private BigDecimal valor;




	
	

}
