package br.cefetrj.jogai.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.DragDropEvent;
import org.primefaces.event.FlowEvent;

import br.cefetrj.jogai.dominio.Grupo;
import br.cefetrj.jogai.dominio.InstituicaoEnsino;
import br.cefetrj.jogai.dominio.Jogo;
import br.cefetrj.jogai.dominio.Participante;
import br.cefetrj.jogai.dominio.TipoGrupo;
import br.cefetrj.jogai.infra.InstituicaoEnsinoDaoJpa;
import br.cefetrj.jogai.infra.JogoDaoJpa;
import br.cefetrj.jogai.infra.ParticipanteDaoJpa;

/**
 * Bean WizardBean - Bean responsável pelo wizard de criação de jogo
 * Provavelmente uma das maiores classes escritas do zero nesse projeto
 * 
 * @author JOGAI
 *
 */ 
@ManagedBean (name="wizardBean")
@ViewScoped
public class WizardBean {
	//A instância do novo jogo
	private Jogo jogoNovo;
	//Quantidade de cada tipo de grupo
	private int numForn;
	private int numFabr;
	private int numVare;
	private int numFornAnterior;
	private int numFabrAnterior;
	private int numVareAnterior;
	//Flags para reset das configurações anteriores
	private int flagGrupos;
	private int flagInst;
	private InstituicaoEnsino ie;
	private InstituicaoEnsinoDaoJpa ieDao;
	private List<Participante> participantes;
	private ParticipanteDaoJpa partDao;
	private JogoDaoJpa jogoDao;
	private List<Grupo> listaGrupos;
	
	//Horrível, mas necessário
	private List<Participante> partSel1;
	private List<Participante> partSel2;
	private List<Participante> partSel3;
	private List<Participante> partSel4;
	private List<Participante> partSel5;
	private List<Participante> partSelK;
	private List<Participante> partSelW;
	private List<Participante> partSelX;
	private List<Participante> partSelY;
	private List<Participante> partSelZ;
	private List<Participante> partSelA;
	private List<Participante> partSelB;
	private List<Participante> partSelC;
	private List<Participante> partSelD;
	private List<Participante> partSelE;
	
	public WizardBean(){
		jogoNovo = new Jogo();
		ie = new InstituicaoEnsino();
		ieDao = new InstituicaoEnsinoDaoJpa();
		participantes = new ArrayList<Participante>();
		jogoDao = new JogoDaoJpa();
		partDao = new ParticipanteDaoJpa();
		partSel1 = new ArrayList<Participante>();
		partSel2 = new ArrayList<Participante>();
		partSel3 = new ArrayList<Participante>();
		partSel4 = new ArrayList<Participante>();
		partSel5 = new ArrayList<Participante>();
		partSelK = new ArrayList<Participante>();
		partSelW = new ArrayList<Participante>();
		partSelX = new ArrayList<Participante>();
		partSelY = new ArrayList<Participante>();
		partSelZ = new ArrayList<Participante>();
		partSelA = new ArrayList<Participante>();
		partSelB = new ArrayList<Participante>();
		partSelC = new ArrayList<Participante>();
		partSelD = new ArrayList<Participante>();
		partSelE = new ArrayList<Participante>();
		listaGrupos = new ArrayList<Grupo>();
		flagGrupos = 0;
		flagInst = 0;
		numForn = 0;
		numFabr = 0;
		numVare = 0;
	}
	
	public int getNumForn() {
		return numForn;
	}


	public void setNumForn(int numForn) {
		this.numForn = numForn;
	}


	public int getNumFabr() {
		return numFabr;
	}


	public void setNumFabr(int numFabr) {
		this.numFabr = numFabr;
	}


	public int getNumVare() {
		return numVare;
	}


	public void setNumVare(int numVare) {
		this.numVare = numVare;
	}

	/**
	 * Define o ID da instituição de ensino que a instância do novo jogo deve ter
	 * @param idIe
	 */
	public void setIdIe(Long idIe){
		if (idIe != null) {
			if (idIe != 0) {
				ie = ieDao.obterPorId(InstituicaoEnsino.class, idIe);
				jogoNovo.setInstituicaoSede(ie);
			}
		}
	}
	
	/**
	 * Retorna a ID da instituição de ensino da instância
	 * @return
	 */
	public Long getIdIe() {
		if (jogoNovo != null) {
			if (jogoNovo.getInstituicaoSede() != null) {
				return jogoNovo.getInstituicaoSede().getId();
			} else {
				return 0L;
			}
		}
		return null;
	}
	
	/**
	 * Cria e adiciona grupos na instância, com base no número de grupos a adicionar, e seu tipo
	 * @param num
	 * @param tipo
	 */
	public void setGrupos(int num, String tipo){
		if (num != 0) {
			if (tipo == "Fornecedor"){
				for(int i=1;i <= num;i++) {
					Grupo grupo = new Grupo(new TipoGrupo("Fornecedor"));
					jogoNovo.adicionarGrupo(grupo);
				}
			}
			if (tipo == "Fabricante"){
				for(int i=1;i <= num;i++) {
					Grupo grupo = new Grupo(new TipoGrupo("Fabricante"));
					jogoNovo.adicionarGrupo(grupo);
				}
			}
			if (tipo == "Varejista"){
				for(int i=1;i <= num;i++) {
					Grupo grupo = new Grupo(new TipoGrupo("Varejista"));
					jogoNovo.adicionarGrupo(grupo);
				}
			}
		}
	}
	
