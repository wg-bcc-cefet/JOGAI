package br.cefetrj.jogai.infra;

import java.util.List;

import br.cefetrj.jogai.dominio.Grupo;
import br.cefetrj.jogai.dominio.Jogo;
import br.cefetrj.jogai.dominio.Participante;
import br.cefetrj.jogai.dominio.TipoGrupo;

public class GrupoDaoJpa extends GenericDaoJpa<Grupo> implements GrupoDao{
	@Override
	public Grupo getGrupoPorTipoGrupo(TipoGrupo tipoGrupo) {
		String consulta = "SELECT a from Grupo a WHERE a.tipoGrupo = ?";
		Object array[] = { tipoGrupo };
		return super.obterEntidade(consulta, array);
	}

	@Override
	public Grupo getGrupoPorParticipanteDeJogo(Participante p, Jogo j){
		
		String consulta = "SELECT g FROM Jogo j JOIN j.grupos g JOIN g.participantes p WHERE p.id = ? and j.id = ?";
		Object array[] = { p.getId(), j.getId() };
		return super.obterEntidade(consulta, array);
		
	}
		
	@Override
	public List<Grupo> getGruposPorJogo(Jogo j) {
		String consulta = "SELECT a from Grupo a WHERE jogo_id = ?";
		Object array[] = { j.getId() };
		return super.obterEntidades(consulta, array);
	}
	
	@Override
	public List<Grupo> getGruposPorJogoETipo(Jogo j, TipoGrupo tipoGrupo){
		String consulta = "SELECT a FROM Grupo a WHERE jogo_id = ? AND a.tipoGrupo = ?";
		Object array[] = { j.getId(), tipoGrupo };
		return super.obterEntidades(consulta, array);
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
