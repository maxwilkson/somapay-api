package br.com.maxribeiro.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.maxribeiro.util.BancoDadosUtil;

@RestController
@RequestMapping("/banco-dados")
public class BancoDadosResource {

	@Autowired
	private BancoDadosUtil bancoDadosUtil;

	@PostMapping("/resetar")
	public ResponseEntity<Object> resetarDados() {
		this.bancoDadosUtil.resetarBanco();
		return ResponseEntity.ok().build();
	}
}