	/**
	 * Retorna os grupos associados à instância
	 * @return
	 */
	public Set<Grupo> getGrupos() {
		if (jogoNovo != null) {
			if (jogoNovo.getGrupos() != null) {
				return jogoNovo.getGrupos();
			} else {
				return null;
			}
		}
		return null;
	}
	
	/**
	 * Retorna uma lista com todas as instituições do banco de dados
	 * @return
	 */
	public List<SelectItem> getInstituicoes(){
		List<SelectItem> lista = new ArrayList<SelectItem>();
		List<InstituicaoEnsino> instituicoes = ieDao.obterTodos();
		lista.add(new SelectItem("0", ""));
		for (InstituicaoEnsino inst : instituicoes) {
			lista.add(new SelectItem(inst.getId(), inst.getRazaoSocial()));
		}
		return lista;
	}
	
	/**
	 * Retorna a instância do jogo
	 * @return
	 */
	public Jogo getJogoNovo() {
		return jogoNovo;
	}

	/**
	 * Define a instância do jogo
	 * @param jogoNovo
	 */
	public void setJogoNovo(Jogo jogoNovo) {
		this.jogoNovo = jogoNovo;
	}

	public InstituicaoEnsino getIe() {
		return ie;
	}

	public void setIe(InstituicaoEnsino ie) {
		this.ie = ie;
	}

