package com.santana.bluebank.controller;

import java.util.List;

import com.santana.bluebank.entities.Cliente;
import com.santana.bluebank.exception.ClienteJaCadastradoException;
import com.santana.bluebank.exception.EmailInvalidoException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("Gerencia os cliente do banco")
public interface ClienteControllerDocs {

	@ApiOperation(value = "Operaçao de criação de cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Cliente criado com sucesso"),
			@ApiResponse(code = 400, message = "Campos obrigatórios ausentes ou dados incorretos")
	})
	Cliente createCliente(Cliente cliente) throws ClienteJaCadastradoException, EmailInvalidoException;
	
	
	@ApiOperation(value = "Retorna a lista de todos os clientes do banco")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Lista de todos os clientes registrados no sistema")
	})
	 List<Cliente> getAllClientes();
}
