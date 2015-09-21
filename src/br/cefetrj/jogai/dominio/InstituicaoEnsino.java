package br.cefetrj.jogai.dominio;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
/**
 * Classe Instituição de Ensino - Designa uma instituição que contém participantes do jogo
 * A instituição possui vários participantes, uma razão social("nome"), um CNPJ, e um e-mail de contato
 * @author JOGAI
 *
 */
//Anotação para mostrar que essa classe é armazenada no banco de dados
@Entity
public class InstituicaoEnsino {

	@Id
	@GeneratedValue
	Long id;
	
	//@OneToOne, @ManyToMany, @ManyToOne e @OneToMany são algumas das anotações que designam os tipos de relações
	//que existem entre as classes
	//Existe um pdf no Dropbox que explica melhor cada anotação
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "INSTITUICAOENSINO_ID", referencedColumnName = "ID")
	private Set<Participante> participantes = new HashSet<Participante>();

	private String razaoSocial;
	private String CNPJ;
	private String email;
	
	//Construtores específicos para facilitar a vida durante o código em outras classes e beans
	public InstituicaoEnsino() {
		// TODO Auto-generated constructor stub
	}

	public InstituicaoEnsino(String razaoSocial, String CNPJ, String email) {
		this.razaoSocial = razaoSocial;
		this.CNPJ = CNPJ;
		this.email = email;
	}
	
	//Getters e Setters
	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return this.id;
	}

	public void adicionarParticipante(Participante participante) {
		this.participantes.add(participante);
	}

	public void removerParticipante(EntidadeSatelite instance) {
		this.participantes.remove(instance);
	}

}
