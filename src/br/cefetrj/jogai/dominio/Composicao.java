package br.cefetrj.jogai.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

//Anotação para mostrar que essa classe é armazenada no banco de dados
@Entity
/**
 * Classe Composição - Representa a composição de um produto em pinos
 * A composição contém o pino que foi utilizado, e a quantidade utilizada.
 * 
 * @author JOGAI
 *
 */
public class Composicao {
	
	@Id
	@GeneratedValue
	Long id;

	//@OneToOne, @ManyToMany, @ManyToOne e @OneToMany são algumas das anotações que designam os tipos de relações
	//que existem entre as classes
	//Existe um pdf no Dropbox que explica melhor cada anotação
	@OneToOne
	private Pino pino;
	
	private int quantidade;

	//Construtores específicos para facilitar a vida durante o código em outras classes e beans
	public Composicao(Pino pino, int quantidade) {
		super();
		this.pino = pino;
		this.quantidade = quantidade;
	}
	
	//Getters e Setters
	public Pino getPino() {
		return pino;
	}

	public void setPino(Pino pino) {
		this.pino = pino;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	
}
