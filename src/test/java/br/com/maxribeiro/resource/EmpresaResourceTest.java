package br.com.maxribeiro.resource;

import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.not;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import br.com.maxribeiro.entities.Empresa;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest
public class EmpresaResourceTest {
	
	@BeforeAll
	public static void setup() {
	    RestAssured.baseURI = "http://localhost:8080";
	}
	
	@Test
	public void postEmpresa() {
		with().body(criarEmpresa()).contentType(ContentType.JSON)
		.when()
		.post("/empresa")
		.then()
		.statusCode(HttpStatus.CREATED.value())
		.and()
		.header("location", not(blankOrNullString()));
	}
	
	private Empresa criarEmpresa() {
		Empresa empresa = new Empresa(
				"Gabrielly e Enrico Marcenaria ME",
				"40098795000137",
				"658144669",
				LocalDate.of(2016, 3, 8),
				"www.gabriellyeenricomarcenariame.com.br",
				"sac@gabriellyeenricomarcenariame.com.br",
				null,
				null,
				null,
				null
				);
		
		return empresa;
	}
	
}