	public List<Participante> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<Participante> participantes) {
		this.participantes = participantes;
	}

	public List<Participante> getPartSel1() {
		return partSel1;
	}

	public void setPartSel1(List<Participante> partSel1) {
		this.partSel1 = partSel1;
	}

	public List<Participante> getPartSel2() {
		return partSel2;
	}

	public void setPartSel2(List<Participante> partSel2) {
		this.partSel2 = partSel2;
	}

	public List<Participante> getPartSel3() {
		return partSel3;
	}

	public void setPartSel3(List<Participante> partSel3) {
		this.partSel3 = partSel3;
	}

	public List<Participante> getPartSel4() {
		return partSel4;
	}

	public void setPartSel4(List<Participante> partSel4) {
		this.partSel4 = partSel4;
	}

	public List<Participante> getPartSel5() {
		return partSel5;
	}

	public void setPartSel5(List<Participante> partSel5) {
		this.partSel5 = partSel5;
	}

	public List<Participante> getPartSelK() {
		return partSelK;
	}

	public void setPartSelK(List<Participante> partSelK) {
		this.partSelK = partSelK;
	}

	public List<Participante> getPartSelW() {
		return partSelW;
	}

	public void setPartSelW(List<Participante> partSelW) {
		this.partSelW = partSelW;
	}

	public List<Participante> getPartSelX() {
		return partSelX;
	}

	public void setPartSelX(List<Participante> partSelX) {
		this.partSelX = partSelX;
	}

	public List<Participante> getPartSelY() {
		return partSelY;
	}

	public void setPartSelY(List<Participante> partSelY) {
		this.partSelY = partSelY;
	}

	public List<Participante> getPartSelZ() {
		return partSelZ;
	}

	public void setPartSelZ(List<Participante> partSelZ) {
		this.partSelZ = partSelZ;
	}

	public List<Participante> getPartSelA() {
		return partSelA;
	}

	public void setPartSelA(List<Participante> partSelA) {
		this.partSelA = partSelA;
	}

	public List<Participante> getPartSelB() {
		return partSelB;
	}

	public void setPartSelB(List<Participante> partSelB) {
		this.partSelB = partSelB;
	}

	public List<Participante> getPartSelC() {
		return partSelC;
	}

	public void setPartSelC(List<Participante> partSelC) {
		this.partSelC = partSelC;
	}

	public List<Participante> getPartSelD() {
		return partSelD;
	}

	public void setPartSelD(List<Participante> partSelD) {
		this.partSelD = partSelD;
	}

	public List<Participante> getPartSelE() {
		return partSelE;
	}

	public void setPartSelE(List<Participante> partSelE) {
		this.partSelE = partSelE;
	}
	
	public int getNumFornAnterior() {
		return numFornAnterior;
	}

	public void setNumFornAnterior(int numFornAnterior) {
		this.numFornAnterior = numFornAnterior;
	}

	public int getNumFabrAnterior() {
		return numFabrAnterior;
	}

	public void setNumFabrAnterior(int numFabrAnterior) {
		this.numFabrAnterior = numFabrAnterior;
	}

	public int getNumVareAnterior() {
		return numVareAnterior;
	}

	public void setNumVareAnterior(int numVareAnterior) {
		this.numVareAnterior = numVareAnterior;
	}

	/**
	 * Método base do Wizard - é quem faz as transições dos passos da página
	 * Mais bem-detalhado na documentação do PrimeFaces - leitura recomendada
	 * @param event
	 * @return
	 */
	public String onFlowProcess(FlowEvent event) {
		//Caso o usuário volte algum passo e mude o número de grupos
		if(numForn != 0 && numFabr != 0 && numVare != 0){
			if (numForn != numFabrAnterior && numFabr != numFabrAnterior && numVare != numVareAnterior){
				listaGrupos = new ArrayList<Grupo>();
			}
			for(int i = 1; i <= numForn; i++){
				Grupo grupo = new Grupo(new TipoGrupo("Fornecedor"));
				listaGrupos.add(grupo);
				numFornAnterior = numForn;
			}
			for(int i = 1; i<= numFabr; i++){
				Grupo grupo = new Grupo(new TipoGrupo("Fabricante"));
				listaGrupos.add(grupo);
				numFabrAnterior = numFabr;
			}
			for(int i = 1; i<= numVare; i++){
				Grupo grupo = new Grupo(new TipoGrupo("Varejista"));
				listaGrupos.add(grupo);
				numVareAnterior = numVare;
			}
			//flagGrupos = 1;
		}
		//Para resgatar os participantes de uma dada instituição
		if(jogoNovo.getInstituicaoSede() != null){
			if(flagInst == 0){
				participantes = partDao.obterTodosDeInstituicao(jogoNovo.getInstituicaoSede());
				flagInst = 1;
			}
		}
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("jogo", jogoNovo);
		return event.getNewStep();
    }
	
	//Métodos de Drag-and-Drop
	//Também são baseados nos métodos vindos do PrimeFaces, vide documentação
	public void onParticipanteDrop1(DragDropEvent ddEvent) {  
        Participante participante = ((Participante) ddEvent.getData());  
  
        partSel1.add(participante);  
        participantes.remove(participante);  
    }
	
	public void onParticipanteDrop2(DragDropEvent ddEvent) {  
        Participante participante = ((Participante) ddEvent.getData());  
  
        partSel2.add(participante);  
        participantes.remove(participante);  
    }
	
	public void onParticipanteDrop3(DragDropEvent ddEvent) {  
        Participante participante = ((Participante) ddEvent.getData());  
  
        partSel3.add(participante);  
        participantes.remove(participante);  
    }
	
	public void onParticipanteDrop4(DragDropEvent ddEvent) {  
        Participante participante = ((Participante) ddEvent.getData());  
  
        partSel4.add(participante);  
        participantes.remove(participante);  
    }
	
	public void onParticipanteDrop5(DragDropEvent ddEvent) {  
        Participante participante = ((Participante) ddEvent.getData());  
  
        partSel5.add(participante);  
        participantes.remove(participante);  
    }
	
	public void onParticipanteDropK(DragDropEvent ddEvent) {  
        Participante participante = ((Participante) ddEvent.getData());  
  
        partSelK.add(participante);  
        participantes.remove(participante);  
    }
	public void onParticipanteDropX(DragDropEvent ddEvent) {  
        Participante participante = ((Participante) ddEvent.getData());  
  
        partSelX.add(participante);  
        participantes.remove(participante);  
    }
	public void onParticipanteDropY(DragDropEvent ddEvent) {  
        Participante participante = ((Participante) ddEvent.getData());  
  
        partSelY.add(participante);  
        participantes.remove(participante);  
    }
	public void onParticipanteDropZ(DragDropEvent ddEvent) {  
        Participante participante = ((Participante) ddEvent.getData());  
  
        partSelZ.add(participante);  
        participantes.remove(participante);  
    }
	public void onParticipanteDropW(DragDropEvent ddEvent) {  
        Participante participante = ((Participante) ddEvent.getData());  
  
        partSelW.add(participante);  
        participantes.remove(participante);  
    }
	
	public void onParticipanteDropA(DragDropEvent ddEvent) {  
        Participante participante = ((Participante) ddEvent.getData());  
  
        partSelA.add(participante);  
        participantes.remove(participante);  
    }
	public void onParticipanteDropB(DragDropEvent ddEvent) {  
        Participante participante = ((Participante) ddEvent.getData());  
  
        partSelB.add(participante);  
        participantes.remove(participante);  
    }
	public void onParticipanteDropC(DragDropEvent ddEvent) {  
        Participante participante = ((Participante) ddEvent.getData());  
  
        partSelC.add(participante);  
        participantes.remove(participante);  
    }
	public void onParticipanteDropD(DragDropEvent ddEvent) {  
        Participante participante = ((Participante) ddEvent.getData());  
  
        partSelD.add(participante);  
        participantes.remove(participante);  
    }
	public void onParticipanteDropE(DragDropEvent ddEvent) {  
        Participante participante = ((Participante) ddEvent.getData());  
  
        partSelE.add(participante);  
        participantes.remove(participante);  
    }
	
	/**
	 * Método de finalização do processo e inclusão no banco de dados
	 * @throws IOException
	 */
	public void concluir() throws IOException{
	
		jogoDao.incluir(jogoNovo);
		FacesContext.getCurrentInstance().getExternalContext().redirect("MenuProfessor.xhtml");
		
	}
	
}
