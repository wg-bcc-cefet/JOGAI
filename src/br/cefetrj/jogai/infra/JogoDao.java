package br.cefetrj.jogai.infra;

import java.util.List;

import br.cefetrj.jogai.dominio.Jogo;

public interface JogoDao {

	public abstract boolean incluir(Jogo p);

	public abstract boolean alterar(Jogo p);

	public abstract boolean excluir(Jogo p);

	public abstract List<Jogo> obterTodos();
}
