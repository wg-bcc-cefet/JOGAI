package br.cefetrj.jogai.infra;

import java.util.List;

import br.cefetrj.jogai.dominio.Grupo;
import br.cefetrj.jogai.dominio.TipoGrupo;

public interface GrupoDao {

	public abstract boolean incluir(Grupo p);

	public abstract boolean alterar(Grupo p);

	public abstract boolean excluir(Grupo p);

	public abstract Grupo getGrupoPorTipoGrupo(TipoGrupo tipoGrupo);

	public abstract List<Grupo> obterTodos();
	
}
