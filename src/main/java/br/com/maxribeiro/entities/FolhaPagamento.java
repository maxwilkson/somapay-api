package br.com.maxribeiro.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.maxribeiro.util.BigDecimalUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "folha_pagamento")
@EqualsAndHashCode(of = "id")
@Data
@NoArgsConstructor
public class FolhaPagamento implements Serializable {
	private static final long serialVersionUID = -7167443279061328024L;

	@Id
	@SequenceGenerator(name = "folha_pagamento_id_seq", sequenceName = "folha_pagamento_id_seq", allocationSize = 1)
	@GeneratedValue(generator = "folha_pagamento_id_seq", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "data_pagamento")
	private LocalDate dataPagamento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;

	@OneToMany(mappedBy = "folhaPagamento", fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE }, orphanRemoval = true)
	private List<FolhaPagamentoItem> itens = new ArrayList<>();

	/**
	 * Historico do percentual da taxa no momento do pagamento.
	 */
	private BigDecimal taxa;

	public FolhaPagamento(LocalDate dataPagamento, Empresa empresa, BigDecimal taxa) {
		this.dataPagamento = dataPagamento;
		this.empresa = empresa;
		this.taxa = taxa;
	}

	public BigDecimal getValorTotalFolha() {
		BigDecimal valorTotal = BigDecimal.ZERO;

		for (FolhaPagamentoItem item : itens) {
			valorTotal = valorTotal.add(item.getValor());
		}

		return BigDecimalUtil.arredondar(valorTotal);
	}

	public BigDecimal getValorTaxaSobreFolha() {
		return BigDecimalUtil.arredondar(this.getValorTotalFolha().multiply(taxa));
	}

	public BigDecimal getValorTotalFolhaComTaxa() {
		return BigDecimalUtil.arredondar(getValorTotalFolha().add(getValorTaxaSobreFolha()));
	}
}
