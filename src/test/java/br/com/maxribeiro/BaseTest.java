package br.com.maxribeiro;

import static io.restassured.RestAssured.with;

import java.util.HashMap;
import java.util.Map;

public class BaseTest {

	public static final String APP_URI = "http://localhost:8080";
	public static final String USUARIO = "user";
	public static final String SENHA = "123";
	public static final String CLIENT_ID = "somapay";
	public static final String CLIENT_SECRET = "s0m@p@y";
	
	public String token;
	
	public void atualizarToken() {
		
		Map<String, String> form = new HashMap<>();
		form.put("grant_type", "password");
		form.put("username", USUARIO);
		form.put("password", SENHA);
		
		this.token = with()
				.auth().basic(CLIENT_ID, CLIENT_SECRET)
				.formParams(form)
				.post("/oauth/token")
				.andReturn().body().jsonPath().get("access_token");
	}
}
