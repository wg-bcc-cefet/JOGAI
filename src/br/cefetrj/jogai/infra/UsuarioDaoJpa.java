package br.cefetrj.jogai.infra;

import javax.persistence.NoResultException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;

import br.cefetrj.jogai.dominio.Usuario;

public class UsuarioDaoJpa extends GenericDaoJpa<Usuario>implements UsuarioDao, UserDetailsService {

	public Usuario getUsuarioByNome(String username) {
		String consulta = "from Usuario u where u.nomeUsuario = ?";
		Object[] positionalParams = { username };
		try {
			return super.obterEntidade(consulta, positionalParams);
		} catch (NoResultException ex) {
			return null;
		}
	}

	public static void main(String[] args) {
		System.out.println(Usuario.criptografar("admin"));
		UsuarioDaoJpa dao = new UsuarioDaoJpa();
		Usuario usuario = dao.getUsuarioByNome("admin");

		System.out.println(BCrypt.checkpw("admin", usuario.getSenha()));

		// System.out.println(usuario.getNomeUsuario());
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		return getUsuarioByNome(userName);
	}
}
