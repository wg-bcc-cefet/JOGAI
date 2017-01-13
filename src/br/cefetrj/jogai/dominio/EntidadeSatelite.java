package br.cefetrj.jogai.dominio;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

/**
 * EntidadeSatelite - Superclasse base para a maior parte das entidades do domínio
 * Essa classe foi copiada de um projeto anterior do prof. Bezerra
 * 
 * @author JOGAI
 *
 */
@MappedSuperclass
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class EntidadeSatelite {

	@Id
	@GeneratedValue
	protected Long id;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
}
