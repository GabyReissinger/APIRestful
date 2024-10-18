package org.serratec.backend.servicedto.dto; // Declaração do pacote onde a classe está localizada

import java.util.Set; // Importa a interface Set para a coleção de perfis

import org.serratec.backend.servicedto.domain.Perfil; // Importa a classe Perfil

// Classe UsuarioInserirDTO: Representa um objeto de transferência de dados para informações de inserção de usuários
public class UsuarioInserirDTO {

	private String nome; // Atributo privado que armazena o nome do usuário
	private String email; // Atributo privado que armazena o email do usuário
	private String senha; // Atributo privado que armazena a senha do usuário
	private String confirmaSenha; // Atributo privado que armazena a confirmação da senha
	private Set<Perfil> perfis; // Atributo privado que armazena um conjunto de perfis associados ao usuário

	// Método para obter o nome do usuário
	public String getNome() {
		return nome; // Retorna o valor do atributo nome
	}

	// Método para definir o nome do usuário
	public void setNome(String nome) {
		this.nome = nome; // Atribui o valor passado ao atributo nome
	}

	// Método para obter o email do usuário
	public String getEmail() {
		return email; // Retorna o valor do atributo email
	}

	// Método para definir o email do usuário
	public void setEmail(String email) {
		this.email = email; // Atribui o valor passado ao atributo email
	}

	// Método para obter a senha do usuário
	public String getSenha() {
		return senha; // Retorna o valor do atributo senha
	}

	// Método para definir a senha do usuário
	public void setSenha(String senha) {
		this.senha = senha; // Atribui o valor passado ao atributo senha
	}

	// Método para obter a confirmação da senha
	public String getConfirmaSenha() {
		return confirmaSenha; // Retorna o valor do atributo confirmaSenha
	}

	// Método para definir a confirmação da senha
	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha; // Atribui o valor passado ao atributo confirmaSenha
	}

	// Método para obter o conjunto de perfis do usuário
	public Set<Perfil> getPerfis() {
		return perfis; // Retorna o conjunto de perfis
	}

	// Método para definir o conjunto de perfis do usuário
	public void setPerfis(Set<Perfil> perfis) {
		this.perfis = perfis; // Atribui o conjunto passado ao atributo perfis
	}
}