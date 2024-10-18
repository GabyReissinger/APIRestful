package org.serratec.backend.servicedto.service;

import java.util.Optional;  // Classe Optional que lida com valores que podem ou não estar presentes

import org.serratec.backend.servicedto.domain.Perfil;  // Classe Perfil, representando a entidade que está sendo manipulada
import org.serratec.backend.servicedto.repository.PerfilRepository;  // Interface do repositório para Perfil
import org.springframework.beans.factory.annotation.Autowired;  // Anotação que injeta automaticamente as dependências necessárias
import org.springframework.stereotype.Service;  // Define esta classe como um serviço do Spring

@Service  // Indica que esta classe é um serviço, compondo a camada de lógica de negócios
public class PerfilService {
	
	@Autowired  // Injeta automaticamente uma instância de PerfilRepository na classe
	private PerfilRepository perfilRepository;  // Repositório responsável por interagir com a base de dados para a entidade Perfil

	// Método que busca um Perfil no banco de dados pelo seu id
	public Perfil buscar (Long id) {
		Optional<Perfil> perfilOpt = perfilRepository.findById(id);  // Busca um perfil pelo id, retornando um Optional
		return perfilOpt.get();  // Retorna o objeto perfil encontrado (ou lança uma exceção se não houver perfil com o id fornecido)
	}
}
