package br.com.maxribeiro.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.maxribeiro.entities.Conta;
import br.com.maxribeiro.entities.Contato;
import br.com.maxribeiro.entities.Empresa;
import br.com.maxribeiro.entities.Endereco;
import br.com.maxribeiro.entities.Funcionario;
import br.com.maxribeiro.entities.SexoEnum;
import br.com.maxribeiro.entities.TipoContatoEnum;
import br.com.maxribeiro.respository.EmpresaRepository;
import br.com.maxribeiro.respository.FolhaPagamentoRespository;
import br.com.maxribeiro.respository.FuncionarioRepository;

@Component
public class BancoDadosUtil {

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private FolhaPagamentoRespository folhaPagamentoRespository;
	
	@Transactional
	public void resetarBanco() {
		folhaPagamentoRespository.deleteAll();
		funcionarioRepository.deleteAll();
		empresaRepository.deleteAll();
		
		popularBanco();
	}

	@Transactional
	public void popularBanco() {

		Empresa empresa1 = empresaRepository.save(criarEmpresa1());
		Empresa empresa2 = empresaRepository.save(criarEmpresa2());

		Funcionario funcionario1 = criarFuncionario1(empresa1);
		Funcionario funcionario2 = criarFuncionario2(empresa1);
		Funcionario funcionario3 = criarFuncionario3(empresa2);
		Funcionario funcionario4 = criarFuncionario4(empresa2);

		List<Funcionario> funcionarios = Arrays.asList(funcionario1, funcionario2, funcionario3, funcionario4);
		funcionarioRepository.saveAll(funcionarios);
	}

	private Funcionario criarFuncionario1(Empresa empresa) {
		Endereco endereco = new Endereco("Rua Júlio Ventura", "748", "Aldeota", "Fortaleza", "CE", "60150050");
		Conta conta = new Conta("Banco Bradesco", 4587L, 1, 7854L, 4, null, BigDecimal.valueOf(3000));
		Contato contato = new Contato(TipoContatoEnum.TELEFONE, "8525976446");
		Set<Contato> contatos = new HashSet<>();
		contatos.add(contato);
		Funcionario funcionario = new Funcionario("Renata Elza Moraes", "29739145400", "391567731",
				LocalDate.of(1992, 12, 11), SexoEnum.FEMININO, "renataelzamoraes..renataelzamoraes@hotamail.com",
				endereco, contatos, empresa, LocalDate.of(2020, 3, 10), null, BigDecimal.valueOf(2500),
				"Auxiliar Administrativo", conta);
		return funcionario;
	}

	private Funcionario criarFuncionario2(Empresa empresa) {
		Endereco endereco = new Endereco("Rua Raquel Magalhães 69", "473", "Centro", "Deputado Irapuan Pinheiro", "CE",
				"63645970");
		Conta conta = new Conta("Banco Bradesco", 9087L, 1, 7704L, 0, null, BigDecimal.valueOf(4000));
		Contato contato = new Contato(TipoContatoEnum.TELEFONE, "8827411054");
		Set<Contato> contatos = new HashSet<>();
		contatos.add(contato);
		Funcionario funcionario = new Funcionario("Oliver Caleb dos Santos", "06589916390", "119964752",
				LocalDate.of(1997, 11, 5), SexoEnum.MASCULINO, "olivercalebdossantos__olivercalebdossantos@imail.com",
				endereco, contatos, empresa, LocalDate.of(2020, 4, 15), null, BigDecimal.valueOf(3000), "Vendedor",
				conta);
		return funcionario;
	}

	private Funcionario criarFuncionario3(Empresa empresa) {
		Endereco endereco = new Endereco("Rua Menino Deus", "249", "Centro", "Sobral", "CE", "62010310");
		Conta conta = new Conta("Banco Bradesco", 1254L, 1, 4572L, 0, null, BigDecimal.valueOf(8000));
		Contato contato = new Contato(TipoContatoEnum.TELEFONE, "8829603701");
		Set<Contato> contatos = new HashSet<>();
		contatos.add(contato);
		Funcionario funcionario = new Funcionario("José Benício Nogueira", "72436659346", "393024702",
				LocalDate.of(1950, 11, 2), SexoEnum.MASCULINO, "josebenicionogueira-83@selaz.com.br", endereco,
				contatos, empresa, LocalDate.of(2015, 1, 15), null, BigDecimal.valueOf(3800), "Gerente", conta);
		return funcionario;
	}

	private Funcionario criarFuncionario4(Empresa empresa) {
		Endereco endereco = new Endereco("Rua Francisca Pereira Lopes", "250", "Pedrinhas", "Juazeiro do Norte", "CE",
				"63018030");
		Conta conta = new Conta("Banco Bradesco", 1254L, 1, 4572L, 0, null, BigDecimal.valueOf(8000));
		Contato contato = new Contato(TipoContatoEnum.TELEFONE, "8828879279");
		Set<Contato> contatos = new HashSet<>();
		contatos.add(contato);
		Funcionario funcionario = new Funcionario("Cauã Cauê Vicente Moraes", "96462362359", "210035912",
				LocalDate.of(1990, 3, 22), SexoEnum.MASCULINO, "cauacauevicentemoraes-74@eguia.com.br", endereco,
				contatos, empresa, LocalDate.of(2018, 3, 20), null, BigDecimal.valueOf(2500), "Balconista", conta);
		return funcionario;
	}

	private Empresa criarEmpresa1() {
		Contato contato = new Contato(TipoContatoEnum.TELEFONE, "8525976446");
		Set<Contato> contatos = new HashSet<>();
		contatos.add(contato);
		Endereco endereco = new Endereco("Rua Quinze de Novembro", "491", "Jandaiguaba", "Caucaia", "CE", "61615700");
		Conta conta = new Conta("Banco do Brasil", 1234L, 0, 4321L, 2, null, BigDecimal.valueOf(700542.50));
		Empresa empresa = new Empresa("Kauê e Sueli Buffet Ltda", "75253226000101", "406276552",
				LocalDate.of(2016, 10, 5), "www.kaueesuelibuffetltda.com.br", "contato@kaueesuelibuffetltda.com.br",
				endereco, contatos, conta, Collections.emptyList());
		return empresa;
	}

	private Empresa criarEmpresa2() {
		Contato contato = new Contato(TipoContatoEnum.TELEFONE, "8525976446");
		Set<Contato> contatos = new HashSet<>();
		contatos.add(contato);
		Endereco endereco = new Endereco("Rua 301B", "261", "Conjunto Ceará", "Fortaleza", "CE", "60530520");
		Conta conta = new Conta("Banco Bradesco", 8796L, 1, 2584L, 5, null, BigDecimal.valueOf(505424.50));
		Empresa empresa = new Empresa("Rita e Patrícia Advocacia ME", "19358335000104", "046893199",
				LocalDate.of(2016, 5, 4), "www.ritaepatriciaadvocaciame.com.br",
				"treinamento@ritaepatriciaadvocaciame.com.br", endereco, contatos, conta, Collections.emptyList());
		return empresa;
	}

}
