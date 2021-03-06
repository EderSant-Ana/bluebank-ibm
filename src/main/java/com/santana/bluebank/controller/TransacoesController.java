package com.santana.bluebank.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.santana.bluebank.entities.Transacao;
import com.santana.bluebank.exception.ContaNaoEncontradaException;
import com.santana.bluebank.exception.TransacaoException;
import com.santana.bluebank.service.TransacoesService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/v1/transacoes")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TransacoesController implements TransacoesControllerDocs{

	private TransacoesService transacoesService;

	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/transferencia")
	public Transacao realizarTransferencia(@Valid @RequestBody Transacao obj) throws TransacaoException, ContaNaoEncontradaException {

			return transacoesService.transferir(obj);
	}

	@GetMapping(value = "/transferencia")
	public List<Transacao> getAllTransacoes(){

			return transacoesService.listarTransacoes();
	}
}