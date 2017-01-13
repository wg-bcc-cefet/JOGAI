package br.cefetrj.jogai.infra;

import java.util.List;

import br.cefetrj.jogai.dominio.Pino;

public interface PinoDao {

	public abstract boolean incluir(Pino p);

	public abstract boolean alterar(Pino p);

	public abstract boolean excluir(Pino p);

	public abstract List<Pino> obterTodos();

}

