package com.santana.bluebank.controller;

import com.santana.bluebank.exception.ContaNaoEncontradaException;
import com.santana.bluebank.exception.TransacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.santana.bluebank.entities.Transacao;
import com.santana.bluebank.service.TransacoesService;

import lombok.AllArgsConstructor;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/transacoes")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TransacoesController {

	private TransacoesService transacoesService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/transferencia")
	public ResponseEntity<Transacao> realizarTransferencia(@Valid @RequestBody Transacao obj) throws TransacaoException, ContaNaoEncontradaException {

			Transacao objSaved = transacoesService.transferir(obj);
			return new ResponseEntity<Transacao>(objSaved, HttpStatus.OK);

	}
	
	

}