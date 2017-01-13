package br.cefetrj.jogai.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

//Anotação para mostrar que essa classe é armazenada no banco de dados
@Entity
/**
 * Classe Item de Comercialização - Representa qualquer item presente em qualquer comercialização
 * Favor checar o diagrama de classes para entender a estrutura produto->vendável->item
 * @author JOGAI
 *
 */
public class ItemComercializacao {

	@Id
	@GeneratedValue
	Long id;
	
	//@OneToOne, @ManyToMany, @ManyToOne e @OneToMany são algumas das anotações que designam os tipos de relações
	//que existem entre as classes
	//Existe um pdf no Dropbox que explica melhor cada anotação
	@OneToOne
	private Vendavel vendavel;
	
	private int quantidade;
	
	//Construtores específicos para facilitar a vida durante o código em outras classes e beans
	public ItemComercializacao(Vendavel vendavel) {
		super();
		this.vendavel = vendavel;
	}
	
	public ItemComercializacao(Vendavel vendavel, int qtd) {
		super();
		this.vendavel = vendavel;
		this.quantidade = qtd;
	}
	
	//Getters e Setters
	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Vendavel getVendavel() {
		return vendavel;
	}

	public void setVendavel(Vendavel vendavel) {
		this.vendavel = vendavel;
	}
	
}
