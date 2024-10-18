package org.serratec.backend.servicedto.config;

import java.util.Arrays;

import org.serratec.backend.servicedto.security.JwtAuthenticationFilter;
import org.serratec.backend.servicedto.security.JwtAuthorizationFilter;
import org.serratec.backend.servicedto.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration  // Indica que esta classe contém configurações do Spring
@EnableWebSecurity  // Habilita a configuração de segurança web
public class ConfigSeguranca {

    @Autowired
    UserDetailsService userDetailsService;  // Serviço de gerenciamento de usuários (para carregar detalhes do usuário)

    @Autowired
    JwtUtil jwtUtil;  // Utilitário JWT, usado para operações com tokens JWT

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Desabilita o CSRF, já que JWT é usado e não requer proteção contra CSRF
        http.csrf(csrf -> csrf.disable())
            // Configura o CORS para permitir requisições de outros domínios
            .cors((cors) -> cors.configurationSource(corsConfigurationSource()))
            // Define as permissões para diferentes endpoints da API
            .authorizeHttpRequests(authorize -> 
                authorize
                    .requestMatchers(HttpMethod.GET, "/funcionarios").permitAll()  // Permite acesso público a /funcionarios (GET)
                    .requestMatchers(HttpMethod.POST, "/usuarios").permitAll()  // Permite acesso público a /usuarios (POST)
                    .requestMatchers(HttpMethod.GET, "/usuarios/{id}").hasAuthority("ADMIN")  // Apenas "ADMIN" pode acessar /usuarios/{id} (GET)
                    .requestMatchers(HttpMethod.GET, "/funcionarios/nome").hasAnyAuthority("ADMIN", "USER")  // "ADMIN" e "USER" podem acessar /funcionarios/nome (GET)
                    .anyRequest().authenticated()  // Qualquer outra requisição precisa estar autenticada
            )
            .httpBasic(Customizer.withDefaults())  // Usa autenticação básica (pode ser substituído por JWT)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));  // Define que a aplicação não mantém estado de sessão (stateless)

        // Configura o filtro de autenticação JWT para interceptar as requisições de login e gerar tokens JWT
        JwtAuthenticationFilter jwtAuthenticationFilter = 
            new JwtAuthenticationFilter(authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), jwtUtil);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");  // Define o endpoint de login

        // Configura o filtro de autorização JWT para verificar os tokens nas requisições e validar sua autenticidade
        JwtAuthorizationFilter jwtAuthorizationFilter = 
            new JwtAuthorizationFilter(authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), jwtUtil, userDetailsService);

        // Adiciona os filtros de autenticação e autorização JWT à cadeia de filtros do Spring Security
        http.addFilter(jwtAuthenticationFilter);
        http.addFilter(jwtAuthorizationFilter);

        // Retorna a configuração de segurança construída
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        // Define as políticas de CORS, permitindo requisições de http://localhost:3000 e certos métodos HTTP
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));

        // Aplica as configurações CORS para todas as rotas da API
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration.applyPermitDefaultValues());

        return source;
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        // Define o algoritmo BCrypt para criptografar senhas de forma segura
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) 
            throws Exception {
        // Configura o gerenciador de autenticação, usado para autenticar usuários
        return authenticationConfiguration.getAuthenticationManager();
    }
}

	
	/*
	@Bean 	aplicação em memória
	InMemoryUserDetailsManager userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("teste")
				.password("123456")
				.roles("RH")
				.build();
		return new InMemoryUserDetailsManager(user);
	}
	
	Resumo dos Comentários:
Anotações @Configuration e @EnableWebSecurity: 
Indicam que esta classe contém as configurações de segurança da aplicação.

Beans: São componentes gerenciados pelo Spring que configuram serviços de 
segurança (como criptografia de senhas e gerenciamento de autenticação).

CORS: Configuração que permite que a aplicação aceite requisições de um 
domínio específico (neste caso, http://localhost:3000).

Filtros JWT: Dois filtros são adicionados para:

Autenticação: Verificar as credenciais de login e gerar tokens JWT.

Autorização: Validar o token JWT em cada requisição subsequente para 
autorizar o acesso.

Permissões: Definem quais rotas são acessíveis publicamente e quais 
requerem autenticação/autorização.
	*/
	