package br.cefetrj.jogai.infra;

import java.util.List;

import br.cefetrj.jogai.dominio.Comercializacao;
import br.cefetrj.jogai.dominio.Grupo;

public class ComercializacaoDaoJpa extends GenericDaoJpa<Comercializacao> implements ComercializacaoDao{
	
	@Override
	public boolean alterar(Comercializacao p) {
		return super.alterar(p);
	}
	
	@Override
	public boolean incluir(Comercializacao p) {
		return super.incluir(p);
	}
	
	@Override
	public boolean excluir(Comercializacao p) {
		return super.excluir(Comercializacao.class, p.getId());
	}
	
	@Override
	public List<Comercializacao> obterComercializacoesPorGrupo(Grupo grupo) {
		
		String consulta = "SELECT a FROM Comercializacao a WHERE grupo_id = ?";
		Object array[] = { grupo.getId() };
		return super.obterEntidades(consulta, array);
		
	}
	
	@Override
	public List<Comercializacao> obterTodos() {
		return super.obterTodos(Comercializacao.class);
	}
	
}
