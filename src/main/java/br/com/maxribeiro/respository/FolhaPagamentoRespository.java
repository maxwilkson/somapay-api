package br.com.maxribeiro.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.maxribeiro.entities.FolhaPagamento;

public interface FolhaPagamentoRespository extends JpaRepository<FolhaPagamento, Long> {

}
