package br.cefetrj.jogai.infra;

import java.util.List;

import br.cefetrj.jogai.dominio.TipoGrupo;

public class TipoGrupoDaoJpa extends GenericDaoJpa<TipoGrupo> implements TipoGrupoDao {

	@Override
	public TipoGrupo getTipoGrupoPorNome(String nome) {
		String consulta = "SELECT a from TipoGrupo a WHERE a.nome = ?";
		Object array[] = { nome };
		return super.obterEntidade(consulta, array);
	}

	@Override
	public boolean excluir(TipoGrupo p) {
		return super.excluir(TipoGrupo.class, p.getId());
	}

	@Override
	public List<TipoGrupo> obterTodos() {
		return super.obterTodos(TipoGrupo.class);
	}

	
	public static void main(String[] args) {
		TipoGrupoDaoJpa dao = new TipoGrupoDaoJpa();
		List<TipoGrupo> tipos = dao.obterTodos();
		for (TipoGrupo tipoGrupo : tipos) {
			System.out.println("passou");
			System.out.println(tipoGrupo.getNome());
		}
	}
}
