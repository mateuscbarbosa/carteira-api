package br.com.alura.carteira.infra;

public class RegraDeNegocioException extends RuntimeException {
	
	public RegraDeNegocioException(String mensagem) {
		super(mensagem);
	}
}
