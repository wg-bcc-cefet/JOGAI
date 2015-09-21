package br.cefetrj.test.etc;

import java.util.List;

import br.cefetrj.jogai.dominio.TipoGrupo;

public interface TipoGrupoDao {

	public abstract boolean incluir(TipoGrupo p);

	public abstract boolean alterar(TipoGrupo p);

	public abstract boolean excluir(TipoGrupo p);

	public abstract TipoGrupo getTipoGrupoPorNome(String matricula);

	public abstract List<TipoGrupo> obterTodos();

}