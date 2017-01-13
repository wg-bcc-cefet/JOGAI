package br.cefetrj.jogai.infra;

import br.cefetrj.jogai.dominio.Usuario;

public class LoginService {

	private UsuarioRepository usuarioRepo = new UsuarioRepository();

	public Usuario getUsuario(String username, String password) {
		Usuario usr = usuarioRepo.getUsuarioByNome(username);
		if (usr != null) {
			if (usr.ehSenhaCorreta(password)) {
				return usr;
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		LoginService servico = new LoginService();
		Usuario usr = servico.getUsuario("admin", "admin");
		System.out.println(usr.getNomeUsuario());
	}
}
