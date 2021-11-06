package com.santana.bluebank.service;

import com.santana.bluebank.controller.TransacoesController;
import com.santana.bluebank.exception.ContaNaoEncontradaException;
import com.santana.bluebank.exception.TransacaoException;
import com.santana.bluebank.repository.TransacoesRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles("test")
public class TransacoesServiceTest {

    TransacoesService service;

    @MockBean
    TransacoesController controller;

    @MockBean
    TransacoesRepository repository;

    @Test
    @DisplayName("Deve fazer uma Transação")
    public void fazerTransacaoTest() throws ContaNaoEncontradaException, TransacaoException {
        //cenario
    }
}
