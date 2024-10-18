package org.serratec.backend.servicedto.domain;

import java.time.LocalDate; // Importa a classe LocalDate para representar datas
import java.util.Objects; // Utilizado para implementar os métodos equals e hashCode de maneira eficiente

import jakarta.persistence.Column; // Anotação usada para personalizar colunas de tabelas no banco de dados
import jakarta.persistence.Entity; // Indica que esta classe é uma entidade JPA
import jakarta.persistence.GeneratedValue; // Anotação para gerar automaticamente o valor do identificador
import jakarta.persistence.GenerationType; // Define a estratégia de geração do valor do identificador
import jakarta.persistence.Id; // Indica que o campo é a chave primária da tabela

@Entity // Define que esta classe é uma entidade e será mapeada para uma tabela no banco
		// de dados
public class Funcionario {

	@Id // Define que o campo "id" é a chave primária da tabela
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Indica que o valor da chave primária será gerado
														// automaticamente pelo banco de dados
	@Column(name = "id_funcionario") // Personaliza o nome da coluna no banco de dados como "id_funcionario"
	private Long id;

	private String nome; // Campo que representa o nome do funcionário

	private Double salario; // Campo que representa o salário do funcionário

	@Column(name = "data_nascimento") // Personaliza o nome da coluna no banco de dados como "data_nascimento"
	private LocalDate dataNascimento; // Campo que representa a data de nascimento do funcionário

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

	// Método getter para o campo "salario"
	public Double getSalario() {
		return salario;
	}

	// Método setter para o campo "salario"
	public void setSalario(Double salario) {
		this.salario = salario;
	}

	// Método getter para o campo "dataNascimento"
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	// Método setter para o campo "dataNascimento"
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	// Sobrescreve o método hashCode para garantir uma comparação eficiente de
	// objetos com base no campo "id"
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	// Sobrescreve o método equals para garantir que dois objetos Funcionario são
	// iguais se tiverem o mesmo "id"
	@Override
	public boolean equals(Object obj) {
		if (this == obj) // Se os objetos forem o mesmo (mesma referência), retorna true
			return true;
		if (obj == null) // Se o objeto comparado for nulo, retorna false
			return false;
		if (getClass() != obj.getClass()) // Se os objetos forem de classes diferentes, retorna false
			return false;
		Funcionario other = (Funcionario) obj; // Converte o objeto para Funcionario
		return Objects.equals(id, other.id); // Compara os IDs dos funcionários
	}

}

/*Explicação do Código:

Classe Entidade (JPA):

A classe Funcionario é anotada com @Entity, o que significa que ela será mapeada para uma tabela no banco de dados. O nome da tabela será o nome da classe por padrão, mas pode ser personalizado.
Chave Primária:

@Id: Define o campo id como a chave primária.
@GeneratedValue(strategy = GenerationType.IDENTITY): A estratégia IDENTITY é usada para que o banco de dados gere o valor do ID automaticamente, o que é comum em bancos de dados como MySQL e PostgreSQL.
@Column(name = "id_funcionario"): Personaliza o nome da coluna correspondente ao campo id no banco de dados como id_funcionario.
Outros Campos:

O campo nome armazena o nome do funcionário.
O campo salario armazena o valor do salário do funcionário.
O campo dataNascimento armazena a data de nascimento, utilizando a classe LocalDate para representar a data de forma mais robusta.
Métodos Getter e Setter:

Métodos getters e setters são usados para acessar e modificar os valores dos campos privados da classe. Eles seguem as boas práticas de encapsulamento.
Sobrescrita dos Métodos hashCode e equals:

O método hashCode() é sobrescrito para gerar um código de hash baseado no campo id, o que é útil em estruturas de dados que utilizam hashing, como HashSet ou HashMap.
O

método equals() é sobrescrito para comparar dois objetos Funcionario com base no id. Se dois objetos Funcionario tiverem o mesmo id, eles serão considerados iguais.
*/