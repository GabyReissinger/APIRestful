package org.serratec.backend.servicedto.security; // Declaração do pacote onde a classe está localizada

import java.util.Date; // Importa a classe Date para trabalhar com datas

import javax.crypto.SecretKey; // Importa a classe SecretKey para representar a chave secreta

import org.springframework.beans.factory.annotation.Value; // Importa a anotação Value para injetar valores de configuração
import org.springframework.stereotype.Component; // Importa a anotação Component para marcar a classe como um componente Spring

import io.jsonwebtoken.Claims; // Importa a classe Claims para representar as declarações do JWT
import io.jsonwebtoken.Jwts; // Importa a classe Jwts para criar e manipular JWTs
import io.jsonwebtoken.security.Keys; // Importa a classe Keys para gerar chaves de assinatura

@Component // Anotação que indica que esta classe é um componente Spring
public class JwtUtil {

	@Value("${auth-jwt-secret}") // Injeção do valor da chave secreta a partir da configuração
	private String jwtSecret;

	@Value("${auth-jwt-expiration-miliseg}") // Injeção do valor da expiração do token em milissegundos
	private Long jwtExpirationMiliseg;

	// Método para gerar um token JWT a partir do nome de usuário
	public String generateToken(String username) {
		SecretKey secreKeySpec = Keys.hmacShaKeyFor(jwtSecret.getBytes()); // Gera a chave secreta a partir da string
		return Jwts.builder() // Cria um construtor para o JWT
				.setSubject(username) // Define o sujeito do token como o nome de usuário
				.setExpiration(new Date(System.currentTimeMillis() + this.jwtExpirationMiliseg)) // Define a data de
																									// expiração do
																									// token
				.signWith(secreKeySpec) // Assina o token com a chave secreta
				.compact(); // Compacta o token em uma string
	}

	// Método para validar se um token JWT é válido
	public boolean isValidToken(String token) {
		Claims claims = getClaims(token); // Obtém as declarações do token
		if (claims != null) {
			String username = claims.getSubject(); // Obtém o nome de usuário das declarações
			Date expirationDate = claims.getExpiration(); // Obtém a data de expiração do token
			Date now = new Date(System.currentTimeMillis()); // Obtém a data atual
			// Verifica se o nome de usuário e a data de expiração são válidos
			if (username != null && expirationDate != null && now.before(expirationDate)) {
				return true; // O token é válido
			}
		}
		return false; // O token não é válido
	}

	// Método para obter o nome de usuário a partir de um token JWT
	public String getUserName(String token) {
		Claims claims = getClaims(token); // Obtém as declarações do token
		if (claims != null) {
			return claims.getSubject(); // Retorna o nome de usuário
		}
		return null; // Retorna null se não houver declarações
	}

	// Método para obter as declarações de um token JWT
	public Claims getClaims(String token) {
		return Jwts.parserBuilder() // Cria um construtor para o parser do JWT
				.setSigningKey(jwtSecret.getBytes()) // Define a chave secreta para validar a assinatura
				.build() // Constrói o parser
				.parseClaimsJws(token) // Analisa o token e obtém as declarações
				.getBody(); // Retorna o corpo das declarações
	}
}