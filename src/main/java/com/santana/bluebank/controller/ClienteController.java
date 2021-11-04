package com.santana.bluebank.controller;

import java.util.List;

import com.santana.bluebank.entities.Transacao;
import com.santana.bluebank.exception.ClienteJaCadastradoException;
import com.santana.bluebank.exception.ContaNaoEncontradaException;
import com.santana.bluebank.exception.TransacaoException;
import com.santana.bluebank.service.ClientesService;
import com.santana.bluebank.service.TransacoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.santana.bluebank.entities.Cliente;
import com.santana.bluebank.entities.Conta;
import com.santana.bluebank.repository.ClienteRepository;
import com.santana.bluebank.repository.ContaRepository;

import lombok.AllArgsConstructor;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteController {

	private ClienteRepository clienteRepository;
	private ContaRepository contaRepository;
	private ClientesService clientesService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/clientes")
	public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) throws ClienteJaCadastradoException {
		
//		Cliente clienteSaved = clienteRepository.save(cliente);
//		clienteSaved.setConta(contaRepository.save(new Conta(null, cliente)));

		Cliente objSaved = clientesService.save(cliente);
		return new ResponseEntity<Cliente>(objSaved, HttpStatus.OK);
//		return clienteSaved;
	}
	
	@GetMapping(value = "/clientes")
	public List<Cliente> getAllClientes(){
		List<Cliente> list =  clienteRepository.findAll();
		return list;
	}
}
