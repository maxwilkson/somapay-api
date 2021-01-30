package br.com.maxribeiro.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "conta")
@EqualsAndHashCode(of = "id")
@Data
@NoArgsConstructor
public class Conta implements Serializable {
	private static final long serialVersionUID = -8364018879825295464L;

	@Id
	@SequenceGenerator(name = "conta_id_seq", sequenceName = "conta_id_seq", allocationSize = 1)
	@GeneratedValue(generator = "conta_id_seq", strategy = GenerationType.SEQUENCE)
	private Long id;

	private String banco;

	private Long agencia;

	private Integer digitoAgencia;

	private Long numero;

	private Integer digitoConta;

	/**
	 * Campo utilizado pela CAIXA ECONOMICA FEDERAL.
	 */
	private Long operacao;

	private BigDecimal saldo;

	public Conta(String banco, Long agencia, Integer digitoAgencia, Long numero, Integer digitoConta, Long operacao,
			BigDecimal saldo) {
		super();
		this.banco = banco;
		this.agencia = agencia;
		this.digitoAgencia = digitoAgencia;
		this.numero = numero;
		this.digitoConta = digitoConta;
		this.operacao = operacao;
		this.saldo = saldo;
	}

	public void depositar(BigDecimal valor) {
		saldo = saldo.add(valor);
	}

	public void sacar(BigDecimal valor) {
		saldo = saldo.subtract(valor);
	}

}
