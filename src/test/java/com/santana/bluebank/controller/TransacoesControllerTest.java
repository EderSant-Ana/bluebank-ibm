package com.santana.bluebank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santana.bluebank.entities.Transacao;
import com.santana.bluebank.enums.TipoOperacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(TransacoesController.class)
public class TransacoesControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TransacoesController transacoesController;

    Transacao transacao;

    @BeforeEach
    public void setUp() {
        LocalDateTime data = LocalDateTime.ofInstant(Instant.now(), ZoneId.of("America/Sao_Paulo"));

        transacao = new Transacao(
                Integer.valueOf(1),"Blue-Bank", "0001",
                "71006-5", "93693-5",
                TipoOperacao.TRANSFERENCIA, BigDecimal.valueOf(15000), data);
    }

    @Test
    public void testCreateTransferencia() throws Exception {

        String json = new ObjectMapper().writeValueAsString(transacao);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/v1/transacoes/transferencia")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isOk());
    }
}
