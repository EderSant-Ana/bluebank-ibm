package com.santana.bluebank.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santana.bluebank.entities.Cliente;
import com.santana.bluebank.entities.Conta;
import com.santana.bluebank.entities.Endereco;
import com.santana.bluebank.entities.Telefone;
import com.santana.bluebank.enums.TipoRegimeEmpregaticio;
import com.santana.bluebank.utils.GerarNumeroConta;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest2 {
	
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private ClienteController clienteContoller;
        
    Cliente c1;

	@Test
	void testCreateCliente() throws Exception {
		
		List<Endereco> enderecos = new ArrayList<>();
		Endereco e1 = new Endereco(Integer.valueOf(1), "Rua Rio Japurá", "793", "", "Perpétuo Socorro", "68905-540", "AP", "Macapá");
		Endereco e2 = new Endereco(Integer.valueOf(2), "Rua Rio Japurá", "802", "", "Perpétuo Socorro", "68905-540", "AP", "Macapá");
		
		enderecos.addAll(Arrays.asList(e1, e2));

		List<Telefone> telefones = new ArrayList<>();
		Telefone t1 = new Telefone(Integer.valueOf(1),"(96) 3938-1320");
		Telefone t2 = new Telefone(Integer.valueOf(2),"(96) 3938-1320");

		telefones.addAll(Arrays.asList(t1,t2));

		c1 = new Cliente(Integer.valueOf(1), "Giovanni Joaquim Miguel Nogueira",
				"931.291.356-50",  enderecos, telefones, Integer.valueOf(30), 
				"giovannijoaquimmiguelnogueira@smbcontabil.com.br", TipoRegimeEmpregaticio.ASSISTENCIAL,
				new BigDecimal("0"));

		Conta conta1 = new Conta(1, c1);
		conta1.setNumeroConta(GerarNumeroConta.gerarNumeroConta());
		conta1.definirLimiteDisponivel(c1);
		
		c1.setConta(conta1);

		String json = new ObjectMapper().writeValueAsString(c1);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.post("/v1/clientes")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(json);
		
		mvc.perform(request)
		.andExpect(status().isCreated());
	}
}
