package br.cefetrj.jogai.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import br.cefetrj.jogai.dominio.EntidadeSatelite;
import br.cefetrj.jogai.dominio.Grupo;
import br.cefetrj.jogai.dominio.TipoGrupo;
import br.cefetrj.jogai.infra.GrupoDaoJpa;
import br.cefetrj.jogai.infra.TipoGrupoDaoJpa;

@ManagedBean(name = "grupoBean")
@ViewScoped
public class GrupoParticipantesBean extends EntidadeBean {

	private static final long serialVersionUID = 4116256850266235254L;

	TipoGrupoDaoJpa tipoGrupoDao = new TipoGrupoDaoJpa();

	private GrupoDaoJpa controller;

	public GrupoParticipantesBean() {
		controller = new GrupoDaoJpa();
	}

	@Override
	public Grupo getInstance() {
		return (Grupo) instance;
	}

	public void setInstance(Grupo instance) {
		if (instance != null) {
			this.instance = instance;
		}
	}

	@Override
	protected List<EntidadeSatelite> getListaEntidades() {
		List<Grupo> temp = controller.obterTodos();
		ArrayList<EntidadeSatelite> lista = new ArrayList<EntidadeSatelite>();
		lista.addAll(temp);
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
		// dataAnterior = ((Grupo) sequencerList.get(sequencerList
		// .size() - 1)).getDatDataSituacao();
		// } else {
		// dataAnterior = null;
		// }
		// dataPosterior = null;
		// } else {
		// int id = getIndiceItemSelecionado();
		//
		// try {
		// dataAnterior = ((Grupo) sequencerList.get(id - 1))
		// .getDatDataSituacao();
		// } catch (Exception e) {
		// dataAnterior = null;
		// }
		// try {
		// dataPosterior = ((Grupo) sequencerList.get(id + 1))
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

	public List<SelectItem> getTiposGrupo() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		List<TipoGrupo> tipos = tipoGrupoDao.obterTodos();
		lista.add(new SelectItem("0", ""));
		for (TipoGrupo tipoGrupo : tipos) {
			lista.add(new SelectItem(tipoGrupo.getId(), tipoGrupo.getNome()));
		}
		return lista;
	}

	public Long getIdTipoGrupo() {
		if (getInstance() != null) {
			if (getInstance().getTipoGrupo() != null) {
				return getInstance().getTipoGrupo().getId();
			} else {
				return 0L;
			}
		}
		return null;
	}

	public void setIdTipoGrupo(Long idTipoGrupo) {
		if (idTipoGrupo != null) {
			if (idTipoGrupo != 0) {
				TipoGrupo tipo = tipoGrupoDao.obterPorId(TipoGrupo.class,
						idTipoGrupo);
				getInstance().setTipoGrupo(tipo);
			} else {
				getInstance().setTipoGrupo(null);
			}
		}
	}

	@Override
	protected Grupo getNovaInstancia() {
		Grupo instance = new Grupo();
		return instance;
	}

	@Override
	public String getPersonalizarId() {
		return "salvar";
	}

	@Override
	protected void incluir() {
		controller.incluir((Grupo) instance);
		trancarCampos = false;
	}

	@Override
	protected void alterar() {
		controller.alterar((Grupo) instance);
		trancarCampos = false;
	}
}
