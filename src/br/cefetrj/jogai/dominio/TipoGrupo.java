package br.cefetrj.jogai.dominio;

import javax.persistence.Entity;

/**
 * Classe Tipo do Grupo - Representa os 3 diferentes tipos de grupo que existem
 * Cada tipo possui um nome para propósitos de diferenciação
 * @author JOGAI
 *
 */
//Anotação para mostrar que essa classe é armazenada no banco de dados
@Entity
public class TipoGrupo extends EntidadeSatelite  {
	
	private String nome;

	//Construtores específicos para facilitar a vida durante o código em outras classes e beans
	private TipoGrupo(){
	}

	public TipoGrupo(String nomeTipo) {
		super();
		this.nome = nomeTipo;
	}
	
	//Getters e Setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nomeTipo) {
		this.nome = nomeTipo;
	}
}
