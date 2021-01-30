package br.com.maxribeiro.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FolhaPagamentoItemDto {

	private String funcionarioCpf;

	private String funcionarioNome;

	private BigDecimal valor;

}
