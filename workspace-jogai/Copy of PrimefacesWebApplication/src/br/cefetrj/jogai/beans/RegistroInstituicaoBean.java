package br.cefetrj.jogai.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.cefetrj.jogai.dominio.InstituicaoEnsino;
import br.cefetrj.jogai.dominio.Usuario;
import br.cefetrj.jogai.infra.UsuarioDaoJpa;

@ManagedBean(name = "registroInstituicaoBean")
@RequestScoped
public class RegistroInstituicaoBean {
	private String nomeUsuario;
	private String senha;
	private String razaoSocial;
	private String CNPQ;
	private String email;
	
	public void registrar(){
		UsuarioDaoJpa UserDao = new UsuarioDaoJpa();
		InstituicaoEnsino instituicao = new InstituicaoEnsino(razaoSocial,CNPQ,email);
		Usuario user = new Usuario(nomeUsuario,senha,"Instituição",instituicao);
		UserDao.incluir(user);
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCNPQ() {
		return CNPQ;
	}

	public void setCNPQ(String cNPQ) {
		CNPQ = cNPQ;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
