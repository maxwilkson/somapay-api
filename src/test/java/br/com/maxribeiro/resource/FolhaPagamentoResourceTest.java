package br.com.maxribeiro.resource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.not;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import br.com.maxribeiro.BaseTest;
import br.com.maxribeiro.entities.Empresa;
import br.com.maxribeiro.util.BancoDadosUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest
public class FolhaPagamentoResourceTest extends BaseTest {
	
	@Autowired
	private BancoDadosUtil bancoDadosUtil;
	
	@BeforeAll
	public static void setup() {
	    RestAssured.baseURI = APP_URI;
	}

	@BeforeEach
	public void init() {
		bancoDadosUtil.resetarBanco();
	}
	
	@Test
	public void postEmpresa() {
		given()
		.when()
		.post("/empresas/1/folha-pagamento/pagar")
		.then()
		.statusCode(equalTo(HttpStatus.OK.value()));
		
		given()
		.when()
		.get("/empresas/1/conta/saldo")
		.then()
		.statusCode(equalTo(HttpStatus.OK.value()))
		.and()
		.body(equalTo(new BigDecimal("700542.50")));

	}
}