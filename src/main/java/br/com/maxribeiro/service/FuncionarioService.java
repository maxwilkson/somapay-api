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
import br.com.maxribeiro.entities.Funcionario;
import br.com.maxribeiro.respository.EmpresaRepository;
import br.com.maxribeiro.respository.FuncionarioRepository;
import br.com.maxribeiro.util.ContaUtil;
import br.com.maxribeiro.util.EnderecoUtil;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository repository;

	@Autowired
	private EmpresaRepository empresaRepository;

	public Optional<Funcionario> buscarPorId(Long id) {
		return this.repository.findById(id);
	}

	public List<Funcionario> listar(Long empresaId) {
		return this.repository.findByEmpresaId(empresaId);
	}

	public Funcionario adicionar(Funcionario funcionario, Long empresaId) {
		Optional<Empresa> empresaOpt = this.empresaRepository.findById(empresaId);
		if (!empresaOpt.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		funcionario.setEmpresa(empresaOpt.get());
		return this.repository.save(funcionario);
	}

	public Funcionario atualizar(Funcionario funcionario, Long id) {
		Optional<Funcionario> funcionarioOpt = buscarPorId(id);
		if (!funcionarioOpt.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}

		Funcionario funcionarioBD = funcionarioOpt.get();
		funcionarioBD.getContatos().clear();
		funcionarioBD.getContatos().addAll(funcionario.getContatos());

		BeanUtils.copyProperties(funcionario, funcionarioBD, "id", "contatos");
		return repository.save(funcionarioBD);

	}

	public Funcionario atualizarParcial(Funcionario funcionario, Long id) {
		Optional<Funcionario> funcionarioOpt = buscarPorId(id);
		if (!funcionarioOpt.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}

		Funcionario funcionarioBD = funcionarioOpt.get();

		if (StringUtils.hasText(funcionario.getNome()))
			funcionarioBD.setNome(funcionario.getNome());
		if (StringUtils.hasText(funcionario.getCpf()))
			funcionarioBD.setCpf(funcionario.getCpf());
		if (StringUtils.hasText(funcionario.getRg()))
			funcionarioBD.setRg(funcionario.getRg());
		if (funcionario.getDataNascimento() != null)
			funcionarioBD.setDataNascimento(funcionario.getDataNascimento());
		if (StringUtils.hasText(funcionario.getEmail()))
			funcionarioBD.setEmail(funcionario.getEmail());
		if (funcionario.getSexo() != null)
			funcionarioBD.setSexo(funcionario.getSexo());
		if (funcionario.getDataAdmissao() != null)
			funcionarioBD.setDataAdmissao(funcionario.getDataAdmissao());
		if (funcionario.getDataDemissao() != null)
			funcionarioBD.setDataDemissao(funcionario.getDataDemissao());
		if (funcionario.getSalario() != null)
			funcionarioBD.setSalario(funcionario.getSalario());
		if (funcionario.getEmpresa() != null)
			funcionarioBD.setEmpresa(funcionario.getEmpresa());
		if (StringUtils.hasText(funcionario.getFuncao()))
			funcionarioBD.setFuncao(funcionario.getFuncao());

		if (funcionario.getContatos() != null) {
			funcionarioBD.getContatos().clear();
			funcionarioBD.getContatos().addAll(funcionario.getContatos());
		}

		EnderecoUtil.copiarPropriedadesNaoNulas(funcionario.getEndereco(), funcionarioBD.getEndereco());
		ContaUtil.copiarPropriedadesNaoNulas(funcionario.getConta(), funcionarioBD.getConta());

		return repository.save(funcionarioBD);
	}

	public void excluir(Long id) {
		Optional<Funcionario> funcionario = this.repository.findById(id);
		if (funcionario.isPresent())
			this.repository.delete(funcionario.get());
	}

	public BigDecimal buscarSaldoPorFuncionarioId(Long id) {
		Funcionario funcionario = this.repository.findByFuncionarioIdOnlyConta(id);
		if (funcionario == null)
			throw new EmptyResultDataAccessException(1);
		return funcionario.getConta().getSaldo();
	}

}
