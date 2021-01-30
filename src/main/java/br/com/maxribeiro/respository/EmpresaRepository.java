package br.com.maxribeiro.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.maxribeiro.entities.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

	@Query("SELECT NEW Empresa(e.conta) FROM Empresa e WHERE e.id = :empresaId")
	Empresa findByEmpresaIdOnlyConta(Long empresaId);

	Optional<Empresa> findByCnpj(String string);
}
