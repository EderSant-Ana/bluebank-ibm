package com.santana.bluebank.controller;


import com.santana.bluebank.entities.Transacao;
import com.santana.bluebank.enums.TipoOperacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.util.*;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransacoesController.class)
public class TransacoesControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TransacoesController transacoesController;

    @BeforeEach
    public void setUp() {

        Transacao t = new Transacao(Integer.valueOf(1), "Blue-Bank", "0001",  "93693-5", "Jose",
                "71006-5", "Maria", TipoOperacao.TRANSFERENCIA, new BigDecimal(600.00));


        List<Transacao> transacaos = new ArrayList<>();
        transacaos.add(t);

        given(transacoesController.getAllTransacoes()).willReturn(transacaos);

    }

    @Test
    void dadoUmaListaDeTransacoes_quandoForRealizadoGetAllTransacoes_entaoDeveRetornarJsonArray() throws Exception {

        MvcResult result = mvc.perform(get("/v1/transacoes/transferencia").
                contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).
                andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println(content);
    }
}
