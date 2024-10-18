package org.serratec.backend.servicedto.security; // Declaração do pacote onde a classe está localizada

import java.io.IOException; // Importa a classe IOException para lidar com exceções de entrada/saída
import java.util.ArrayList; // Importa a classe ArrayList para criar uma lista vazia de autorizações

import org.serratec.backend.servicedto.dto.LoginDTO; // Importa a classe LoginDTO para representar o objeto de login
import org.springframework.http.HttpHeaders; // Importa a classe HttpHeaders para trabalhar com cabeçalhos HTTP
import org.springframework.security.authentication.AuthenticationManager; // Importa a interface AuthenticationManager para gerenciar a autenticação
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; // Importa a classe UsernamePasswordAuthenticationToken para criar um token de autenticação
import org.springframework.security.core.Authentication; // Importa a interface Authentication para representar a autenticação
import org.springframework.security.core.AuthenticationException; // Importa a classe AuthenticationException para lidar com exceções de autenticação
import org.springframework.security.core.userdetails.UserDetails; // Importa a interface UserDetails para representar os detalhes do usuário
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter; // Importa a classe UsernamePasswordAuthenticationFilter para criar um filtro de autenticação

import com.fasterxml.jackson.databind.ObjectMapper; // Importa a classe ObjectMapper para converter JSON em objetos

import jakarta.servlet.FilterChain; // Importa a interface FilterChain para trabalhar com a cadeia de filtros
import jakarta.servlet.ServletException; // Importa a classe ServletException para lidar com exceções de servlet
import jakarta.servlet.http.HttpServletRequest; // Importa a classe HttpServletRequest para trabalhar com requisições HTTP
import jakarta.servlet.http.HttpServletResponse; // Importa a classe HttpServletResponse para trabalhar com respostas HTTP

// Classe JwtAuthenticationFilter: Filtro de autenticação que utiliza JWT
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	// Atributos
	private AuthenticationManager authenticationManager; // Gerenciador de autenticação
	private JwtUtil jwtUtil; // Utilitário para gerar tokens JWT

	// Construtor
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
		this.authenticationManager = authenticationManager; // Inicializa o gerenciador de autenticação
		this.jwtUtil = jwtUtil; // Inicializa o utilitário para gerar tokens JWT
	}

	// Método para tentar autenticar o usuário
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			// Lê o objeto de login da requisição
			LoginDTO login = new ObjectMapper().readValue(request.getInputStream(), LoginDTO.class);

			// Cria um token de autenticação com o nome de usuário e senha
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(login.getUsername(),
					login.getPassword(), new ArrayList<>());

			// Autentica o usuário
			Authentication auth = authenticationManager.authenticate(authToken);

			// Retorna a autenticação
			return auth;
		} catch (IOException e) {
			// Lança uma exceção se houver um erro ao ler o objeto de login
			throw new RuntimeException("Falha ao autenticar usuário", e);
		}
	}

	// Método para lidar com a autenticação bem-sucedida
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// Obtém o nome de usuário da autenticação
		String username = ((UserDetails) authResult.getPrincipal()).getUsername();

		// Gera um token JWT para o usuário
		String token = jwtUtil.generateToken(username);

		// Adiciona o token JWT ao cabeçalho da resposta
		response.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);

		// Adiciona o cabeçalho de exposição para permitir que o token seja acessado
		response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.AUTHORIZATION);
	}
}