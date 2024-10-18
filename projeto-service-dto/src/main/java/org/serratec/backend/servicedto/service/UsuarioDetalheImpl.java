package org.serratec.backend.servicedto.service;

import org.serratec.backend.servicedto.domain.Usuario;  // Classe de domínio que representa um usuário
import org.serratec.backend.servicedto.repository.UsuarioRepository;  // Repositório que permite acesso à base de dados para Usuário
import org.springframework.beans.factory.annotation.Autowired;  // Anotação para injeção automática de dependências
import org.springframework.security.core.userdetails.UserDetails;  // Interface que representa as informações de um usuário
import org.springframework.security.core.userdetails.UserDetailsService;  // Interface do Spring Security para carregamento de usuários
import org.springframework.security.core.userdetails.UsernameNotFoundException;  // Exceção lançada quando um usuário não é encontrado
import org.springframework.stereotype.Service;  // Define essa classe como um serviço do Spring

@Service  // Indica que essa classe faz parte da camada de serviço da aplicação, sendo gerenciada pelo Spring
public class UsuarioDetalheImpl implements UserDetailsService {  // Implementa a interface UserDetailsService do Spring Security

    @Autowired  // Injeção automática do repositório que contém os dados dos usuários
    private UsuarioRepository usuarioRepository;

    // Sobrescreve o método da interface UserDetailsService que carrega um usuário pelo nome de usuário (email no caso)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(username);  // Busca o usuário pelo email (username)
        
        // Se o usuário não for encontrado, lança uma exceção indicando que o usuário não foi encontrado
        if (usuario == null) {
            throw new RuntimeException();  // Lança uma exceção se o usuário não existir
        }

        // Se o usuário for encontrado, retorna o próprio objeto Usuario que implementa UserDetails
        return usuario;
    }
}
