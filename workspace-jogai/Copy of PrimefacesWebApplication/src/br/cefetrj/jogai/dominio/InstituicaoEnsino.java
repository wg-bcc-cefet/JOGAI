package br.cefetrj.jogai.dominio;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class InstituicaoEnsino {
	
	@Id
	@GeneratedValue
	Long id;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "INSTITUICAOENSINO_ID",	referencedColumnName = "ID")
	private List<Participante> participantes;
	
	@OneToOne
	private Usuario usuario;
	
	private String razaoSocial;
	private String CNPJ;
	private String email;
	
	public InstituicaoEnsino(String razaoSocial, String CNPJ, String email){
		this.razaoSocial = razaoSocial;
		this.CNPJ = CNPJ;
		this.email = email;
	}
	
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

	public List<Participante> getParticipantes() {
		return this.participantes;
	}

	public void adicionarParticipante(Participante participante) {
		this.participantes.add(participante);
	}
	
	
	
}
