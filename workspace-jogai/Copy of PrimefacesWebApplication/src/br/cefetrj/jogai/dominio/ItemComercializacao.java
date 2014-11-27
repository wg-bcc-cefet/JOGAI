package br.cefetrj.jogai.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ItemComercializacao {

	@Id
	@GeneratedValue
	Long id;
	
	@OneToOne
	private Vendavel vendavel;
	
	private int quantidade;

	public ItemComercializacao(Vendavel vendavel) {
		super();
		this.vendavel = vendavel;
	}
	
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
