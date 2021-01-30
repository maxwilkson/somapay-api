package br.com.maxribeiro.util;

import org.springframework.util.StringUtils;

import br.com.maxribeiro.entities.Conta;

public abstract class ContaUtil {

	public static void copiarPropriedadesNaoNulas(Conta contaFonte, Conta contaAlvo) {

		if (StringUtils.hasText(contaFonte.getBanco()))
			contaAlvo.setBanco(contaFonte.getBanco());
		if (contaFonte.getAgencia() != null)
			contaAlvo.setAgencia(contaFonte.getAgencia());
		if (contaFonte.getDigitoAgencia() != null)
			contaAlvo.setDigitoAgencia(contaFonte.getDigitoAgencia());
		if (contaFonte.getNumero() != null)
			contaAlvo.setNumero(contaFonte.getNumero());
		if (contaFonte.getDigitoConta() != null)
			contaAlvo.setDigitoConta(contaFonte.getDigitoConta());
		if (contaFonte.getOperacao() != null)
			contaAlvo.setOperacao(contaFonte.getOperacao());
		if (contaFonte.getSaldo() != null)
			contaAlvo.setSaldo(contaFonte.getSaldo());
	}
}
