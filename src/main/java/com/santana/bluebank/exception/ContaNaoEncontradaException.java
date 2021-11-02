package com.santana.bluebank.exception;

public class ContaNaoEncontradaException extends Exception {
	private static final long serialVersionUID = 1L;

	public ContaNaoEncontradaException(String message) {
        super(message);
    }
}
