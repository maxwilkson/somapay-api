package br.com.maxribeiro.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.maxribeiro.entities.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {

	@Query("SELECT f.conta FROM Funcionario f WHERE f.id = :funcionarioId")
	Conta findByFuncionarioId(Long funcionarioId);
	
	@Query("SELECT e.conta FROM Empresa e WHERE e.id = :empresaId")
	Conta findByEmpresaId(Long empresaId);
}
