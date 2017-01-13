package br.cefetrj.jogai.dominio;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

//Anotação para mostrar que essa classe é armazenada no banco de dados
@Entity
/**
 * Classe Jogo - Representa um jogo corrente.
 * Possui uma instituição sede e um conjunto de grupos jogadores
 * @author JOGAI
 *
 */
public class Jogo extends EntidadeSatelite {

	private String descricao;

	//@OneToOne, @ManyToMany, @ManyToOne e @OneToMany são algumas das anotações que designam os tipos de relações
	//que existem entre as classes
	//Existe um pdf no Dropbox que explica melhor cada anotação
	@ManyToOne
	private InstituicaoEnsino instituicaoSede;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "JOGO_ID", referencedColumnName = "ID")
	private Set<Grupo> grupos = new HashSet<Grupo>();
	
	//Construtores específicos para facilitar a vida durante o código em outras classes e beans
	public Jogo(){
	}
	
	public Jogo(String descricao){
		super();
		this.descricao = descricao;
	}
	
	//Getters e Setters
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public InstituicaoEnsino getInstituicaoSede() {
		return instituicaoSede;
	}

	public void setInstituicaoSede(InstituicaoEnsino instituicaoSede) {
		this.instituicaoSede = instituicaoSede;
	}
	
	public void adicionarGrupo(Grupo g){
		this.grupos.add(g);
	}
	
	public void removerGrupo(Grupo g){
		this.grupos.remove(g);
	}
	
	public void resetarGrupos(){
		this.grupos = null;
		this.grupos = new HashSet<Grupo>();
	}
	
	public Set<Grupo> getGrupos(){
		return this.grupos;
	}
	
	
}
