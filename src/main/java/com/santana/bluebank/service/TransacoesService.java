package com.santana.bluebank.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santana.bluebank.entities.Conta;
import com.santana.bluebank.entities.Transacao;
import com.santana.bluebank.exception.ContaNaoEncontradaException;
import com.santana.bluebank.exception.TransacaoException;
import com.santana.bluebank.repository.ContaRepository;
import com.santana.bluebank.repository.TransacoesRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TransacoesService {

	private ContaRepository contaRepository;
	private TransacoesRepository transacoesRepository;

	/*
	 Método para realizar uma transferência
	 @param obj
	 @return Transacao
	 */
	public Transacao transferir(Transacao obj) throws TransacaoException, ContaNaoEncontradaException {
		Conta contaOrigem = verificaConta(obj.getNumeroContaOrigem());
		Conta contaDestino = verificaConta(obj.getNumeroContaDestino());
		Transacao transacaoSalva = null;

		if (verificaSaldo(contaOrigem.getLimiteDisponivel(), obj.getValor())) {

			obj.setNomeDepositante(contaOrigem.getCliente().getNome());
			contaOrigem.setLimiteDisponivel(contaOrigem.getLimiteDisponivel().subtract(obj.getValor()));
			obj.setNomeDestinatario(contaDestino.getCliente().getNome());
			contaDestino.setLimiteDisponivel(contaDestino.getLimiteDisponivel().add(obj.getValor()));

			transacaoSalva = transacoesRepository.save(obj);

		}
		return transacaoSalva;
	}

	/*
	 Método para validar o valor da transferência e saldo em conta, além de valores incorretos
	 @param saldo
	 @param valorTransferencia
	 @return boolean
	 */
	public boolean verificaSaldo(BigDecimal saldo, BigDecimal valorTransferencia) throws TransacaoException {
		if (valorTransferencia.compareTo(saldo) > 0) {
			throw new TransacaoException("Valor da transferência Maior que o Saldo disponível!");

		} else if (valorTransferencia.compareTo(new BigDecimal("0")) == 0) {
			throw new TransacaoException("Valor da transferência não pode ser 0!");

		} else if (valorTransferencia.compareTo(new BigDecimal("0")) < 0) {
			throw new TransacaoException("Valor da transferência não pode ser negativo!");

		}
		return true;
	}

	/*
	 Método para validar se a conta existe no banco
	 @param c
	 @return boolean
	 */
	public Conta verificaConta(String c) throws ContaNaoEncontradaException {
		c = validaConta(c);

		Conta conta = contaRepository.findByNumeroConta(c)
				.orElseThrow(() -> new ContaNaoEncontradaException("Conta não encontrada!"));

		return conta;
	}

	/*
	 Método para validar se a conta existe (conta sem o '-')
	 @param c
	 @return boolean
	 */
	public String validaConta(String c) {
		if (c.equals("") || c.length() < 6 || c.length() > 7) {
			return null;

		} else if (c.length() == 6) {
			StringBuilder str = new StringBuilder();
			str.append(c);
			str.insert(5, "-");
			return c = str.toString();
		}
		return c;
	}

	/*
	 Método para listar todas as transações
	 @return List<Transacao>
	 */
	public List<Transacao> listarTransacoes(){
		List<Transacao> transacoes = transacoesRepository.findAll();
		Collections.sort(transacoes);

		return transacoes;
	}
}
