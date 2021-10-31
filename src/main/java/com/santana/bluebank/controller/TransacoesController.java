package com.santana.bluebank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.santana.bluebank.entities.Conta;
import com.santana.bluebank.entities.Transacao;
import com.santana.bluebank.repository.ContaRepository;
import com.santana.bluebank.repository.TransacoesRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/v1/transacoes")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TransacoesController {

	private TransacoesRepository transacoesRepository;
	private ContaRepository contaRepository;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/transferencia")
	public Transacao realizarTransferencia(@RequestBody Transacao obj) {

		Conta contaOrigem = contaRepository.findByNumeroConta(obj.getNumeroContaOrigem());
		System.out.println(contaOrigem);

		obj.setNomeDepositante(contaOrigem.getCliente().getNome());

		Conta contaDestino = contaRepository.findByNumeroConta(obj.getNumeroContaDestino());

		obj.setNomeDestinatario(contaDestino.getCliente().getNome());

		contaOrigem.setLimiteDisponivel(contaOrigem.getLimiteDisponivel().subtract(obj.getValor()));
		contaDestino.setLimiteDisponivel(contaDestino.getLimiteDisponivel().add(obj.getValor()));

		Transacao objSaved = transacoesRepository.save(obj);
		return objSaved;

	}

}