package br.com.danielnunes.exercicio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.danielnunes.exercicio.entities.Contribuicao;

public interface ContribuicaoRepository extends JpaRepository<Contribuicao, Long> {

}
