package com.santana.bluebank.controller;

import java.util.List;

import com.santana.bluebank.entities.Transacao;
import com.santana.bluebank.exception.ContaNaoEncontradaException;
import com.santana.bluebank.exception.TransacaoException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("Gerencia as transações realizadas entre as contas")
public interface TransacoesControllerDocs {

	@ApiOperation(value = "Operaçao de transferência entre contas")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Transferência realizada com sucesso"),
			@ApiResponse(code = 400, message = "Campos obrigatórios ausentes ou dados incorretos"),
			@ApiResponse(code = 404, message = "Conta de origem ou de destino não encontrada") })
	Transacao realizarTransferencia(Transacao obj) throws TransacaoException, ContaNaoEncontradaException;

	@ApiOperation(value = "Retorna a lista de todas as transações realizadas no sistema")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Lista de todas as transações registradas no sistema")
	})
	List<Transacao> getAllTransacoes();
}
