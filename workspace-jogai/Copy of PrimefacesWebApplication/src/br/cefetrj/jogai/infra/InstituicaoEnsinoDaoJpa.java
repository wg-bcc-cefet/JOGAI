package br.cefetrj.jogai.infra;

import java.util.List;

import br.cefetrj.jogai.dominio.InstituicaoEnsino;

public class InstituicaoEnsinoDaoJpa extends GenericDaoJpa<InstituicaoEnsino> implements InstituicaoEnsinoDao{
	
	@Override
	public boolean alterar(InstituicaoEnsino p) {
		return super.alterar(p);
	}
	
	@Override
	public boolean incluir(InstituicaoEnsino p) {
		return super.incluir(p);
	}
	
	@Override
	public boolean excluir(InstituicaoEnsino p) {
		return super.excluir(InstituicaoEnsino.class, p.getId());
	}

	@Override
	public List<InstituicaoEnsino> obterTodos() {
		return super.obterTodos(InstituicaoEnsino.class);
	}
	
}
