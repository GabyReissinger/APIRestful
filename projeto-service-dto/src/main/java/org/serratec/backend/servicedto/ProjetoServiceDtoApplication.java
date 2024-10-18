package org.serratec.backend.servicedto; // Declaração do pacote onde a classe está localizada

import org.springframework.boot.SpringApplication; // Importa a classe SpringApplication para iniciar a aplicação Spring Boot
import org.springframework.boot.autoconfigure.SpringBootApplication; // Importa a anotação SpringBootApplication para habilitar a configuração automática

@SpringBootApplication // Anotação que indica que esta é uma aplicação Spring Boot
public class ProjetoServiceDtoApplication {

	// Método principal que inicia a aplicação
	public static void main(String[] args) {
		SpringApplication.run(ProjetoServiceDtoApplication.class, args); // Executa a aplicação Spring Boot
	}
}