package com.santana.bluebank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.santana.bluebank.entities.Cliente;
import com.santana.bluebank.entities.Conta;
import com.santana.bluebank.repository.ClienteRepository;
import com.santana.bluebank.repository.ContaRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/v1")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ContaController {

	private ContaRepository contaRepository;
	private ClienteRepository clienteRepository;
	
	@GetMapping(value = "/contas")
	public List<Conta> getAllContas(){
		List<Conta> list =  contaRepository.findAll();
		return list;
	}
	
	@PostMapping(value = "/contas")
	public Conta createAccount(@RequestParam String cpf) {
		Cliente cliente = clienteRepository.findByCpf(cpf).orElseThrow(() -> new IllegalArgumentException());	

		return contaRepository.save(new Conta(null, cliente));
	}
	
	
}
