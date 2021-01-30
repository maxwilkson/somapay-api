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

import br.com.maxribeiro.entities.Funcionario;
import br.com.maxribeiro.event.RecursoCriadoEvent;
import br.com.maxribeiro.service.FuncionarioService;

@RestController
@RequestMapping("/empresas/{empresaId}/funcionarios")
public class FuncionarioResource {

	@Autowired
	private FuncionarioService service;

	@Autowired
	private ApplicationEventPublisher publisher;

	@PostMapping
	public ResponseEntity<Funcionario> adicionar(@PathVariable Long empresaId, @RequestBody Funcionario funcionario,
			HttpServletResponse response) {
		Funcionario funcionarioBD = this.service.adicionar(funcionario, empresaId);
		publisher.publishEvent(new RecursoCriadoEvent(funcionarioBD, response, funcionarioBD.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioBD);
	}

	@GetMapping
	public List<Funcionario> listar(@PathVariable Long empresaId) {
		return this.service.listar(empresaId);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Funcionario> buscarPorId(@PathVariable Long id) {
		Optional<Funcionario> funcionario = this.service.buscarPorId(id);
		return funcionario.isPresent() ? ResponseEntity.ok(funcionario.get()) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{id}/conta/saldo")
	public BigDecimal buscarSaldo(@PathVariable Long id) {
		BigDecimal saldo = this.service.buscarSaldoPorFuncionarioId(id);
		return saldo;
	}

	@PutMapping("/{id}")
	public ResponseEntity<Funcionario> atualizar(@PathVariable Long id, @RequestBody Funcionario funcionario) {
		Funcionario funcionarioBD = this.service.atualizar(funcionario, id);
		return ResponseEntity.ok(funcionarioBD);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Funcionario> atualizarParcial(@PathVariable Long id, @RequestBody Funcionario funcionario) {
		Funcionario funcionarioBD = this.service.atualizarParcial(funcionario, id);
		return ResponseEntity.ok(funcionarioBD);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) {
		this.service.excluir(id);
	}

}
