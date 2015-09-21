package br.cefetrj.jogai.dominio;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Classe Comercialização - Representa uma transação entre dois grupos
 * Cada comercialização possui um grupo cliente (que recebe o produto), um grupo fornecedor (que está vendendo),
 * um conjunto de itens vendáveis, e o preço total da transação
 * 
 * @author JOGAI
 *
 */
//Anotação para mostrar que essa classe é armazenada no banco de dados
@Entity
public class Comercializacao {
	
	@Id
	@GeneratedValue
	Long id;
	
	//@OneToOne, @ManyToMany, @ManyToOne e @OneToMany são algumas das anotações que designam os tipos de relações
	//que existem entre as classes
	//Existe um pdf no Dropbox que explica melhor cada anotação
	@ManyToOne
	private Grupo cliente;
	
	@ManyToOne
	private Grupo fornecedor;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "COMERCIALIZACAO_ID", referencedColumnName = "ID")
	private Set<ItemComercializacao> itens = new HashSet<ItemComercializacao>();
	
	private BigDecimal precoTotal;
	
	//Getters e Setters
	public Grupo getCliente() {
		return cliente;
	}
	public void setCliente(Grupo cliente) {
		this.cliente = cliente;
	}
	public Grupo getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Grupo fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public Set<ItemComercializacao> getItens() {
		return itens;
	}
	
	public void setItens(Set<ItemComercializacao> itens) {
		this.itens = itens;
	}
	
	public void adicionarItem(ItemComercializacao item){
		
		itens.add(item);
		
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public BigDecimal getPrecoTotal() {
		return precoTotal;
	}
	public void setPrecoTotal(BigDecimal precoTotal) {
		this.precoTotal = precoTotal;
	}
	
	//Construtores específicos para facilitar a vida durante o código em outras classes e beans
	public Comercializacao(){
	}
	
	public Comercializacao(Grupo cliente, Grupo fornecedor) {
		super();
		this.cliente= cliente;
		this.fornecedor = fornecedor;
	}
}
