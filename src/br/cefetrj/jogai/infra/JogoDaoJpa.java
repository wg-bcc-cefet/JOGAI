package br.cefetrj.jogai.infra;

import java.util.List;

import br.cefetrj.jogai.dominio.Jogo;

public class JogoDaoJpa extends GenericDaoJpa<Jogo> implements JogoDao{
	
	@Override
	public boolean alterar(Jogo p) {
		return super.alterar(p);
	}
	
	@Override
	public boolean incluir(Jogo p) {
		return super.incluir(p);
	}
	
	@Override
	public boolean excluir(Jogo p) {
		return super.excluir(Jogo.class, p.getId());
	}
	
	@Override
	public List<Jogo> obterTodos() {
		return super.obterTodos(Jogo.class);
	}
	
}
