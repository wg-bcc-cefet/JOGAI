package br.cefetrj.jogai.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TipoGrupo extends EntidadeSatelite  {
	
	@Id
	@GeneratedValue
	Long id;
	
	private String nome;
	
	private TipoGrupo(){
	}

	public TipoGrupo(String nomeTipo) {
		super();
		this.nome = nomeTipo;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nomeTipo) {
		this.nome = nomeTipo;
	}
	
	public Long getId() {
		return id;
	}
}
