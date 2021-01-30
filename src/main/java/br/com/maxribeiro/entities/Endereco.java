package br.com.maxribeiro.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "endereco")
@EqualsAndHashCode(of = "id")
@Data
@NoArgsConstructor
public class Endereco implements Serializable {
	private static final long serialVersionUID = 610557649769542794L;

	@Id
	@SequenceGenerator(name = "endereco_id_seq", sequenceName = "endereco_id_seq", allocationSize = 1)
	@GeneratedValue(generator = "endereco_id_seq", strategy = GenerationType.SEQUENCE)
	private Long id;

	private String logradouro;

	private String numero;

	private String bairro;

	private String cidade;

	private String estado;

	private String cep;

	public Endereco(String logradouro, String numero, String bairro, String cidade, String estado, String cep) {
		super();
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
	}

}
