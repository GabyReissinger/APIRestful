package org.serratec.backend.servicedto.domain;

import java.io.Serializable; // Interface que permite que objetos sejam convertidos em um fluxo de bytes
import java.util.Objects; // Classe que contém métodos utilitários, como o equals e o hashCode

import jakarta.persistence.Embeddable; // Anotação que indica que esta classe pode ser embutida em outra entidade
import jakarta.persistence.JoinColumn; // Anotação usada para personalizar a coluna de relacionamento (chave estrangeira)
import jakarta.persistence.ManyToOne; // Define uma relação muitos-para-um entre entidades

@Embeddable // Indica que esta classe pode ser embutida em outra entidade como parte de uma
			// chave composta
public class UsuarioPerfilPK implements Serializable { // Classe que representa a chave primária composta e é
														// serializável

	private static final long serialVersionUID = 1L; // Identificador único para a versão da classe serializável

	@ManyToOne // Define uma relação muitos-para-um com a entidade Usuario
	@JoinColumn(name = "id_usuario") // Define a coluna de junção que mapeia a relação entre UsuarioPerfilPK e
										// Usuario
	private Usuario usuario; // O usuário associado a este perfil

	@ManyToOne // Define uma relação muitos-para-um com a entidade Perfil
	@JoinColumn(name = "id_perfil") // Define a coluna de junção que mapeia a relação entre UsuarioPerfilPK e Perfil
	private Perfil perfil; // O perfil associado ao usuário

	// Getter para o atributo usuario
	public Usuario getUsuario() {
		return usuario;
	}

	// Setter para o atributo usuario
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	// Getter para o atributo perfil
	public Perfil getPerfil() {
		return perfil;
	}

	// Setter para o atributo perfil
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	// Sobrescrita do método hashCode para garantir que a comparação de objetos leve
	// em conta os atributos perfil e usuario
	@Override
	public int hashCode() {
		return Objects.hash(perfil, usuario); // Gera um código hash baseado nos atributos perfil e usuario
	}

	// Sobrescrita do método equals para garantir que dois objetos sejam iguais se
	// tiverem os mesmos valores para perfil e usuario
	@Override
	public boolean equals(Object obj) {
		if (this == obj)  // Verifica se o objeto comparado é o mesmo
			return true;
		if (obj == null)  // Verifica se o objeto comparado é nulo
			return false;
		if (getClass() != obj.getClass())  // Verifica se o objeto comparado é da mesma classe
			return false;
		UsuarioPerfilPK other = (UsuarioPerfilPK) obj;  // Faz o cast para UsuarioPerfilPK e compara os atributos perfil e usuario
		return Objects.equals(perfil, other.perfil) && Objects.equals(usuario, other.usuario);
	}

}
/*
 * Explicação do Código: Anotações JPA:
 * 
 * @Embeddable: Indica que a classe pode ser utilizada como parte de uma chave
 * composta em outra entidade, ou seja, ela será embutida como uma chave
 * primária composta.
 * 
 * @ManyToOne: Define que a relação entre as entidades UsuarioPerfilPK e
 * Usuario, assim como UsuarioPerfilPK e Perfil, é de muitos-para-um. Isso
 * significa que vários registros de UsuarioPerfilPK podem estar associados a um
 * único Usuario ou Perfil.
 * 
 * @JoinColumn(name = "id_usuario"): Personaliza o nome da coluna no banco de
 * dados para o relacionamento com Usuario, definindo que a chave estrangeira
 * será "id_usuario".
 * 
 * @JoinColumn(name = "id_perfil"): Faz o mesmo para o relacionamento com
 * Perfil, especificando "id_perfil" como a chave estrangeira. Atributos:
 * 
 * usuario: Representa o objeto Usuario relacionado a essa chave composta.
 * perfil: Representa o objeto Perfil relacionado a essa chave composta.
 * Serializable:
 * 
 * A implementação da interface Serializable permite que a classe seja
 * convertida em um fluxo de bytes, essencial para o funcionamento da JPA quando
 * se lida com chaves compostas. O campo serialVersionUID garante que a versão
 * correta da classe seja usada durante a desserialização. Métodos Getters e
 * Setters:
 * 
 * São usados para acessar e modificar os atributos usuario e perfil. Métodos
 * hashCode() e equals():
 * 
 * O método hashCode() é sobrescrito para garantir que dois objetos da classe
 * UsuarioPerfilPK que tenham os mesmos valores para usuario e perfil gerem o
 * mesmo código hash. Isso é importante para a comparação e armazenamento de
 * objetos em coleções como HashSet. O método equals() é sobrescrito para
 * verificar a igualdade entre dois objetos da classe UsuarioPerfilPK. Dois
 * objetos serão considerados iguais se tiverem os mesmos valores para usuario e
 * perfil. Função da Classe: A classe UsuarioPerfilPK representa a chave
 * primária composta da tabela intermediária que associa a entidade Usuario à
 * entidade Perfil. É usada para criar a relação muitos-para-muitos entre
 * Usuario e Perfil, onde cada combinação de um usuário com um perfil é única e
 * identificada por essa chave composta.
 */