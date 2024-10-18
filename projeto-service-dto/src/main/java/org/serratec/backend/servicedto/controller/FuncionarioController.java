package org.serratec.backend.servicedto.controller;

import java.util.List;

import org.serratec.backend.servicedto.domain.Funcionario; // Importa a classe Funcionario que representa o domínio
import org.serratec.backend.servicedto.dto.FuncionarioSalarioDTO; // Importa o DTO para representar salários de funcionários
import org.serratec.backend.servicedto.repository.FuncionarioRepository; // Importa o repositório que acessa os dados de funcionários
import org.springframework.beans.factory.annotation.Autowired; // Permite a injeção automática de dependências
import org.springframework.data.domain.Page; // Representa uma página de resultados
import org.springframework.data.domain.Pageable; // Permite paginação e definição de parâmetros para busca de dados
import org.springframework.data.domain.Sort; // Especifica a ordenação dos dados
import org.springframework.data.web.PageableDefault; // Define valores padrão para paginação e ordenação
import org.springframework.http.ResponseEntity; // Representa a resposta HTTP, com o código de status e o corpo da resposta
import org.springframework.web.bind.annotation.GetMapping; // Anotação para mapeamento de requisições HTTP GET
import org.springframework.web.bind.annotation.RequestMapping; // Anotação para definir o caminho base da URL
import org.springframework.web.bind.annotation.RequestParam; // Anotação para receber parâmetros da URL
import org.springframework.web.bind.annotation.RestController; // Indica que essa classe é um controlador REST

@RestController // Define essa classe como um controlador REST que gerencia requisições HTTP
@RequestMapping("/funcionarios") // Mapeia a URL base /funcionarios para os métodos dentro desta classe
public class FuncionarioController {

	@Autowired // Injeção automática do repositório de funcionários
	private FuncionarioRepository funcionarioRepository;

	@GetMapping // Mapeia o método listar() para requisições GET no caminho base /funcionarios
	public ResponseEntity<List<Funcionario>> listar() {
		// Retorna uma lista de todos os funcionários, com um código de status 200 OK
		return ResponseEntity.ok(funcionarioRepository.findAll());
	}

	@GetMapping("/pagina") // Mapeia o método listarPaginado() para GET em /funcionarios/pagina
	public ResponseEntity<Page<Funcionario>> listarPaginado(
			// Define a paginação e ordenação padrão: ordenar por "nome" de forma
			// ascendente, com a primeira página (0) e 8 elementos por página
			@PageableDefault(sort = "nome", direction = Sort.Direction.ASC, page = 0, size = 8) Pageable pageable) {
		// Busca todos os funcionários com paginação
		Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);
		// Retorna a página de funcionários, com status 200 OK
		return ResponseEntity.ok(funcionarios);
	}

	@GetMapping("/salario") // Mapeia o método listarSalarios() para GET em /funcionarios/salario
	public ResponseEntity<Page<Funcionario>> listarSalarios(
			// Recebe dois parâmetros da URL para definir o intervalo de salário, com
			// valores padrão se não forem passados
			@RequestParam(defaultValue = "0") Double valorMinimo,
			@RequestParam(defaultValue = "10000") Double valorMaximo, Pageable pageable) {
		// Busca funcionários cujo salário esteja no intervalo definido, com paginação
		Page<Funcionario> funcionarios = funcionarioRepository.buscarSalario(valorMinimo, valorMaximo, pageable);
		// Retorna a página de funcionários que atendem ao critério, com status 200 OK
		return ResponseEntity.ok(funcionarios);
	}

	@GetMapping("/nome") // Mapeia o método buscarPorNome() para GET em /funcionarios/nome
	public ResponseEntity<Page<Funcionario>> buscarPorNome(
			// Recebe o parâmetro "paramNome" da URL, com valor padrão "a" se não for
			// informado
			@RequestParam(defaultValue = "a") String paramNome, Pageable pageable) {
		// Busca funcionários cujo nome contenha o valor passado no parâmetro
		// "paramNome", com paginação
		Page<Funcionario> funcionarios = funcionarioRepository.buscarPorNome(paramNome, pageable);
		// Retorna a página de funcionários que atendem ao critério de nome, com status
		// 200 OK
		return ResponseEntity.ok(funcionarios);
	}

	@GetMapping("/salarios-por-idade") // Mapeia o método buscarSalariosPorIdade() para GET em
										// /funcionarios/salarios-por-idade
	public ResponseEntity<List<FuncionarioSalarioDTO>> buscarSalariosPorIdade() {
		// Busca a lista de salários de funcionários agrupados por idade
		return ResponseEntity.ok(funcionarioRepository.buscarSalariosPorIdade());
	}
}
/*
 * Explicação do Código com Comentários: Controller REST:
 * 
 * A classe é anotada com @RestController, indicando que ela responde a
 * requisições HTTP e devolve dados (geralmente JSON).
 * 
 * @RequestMapping("/funcionarios"): Define que todas as rotas dessa classe
 * começam com /funcionarios. Injeção de Dependência:
 * 
 * funcionarioRepository: O repositório que contém os métodos de acesso ao banco
 * de dados, como busca de funcionários, é injetado automaticamente
 * com @Autowired. Listagem de Funcionários:
 * 
 * listar(): Este método responde a requisições GET no endpoint /funcionarios,
 * retornando uma lista de todos os funcionários. Listagem Paginada:
 * 
 * listarPaginado(): Este método permite a listagem paginada de funcionários no
 * endpoint /funcionarios/pagina, com a ordenação por nome e resultados de 8 por
 * página, conforme o padrão configurado. Filtragem por Salário:
 * 
 * listarSalarios(): Este método responde a requisições GET no endpoint
 * /funcionarios/salario, filtrando funcionários por um intervalo de salários
 * fornecido pelos parâmetros valorMinimo e valorMaximo, e retorna os resultados
 * paginados. Busca por Nome:
 * 
 * buscarPorNome(): Este método permite buscar funcionários cujo nome contenha a
 * string passada no parâmetro paramNome, com um valor padrão "a" se não for
 * informado. Os resultados são retornados paginados. Salários por Idade:
 * 
 * buscarSalariosPorIdade(): Retorna uma lista de funcionários com salários
 * agrupados por idade, com o retorno formatado em um DTO
 * (FuncionarioSalarioDTO), que é uma representação dos dados.
 */
