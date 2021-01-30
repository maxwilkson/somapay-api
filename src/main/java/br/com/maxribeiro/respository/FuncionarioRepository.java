package br.com.maxribeiro.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.maxribeiro.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	List<Funcionario> findByEmpresaId(Long idEmpresa);

}
