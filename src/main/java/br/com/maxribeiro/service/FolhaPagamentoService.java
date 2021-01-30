package br.com.maxribeiro.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.maxribeiro.dto.FolhaPagamentoDto;
import br.com.maxribeiro.dto.FolhaPagamentoItemDto;
import br.com.maxribeiro.entities.Empresa;
import br.com.maxribeiro.entities.FolhaPagamento;
import br.com.maxribeiro.entities.FolhaPagamentoItem;
import br.com.maxribeiro.entities.Funcionario;
import br.com.maxribeiro.respository.ContaRepository;
import br.com.maxribeiro.respository.FolhaPagamentoRespository;

@Service
public class FolhaPagamentoService {

	@Autowired
	private FolhaPagamentoRespository folhaRespository;

	@Autowired
	private EmpresaService empresaService;

	@Autowired
	private ContaRepository contaRepository;

	private BigDecimal taxa = new BigDecimal("0.0038");

	@Transactional
	public FolhaPagamentoDto pagar(Long empresaId) {

		Optional<Empresa> empresaOpt = empresaService.buscarPorId(empresaId);
		if (!empresaOpt.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}

		Empresa empresa = empresaOpt.get();

		FolhaPagamento folha = gerarFolhaPagamento(empresa);

		pagarFolha(folha);

		cobrarTaxaSobreFolha(folha);

		FolhaPagamentoDto folhaDto = criarFolhaPagamentoDto(folha);

		return folhaDto;
	}

	private FolhaPagamentoDto criarFolhaPagamentoDto(FolhaPagamento folha) {

		FolhaPagamentoDto folhaDto = new FolhaPagamentoDto();
		List<FolhaPagamentoItemDto> itensDto = new ArrayList<>();

		folhaDto.setEmpresaCnpj(folha.getEmpresa().getCnpj());
		folhaDto.setEmpresaNome(folha.getEmpresa().getNome());
		folhaDto.setDataPagamento(folha.getDataPagamento());
		folhaDto.setValorTotalFolha(folha.getValorTotalFolha());
		folhaDto.setValorTaxaSobreFolha(folha.getValorTaxaSobreFolha());
		folhaDto.setValorTotalFolhaComTaxa(folha.getValorTotalFolhaComTaxa());
		for (FolhaPagamentoItem item : folha.getItens()) {
			FolhaPagamentoItemDto itemDto = new FolhaPagamentoItemDto(
					item.getFuncionario().getCpf(),
					item.getFuncionario().getNome(),
					item.getValor());
			itensDto.add(itemDto);
		}

		folhaDto.setItens(itensDto);

		return folhaDto;
	}

	private FolhaPagamento gerarFolhaPagamento(Empresa empresa) {

		List<Funcionario> funcionarios = empresa.getFuncionarios();
		FolhaPagamento folha = new FolhaPagamento(LocalDate.now(), empresa, this.taxa);

		for (Funcionario funcionario : funcionarios) {
			FolhaPagamentoItem item = new FolhaPagamentoItem(funcionario.getSalario(), funcionario, folha);
			folha.getItens().add(item);
		}

		return folhaRespository.save(folha);
	}

	private void pagarFolha(FolhaPagamento folha) {
		for (FolhaPagamentoItem item : folha.getItens()) {
			folha.getEmpresa().getConta().sacar(item.getValor());
			item.getFuncionario().getConta().depositar(item.getValor());

			contaRepository.save(folha.getEmpresa().getConta());
			contaRepository.save(item.getFuncionario().getConta());
		}
	}

	private void cobrarTaxaSobreFolha(FolhaPagamento folha) {
		folha.getEmpresa().getConta().sacar(folha.getValorTaxaSobreFolha());
		contaRepository.save(folha.getEmpresa().getConta());
	}
}
