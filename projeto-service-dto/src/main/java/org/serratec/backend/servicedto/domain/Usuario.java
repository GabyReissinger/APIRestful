package org.serratec.backend.servicedto.domain;

import java.io.Serializable; // Interface que permite que objetos sejam convertidos em bytes, necessários para transmissão ou armazenamento
import java.util.ArrayList; // Lista dinâmica
import java.util.Collection; // Interface que define um conjunto de elementos
import java.util.HashSet; // Implementação de um Set (conjunto) que não permite elementos duplicados
import java.util.List; // Interface de listas
import java.util.Objects; // Utilizado para implementar os métodos equals e hashCode de maneira eficiente
import java.util.Set; // Interface que define um conjunto de elementos, sem duplicatas

import org.springframework.security.core.GrantedAuthority; // Interface que define as permissões concedidas a um usuário
import org.springframework.security.core.authority.SimpleGrantedAuthority; // Implementação simples de GrantedAuthority, usada para representar uma permissão
import org.springframework.security.core.userdetails.UserDetails; // Interface fornecida pelo Spring Security, que define os dados principais de um usuário

import jakarta.persistence.CascadeType; // Define as operações em cascata em relações JPA
import jakarta.persistence.Column; // Anotação usada para personalizar colunas de tabelas no banco de dados
import jakarta.persistence.Entity; // Define que a classe é uma entidade JPA
import jakarta.persistence.FetchType; // Define a estratégia de carregamento dos dados relacionados (EAGER ou LAZY)
import jakarta.persistence.GeneratedValue; // Anotação para gerar automaticamente o valor do identificador
import jakarta.persistence.GenerationType; // Define a estratégia de geração do valor do identificador
import jakarta.persistence.Id; // Indica que o campo é a chave primária da tabela
import jakarta.persistence.OneToMany; // Define uma relação um-para-muitos

@Entity // Indica que esta classe é uma entidade JPA mapeada para uma tabela no banco de
		// dados
public class Usuario implements UserDetails, Serializable { // Implementa UserDetails para integração com Spring
															// Security e Serializable para permitir que objetos sejam
															// serializados

	private static final long serialVersionUID = 1L; // Identificador da versão da classe, necessário para serialização

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Define que o ID será gerado automaticamente pelo banco de
														// dados
	@Column(name = "id_usuario") // Personaliza o nome da coluna "id_usuario"
	private Long id;

	private String nome; // Nome do usuário

	private String email; // E-mail do usuário, que será utilizado como nome de usuário para autenticação

	private String senha; // Senha do usuário para autenticação

	@OneToMany(mappedBy = "id.usuario", fetch = FetchType.EAGER, cascade = CascadeType.ALL) // Relação um-para-muitos
																							// entre usuário e perfis de
																							// usuário, carregamento
																							// ansioso (EAGER) e todas
																							// as operações são em
																							// cascata
	private Set<UsuarioPerfil> usuarioPerfis = new HashSet<>(); // Conjunto de perfis associados ao usuário, sem
																// duplicatas

	// Construtor padrão
	public Usuario() {
	}

	// Construtor com parâmetros
	public Usuario(Long id, String nome, String email, String senha) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

	// Métodos getters e setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<UsuarioPerfil> getUsuarioPerfis() {
		return usuarioPerfis;
	}

	public void setUsuarioPerfis(Set<UsuarioPerfil> usuarioPerfis) {
		this.usuarioPerfis = usuarioPerfis;
	}

	// Sobrescreve o método hashCode para gerar um código de hash baseado no campo
	// "id"
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	// Sobrescreve o método equals para garantir que dois objetos Usuario são iguais
	// se tiverem o mesmo "id"
	@Override
	public boolean equals(Object obj) {
		if (this == obj) // Se os objetos forem o mesmo (mesma referência), retorna true
			return true;
		if (obj == null) // Se o objeto comparado for nulo, retorna false
			return false;
		if (getClass() != obj.getClass()) // Se os objetos forem de classes diferentes, retorna false
			return false;
		Usuario other = (Usuario) obj; // Converte o objeto para Usuario
		return Objects.equals(id, other.id); // Compara os IDs dos usuários
	}

	// Implementação do método getAuthorities da interface UserDetails
	// Retorna as autoridades (perfis) do usuário
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>(); // Cria uma lista de autoridades
		for (UsuarioPerfil usuarioPerfil : getUsuarioPerfis()) { // Para cada perfil associado ao usuário
			authorities.add(new SimpleGrantedAuthority(usuarioPerfil.getId().getPerfil().getNome())); // Adiciona a
																										// autoridade
																										// correspondente
																										// ao perfil
		}
		return authorities; // Retorna a lista de autoridades
	}

	// Implementação do método getPassword da interface UserDetails, que retorna a
	// senha do usuário
	@Override
	public String getPassword() {
		return senha;
	}

	// Implementação do método getUsername da interface UserDetails, que retorna o
	// nome de usuário (e-mail, neste caso)
	@Override
	public String getUsername() {
		return email;
	}
}
/*
 * Explicação do Código: Serializable e UserDetails:
 * 
 * A classe Usuario implementa Serializable para permitir que objetos do tipo
 * Usuario sejam convertidos em bytes para transmissão ou armazenamento. A
 * implementação da interface UserDetails é necessária para a integração com o
 * Spring Security, onde ela fornece os detalhes de autenticação e autorização
 * de um usuário. Chave Primária e Geração Automática:
 * 
 * A anotação @Id define o campo id como a chave primária da tabela,
 * e @GeneratedValue(strategy = GenerationType.IDENTITY) permite que o banco de
 * dados gere automaticamente o valor do id. Relação Um-para-Muitos
 * (One-to-Many):
 * 
 * A anotação @OneToMany indica uma relação entre a entidade Usuario e
 * UsuarioPerfil, onde um usuário pode ter muitos perfis. O FetchType.EAGER
 * garante que os perfis sejam carregados junto com o usuário, e CascadeType.ALL
 * define que todas as operações realizadas no Usuario (inserção, atualização,
 * remoção) serão propagadas para os perfis associados. Métodos Getters e
 * Setters:
 * 
 * Permitem acessar e modificar os atributos privados da classe Usuario, como
 * id, nome, email, senha, e usuarioPerfis. Equals e HashCode:
 * 
 * Sobrescrevem os métodos equals e hashCode para garantir a comparação correta
 * de objetos Usuario com base no id. Métodos da Interface UserDetails:
 * 
 * getAuthorities(): Retorna as permissões (ou papéis) do usuário. No caso,
 * percorre os perfis associados ao usuário e cria uma lista de
 * GrantedAuthority. getPassword(): Retorna a senha do usuário, usada para
 * autenticação. getUsername(): Retorna o nome de usuário, que neste caso é o
 * e-mail, usado para login.
 */
