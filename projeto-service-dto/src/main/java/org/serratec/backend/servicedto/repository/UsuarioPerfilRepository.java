package org.serratec.backend.servicedto.repository; // Declaração do pacote onde a interface está localizada

import org.serratec.backend.servicedto.domain.UsuarioPerfil; // Importa a classe UsuarioPerfil, que representa a entidade do banco de dados
import org.serratec.backend.servicedto.domain.UsuarioPerfilPK; // Importa a classe UsuarioPerfilPK, que representa a chave primária composta da entidade
import org.springframework.data.jpa.repository.JpaRepository; // Importa a interface JpaRepository para operações CRUD
import org.springframework.stereotype.Repository; // Importa a anotação Repository para marcar a interface como um repositório

// Interface UsuarioPerfilRepository: Define operações de acesso a dados para a entidade UsuarioPerfil
@Repository // Anotação que indica que esta interface é um repositório Spring
public interface UsuarioPerfilRepository extends JpaRepository<UsuarioPerfil, UsuarioPerfilPK> {

	// A interface JpaRepository fornece métodos padrão para operações CRUD, como:
	// - save(UsuarioPerfil usuarioPerfil): Salva ou atualiza um UsuarioPerfil no
	// banco de dados
	// - delete(UsuarioPerfil usuarioPerfil): Exclui um UsuarioPerfil do banco de
	// dados
	// - findAll(): Retorna uma lista de todos os UsuarioPerfils no banco de dados
	// - findById(UsuarioPerfilPK id): Retorna um UsuarioPerfil pelo seu
	// identificador composto
}