package com.santana.bluebank.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.br.CPF;

import com.santana.bluebank.enums.TipoRegimeEmpregaticio;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tb_clientes")
@NoArgsConstructor
@Data
public class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cliente_id")
	private Integer id;

	@NotEmpty(message="Nome é um atributo obrigatório")
	@Pattern(regexp="([A-Z][a-z]{2,} )([A-Z][a-z]{2,} )*?([A-Z][a-z]{2,})",
	message = "Não são permitidos caracteres numéricos para o campo nome e são necessários no mínimo de 3 caracteres em cada palavra.")
	@Column(name = "nome", nullable = false)	
	private String nome;

	@CPF
	@Column(name = "cpf", unique = true, nullable = false)
	private String cpf;

	@Valid
	@NotEmpty(message="Endereço é um atributo obrigatório")
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "cliente_id")
	private List<Endereco> enderecos = new ArrayList<>();
	
	@Valid
	@NotEmpty(message="Telefone é um atributo obrigatório")
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "cliente_cliente_id")
	private Set<Telefone> telefones = new HashSet<>();
	
	@NotNull(message="Idade é um atributo obrigatório")
	@Range(min = 18, max= 90, message = "Idade não atende a política do BlueBank para abertura de conta") 
	private Integer idade;
	
	@NotEmpty(message= "E-mail é um atributo obrigatório")
	@Email(message = "Por favor, insira um e-mail válido")
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	
	@NotNull(message= "Regime Empregaticio é um atributo obrigatório")
	@Enumerated(EnumType.STRING)
	@Column(name = "regime_contratacao")
	private TipoRegimeEmpregaticio tipoRegimeEmpregaticio;
	
	@Column(name = "renda_mensal")
	@Min(value=0, message = "O mínimo permitido para renda mensal individual é 0")
	@NotNull(message="rendaMensalIndividual é um atributo obrigatório")
	private BigDecimal rendaMensalIndividual;

	@ToString.Exclude
	@OneToOne(mappedBy = "cliente")
	private Conta conta;

	public Cliente(Integer id, String nome, @NotEmpty @CPF String cpf, Integer idade, String email,
			TipoRegimeEmpregaticio tipoRegimeEmpregaticio, BigDecimal rendaMensalIndividual) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.idade = idade;
		this.email = email;
		this.tipoRegimeEmpregaticio = tipoRegimeEmpregaticio;
		this.rendaMensalIndividual = rendaMensalIndividual;
	}

	//Utilizar esse construtor somente para testes
	public Cliente(Integer id,
			@NotEmpty(message = "Nome é um atributo obrigatório") @Pattern(regexp = "([A-Z][a-z]{2,} )([A-Z][a-z]{2,} )*?([A-Z][a-z]{2,})", message = "Não são permitidos caracteres numéricos para o campo nome e são necessários no mínimo de 3 caracteres em cada palavra.") String nome,
			@CPF String cpf,
			@NotEmpty(message = "Endereço é um atributo obrigatório") List<Endereco> enderecos,
			@NotEmpty(message = "Telefone é um atributo obrigatório") Set<Telefone> telefones,
			@NotNull(message = "Idade é um atributo obrigatório") @Range(min = 18, max = 90, message = "Idade não atende a política do BlueBank para abertura de conta") Integer idade,
			@NotEmpty(message = "E-mail é um atributo obrigatório") @Email String email,
			@NotNull(message = "Regime Empregaticio é um atributo obrigatório") TipoRegimeEmpregaticio tipoRegimeEmpregaticio,
			@Min(value = 0, message = "O mínimo permitido para renda mensal individual é 0") @NotNull(message = "rendaMensalIndividual é um atributo obrigatório") BigDecimal rendaMensalIndividual) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.enderecos = enderecos;
		this.telefones = telefones;
		this.idade = idade;
		this.email = email;
		this.tipoRegimeEmpregaticio = tipoRegimeEmpregaticio;
		this.rendaMensalIndividual = rendaMensalIndividual;
	}
	
	

}
