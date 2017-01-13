package br.cefetrj.jogai.infra;

import java.util.List;

import br.cefetrj.jogai.dominio.Comercializacao;
import br.cefetrj.jogai.dominio.Grupo;

public interface ComercializacaoDao {

	public abstract boolean incluir(Comercializacao p);

	public abstract boolean alterar(Comercializacao p);

	public abstract boolean excluir(Comercializacao p);

	public abstract List<Comercializacao> obterTodos();
	
	public abstract List<Comercializacao> obterComercializacoesPorGrupo(Grupo grupo);
	
}
