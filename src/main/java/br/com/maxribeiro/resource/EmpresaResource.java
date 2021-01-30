package br.com.maxribeiro.resource;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.maxribeiro.entities.Empresa;
import br.com.maxribeiro.event.RecursoCriadoEvent;
import br.com.maxribeiro.service.EmpresaService;

@RestController
@RequestMapping("/empresas")
public class EmpresaResource {

	@Autowired
	private EmpresaService service;

	@Autowired
	private ApplicationEventPublisher publisher;

	@PostMapping
	public ResponseEntity<Empresa> adicionar(@RequestBody Empresa empresa, HttpServletResponse response) {
		Empresa empresaBD = this.service.adicionar(empresa);
		publisher.publishEvent(new RecursoCriadoEvent(empresaBD, response, empresaBD.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(empresaBD);
	}

	@GetMapping
	public List<Empresa> listar() {
		return this.service.listar();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Empresa> buscarPorId(@PathVariable Long id) {
		Optional<Empresa> empresa = this.service.buscarPorId(id);
		return empresa.isPresent() ? ResponseEntity.ok(empresa.get()) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{id}/conta/saldo")
	public BigDecimal buscarSaldo(@PathVariable Long id) {
		BigDecimal saldo = this.service.buscarSaldoPorEmpresaId(id);
		return saldo;
	}

	@PutMapping("/{id}")
	public ResponseEntity<Empresa> atualizar(@PathVariable Long id, @RequestBody Empresa empresa) {
		Empresa empresaBD = this.service.atualizar(empresa, id);
		return ResponseEntity.ok(empresaBD);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Empresa> atualizarParcial(@PathVariable Long id, @RequestBody Empresa empresa) {
		Empresa empresaBD = this.service.atualizarParcial(empresa, id);
		return ResponseEntity.ok(empresaBD);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) {
		this.service.excluir(id);
	}
}
