package org.serratec.backend.servicedto.domain;

import java.time.LocalDate; // Classe que representa uma data sem horário

import jakarta.persistence.Column; // Anotação usada para personalizar colunas no banco de dados
import jakarta.persistence.EmbeddedId; // Anotação usada para identificar uma chave composta
import jakarta.persistence.Entity; // Define que esta classe é uma entidade JPA
import jakarta.persistence.Table; // Anotação usada para especificar a tabela correspondente a uma entidade no banco de dados

@Entity // Indica que esta classe é uma entidade JPA que será mapeada para uma tabela no
		// banco de dados
@Table(name = "usuario_perfil") // Define o nome da tabela no banco de dados para esta entidade
public class UsuarioPerfil {

	@EmbeddedId // Anotação que indica que a chave primária é composta, utilizando outra classe
				// como embutida
	private UsuarioPerfilPK id = new UsuarioPerfilPK(); // A chave primária composta (UsuarioPerfilPK) é usada para
														// associar o Usuário e o Perfil

	@Column(name = "data_criacao") // Define o nome da coluna no banco de dados para armazenar a data de criação
	private LocalDate dataCriacao; // Data em que o perfil foi associado ao usuário

	// Construtor padrão
	public UsuarioPerfil() {
	}

	// Construtor que recebe um usuário, perfil e data de criação como parâmetros
	public UsuarioPerfil(Usuario usuario, Perfil perfil, LocalDate dataCriacao) {
		this.id.setUsuario(usuario); // Define o usuário dentro da chave composta
		this.id.setPerfil(perfil); // Define o perfil dentro da chave composta
		this.dataCriacao = dataCriacao; // Define a data de criação
	}

	// Método getter para a chave composta (id)
	public UsuarioPerfilPK getId() {
		return id;
	}

	// Método setter para a chave composta (id)
	public void setId(UsuarioPerfilPK id) {
		this.id = id;
	}

	// Método getter para a data de criação
	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	// Método setter para a data de criação
	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

}
/*
 * Explicação do Código: Anotações JPA:
 * 
 * @Entity: Indica que a classe será mapeada para uma tabela no banco de dados.
 * 
 * @Table(name = "usuario_perfil"): Especifica que a tabela correspondente a
 * esta entidade no banco será chamada "usuario_perfil".
 * 
 * @EmbeddedId: Define que a chave primária da entidade é composta, ou seja, é
 * formada por múltiplos campos agrupados dentro da classe UsuarioPerfilPK.
 * Chave Primária Composta (EmbeddedId):
 * 
 * A classe UsuarioPerfilPK representa a chave composta que contém a referência
 * a um Usuário e um Perfil. O campo id é do tipo UsuarioPerfilPK, que encapsula
 * essas duas informações. A anotação @EmbeddedId diz que essa chave composta
 * será usada como a chave primária para a entidade. Atributos:
 * 
 * dataCriacao: É um atributo que armazena a data em que o perfil foi associado
 * ao usuário. Ele é mapeado para uma coluna chamada "data_criacao" no banco de
 * dados, e o tipo LocalDate é usado para representar a data sem a necessidade
 * de um horário. Construtores:
 * 
 * O construtor padrão (public UsuarioPerfil()) é necessário para que o JPA
 * possa criar instâncias da entidade. O construtor adicional recebe um Usuario,
 * um Perfil e uma LocalDate, e define o valor desses atributos no objeto
 * UsuarioPerfil. Métodos Getters e Setters:
 * 
 * Permitem acessar e modificar os valores dos atributos privados da classe,
 * como id (a chave composta) e dataCriacao. Função da Classe: A classe
 * UsuarioPerfil representa a relação entre as entidades Usuário e Perfil,
 * formando uma associação de muitos-para-muitos. Essa entidade intermediária
 * armazena a data de criação da associação, permitindo controlar quando o
 * perfil foi atribuído ao usuário. A chave composta (UsuarioPerfilPK) assegura
 * que um usuário não possa ter o mesmo perfil atribuído mais de uma vez.
 */
