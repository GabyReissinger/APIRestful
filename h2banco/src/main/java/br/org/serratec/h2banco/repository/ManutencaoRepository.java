package br.org.serratec.h2banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.serratec.h2banco.domain.Manutencao;

@Repository
public interface ManutencaoRepository extends JpaRepository<Manutencao, Long> {

}
