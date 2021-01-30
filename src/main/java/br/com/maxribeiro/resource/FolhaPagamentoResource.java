package br.com.maxribeiro.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.maxribeiro.dto.FolhaPagamentoDto;
import br.com.maxribeiro.service.FolhaPagamentoService;

@RestController
@RequestMapping("/empresas/{empresaId}/folha-pagamento")
public class FolhaPagamentoResource {

	@Autowired
	private FolhaPagamentoService service;

	@PostMapping("/pagar")
	public ResponseEntity<FolhaPagamentoDto> pagar(@PathVariable Long empresaId) {
		FolhaPagamentoDto dto = service.pagar(empresaId);
		return ResponseEntity.ok(dto);
	}
}
