package br.cefetrj.jogai.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Grupo extends EntidadeSatelite {

	@Id
	@GeneratedValue
	Long id;

	@ManyToOne
	private TipoGrupo tipoGrupo;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "GRUPO_ID",	referencedColumnName = "ID")
	private List<Participante> participantes = new ArrayList<Participante>();

	public Grupo() {
	}

	public Grupo(TipoGrupo tipoGrupo) {
		super();
		this.tipoGrupo = tipoGrupo;
	}

	public TipoGrupo getTipoGrupo() {
		return tipoGrupo;
	}

	public void setTipoGrupo(TipoGrupo tipoGrupo) {
		this.tipoGrupo = tipoGrupo;
	}

	public List<Participante> getParticipantesGrupo() {
		return participantes;
	}

	public void setParticipantesGrupo(List<Participante> participantesGrupo) {
		this.participantes = participantesGrupo;
	}

	public void addParticipante(Participante participante) {
		participantes.add(participante);
	}

	public void removerParticipante(Participante participante) {
		participantes.remove(participante);
	}

	public Long getId() {
		return this.id;
	}
}
