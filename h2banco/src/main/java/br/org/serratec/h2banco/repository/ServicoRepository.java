package br.org.serratec.h2banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.serratec.h2banco.domain.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {
	
}
