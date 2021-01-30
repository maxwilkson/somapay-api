package br.com.maxribeiro.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class BigDecimalUtil {

	public static BigDecimal arredondar(BigDecimal valor) {
		return valor.setScale(2, RoundingMode.HALF_EVEN);
	}
}
