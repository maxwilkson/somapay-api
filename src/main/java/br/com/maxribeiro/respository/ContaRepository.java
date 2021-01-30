package br.com.maxribeiro.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.maxribeiro.entities.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {

}
