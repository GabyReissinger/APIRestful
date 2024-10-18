package org.serratec.backend.servicedto.repository; // Declaração do pacote onde a interface está localizada

import java.util.List; // Importa a classe List para trabalhar com listas

import org.serratec.backend.servicedto.domain.Funcionario; // Importa a classe Funcionario, que representa a entidade do banco de dados
import org.serratec.backend.servicedto.dto.FuncionarioSalarioDTO; // Importa a classe FuncionarioSalarioDTO, que é um DTO para transferir dados
import org.springframework.data.domain.Page; // Importa a classe Page para manipulação de páginas de resultados
import org.springframework.data.domain.Pageable; // Importa a classe Pageable para paginar resultados
import org.springframework.data.jpa.repository.JpaRepository; // Importa a interface JpaRepository para operações CRUD
import org.springframework.data.jpa.repository.Query; // Importa a anotação Query para definir consultas personalizadas
import org.springframework.stereotype.Repository; // Importa a anotação Repository para marcar a interface como um repositório

// Interface FuncionarioRepository: Define operações de acesso a dados para a entidade Funcionario
@Repository // Anotação que indica que esta interface é um repositório Spring
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	// Método para buscar Funcionarios com salário dentro de um intervalo
	Page<Funcionario> findBySalarioBetween(Double valorMinimo, Double valorMaximo, Pageable pageable);

	// Consulta personalizada usando JPQL para buscar Funcionarios com salário
	// dentro de um intervalo
	@Query("SELECT f FROM Funcionario f WHERE f.salario >= :valorMinimo AND f.salario <= :valorMaximo")
	Page<Funcionario> buscarSalario(Double valorMinimo, Double valorMaximo, Pageable pageable);

	// Consulta nativa para buscar Funcionarios com salário dentro de um intervalo
	@Query(value = "SELECT * FROM Funcionario f WHERE f.salario >= :valorMinimo AND f.salario <= :valorMaximo", nativeQuery = true)
	Page<Funcionario> buscarSalarioNativo(Double valorMinimo, Double valorMaximo, Pageable pageable);

	// Consulta personalizada usando JPQL para buscar Funcionarios por nome
	@Query("SELECT f FROM Funcionario f WHERE UPPER(f.nome) LIKE UPPER(CONCAT('%', :paramNome, '%'))")
	Page<Funcionario> buscarPorNome(String paramNome, Pageable pageable);

	// Método para buscar Funcionarios cujo nome contém uma substring, ignorando
	// maiúsculas/minúsculas
	Page<Funcionario> findByNomeContainingIgnoreCase(String paramNome, Pageable pageable);

	// Consulta nativa para buscar estatísticas de salários agrupados por idade
	@Query(value = """
			    SELECT
			        date_part('year', age(now(), data_nascimento)) as idade,
			        avg(salario) as mediaSalario,
			        min(salario) as menorSalario,
			        max(salario) as maiorSalario,
			        count(*) as totalFuncionarios
			    FROM funcionario
			    GROUP BY idade
			    HAVING count(*) > 1
			    ORDER BY idade DESC
			""", nativeQuery = true)
	List<FuncionarioSalarioDTO> buscarSalariosPorIdade();
}
/*
 * @Query: Anotação que permite definir consultas personalizadas. Existem três
 * consultas definidas: buscarSalario: Usa JPQL para buscar Funcionarios com
 * salários dentro de um intervalo. buscarSalarioNativo: Usa uma consulta SQL
 * nativa para buscar Funcionarios com salários dentro de um intervalo.
 * buscarPorNome: Usa JPQL para buscar Funcionarios cujo nome contém uma
 * substring, ignorando maiúsculas/minúsculas.
 */
