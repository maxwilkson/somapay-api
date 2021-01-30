package br.com.maxribeiro.util;

import org.springframework.util.StringUtils;

import br.com.maxribeiro.entities.Endereco;

public abstract class EnderecoUtil {

	public static void copiarPropriedadesNaoNulas(Endereco enderecoFonte, Endereco enderecoAlvo) {

		if (StringUtils.hasText(enderecoFonte.getBairro()))
			enderecoAlvo.setBairro(enderecoFonte.getBairro());
		if (StringUtils.hasText(enderecoFonte.getCep()))
			enderecoAlvo.setCep(enderecoFonte.getCep());
		if (StringUtils.hasText(enderecoFonte.getCidade()))
			enderecoAlvo.setCidade(enderecoFonte.getCidade());
		if (StringUtils.hasText(enderecoFonte.getEstado()))
			enderecoAlvo.setEstado(enderecoFonte.getEstado());
		if (StringUtils.hasText(enderecoFonte.getLogradouro()))
			enderecoAlvo.setLogradouro(enderecoFonte.getLogradouro());
		if (StringUtils.hasText(enderecoFonte.getNumero()))
			enderecoAlvo.setNumero(enderecoFonte.getNumero());

	}
}
