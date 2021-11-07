package com.santana.bluebank.service;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.santana.bluebank.entities.Cliente;
import com.santana.bluebank.entities.Conta;
import com.santana.bluebank.entities.Endereco;
import com.santana.bluebank.entities.Telefone;
import com.santana.bluebank.entities.Transacao;
import com.santana.bluebank.enums.TipoOperacao;
import com.santana.bluebank.enums.TipoRegimeEmpregaticio;
import com.santana.bluebank.exception.ContaNaoEncontradaException;
import com.santana.bluebank.exception.TransacaoException;
import com.santana.bluebank.repository.ContaRepository;
import com.santana.bluebank.repository.TransacoesRepository;
import com.santana.bluebank.utils.GerarNumeroConta;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TransacoesServiceTransferenciaTest {

	@Mock
	private ContaRepository contaRepository;
	
	@Mock
	private TransacoesRepository transacoesRepository;
    
	@InjectMocks
    private TransacoesService transacoesService;
	    
    @BeforeEach
    public void setUp() throws TransacaoException, ContaNaoEncontradaException {
    	
    	transacoesService = new TransacoesService(contaRepository, transacoesRepository);
    }
    
	
	@Test
	void test() throws Exception {

		//GIVEN
    	List<Endereco> enderecos = new ArrayList<>();
		Endereco e1 = new Endereco(Integer.valueOf(1), "Rua Rio Japurá", "793", "", "Perpétuo Socorro", "68905-540", "AP", "Macapá");
		Endereco e2 = new Endereco(Integer.valueOf(2), "Rua Rio Japurá", "802", "", "Perpétuo Socorro", "68905-540", "AP", "Macapá");
		
		enderecos.addAll(Arrays.asList(e1, e2));
		
		Set<Telefone> telefones = new HashSet<>();
		telefones.addAll(Arrays.asList(new Telefone(1, "(96) 3938-1320"), new Telefone(2, "(96) 98806-2952")));
		
		Cliente c1 = new Cliente(Integer.valueOf(1), "Laura Sônia", 
				"452.690.498-80",  enderecos, telefones, Integer.valueOf(30), 
				"louiseclaudiamoura_@zoomfoccus.com.br", TipoRegimeEmpregaticio.FORMAL,
				new BigDecimal(2000));
		
		Conta conta1 = new Conta(1, c1);
		conta1.setNumeroConta(GerarNumeroConta.gerarNumeroConta());
		
		conta1.definirLimiteDisponivel(c1);

		Cliente c2 = new Cliente(Integer.valueOf(2), "Giovanna Joaquim Miguel Nogueira", 
				"931.291.356-51",  enderecos, telefones, Integer.valueOf(32), 
				"giovannajoaquimmiguelnogueira@smbcontabil.com.br", TipoRegimeEmpregaticio.ASSISTENCIAL,
				new BigDecimal(0));

		Conta conta2 = new Conta(2, c2);
		conta2.setNumeroConta(GerarNumeroConta.gerarNumeroConta());
		conta2.definirLimiteDisponivel(c2);
		
		c2.setConta(conta2);
		
		List<Cliente> clientes = new ArrayList<>();
		clientes.add(c2);
		
		//WHEN
		when(contaRepository.findByNumeroConta(conta1.getNumeroConta())).thenReturn(Optional.of(conta1));
		when(contaRepository.findByNumeroConta(conta2.getNumeroConta())).thenReturn(Optional.of(conta2));
		
		Optional<Conta> optConta1 = contaRepository.findByNumeroConta(conta1.getNumeroConta());
		Optional<Conta> optConta2 = contaRepository.findByNumeroConta(conta2.getNumeroConta());
		
		Transacao t1 = new Transacao(1, "Blue-Bank", "0001", optConta1.get().getNumeroConta(), 
				optConta2.get().getNumeroConta(),
				TipoOperacao.TRANSFERENCIA, new BigDecimal(100));
		
		Transacao transacaoCompleta = new Transacao(1, "Blue-Bank", "0001", optConta1.get().getNumeroConta(), 
				optConta1.get().getCliente().getNome(), optConta2.get().getNumeroConta(),
				optConta2.get().getCliente().getNome(), TipoOperacao.TRANSFERENCIA, new BigDecimal(100));

		
		when(transacoesService.transferir(t1)).thenReturn(transacaoCompleta);
		
		//THEN
		
		BigDecimal conta1Saldo = optConta1.get().getLimiteDisponivel().setScale(0, RoundingMode.DOWN);
		BigDecimal conta2Saldo = optConta2.get().getLimiteDisponivel().setScale(0, RoundingMode.DOWN);

		assertThat(new BigDecimal("1500"), Matchers.comparesEqualTo(conta1Saldo));
		assertThat(new BigDecimal("700"), is(equalTo(conta2Saldo)));

		assertThat(transacaoCompleta.getNomeDepositante(), is(equalTo(conta1.getCliente().getNome())));
		assertThat(transacaoCompleta.getNomeDestinatario(), is(equalTo(conta2.getCliente().getNome())));		
		
	}
}
