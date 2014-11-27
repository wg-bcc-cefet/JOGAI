package br.cefetrj.jogai.dominio;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Turma {

	@Id
	@GeneratedValue
	Long id;

	@ManyToOne
	private InstituicaoEnsino instituicao;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "TURMA_PARTICIPANTE", joinColumns = { @JoinColumn(name = "TURMA_ID", referencedColumnName = "ID") }, inverseJoinColumns = { @JoinColumn(name = "PARTICIPANTE_ID", referencedColumnName = "ID") })
	private List<Participante> alunos;

	private String descricao;
	private int ano;
	private Date dataInicio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public InstituicaoEnsino getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(InstituicaoEnsino instituicao) {
		this.instituicao = instituicao;
	}

	public List<Participante> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Participante> alunos) {
		this.alunos = alunos;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

}
