package br.com.maxribeiro.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.maxribeiro.entities.Empresa;
import br.com.maxribeiro.respository.EmpresaRepository;
import br.com.maxribeiro.util.ContaUtil;
import br.com.maxribeiro.util.EnderecoUtil;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository repository;

	public Optional<Empresa> buscarPorId(Long id) {
		return this.repository.findById(id);
	}

	public List<Empresa> listar() {
		return this.repository.findAll();
	}

	public Empresa adicionar(Empresa empresa) {
		return this.repository.save(empresa);
	}

	public Empresa atualizar(Empresa empresa, Long id) {
		Optional<Empresa> empresaOpt = buscarPorId(id);
		if (!empresaOpt.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}

		Empresa empresaBD = empresaOpt.get();
		empresaBD.getContatos().clear();
		empresaBD.getContatos().addAll(empresa.getContatos());

		BeanUtils.copyProperties(empresa, empresaBD, "id", "contatos", "funcionarios");
		return repository.save(empresaBD);

	}

	public Empresa atualizarParcial(Empresa empresa, Long id) {
		Optional<Empresa> empresaOpt = buscarPorId(id);
		if (!empresaOpt.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}

		Empresa empresaBD = empresaOpt.get();

		if (StringUtils.hasText(empresa.getNome()))
			empresaBD.setNome(empresa.getNome());
		if (StringUtils.hasText(empresa.getCnpj()))
			empresaBD.setCnpj(empresa.getCnpj());
		if (StringUtils.hasText(empresa.getInscricaoEstadual()))
			empresaBD.setInscricaoEstadual(empresa.getInscricaoEstadual());
		if (empresa.getDataAbertura() != null)
			empresaBD.setDataAbertura(empresa.getDataAbertura());
		if (StringUtils.hasText(empresa.getSite()))
			empresaBD.setSite(empresa.getSite());

		if (empresa.getContatos() != null) {
			empresaBD.getContatos().clear();
			empresaBD.getContatos().addAll(empresa.getContatos());
		}

		EnderecoUtil.copiarPropriedadesNaoNulas(empresa.getEndereco(), empresaBD.getEndereco());
		ContaUtil.copiarPropriedadesNaoNulas(empresa.getConta(), empresaBD.getConta());

		return repository.save(empresaBD);
	}

	public void excluir(Long id) {
		Optional<Empresa> empresa = this.repository.findById(id);
		if (empresa.isPresent())
			this.repository.delete(empresa.get());
	}

	public BigDecimal buscarSaldoPorEmpresaId(Long id) {
		Empresa empresa = this.repository.findByEmpresaIdOnlyConta(id);
		if (empresa == null)
			throw new EmptyResultDataAccessException(1);
		return empresa.getConta().getSaldo();
	}

}
