package org.serratec.backend.servicedto.repository; // Declaração do pacote onde a interface está localizada

import org.serratec.backend.servicedto.domain.Usuario; // Importa a classe Usuario, que representa a entidade do banco de dados
import org.springframework.data.jpa.repository.JpaRepository; // Importa a interface JpaRepository para operações CRUD
import org.springframework.stereotype.Repository; // Importa a anotação Repository para marcar a interface como um repositório

// Interface UsuarioRepository: Define operações de acesso a dados para a entidade Usuario
@Repository // Anotação que indica que esta interface é um repositório Spring
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	// Método para buscar um Usuario pelo seu email
	Usuario findByEmail(String email);
}