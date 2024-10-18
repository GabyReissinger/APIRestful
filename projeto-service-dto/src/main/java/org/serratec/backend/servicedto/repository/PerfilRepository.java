package org.serratec.backend.servicedto.repository; // Declaração do pacote onde a interface está localizada

import org.serratec.backend.servicedto.domain.Perfil; // Importa a classe Perfil, que representa a entidade do banco de dados
import org.springframework.data.jpa.repository.JpaRepository; // Importa a interface JpaRepository para operações CRUD
import org.springframework.stereotype.Repository; // Importa a anotação Repository para marcar a interface como um repositório

// Interface PerfilRepository: Define operações de acesso a dados para a entidade Perfil
@Repository // Anotação que indica que esta interface é um repositório Spring
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

	// A interface JpaRepository fornece métodos padrão para operações CRUD, como:
	// - save(Perfil perfil): Salva ou atualiza um Perfil no banco de dados
	// - delete(Perfil perfil): Exclui um Perfil do banco de dados
	// - findAll(): Retorna uma lista de todos os Perfis no banco de dados
	// - findById(Long id): Retorna um Perfil pelo seu identificador
	// - findByNome(String nome): Retorna uma lista de Perfis cujo nome contém a
	// substring fornecida
}