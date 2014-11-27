package br.cefetrj.jogai.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.primefaces.event.DragDropEvent;

import br.cefetrj.jogai.dominio.Grupo;
import br.cefetrj.jogai.dominio.Participante;
import br.cefetrj.jogai.dominio.TipoGrupo;
import br.cefetrj.jogai.infra.GrupoDaoJpa;
import br.cefetrj.test.etc.TipoGrupoDaoJpa;

@ManagedBean(name="tableBean")
@SessionScoped
public class TableBean {
	private List<Participante> participantesDisponiveis = new ArrayList<Participante>();
	private List<Participante> participantesDropadosA = new ArrayList<Participante>();
	private List<Participante> participantesDropadosB = new ArrayList<Participante>();
	private List<Participante> participantesDropadosC = new ArrayList<Participante>();
	private TipoGrupo tipoGrupoLavras = new TipoGrupo("Lavras");
	private TipoGrupo tipoGrupoOurives = new TipoGrupo("Ourives");
	private TipoGrupo tipoGrupoJoalheria = new TipoGrupo("Joalheria");
	
	private Grupo a = new Grupo(tipoGrupoLavras);
	private Grupo b = new Grupo(tipoGrupoOurives);
	private Grupo c = new Grupo(tipoGrupoJoalheria);
	
	public TableBean(){
		GrupoDaoJpa grupoDao = new GrupoDaoJpa();
		TipoGrupoDaoJpa tipoGrupoDao = new TipoGrupoDaoJpa();
		participantesDisponiveis = new ArrayList<Participante>();
		participantesDropadosA = new ArrayList<Participante>();
		participantesDropadosB = new ArrayList<Participante>();
		participantesDropadosC = new ArrayList<Participante>();
		popularListaParticipantes(participantesDisponiveis,8);
		tipoGrupoDao.incluir(tipoGrupoLavras);
		tipoGrupoDao.incluir(tipoGrupoOurives);
		tipoGrupoDao.incluir(tipoGrupoJoalheria);
		grupoDao.incluir(a);
		grupoDao.incluir(b);
		grupoDao.incluir(c);
	}
	
	public void popularListaParticipantes(List<Participante> listaParticipantes, int tamanho){
		for(int i = 0 ; i <= tamanho ; i++){
			listaParticipantes.add(new Participante(getRandomPrimeiroNome(),getRandomUltimoNome()));
		}
	}

	public String getRandomPrimeiroNome(){
		String[] primeiroNome = new String[8];
		primeiroNome[0] = "João";
		primeiroNome[1] = "Pedro";
		primeiroNome[2] = "Maria";
		primeiroNome[3] = "Ricardo";
		primeiroNome[4] = "Letícia";
		primeiroNome[5] = "Breno";
		primeiroNome[6] = "Francisco";
		primeiroNome[7] = "Rebecca";
		Random gerador = new Random();
		return primeiroNome[gerador.nextInt(7)+1];
	}
	
	public String getRandomUltimoNome(){
		String[] ultimoNome = new String[8];
		ultimoNome[0] = "Torres";
		ultimoNome[1] = "Bastos";
		ultimoNome[2] = "Gomes";
		ultimoNome[3] = "Rocha";
		ultimoNome[4] = "Martins";
		ultimoNome[5] = "Rodrigues";
		ultimoNome[6] = "Reis";
		ultimoNome[7] = "Quintana";
		Random gerador = new Random();
		return ultimoNome[gerador.nextInt(7)+1];
	}
	
	public void onParticipanteDropA(DragDropEvent ddEvent) {
		GrupoDaoJpa dao = new GrupoDaoJpa();
        Participante participante = ((Participante) ddEvent.getData());
        participantesDropadosA.add(participante);
        a.addParticipante(participante);
        dao.alterar(a);
        participantesDisponiveis.remove(participante);
    }
	
	public void onParticipanteDropB(DragDropEvent ddEvent) {
		GrupoDaoJpa dao = new GrupoDaoJpa();
        Participante participante = ((Participante) ddEvent.getData());
        participantesDropadosB.add(participante);
        b.addParticipante(participante);
        dao.alterar(b);
        participantesDisponiveis.remove(participante);
    }  

	public void onParticipanteDropC(DragDropEvent ddEvent) {
		GrupoDaoJpa dao = new GrupoDaoJpa();
	    Participante participante = ((Participante) ddEvent.getData());
	    participantesDropadosC.add(participante);
	    c.addParticipante(participante);
	    dao.alterar(c);
	    participantesDisponiveis.remove(participante);
	}  
	
	public List<Participante> getParticipantesDisponiveis() {
		
		return participantesDisponiveis;
		
	}

	public void setParticipantesDisponiveis(List<Participante> participantes) {
		this.participantesDisponiveis = participantes;
	}

	public List<Participante> getParticipantesDropadosA() {
		return participantesDropadosA;	
	}

	public void setParticipantesDropadosA(List<Participante> participantesDropadosA) {
		this.participantesDropadosA = participantesDropadosA;
	}
	
	public List<Participante> getParticipantesDropadosB() {
		return participantesDropadosB;
	}

	public void setParticipantesDropadosB(List<Participante> participantesDropadosB) {
		this.participantesDropadosB = participantesDropadosB;
	}
	
	public List<Participante> getParticipantesDropadosC() {
		return participantesDropadosC;
	}

	public void setParticipantesDropadosC(List<Participante> participantesDropadosC) {
		this.participantesDropadosC = participantesDropadosC;
	}
	
}
