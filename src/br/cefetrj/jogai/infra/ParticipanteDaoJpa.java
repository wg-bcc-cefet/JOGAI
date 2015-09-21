package br.cefetrj.jogai.infra;

import java.util.List;

import br.cefetrj.jogai.dominio.InstituicaoEnsino;
import br.cefetrj.jogai.dominio.Participante;

public class ParticipanteDaoJpa extends GenericDaoJpa<Participante> implements ParticipanteDao{
	@Override
	public Participante getParticipantePorPrimeiroNome(String primeiroNome) {
		String consulta = "SELECT a from Participante a WHERE a.primeiroNome = ?";
		Object array[] = { primeiroNome };
		return super.obterEntidade(consulta, array);
	}
	
	@Override
	public Participante getParticipantePorUltimoNome(String ultimoNome) {
		String consulta = "SELECT a from Participante a WHERE a.ultimoNome = ?";
		Object array[] = { ultimoNome };
		return super.obterEntidade(consulta, array);
	}

	@Override
	public boolean excluir(Participante p) {
		return super.excluir(Participante.class, p.getId());
	}

	@Override
	public List<Participante> obterTodos() {
		return super.obterTodos(Participante.class);
	}
	
	@Override
	public List<Participante> obterTodosDeInstituicao(InstituicaoEnsino ie) {
		String consulta = "SELECT a from Participante a WHERE instituicaoensino_id = ?";
		Object array[] = { ie.getId() };
		return super.obterEntidades(consulta, array);
	}

	@Override
	public boolean incluirEmGrupo(Participante p) {
		// Consulta para incluir em grupo?
		return false;
	}
}
