package org.serratec.backend.servicedto.domain;

import java.util.Objects; // Utilizado para implementar os métodos equals e hashCode de maneira eficiente

import jakarta.persistence.Column; // Anotação usada para personalizar colunas de tabelas no banco de dados
import jakarta.persistence.Entity; // Indica que esta classe é uma entidade JPA
import jakarta.persistence.GeneratedValue; // Anotação para gerar automaticamente o valor do identificador
import jakarta.persistence.GenerationType; // Define a estratégia de geração do valor do identificador
import jakarta.persistence.Id; // Indica que o campo é a chave primária da tabela

@Entity // Define que esta classe é uma entidade e será mapeada para uma tabela no banco
		// de dados
public class Perfil {

	@Id // Define que o campo "id" é a chave primária da tabela
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Indica que o valor da chave primária será gerado
														// automaticamente pelo banco de dados
	@Column(name = "id_perfil") // Personaliza o nome da coluna no banco de dados como "id_perfil"
	private Long id;

	private String nome; // Campo que representa o nome do perfil

	// Método getter para o campo "id"
	public Long getId() {
		return id;
	}

	// Método setter para o campo "id"
	public void setId(Long id) {
		this.id = id;
	}

	// Método getter para o campo "nome"
	public String getNome() {
		return nome;
	}

	// Método setter para o campo "nome"
	public void setNome(String nome) {
		this.nome = nome;
	}

	// Sobrescreve o método hashCode para garantir uma comparação eficiente de
	// objetos com base no campo "id"
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	// Sobrescreve o método equals para garantir que dois objetos Perfil são iguais
	// se tiverem o mesmo "id"
	@Override
    public boolean equals(Object obj) {
        if (this == obj)  // Se os objetos forem o mesmo (mesma referência), retorna true
            return true;
        if (obj == null)  // Se o objeto comparado for nulo, retorna false
            return false;
        if (getClass() != obj.getClass())  // Se os objetos forem de classes diferentes, retorna false
            return false;
        Perfil other = (Perfil) obj;  // Converte o objeto para Perfil
        return Objects.equals(id, other.id);  // Compara os IDs dos perfis
    }
}
/*
 * Explicação do Código: Classe Entidade (JPA):
 * 
 * A classe Perfil é anotada com @Entity, o que significa que ela será mapeada
 * para uma tabela no banco de dados. O nome da tabela será o nome da classe por
 * padrão, mas pode ser personalizado. Chave Primária:
 * 
 * @Id: Define o campo id como a chave primária.
 * 
 * @GeneratedValue(strategy = GenerationType.IDENTITY): A estratégia IDENTITY é
 * usada para que o banco de dados gere o valor do ID automaticamente, o que é
 * comum em bancos de dados como MySQL e PostgreSQL.
 * 
 * @Column(name = "id_perfil"): Personaliza o nome da coluna correspondente ao
 * campo id no banco de dados como id_perfil. Outros Campos:
 * 
 * O campo nome armazena o nome do perfil. Métodos Getter e Setter:
 * 
 * Métodos getters e setters são usados para acessar e modificar os valores dos
 * campos privados da classe. Eles seguem as boas práticas de encapsulamento.
 * Sobrescrita dos Métodos hashCode e equals:
 * 
 * O método hashCode() é sobrescrito para gerar um código de hash baseado no
 * campo id, útil em estruturas de dados que utilizam hashing. O método equals()
 * é sobrescrito para comparar dois objetos Perfil com base no id. Se dois
 * objetos Perfil tiverem o mesmo id, eles serão considerados iguais.
 */
