package org.serratec.backend.servicedto.dto; // Declaração do pacote onde a classe está localizada

import java.util.HashSet; // Importa a classe HashSet para uso na coleção de perfis
import java.util.Set; // Importa a interface Set para a coleção de perfis

import org.serratec.backend.servicedto.domain.Perfil; // Importa a classe Perfil
import org.serratec.backend.servicedto.domain.Usuario; // Importa a classe Usuario
import org.serratec.backend.servicedto.domain.UsuarioPerfil; // Importa a classe UsuarioPerfil

// Classe UsuarioDTO: Representa um objeto de transferência de dados para informações de usuários
public class UsuarioDTO {

	private Long id; // Atributo privado que armazena o ID do usuário
	private String nome; // Atributo privado que armazena o nome do usuário
	private String email; // Atributo privado que armazena o email do usuário
	private Set<Perfil> perfis; // Atributo privado que armazena um conjunto de perfis associados ao usuário

	// Construtor padrão
	public UsuarioDTO() {
	}

	// Construtor que recebe ID, nome e email
	public UsuarioDTO(Long id, String nome, String email) {
		this.id = id; // Inicializa o ID do usuário
		this.nome = nome; // Inicializa o nome do usuário
		this.email = email; // Inicializa o email do usuário
	}

	// Construtor que recebe um objeto Usuario e inicializa o DTO
	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId(); // Inicializa o ID a partir do objeto Usuario
		this.nome = usuario.getNome(); // Inicializa o nome a partir do objeto Usuario
		this.email = usuario.getEmail(); // Inicializa o email a partir do objeto Usuario
		this.perfis = new HashSet<>(); // Inicializa o conjunto de perfis

		// Adiciona os perfis associados ao usuário ao conjunto de perfis
		for (UsuarioPerfil usuarioPerfil : usuario.getUsuarioPerfis()) {
			this.perfis.add(usuarioPerfil.getId().getPerfil()); // Adiciona o perfil ao conjunto
		}
	}

	// Método para obter o ID do usuário
	public Long getId() {
		return id; // Retorna o valor do atributo id
	}

	// Método para definir o ID do usuário
	public void setId(Long id) {
		this.id = id; // Atribui o valor passado ao atributo id
	}

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

	// Método para obter o conjunto de perfis do usuário
	public Set<Perfil> getPerfis() {
		return perfis; // Retorna o conjunto de perfis
	}

	// Método para definir o conjunto de perfis do usuário
	public void setPerfis(Set<Perfil> perfis) {
		this.perfis = perfis; // Atribui o conjunto passado ao atributo perfis
	}
}