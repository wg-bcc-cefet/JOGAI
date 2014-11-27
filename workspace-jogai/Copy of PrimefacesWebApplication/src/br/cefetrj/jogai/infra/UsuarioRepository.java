package br.cefetrj.jogai.infra;


import br.cefetrj.jogai.dominio.Usuario;

public class UsuarioRepository {
	UsuarioDao dao = new UsuarioDaoJpa();

	public Usuario getUsuarioByNome(String username) {
		return dao.getUsuarioByNome(username);
	}

	public void adicionar(Usuario usuario) {
		dao.incluir(usuario);
	}
}
