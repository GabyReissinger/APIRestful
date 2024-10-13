package br.org.serratec.olamundo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import br.org.serratec.olamundo.domain.Aluno;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

	private static List<Aluno> lista = new ArrayList<Aluno>();
	static {
		lista.add(new Aluno(1111L, "Fulano", "1111-1111"));
		lista.add(new Aluno(2222L, "Beltrano", "2222-2222"));
		lista.add(new Aluno(3333L, "Ciclano", "3333-3333"));
	}

	@GetMapping
	public List<Aluno> listar() {
		return lista;
	}

	// Achar um aluno
	@GetMapping("/{matricula}")
	public Aluno buscar(@PathVariable Long matricula) {

		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getMatricula().equals(matricula)) {
				return lista.get(i);
			}

		}
		return null;

		/*
		 * Isso é uma lambida return lista.stream() .filter(aluno ->
		 * aluno.getMatricula().equals(matricula)) .findFirst() .orElse(null);
		 */
	}

	// Para adicionar um aluno
	@PostMapping
	public Aluno inserir(@RequestBody Aluno aluno) {
		lista.add(aluno);
		return aluno;
	}

	// Alterar os dados do aluno
	@PutMapping("/{matricula}")
	public Aluno atualizar(@PathVariable Long matricula, @RequestBody Aluno aluno) {
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getMatricula().equals(matricula)) {
				Aluno a = new Aluno(matricula, aluno.getNome(), aluno.getTelefone());
				lista.set(i, a);
				return a;
			}

		}
		return null;
	}

	@DeleteMapping("/{matricula}")
	public void delete(@PathVariable Long matricula) {
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getMatricula().equals(matricula)) {
				lista.remove(i);
				break;
			}
		}
	}

}
