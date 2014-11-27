package br.cefetrj.jogai.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.cefetrj.jogai.dominio.EntidadeSatelite;
import br.cefetrj.jogai.dominio.InstituicaoEnsino;
import br.cefetrj.jogai.dominio.Participante;
import br.cefetrj.jogai.infra.InstituicaoEnsinoDaoJpa;

@ManagedBean(name = "participanteBean")
@ViewScoped
public class ParticipanteBean extends EntidadeBean {

	private static final long serialVersionUID = 4116256850266235254L;

	InstituicaoEnsinoDaoJpa ieDao = new InstituicaoEnsinoDaoJpa();
	private InstituicaoEnsino ie;

	public ParticipanteBean() {
		ie = (InstituicaoEnsino) FacesContext
	            .getCurrentInstance().getExternalContext().getSessionMap()
	            .get("instituicaoensino");
	}

	@Override
	public Participante getInstance() {
		return (Participante) instance;
	}

	public void setInstance(Participante instance) {
		if (instance != null) {
			this.instance = instance;
		}
	}

	@Override
	protected List<EntidadeSatelite> getListaEntidades() {
		
		ArrayList<EntidadeSatelite> lista = new ArrayList<EntidadeSatelite>();
		lista.addAll(ie.getParticipantes());
		return lista;
		
	}

	@Override
	public void setSequencerList(List<EntidadeSatelite> lista) {
		this.sequencerList = lista;
	}

	@Override
	public List<EntidadeSatelite> getSequencerList() {
		return sequencerList;
	}

	@Override
	public String validarCamposParaInclusao() {
		return validarCamposParaManipulacao();
	}

	@Override
	public String validarCamposParaEdicao() {
		return validarCamposParaManipulacao();
	}

	private String validarCamposParaManipulacao() {

		// Date data = getInstance().getDatDataSituacao();
		// if (data == null) {
		// componentes.setFocusDataSituacao(true);
		// return "É necessário informar a data da situação.";
		// }
		//
		// TipoGrupo tipoSituacao = getInstance().getTipoGrupo();
		// if (tipoSituacao == null || tipoSituacao.getIdtTipoGrupo() == 0) {
		// componentes.setFocusTipoGrupo(true);
		// return "É necessário informar o tipo de situação.";
		// }
		//
		// if (this.poco != null) {
		// if (this.poco.getDataInstalacao() != null) {
		// if (DateUtils.compareTo(this.poco.getDataInstalacao(), data) > 0) {
		// SimpleDateFormat formatador = new SimpleDateFormat(
		// "dd/MM/yyyy");
		// String strData = formatador.format(data);
		// String strDataInstalacao = formatador.format(this.poco
		// .getDataInstalacao());
		// componentes.setFocusDataSituacao(true);
		// return "A data fornecida ("
		// + strData
		// + ") deve ser igual ou posterior à data de instalação do poço ("
		// + strDataInstalacao + ").";
		// }
		// }
		// }
		//
		// Date dataAnterior = null;
		// Date dataPosterior = null;
		//
		// if (operacaoSolicitada == EnumEstadoCadastroPoco.INCLUSAO_SOLICITADA)
		// {
		// if (sequencerList.size() > 0) {
		// dataAnterior = ((Participante) sequencerList.get(sequencerList
		// .size() - 1)).getDatDataSituacao();
		// } else {
		// dataAnterior = null;
		// }
		// dataPosterior = null;
		// } else {
		// int id = getIndiceItemSelecionado();
		//
		// try {
		// dataAnterior = ((Participante) sequencerList.get(id - 1))
		// .getDatDataSituacao();
		// } catch (Exception e) {
		// dataAnterior = null;
		// }
		// try {
		// dataPosterior = ((Participante) sequencerList.get(id + 1))
		// .getDatDataSituacao();
		// } catch (Exception e) {
		// dataPosterior = null;
		// }
		// }
		//
		// String erro = validarDataNaoPosteriorDatatual(data, "Data Situação");
		// if (erro != null) {
		// componentes.setFocusDataSituacao(true);
		// return erro;
		// }
		//
		// erro = this.validarDatas(data, dataAnterior, dataPosterior);
		// if(erro != null){
		// componentes.setFocusDataSituacao(true);
		// return erro;
		// }

		return erro;
	}

	@Override
	protected Participante getNovaInstancia() {
		Participante instance = new Participante();
		return instance;
	}

	@Override
	public String getPersonalizarId() {
		return "salvar";
	}

	@Override
	protected void incluir() {
		ie.adicionarParticipante((Participante) instance);
		ieDao.alterar(ie);
		trancarCampos = false;
	}

	@Override
	protected void alterar() {
		ieDao.alterar(ie);
		trancarCampos = false;
	}
}
