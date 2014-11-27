package br.cefetrj.jogai.dominio;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "VENDAVEL_TYPE")
public class Vendavel {

	@Id
	@GeneratedValue
	Long id;

	public Long getId() {
		return id;
	}
	
	protected Vendavel() {
		// TODO Auto-generated constructor stub
	}
}
