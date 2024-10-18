package org.serratec.backend.servicedto.exception; // Declaração do pacote onde a classe está localizada

import org.springframework.http.ResponseEntity; // Importa a classe ResponseEntity para construir respostas HTTP
import org.springframework.web.bind.annotation.ControllerAdvice; // Importa a anotação ControllerAdvice para tratamento global de exceções
import org.springframework.web.bind.annotation.ExceptionHandler; // Importa a anotação ExceptionHandler para definir métodos que tratam exceções
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler; // Importa a classe base para tratamento de exceções

// Classe ControllerExceptionHandler: Trata exceções lançadas em controladores
@ControllerAdvice // Anotação que indica que esta classe fornece tratamento de exceções global
					// para controladores
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	// Método que trata exceções do tipo EmailException
	@ExceptionHandler(EmailException.class) // Define que este método deve ser chamado para EmailException
	private ResponseEntity<Object> handleEmailException(EmailException ex) {
		// Retorna uma resposta HTTP 422 Unprocessable Entity com a mensagem da exceção
		return ResponseEntity.unprocessableEntity().body(ex.getMessage());
	}

	// Método que trata exceções do tipo SenhaException
	@ExceptionHandler(SenhaException.class) // Define que este método deve ser chamado para SenhaException
	private ResponseEntity<Object> handleSenhaException(SenhaException ex) {
		// Retorna uma resposta HTTP 422 Unprocessable Entity com a mensagem da exceção
		return ResponseEntity.unprocessableEntity().body(ex.getMessage());
	}
}