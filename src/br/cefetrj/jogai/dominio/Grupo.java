package br.cefetrj.jogai.dominio;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Classe Grupo - Representa cada grupo presente no jogo
 * Possui um e apenas um Tipo de Grupo, pertence a um Jogo, possui vários participantes (0..*),
 * e pode fazer várias comercializações
 * @author JOGAI
 *
 */
//Anotação para mostrar que essa classe é armazenada no banco de dados
@Entity
public class Grupo extends EntidadeSatelite {
	
	//@OneToOne, @ManyToMany, @ManyToOne e @OneToMany são algumas das anotações que designam os tipos de relações
	//que existem entre as classes
	//Existe um pdf no Dropbox que explica melhor cada anotação
	@ManyToOne
	private TipoGrupo tipoGrupo;

	@ManyToOne
	private Jogo jogo;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "GRUPO_ID", referencedColumnName = "ID")
	private Set<Participante> participantes = new HashSet<Participante>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "GRUPO_ID", referencedColumnName = "ID")
	private Set<Comercializacao> comercializacoes = new HashSet<Comercializacao>();
	
	//Construtores específicos para facilitar a vida durante o código em outras classes e beans
	public Grupo() {
	}
	
	public Grupo(TipoGrupo tipoGrupo) {
		super();
		this.tipoGrupo = tipoGrupo;
	}
	
	//Getters e Setters
	public TipoGrupo getTipoGrupo() {
		return tipoGrupo;
	}

	public void setTipoGrupo(TipoGrupo tipoGrupo) {
		this.tipoGrupo = tipoGrupo;
	}

	public Set<Comercializacao> getComercializacoes() {
		return comercializacoes;
	}

	public void setComercializacoes(Set<Comercializacao> comercializacoes) {
		this.comercializacoes = comercializacoes;
	}

	public Set<Participante> getParticipantesGrupo() {
		return participantes;
	}

	public void setParticipantesGrupo(Set<Participante> participantesGrupo) {
		this.participantes = participantesGrupo;
	}

	public void addParticipante(Participante participante) {
		participantes.add(participante);
	}

	public void removerParticipante(Participante participante) {
		participantes.remove(participante);
	}

	public Set<Participante> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(Set<Participante> participantes) {
		this.participantes = participantes;
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public void addComercializacao(Comercializacao com) {
		
		comercializacoes.add(com);
		
	}
}
