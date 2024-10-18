package org.serratec.backend.servicedto.security; // Declaração do pacote onde a classe está localizada

import java.io.IOException; // Importa a classe IOException para lidar com exceções de entrada/saída

import org.springframework.http.HttpHeaders; // Importa a classe HttpHeaders para trabalhar com cabeçalhos HTTP
import org.springframework.security.authentication.AuthenticationManager; // Importa a interface AuthenticationManager para gerenciar a autenticação
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; // Importa a classe UsernamePasswordAuthenticationToken para criar um token de autenticação
import org.springframework.security.core.context.SecurityContextHolder; // Importa a classe SecurityContextHolder para gerenciar o contexto de segurança
import org.springframework.security.core.userdetails.UserDetails; // Importa a interface UserDetails para representar os detalhes do usuário
import org.springframework.security.core.userdetails.UserDetailsService; // Importa a interface UserDetailsService para carregar detalhes do usuário
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter; // Importa a classe BasicAuthenticationFilter para criar um filtro de autenticação básica

import jakarta.servlet.FilterChain; // Importa a interface FilterChain para trabalhar com a cadeia de filtros
import jakarta.servlet.ServletException; // Importa a classe ServletException para lidar com exceções de servlet
import jakarta.servlet.http.HttpServletRequest; // Importa a classe HttpServletRequest para trabalhar com requisições HTTP
import jakarta.servlet.http.HttpServletResponse; // Importa a classe HttpServletResponse para trabalhar com respostas HTTP

// Classe JwtAuthorizationFilter: Filtro de autorização que utiliza JWT
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

	// Atributos
	private JwtUtil jwtUtil; // Utilitário para trabalhar com tokens JWT
	private UserDetailsService userDetailsService; // Serviço para carregar detalhes do usuário

	// Construtor
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil,
			UserDetailsService userDetailsService) {
		super(authenticationManager); // Chama o construtor da classe pai com o gerenciador de autenticação
		this.jwtUtil = jwtUtil; // Inicializa o utilitário para trabalhar com tokens JWT
		this.userDetailsService = userDetailsService; // Inicializa o serviço para carregar detalhes do usuário
	}

	// Método que processa o filtro de autorização
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// Obtém o cabeçalho Authorization da requisição
		String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (header != null && header.startsWith("Bearer ")) { // Verifica se o cabeçalho contém um token Bearer
			// Obtém a autenticação usando o token
			UsernamePasswordAuthenticationToken auth = getAuthentication(header.substring(7));
			if (auth != null) { // Se a autenticação for válida
				// Define a autenticação no contexto de segurança
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		// Continua a cadeia de filtros
		chain.doFilter(request, response);
	}

	// Método para obter a autenticação a partir de um token JWT
	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		if (jwtUtil.isValidToken(token)) { // Verifica se o token é válido
			String username = jwtUtil.getUserName(token); // Obtém o nome de usuário do token
			UserDetails user = userDetailsService.loadUserByUsername(username); // Carrega os detalhes do usuário
			// Retorna um token de autenticação com os detalhes do usuário e suas
			// autoridades
			return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		}
		return null; // Retorna null se o token não for válido
	}
}