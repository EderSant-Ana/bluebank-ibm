package com.santana.bluebank.service;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.santana.bluebank.entities.Cliente;
import com.santana.bluebank.entities.Conta;
import com.santana.bluebank.entities.Endereco;
import com.santana.bluebank.entities.Telefone;
import com.santana.bluebank.entities.Transacao;
import com.santana.bluebank.enums.TipoOperacao;
import com.santana.bluebank.enums.TipoRegimeEmpregaticio;
import com.santana.bluebank.exception.ContaNaoEncontradaException;
import com.santana.bluebank.exception.TransacaoException;
import com.santana.bluebank.utils.GerarNumeroConta;

@WebMvcTest(TransacoesService.class)
class TransacoesServiceTest {
	

//	@Mock
//	private ContaRepository contaRepository;
//	
//	@InjectMocks
//	private TransacoesService transacoesService;

    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private TransacoesService transacoesService;
    
    @BeforeEach
    public void setUp() throws TransacaoException, ContaNaoEncontradaException {
    	List<Endereco> enderecos = new ArrayList<>();
		Endereco e1 = new Endereco(Integer.valueOf(1), "Rua Rio Japurá", "793", "", "Perpétuo Socorro", "68905-540", "AP", "Macapá");
		Endereco e2 = new Endereco(Integer.valueOf(2), "Rua Rio Japurá", "802", "", "Perpétuo Socorro", "68905-540", "AP", "Macapá");
		
		enderecos.addAll(Arrays.asList(e1, e2));
		
		Set<Telefone> telefones = new HashSet<>();
		telefones.addAll(Arrays.asList(new Telefone(1, "(96) 3938-1320"), new Telefone(2, "(96) 98806-2952")));
		
		Cliente c1 = new Cliente(Integer.valueOf(1), "Giovanni Joaquim Miguel Nogueira", 
				"931.291.356-50",  enderecos, telefones, Integer.valueOf(30), 
				"giovannijoaquimmiguelnogueira@smbcontabil.com.br", TipoRegimeEmpregaticio.FORMAL,
				new BigDecimal("0"));

		Conta conta1 = new Conta(1, c1);
		conta1.setNumeroConta(GerarNumeroConta.gerarNumeroConta());
		conta1.definirLimiteDisponivel(c1);

		Cliente c2 = new Cliente(Integer.valueOf(2), "Giovanna Joaquim Miguel Nogueira", 
				"931.291.356-51",  enderecos, telefones, Integer.valueOf(32), 
				"giovannajoaquimmiguelnogueira@smbcontabil.com.br", TipoRegimeEmpregaticio.ASSISTENCIAL,
				new BigDecimal("10000"));

		Conta conta2 = new Conta(2, c2);
		conta2.setNumeroConta(GerarNumeroConta.gerarNumeroConta());
		conta2.definirLimiteDisponivel(c2);
		
		c2.setConta(conta2);
		
		List<Cliente> clientes = new ArrayList<>();
		clientes.add(c2);
		
		Transacao t1 = new Transacao(1, "Blue-Bank", "0001", conta1.getNumeroConta(), conta2.getNumeroConta(),
				TipoOperacao.TRANSFERENCIA, BigDecimal.valueOf(200.00));
		
		Transacao transacaoCompleta = new Transacao(1, "Blue-Bank", "0001", conta1.getNumeroConta(), 
				conta1.getCliente().getNome(), conta2.getNumeroConta(),
				conta1.getCliente().getNome(), TipoOperacao.TRANSFERENCIA, t1.getValor());
		
		given(transacoesService.transferir(t1)).willReturn(transacaoCompleta);
    }
    
	
	@Test
	void test() throws Exception {

		MvcResult result = mvc.perform(get("/v1/transacoes/transferencia").
				contentType(MediaType.APPLICATION_JSON)).
				andExpect(status().isOk()).
//				andExpect(jsonPath("$[0].nome").
//						value(is(not(emptyOrNullString())))).
//				andExpect(jsonPath("$[0].nome")
//						.value("Giovanni Joaquim Miguel Nogueira")).
//				andExpect(jsonPath("$[0].cpf")
//						.value(is(not(emptyOrNullString())))).				
				andReturn();
		
		String content = result.getResponse().getContentAsString();
		System.out.println(content);
	}

}
