package br.cefetrj.jogai.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Composicao {
	
	@Id
	@GeneratedValue
	Long id;
	
	@OneToOne
	private Pino pino;
	
	private int quantidade;

	
	public Composicao(Pino pino, int quantidade) {
		super();
		this.pino = pino;
		this.quantidade = quantidade;
	}
	
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
