package br.com.maxribeiro.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.maxribeiro.util.BigDecimalUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "folha_pagamento_item")
@EqualsAndHashCode(of = "id")
@Data
@NoArgsConstructor
public class FolhaPagamentoItem implements Serializable {
	private static final long serialVersionUID = -7167443279061328024L;

	@Id
	@SequenceGenerator(name = "folha_pagamento_id_seq", sequenceName = "folha_pagamento_id_seq", allocationSize = 1)
	@GeneratedValue(generator = "folha_pagamento_id_seq", strategy = GenerationType.SEQUENCE)
	private Long id;

	private BigDecimal valor;

	@ManyToOne
	@JoinColumn(name = "funcionario_id")
	private Funcionario funcionario;

	@ManyToOne
	@JoinColumn(name = "folha_pagamento_id")
	private FolhaPagamento folhaPagamento;

	public FolhaPagamentoItem(BigDecimal valor, Funcionario funcionario, FolhaPagamento folhaPagamento) {
		this.valor = valor;
		this.funcionario = funcionario;
		this.folhaPagamento = folhaPagamento;
	}

	public BigDecimal getValor() {
		return BigDecimalUtil.arredondar(this.valor);
	}
}
