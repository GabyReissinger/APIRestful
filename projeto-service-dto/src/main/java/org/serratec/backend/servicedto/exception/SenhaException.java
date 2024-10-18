package org.serratec.backend.servicedto.exception; // Declaração do pacote onde a classe está localizada

// Classe SenhaException: Exceção personalizada para tratar erros relacionados a senhas
public class SenhaException extends RuntimeException {

	private static final long serialVersionUID = 1L; // Versão da serialização da classe

	// Construtor que recebe uma mensagem de erro
	public SenhaException(String message) {
		super(message); // Chama o construtor da classe pai (RuntimeException) com a mensagem fornecida
	}
}