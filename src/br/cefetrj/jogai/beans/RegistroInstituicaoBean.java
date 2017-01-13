package br.cefetrj.jogai.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.cefetrj.jogai.dominio.InstituicaoEnsino;
import br.cefetrj.jogai.dominio.Usuario;
import br.cefetrj.jogai.infra.UsuarioDaoJpa;

/**
 * RegistroInstituicaoBean - Bean que lida com a página de registro das instituições de ensino
 * @author JOGAI
 *
 */
@ManagedBean(name = "registroInstituicaoBean")
@RequestScoped
public class RegistroInstituicaoBean {
	private String nomeUsuario;
	private String senha;
	private String razaoSocial;
	private String CNPJ;
	private String email;

	/**
	 * Insere a instituição e um usuário no banco de dados com os dados especificados 
	 */
	public void registrar() {
		UsuarioDaoJpa UserDao = new UsuarioDaoJpa();
		InstituicaoEnsino instituicao = new InstituicaoEnsino(razaoSocial,
				CNPJ, email);
		Usuario user = new Usuario(nomeUsuario, senha, "ROLE_INSTITUICAO",
				instituicao);
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

	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String novo) {
		CNPJ = novo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
