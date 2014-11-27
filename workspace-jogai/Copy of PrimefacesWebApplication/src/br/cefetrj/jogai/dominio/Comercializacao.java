package br.cefetrj.jogai.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comercializacao {
	
	@Id
	@GeneratedValue
	Long id;
	
	@ManyToOne
	private Grupo cliente;
	
	@ManyToOne
	private Grupo fornecedor;
	
	public Grupo getCliente() {
		return cliente;
	}
	public void setCliente(Grupo cliente) {
		this.cliente = cliente;
	}
	public Grupo getFornecedorTrans() {
		return fornecedor;
	}
	public void setFornecedor(Grupo fornecedor) {
		this.fornecedor = fornecedor;
	}
	public Comercializacao(Grupo cliente, Grupo fornecedor) {
		super();
		this.cliente= cliente;
		this.fornecedor = fornecedor;
	}
}
