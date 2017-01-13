package br.cefetrj.test.etc;

import br.cefetrj.jogai.dominio.Usuario;
import br.cefetrj.jogai.infra.UsuarioDaoJpa;

public class IncludeJogo {

	public static void main(String[] args){
		
		UsuarioDaoJpa dao = new UsuarioDaoJpa();

		Usuario prof = new Usuario("prof","prof","ROLE_PROFESSOR");
		dao.incluir(prof);

		System.exit(0);
	}
}