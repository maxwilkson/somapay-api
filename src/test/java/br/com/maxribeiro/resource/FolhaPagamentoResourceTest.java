package br.com.maxribeiro.resource;

import static io.restassured.RestAssured.with;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import br.com.maxribeiro.BaseTest;
import br.com.maxribeiro.entities.Empresa;
import br.com.maxribeiro.respository.EmpresaRepository;
import br.com.maxribeiro.util.BancoDadosUtil;
import br.com.maxribeiro.util.BigDecimalUtil;
import io.restassured.RestAssured;

@SpringBootTest
public class FolhaPagamentoResourceTest extends BaseTest {
	
	@Autowired
	private BancoDadosUtil bancoDadosUtil;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@BeforeAll
	public static void setup() {
	    RestAssured.baseURI = APP_URI;
	}

	@BeforeEach
	public void init() {
		bancoDadosUtil.resetarBanco();
		atualizarToken();
	}
	
	@Test
	public void postEmpresa() {
		Empresa empresa = empresaRepository.findByCnpj("75253226000101").get();
		
		with().auth().oauth2(super.token)
		.when()
		.post("/empresas/" + empresa.getId() + "/folha-pagamento/pagar")
		.then()
		.statusCode(equalTo(HttpStatus.OK.value()));
		
		BigDecimal saldo = with().auth().oauth2(super.token)
		.when()
		.get("/empresas/" + empresa.getId() + "/conta/saldo")
		.thenReturn().getBody().as(BigDecimal.class);
		
		assertThat(BigDecimalUtil.arredondar(saldo), equalTo(BigDecimalUtil.arredondar(new BigDecimal("695021.60"))));

	}
}