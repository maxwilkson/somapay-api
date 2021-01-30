package br.com.maxribeiro.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.maxribeiro.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	List<Funcionario> findByEmpresaId(Long idEmpresa);
	
	@Query("SELECT NEW Funcionario(f.conta) FROM Funcionario f WHERE f.id = :funcionarioId")
	Funcionario findByFuncionarioIdOnlyConta(Long funcionarioId);
}
