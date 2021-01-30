package br.com.maxribeiro.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FolhaPagamentoDto {

	private String empresaCnpj;

	private String empresaNome;

	private LocalDate dataPagamento;
	
	private BigDecimal valorTotalFolha;
	
	private BigDecimal valorTaxaSobreFolha;
	
	private BigDecimal valorTotalFolhaComTaxa;

	private List<FolhaPagamentoItemDto> itens;

}
