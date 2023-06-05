package br.com.alinson.testeunimed.exception;

public class SenhaInvalidaException extends RuntimeException {

	public SenhaInvalidaException() {
        super("Senha inválida");
    }
	
}
