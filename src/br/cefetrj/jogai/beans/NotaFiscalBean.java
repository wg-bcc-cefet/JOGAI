package br.cefetrj.jogai.beans;

import java.math.BigDecimal;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import br.cefetrj.jogai.dominio.Comercializacao;
import br.cefetrj.jogai.dominio.Grupo;
import br.cefetrj.jogai.dominio.ItemComercializacao;
import br.cefetrj.jogai.dominio.Jogo;
import br.cefetrj.jogai.dominio.Participante;
import br.cefetrj.jogai.dominio.Pino;
import br.cefetrj.jogai.dominio.TipoGrupo;
import br.cefetrj.jogai.infra.ComercializacaoDaoJpa;
import br.cefetrj.jogai.infra.GrupoDaoJpa;
import br.cefetrj.jogai.infra.PinoDaoJpa;

/**
 * NotaFiscalBean - Bean que lida com as páginas de nota fiscal de cada grupo
 * ATUALMENTE INCOMPLETO - Provavelmente deverá ser re-feito
 * @author JOGAI
 *
 */
@ManagedBean(name="notaFiscalBean")
@ViewScoped
public class NotaFiscalBean {

	private PinoDaoJpa pinoDao = new PinoDaoJpa();
	private ComercializacaoDaoJpa comDao = new ComercializacaoDaoJpa();
	private GrupoDaoJpa grupoDao = new GrupoDaoJpa();
	private Jogo jogoAtual;
	private List<Pino> pinos;
	private List<Comercializacao> coms;
	private List<Grupo> grupos;
	public Comercializacao inst;
	private Grupo cliente;
	private Participante usuario = (Participante) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("participante");
	private Grupo jogador;
	private BigDecimal precoTotal;
	private int qtd;
	private Pino pino;
	
	public NotaFiscalBean(){

		jogoAtual = (Jogo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("jogo");
		pinos = pinoDao.obterTodos();
		inst = new Comercializacao();
		coms = comDao.obterComercializacoesPorGrupo(jogador);
		grupos = grupoDao.getGruposPorJogoETipo(jogoAtual, new TipoGrupo("Fabricante"));
		
	}
	
	public void novaInstancia(){
		
		inst = new Comercializacao();
		
	}
	
	public void addComercializacao(Comercializacao com){
		
		coms.add(com);
		
	}

	public PinoDaoJpa getPinoDao() {
		return pinoDao;
	}

	public void setPinoDao(PinoDaoJpa pinoDao) {
		this.pinoDao = pinoDao;
	}

	public Jogo getJogoAtual() {
		return jogoAtual;
	}

	public void setJogoAtual(Jogo jogoAtual) {
		this.jogoAtual = jogoAtual;
	}

	public List<Pino> getPinos() {
		return pinos;
	}

	public void setPinos(List<Pino> pinos) {
		this.pinos = pinos;
	}

	public List<Comercializacao> getComs() {
		return coms;
	}

	public void setComs(List<Comercializacao> coms) {
		this.coms = coms;
	}

	public Comercializacao getInst() {
		return inst;
	}

	public void setInst(Comercializacao inst) {
		this.inst = inst;
	}

	public ComercializacaoDaoJpa getComDao() {
		return comDao;
	}

	public void setComDao(ComercializacaoDaoJpa comDao) {
		this.comDao = comDao;
	}

	public GrupoDaoJpa getGrupoDao() {
		return grupoDao;
	}

	public void setGrupoDao(GrupoDaoJpa grupoDao) {
		this.grupoDao = grupoDao;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
	
	public void buttonAction(ActionEvent actionEvent){
		
		inst.setCliente(cliente);
		inst.setFornecedor(jogador);
		inst.adicionarItem(new ItemComercializacao(pino,qtd));
		inst.setPrecoTotal(precoTotal);
		jogador.addComercializacao(inst);
		novaInstancia();
		
	}
	
	public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Célula Editada");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edição Cancelada");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Célula Editada", "Velho: " + oldValue + ", Novo:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
	
	
}
