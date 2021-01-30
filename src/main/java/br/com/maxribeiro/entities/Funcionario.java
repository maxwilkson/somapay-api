package br.com.maxribeiro.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "funcionario")
@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Funcionario implements Serializable {
	private static final long serialVersionUID = -1804707223782776511L;

	@Id
	@SequenceGenerator(name = "funcionario_id_seq", sequenceName = "funcionario_id_seq", allocationSize = 1)
	@GeneratedValue(generator = "funcionario_id_seq", strategy = GenerationType.SEQUENCE)
	private Long id;

	private String nome;

	private String cpf;

	private String rg;

	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;

	@Enumerated(EnumType.STRING)
	private SexoEnum sexo;

	private String email;

	@OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
	@JoinTable(name = "funcionario_endereco", joinColumns = @JoinColumn(name = "funcionario_id"), inverseJoinColumns = @JoinColumn(name = "endereco_id"))
	private Endereco endereco;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
	@JoinTable(name = "funcionario_contato", joinColumns = @JoinColumn(name = "funcionario_id"), inverseJoinColumns = @JoinColumn(name = "contato_id"))
	private List<Contato> contatos;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Empresa empresa;

	@Column(name = "data_admissao")
	private LocalDate dataAdmissao;

	@Column(name = "data_demissao")
	private LocalDate dataDemissao;

	private BigDecimal salario;

	private String funcao;

	@OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
	@JoinTable(name = "funcionario_conta", joinColumns = @JoinColumn(name = "funcionario_id"), inverseJoinColumns = @JoinColumn(name = "conta_id"))
	private Conta conta;

	public Funcionario(String nome, String cpf, String rg, LocalDate dataNascimento, SexoEnum sexo, String email,
			Endereco endereco, List<Contato> contatos, Empresa empresa, LocalDate dataAdmissao, LocalDate dataDemissao,
			BigDecimal salario, String funcao, Conta conta) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.email = email;
		this.endereco = endereco;
		this.contatos = contatos;
		this.empresa = empresa;
		this.dataAdmissao = dataAdmissao;
		this.dataDemissao = dataDemissao;
		this.salario = salario;
		this.funcao = funcao;
		this.conta = conta;
	}

}
