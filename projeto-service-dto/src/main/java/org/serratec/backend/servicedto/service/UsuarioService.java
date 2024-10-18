package org.serratec.backend.servicedto.service;

import java.time.LocalDate; // Classe usada para trabalhar com datas
import java.util.HashSet; // Implementação de Set que não permite valores duplicados
import java.util.List; // Interface que representa uma lista de elementos
import java.util.Optional; // Classe que pode ou não conter um valor (evita uso de null)
import java.util.Set; // Interface que representa um conjunto de elementos, sem duplicatas

import org.serratec.backend.servicedto.domain.Perfil; // Classe de domínio que representa o Perfil
import org.serratec.backend.servicedto.domain.Usuario; // Classe de domínio que representa o Usuário
import org.serratec.backend.servicedto.domain.UsuarioPerfil; // Classe que relaciona Usuários a Perfis
import org.serratec.backend.servicedto.dto.UsuarioDTO; // Classe DTO (Data Transfer Object) para Usuário
import org.serratec.backend.servicedto.dto.UsuarioInserirDTO; // Classe DTO usada para inserir um novo Usuário
import org.serratec.backend.servicedto.exception.EmailException; // Exceção personalizada para emails duplicados
import org.serratec.backend.servicedto.exception.SenhaException; // Exceção personalizada para senhas que não coincidem
import org.serratec.backend.servicedto.repository.UsuarioRepository; // Repositório para acessar dados do Usuário
import org.springframework.beans.factory.annotation.Autowired; // Anotação para injeção automática de dependências
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // Classe para criptografar senhas com bcrypt
import org.springframework.stereotype.Service; // Define que essa classe faz parte da camada de serviço
import org.springframework.transaction.annotation.Transactional; // Garante que o método seja executado em uma transação

@Service // Define que esta classe é um serviço gerenciado pelo Spring
public class UsuarioService {

	@Autowired // Injeção automática do repositório de Usuários
	private UsuarioRepository usuarioRepository;

	@Autowired // Injeção automática do serviço de Perfis
	private PerfilService perfilService;

	@Autowired // Injeção automática do encoder para criptografia de senhas
	private BCryptPasswordEncoder encoder;

	// Método para buscar todos os usuários e convertê-los em DTOs
	public List<UsuarioDTO> findAll() {
		List<Usuario> usuarios = usuarioRepository.findAll(); // Busca todos os usuários do banco de dados

		/*
		 * Alternativa para a conversão manual de Usuario para UsuarioDTO:
		 * List<UsuarioDTO> usuariosDTO = new ArrayList<>(); for(Usuario usuario:
		 * usuarios) { usuariosDTO.add(new UsuarioDTO(usuario)); }
		 */

		// Usa o stream para mapear a lista de Usuario para UsuarioDTO
		List<UsuarioDTO> usuariosDTO = usuarios.stream().map(UsuarioDTO::new).toList();
		return usuariosDTO;
	}

	// Método para buscar um usuário específico pelo seu ID
	public Optional<Usuario> buscar(Long id) {
		return usuarioRepository.findById(id); // Retorna um Optional contendo o Usuário, se existir
	}

	// Método para inserir um novo usuário na base de dados, usando transações
	@Transactional  // Assegura que o método será executado dentro de uma transação
    public UsuarioDTO inserir(UsuarioInserirDTO usuarioInserirDTO) throws EmailException, SenhaException {
        // Validação: se as senhas não coincidem, lança uma exceção personalizada
        if (!usuarioInserirDTO.getSenha().equals(usuarioInserirDTO.getConfirmaSenha())) {
            throw new SenhaException("Senha e Confirma Senha não são iguais");
        }
        
        // Validação: se o email já existe, lança uma exceção personalizada
        if (usuarioRepository.findByEmail(usuarioInserirDTO.getEmail()) != null) {
            throw new EmailException("Email já existente");
        }
        
        // Cria um novo usuário a partir dos dados fornecidos
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioInserirDTO.getNome());
        usuario.setEmail(usuarioInserirDTO.getEmail());
        usuario.setSenha(encoder.encode(usuarioInserirDTO.getSenha()));  // Criptografa a senha com bcrypt
        
        // Cria um conjunto de perfis para o usuário
        Set<UsuarioPerfil> perfis = new HashSet<>();
        // Para cada perfil no DTO, busca o perfil real e cria uma associação com o usuário
        for (Perfil perfil: usuarioInserirDTO.getPerfis()) {
            perfil = perfilService.buscar(perfil.getId());  // Busca o perfil pelo ID
            UsuarioPerfil usuarioPerfil = new UsuarioPerfil(usuario, perfil, LocalDate.now());  // Associa o perfil ao usuário
            perfis.add(usuarioPerfil);  // Adiciona o perfil ao conjunto de perfis do usuário
        }
        usuario.setUsuarioPerfis(perfis);  // Define os perfis do usuário
        
        // Salva o usuário no banco de dados
        usuario = usuarioRepository.save(usuario);
        
        // Converte o usuário salvo para DTO e retorna
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
        return usuarioDTO;
    }

}
/*
 * Explicação do Código: Anotações e Dependências:
 * 
 * @Service: Declara que essa classe faz parte da camada de serviço, responsável
 * pela lógica de negócios.
 * 
 * @Autowired: Injeção automática das dependências necessárias, como o
 * repositório de usuários, serviço de perfis e o encoder de senhas. Métodos:
 * 
 * findAll(): Busca todos os usuários do banco de dados, os converte para
 * UsuarioDTO usando stream e retorna a lista de DTOs. buscar(Long id): Busca um
 * usuário específico pelo seu ID e retorna um Optional<Usuario>. Isso é útil
 * para evitar retornos nulos e facilita o tratamento de erros.
 * inserir(UsuarioInserirDTO usuarioInserirDTO): Verifica se as senhas e o email
 * são válidos, lançando exceções apropriadas se necessário. Cria um novo
 * Usuario a partir dos dados do DTO e criptografa a senha usando o
 * BCryptPasswordEncoder. Associa o usuário a perfis (UsuarioPerfil), que são
 * buscados individualmente pelo serviço de perfis. Salva o usuário com seus
 * perfis no banco de dados e retorna um UsuarioDTO. Exceções Personalizadas:
 * 
 * EmailException e SenhaException: São usadas para indicar problemas
 * específicos durante a inserção de um novo usuário, como emails duplicados ou
 * senhas que não coincidem. Uso de Transações:
 * 
 * @Transactional: Garante que o método inserir seja executado em uma transação.
 * Isso significa que se ocorrer um erro em qualquer ponto dentro do método, as
 * alterações feitas no banco de dados até aquele momento serão revertidas.
 * Criptografia de Senhas:
 * 
 * O método usa BCryptPasswordEncoder para criptografar as senhas dos usuários
 * antes de armazená-las no banco de dados. Isso adiciona segurança à aplicação,
 * garantindo que as senhas não sejam armazenadas em texto simples. Melhoria
 * Potencial: Tratamento de Erros: Atualmente, o código lança exceções genéricas
 * em algumas situações. Uma melhoria seria fornecer mensagens de erro mais
 * detalhadas ou lançar exceções específicas para facilitar o diagnóstico e o
 * tratamento de erros. Por exemplo, no caso do método buscar(Long id), uma
 * exceção personalizada pode ser lançada se o usuário não for encontrado.
 */