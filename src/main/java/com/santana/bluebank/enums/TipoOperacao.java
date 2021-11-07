package com.santana.bluebank.enums;

public enum TipoOperacao {
	
	TRANSFERENCIA("Transferencia");

	private String descricao;

	private TipoOperacao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
