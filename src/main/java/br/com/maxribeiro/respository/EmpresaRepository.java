package br.com.maxribeiro.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.maxribeiro.entities.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long>{

}
