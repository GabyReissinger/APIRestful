package br.org.serratec.mapeamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.serratec.mapeamento.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
}
