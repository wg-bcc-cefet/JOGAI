package br.cefetrj.jogai.dominio;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue ( "PR" ) 
public class Produto extends Vendavel {

	private String nome;

	private String custo;

	private String custoVenda;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "PRODUTO_ID", referencedColumnName = "ID")
	private List<Composicao> composicao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCusto() {
		return custo;
	}

	public void setCusto(String custo) {
		this.custo = custo;
	}

	public String getCustoVenda() {
		return custoVenda;
	}

	public void setCustoVenda(String custoVenda) {
		this.custoVenda = custoVenda;
	}

	public List<Composicao> getComposicao() {
		return composicao;
	}

	public void setComposicao(List<Composicao> composicao) {
		this.composicao = composicao;
	}

	public Produto() {
		// TODO Auto-generated constructor stub
	}

	public Produto(String nome, String custo, String custoVenda,
			List<Composicao> composicao) {
		super();
		this.nome = nome;
		this.custo = custo;
		this.custoVenda = custoVenda;
		this.composicao = composicao;
	}

	public Produto(String nome, String custo, String custoVenda) {
		super();
		this.nome = nome;
		this.custo = custo;
		this.custoVenda = custoVenda;
	}

}
