package br.cefetrj.jogai.infra;

import br.cefetrj.jogai.dominio.Usuario;

public interface UsuarioDao {

	Usuario getUsuarioByNome(String username);

	boolean incluir(Usuario usuario);

}
