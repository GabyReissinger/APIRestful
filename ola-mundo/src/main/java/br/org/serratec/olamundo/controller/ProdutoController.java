package br.org.serratec.olamundo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.olamundo.domain.Produto;

@RequestMapping
@RestController("/produtos")
public class ProdutoController {

	private List<Produto> produtos = new ArrayList<>();

	@GetMapping
	public List<Produto> listarProduto() {
		return produtos;
	}

	@PostMapping
	public String adicionarProduto(@RequestBody Produto produto) {
		if (produto.getNome() == null || produto.getNome().isEmpty()) {
			return "Nome do produto é obrigatório.";
		}
		if (produto.getPreco() <= 0) {
			return "Preço do produto deve ser maior que zero.";
		}
		produtos.add(produto);
		return "Produto adicionado com sucesso!";
	}

	@GetMapping("/{id}")
	public Object buscarProdutoPorId(@PathVariable Long id) {
		Optional<Produto> produto = produtos.stream().filter(p -> p.getId().equals(id)).findFirst();
		if (produto.isPresent()) {
			return produto.get();
		} else {
			return "Produto não encontrado.";
		}
	}

	@PutMapping("/{id}")
	public String atualizarProduto(@PathVariable Long id, @RequestBody Produto produtoAtualizado) {
		for (Produto produto : produtos) {
			if (produto.getId().equals(id)) {
				produto.setNome(produtoAtualizado.getNome());
				produto.setPreco(produtoAtualizado.getPreco());
				return "Produto atualizado com sucesso!";
			}
		}
		return "Produto não encontrado.";
	}

	@DeleteMapping("/{id}")
	public String removerProduto(@PathVariable Long id) {
		Optional<Produto> produtoOptional = produtos.stream().filter(p -> p.getId().equals(id)).findFirst();
		if (produtoOptional.isPresent()) {
			produtos.remove(produtoOptional.get());
			return "Produto removido com sucesso!";
		} else {
			return "Produto não encontrado.";
		}
	}
}
