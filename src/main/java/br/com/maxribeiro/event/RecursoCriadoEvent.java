package br.com.maxribeiro.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

public class RecursoCriadoEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1458945673496081709L;

	@Getter
	private HttpServletResponse response;

	@Getter
	private Long id;

	public RecursoCriadoEvent(Object source, HttpServletResponse response, Long id) {
		super(source);
		this.response = response;
		this.id = id;
	}

}
