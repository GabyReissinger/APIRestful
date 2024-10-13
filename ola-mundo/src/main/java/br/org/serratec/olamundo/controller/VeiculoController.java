package br.org.serratec.olamundo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.olamundo.domain.Veiculo;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

	private static List<Veiculo> lista = new ArrayList<Veiculo>();
	static {
		lista.add(new Veiculo(1111L, "Fiat", "Uno"));
		lista.add(new Veiculo(2222L, "Ford", "Ka"));
		lista.add(new Veiculo(3333L, "Fiat", "Marea"));
	}

	@GetMapping
	public List<Veiculo> listar() {
		return lista;
	}

	@GetMapping("/{id}")
	public Veiculo buscar(@PathVariable Long id) {
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getId().equals(id)) {
				
			return lista.get(i);
			}
		}
		return null;
	}

	@PostMapping
	public Veiculo adicionar(@RequestBody Veiculo veiculo) {
		lista.add(veiculo);
		return veiculo;
	}

	@PutMapping("/{id}")
	public Veiculo atualizar(@PathVariable Long id, @RequestBody Veiculo veiculo) {
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getId().equals(id)) {
				Veiculo v = new Veiculo(id, veiculo.getMarca(), veiculo.getModelo());
				lista.set(i, v);
				return v;
			}

		}
		return null;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getId().equals(id)) {
				lista.remove(i);
				break;
			}
		}
	}

}
