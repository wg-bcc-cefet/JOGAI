package br.cefetrj.jogai.infra;

import java.util.List;

import br.cefetrj.jogai.dominio.Pino;

public class PinoDaoJpa extends GenericDaoJpa<Pino> implements PinoDao{

	@Override
	public boolean excluir(Pino p) {
		return super.excluir(Pino.class, p.getId());
	}

	@Override
	public List<Pino> obterTodos() {
		return super.obterTodos(Pino.class);
	}
}