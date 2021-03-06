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

import com.santana.bluebank.entities.Cliente;
import com.santana.bluebank.exception.ClienteJaCadastradoException;
import com.santana.bluebank.service.ClienteService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/v1")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteController implements ClienteControllerDocs{

	private ClienteService clienteService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/clientes")
	public Cliente createCliente(@Valid @RequestBody Cliente cliente) throws ClienteJaCadastradoException {
		
		return clienteService.createCliente(cliente);
	}

	@GetMapping(value = "/clientes")
	public List<Cliente> getAllClientes(){
		return clienteService.findAll();
	}
}
