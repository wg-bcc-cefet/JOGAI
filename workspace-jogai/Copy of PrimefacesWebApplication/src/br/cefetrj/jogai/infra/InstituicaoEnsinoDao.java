package br.cefetrj.jogai.infra;

import java.util.List;

import br.cefetrj.jogai.dominio.InstituicaoEnsino;

public interface InstituicaoEnsinoDao {

	public abstract boolean incluir(InstituicaoEnsino p);

	public abstract boolean alterar(InstituicaoEnsino p);

	public abstract boolean excluir(InstituicaoEnsino p);

	public abstract List<InstituicaoEnsino> obterTodos();
	
}
