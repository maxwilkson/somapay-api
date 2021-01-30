package br.com.maxribeiro.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empresa")
@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Empresa implements Serializable {
	private static final long serialVersionUID = -1559769622645792999L;

	@Id
	@SequenceGenerator(name = "empresa_id_seq", sequenceName = "empresa_id_seq", allocationSize = 1)
	@GeneratedValue(generator = "empresa_id_seq", strategy = GenerationType.SEQUENCE)
	private Long id;

	private String nome;

	private String cnpj;

	@Column(name = "inscricao_estadual")
	private String inscricaoEstadual;

	@Column(name = "data_abertura")
	private LocalDate dataAbertura;

	private String site;

	private String email;

	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
	@JoinTable(name = "empresa_endereco", joinColumns = @JoinColumn(name = "empresa_id"), inverseJoinColumns = @JoinColumn(name = "endereco_id"))
	private Endereco endereco;

	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
	@JoinTable(name = "empresa_contato", joinColumns = @JoinColumn(name = "empresa_id"), inverseJoinColumns = @JoinColumn(name = "contato_id"))
	private List<Contato> contatos;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
	@JoinTable(name = "empresa_conta", joinColumns = @JoinColumn(name = "empresa_id"), inverseJoinColumns = @JoinColumn(name = "conta_id"))
	private Conta conta;
	
	@JsonIgnore
	@OneToMany(mappedBy = "empresa", orphanRemoval = true)
	private List<Funcionario> funcionarios;

	public Empresa(String nome, String cnpj, String inscricaoEstadual, LocalDate dataAbertura, String site,
			String email, Endereco endereco, List<Contato> contatos, Conta conta, List<Funcionario> funcionarios) {
		super();
		this.nome = nome;
		this.cnpj = cnpj;
		this.inscricaoEstadual = inscricaoEstadual;
		this.dataAbertura = dataAbertura;
		this.site = site;
		this.email = email;
		this.endereco = endereco;
		this.contatos = contatos;
		this.conta = conta;
		this.funcionarios = funcionarios;
	}
	
}
