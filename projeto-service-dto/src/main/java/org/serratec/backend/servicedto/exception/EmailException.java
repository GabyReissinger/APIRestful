package org.serratec.backend.servicedto.exception; // Declaração do pacote onde a classe está localizada

// Classe EmailException: Exceção personalizada para tratar erros relacionados a email
public class EmailException extends RuntimeException {

	private static final long serialVersionUID = 1L; // Versão da serialização da classe

	// Construtor que recebe uma mensagem de erro
	public EmailException(String message) {
		super(message); // Chama o construtor da classe pai (RuntimeException) com a mensagem fornecida
	}
}