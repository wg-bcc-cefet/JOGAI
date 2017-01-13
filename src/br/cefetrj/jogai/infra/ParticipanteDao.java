package br.cefetrj.jogai.infra;

import java.util.List;

import br.cefetrj.jogai.dominio.InstituicaoEnsino;
import br.cefetrj.jogai.dominio.Participante;

public interface ParticipanteDao {

	public abstract boolean incluir(Participante p);

	public abstract boolean alterar(Participante p);

	public abstract boolean excluir(Participante p);

	public abstract boolean incluirEmGrupo(Participante p);
	
	public abstract Participante getParticipantePorPrimeiroNome(String primeiroNome);
	
	public abstract Participante getParticipantePorUltimoNome(String ultimoNome);

	public abstract List<Participante> obterTodos();

	List<Participante> obterTodosDeInstituicao(InstituicaoEnsino ie);

}
