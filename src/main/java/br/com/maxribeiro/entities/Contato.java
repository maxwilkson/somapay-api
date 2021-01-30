package br.com.maxribeiro.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contato")
@EqualsAndHashCode(of = "id")
@Data
@NoArgsConstructor
public class Contato implements Serializable {
	private static final long serialVersionUID = 8284814850912540128L;

	@Id
	@SequenceGenerator(name = "contato_id_seq", sequenceName = "contato_id_seq", allocationSize = 1)
	@GeneratedValue(generator = "contato_id_seq", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Enumerated(EnumType.STRING)
	private TipoContatoEnum tipo;

	private String numero;

	public Contato(TipoContatoEnum tipo, String numero) {
		super();
		this.tipo = tipo;
		this.numero = numero;
	}

}
