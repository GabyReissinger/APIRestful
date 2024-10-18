package org.serratec.backend.servicedto.dto; // Declaração do pacote onde a classe está localizada

// Classe LoginDTO: Representa um objeto de transferência de dados para informações de login
public class LoginDTO {

	public String username; // Atributo público que armazena o nome de usuário
	private String password; // Atributo privado que armazena a senha do usuário

	// Método para obter o nome de usuário
	public String getUsername() {
		return username; // Retorna o valor do atributo username
	}

	// Método para definir o nome de usuário
	public void setUsername(String username) {
		this.username = username; // Atribui o valor passado ao atributo username
	}

	// Método para obter a senha
	public String getPassword() {
		return password; // Retorna o valor do atributo password
	}

	// Método para definir a senha
	public void setPassword(String password) {
		this.password = password; // Atribui o valor passado ao atributo password
	}
}