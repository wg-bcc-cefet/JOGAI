package br.cefetrj.jogai.beans;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.cefetrj.jogai.infra.LoginService;
import br.cefetrj.jogai.dominio.InstituicaoEnsino;
import br.cefetrj.jogai.dominio.Usuario;

@ManagedBean
@RequestScoped
public class UserLoginBean {

	private LoginService servico = new LoginService();

	private String username;
	private String password;

	private String perfilUsado;

	public UserLoginBean() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPerfilUsado() {
		return perfilUsado;
	}

	public void setPerfilUsado(String perfilUsado) {
		this.perfilUsado = perfilUsado;
	}

	public String login() throws ServletException, IOException {
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpServletRequest request = ((HttpServletRequest) context.getRequest());

		ServletResponse response = ((ServletResponse) context.getResponse());
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/j_spring_security_check");
		dispatcher.forward(request, response);
		FacesContext.getCurrentInstance().responseComplete();

		Usuario usuario = servico.getUsuario(this.getUsername(),
				this.getPassword());
		if (usuario != null) {
			HttpSession session = getSessionCurrent();
			session.setAttribute("usuario_logado", usuario);

			if(usuario.getInstituicaoEnsino() != null){
			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().put("instituicaoensino", (usuario.getInstitiuicaoEnsino());
			} else (usuario.getParticipante() != null) {
				FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().put("participante", (usuario.getParticipante());
			}

		}
		return null;
	}

	private HttpSession getSessionCurrent() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) facesContext
				.getExternalContext().getRequest();
		HttpSession session = request.getSession();
		return session;
	}
}
