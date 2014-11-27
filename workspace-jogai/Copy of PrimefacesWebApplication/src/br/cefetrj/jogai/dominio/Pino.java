package br.cefetrj.jogai.dominio;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue ( "PI" ) 
public class Pino extends Vendavel {

	private String tipo;

	private String cor;

	private String preco;

	public Pino() {
		// TODO Auto-generated constructor stub
	}
	
	public Pino(String tipo, String cor) {
		super();
		this.tipo = tipo;
		this.cor = cor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

}
