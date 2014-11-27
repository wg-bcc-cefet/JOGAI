package br.cefetrj.jogai.infra;

import java.util.List;

import br.cefetrj.jogai.dominio.Grupo;
import br.cefetrj.jogai.dominio.TipoGrupo;

public class GrupoDaoJpa extends GenericDaoJpa<Grupo> implements GrupoDao{
	@Override
	public Grupo getGrupoPorTipoGrupo(TipoGrupo tipoGrupo) {
		String consulta = "SELECT a from Grupo a WHERE a.tipoGrupo = ?";
		Object array[] = { tipoGrupo };
		return super.obterEntidade(consulta, array);
	}

	
	@Override
	public boolean alterar(Grupo p) {
		return super.alterar(p);
	}
	
	@Override
	public boolean incluir(Grupo p) {
		return super.incluir(p);
	}
	
	@Override
	public boolean excluir(Grupo p) {
		return super.excluir(Grupo.class, p.getId());
	}

	@Override
	public List<Grupo> obterTodos() {
		return super.obterTodos(Grupo.class);
	}
}
