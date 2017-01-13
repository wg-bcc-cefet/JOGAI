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

import br.cefetrj.jogai.dominio.Usuario;
import br.cefetrj.jogai.infra.LoginService;

/**
 * Bean UserLoginBean - Bean que lida com a página de log-in e serviços associados
 * Lida diretamente com o Spring Security - leitura da documentação é recomendada
 * @author JOGAI
 *
 */
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

	/**
	 * Método de log-in - coleta de credenciais, validação e desvio
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String login() throws ServletException, IOException {
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpServletRequest request = ((HttpServletRequest) context.getRequest());

		ServletResponse response = ((ServletResponse) context.getResponse());
		//Manda o request para o Spring Security
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/j_spring_security_check");
		dispatcher.forward(request, response);
		FacesContext.getCurrentInstance().responseComplete();

		Usuario usuario = servico.getUsuario(this.getUsername(),
				this.getPassword());
		//Caso o usuário com essas credenciais exista...
		if (usuario != null) {
			HttpSession session = getSessionCurrent();
			//Define o usuário ativo como o usuário resgatado
			session.setAttribute("usuario_logado", usuario);
			
			Usuario usr = (Usuario) session.getAttribute("usuario_logado");
			//System.out.println(usr.getInstituicaoEnsino().getRazaoSocial());
			
			//Caso o usuário tenha uma Instituição de Ensino associada (i.e. ele é um usuário de instituição), coloca a instituição na sessão
			if(usuario.getInstituicaoEnsino() != null){
			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().put("instituicaoensino", (usuario.getInstituicaoEnsino()));
			}
			//Caso contrário, é um usuário aluno - resgata o objeto participante desse aluno.
			else if(usuario.getParticipante() != null) {
				FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().put("participante", (usuario.getParticipante()));
			}

		}
		return null;
	}

	/**
	 * Retorna a sessão atual
	 * @return
	 */
	private HttpSession getSessionCurrent() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) facesContext
				.getExternalContext().getRequest();
		HttpSession session = request.getSession();
		return session;
	}
}
