package br.cefetrj.jogai.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.cefetrj.jogai.dominio.EntidadeSatelite;
import br.cefetrj.jogai.dominio.Grupo;
import br.cefetrj.jogai.dominio.InstituicaoEnsino;
import br.cefetrj.jogai.dominio.Jogo;
import br.cefetrj.jogai.dominio.TipoGrupo;
import br.cefetrj.jogai.infra.GrupoDaoJpa;
import br.cefetrj.jogai.infra.JogoDaoJpa;
import br.cefetrj.jogai.infra.TipoGrupoDaoJpa;

/**
 * Bean GrupoJogoBean - Bean de controle para CRUD de associação grupo-jogo
 *
 */
@ManagedBean(name = "grupoJogoBean")
@ViewScoped
public class GrupoJogoBean extends EntidadeBean {

	private static final long serialVersionUID = 4116256850266235254L;

	TipoGrupoDaoJpa tipoGrupoDao = new TipoGrupoDaoJpa();
	JogoDaoJpa jogoDao = new JogoDaoJpa();
	Jogo jogo = (Jogo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("jogo");
	
	private GrupoDaoJpa controller;

	public GrupoJogoBean() {
		controller = new GrupoDaoJpa();
	}
	
	/**
	 * Retorna a instância (grupo) atual
	 */
	@Override
	public Grupo getInstance() {
		return (Grupo) instance;
	}
	
	/**
	 * Designa a instância atual
	 * @param instance
	 */
	public void setInstance(Grupo instance) {
		if (instance != null) {
			this.instance = instance;
		}
	}

	/**
	 * Retorna uma lista de Entidades, no caso, os grupos que estão associados ao jogo
	 */
	@Override
	protected List<EntidadeSatelite> getListaEntidades() {
		List<Grupo> temp = controller.getGruposPorJogo(jogo);
		ArrayList<EntidadeSatelite> lista = new ArrayList<EntidadeSatelite>();
		lista.addAll(temp);
		return lista;
		
	}

	//Classes herdadas da classe abstrata
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

	/**
	 * Método de obtenção dos tipos de grupo presentes no banco de dados, para popular a lista no CRUD
	 * @return
	 */
	public List<SelectItem> getTiposGrupo() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		List<TipoGrupo> tipos = tipoGrupoDao.obterTodos();
		lista.add(new SelectItem("0", ""));
		for (TipoGrupo tipoGrupo : tipos) {
			lista.add(new SelectItem(tipoGrupo.getId(), tipoGrupo.getNome()));
		}
		return lista;
	}

	/**
	 * Método para resgatar o ID do tipo de grupo da instância
	 * @return
	 */
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

	/**
	 * Método para designar o ID do tipo de grupo da instância
	 * @param idTipoGrupo
	 */
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

	/**
	 * Método para obtenção de todos os jogos disponíveis no banco de dados para popular lista da página
	 * @return
	 */
	public List<SelectItem> getJogos() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		List<Jogo> jogos = jogoDao.obterTodos();
		lista.add(new SelectItem("0", ""));
		for (Jogo jogo : jogos) {
			lista.add(new SelectItem(jogo.getId(), jogo.getDescricao()));
		}
		return lista;
	}

	/**
	 * Método para resgatar o ID do jogo da instância atual
	 * @return
	 */
	public Long getIdJogo() {
		if (getInstance() != null) {
			if (getInstance().getJogo() != null) {
				return getInstance().getJogo().getId();
			} else {
				return 0L;
			}
		}
		return null;
	}

	/**
	 * Método para designar um ID de jogo para instância atual
	 * @param idJogo
	 */
	public void setIdJogo(Long idJogo) {
		if (idJogo != null) {
			if (idJogo != 0) {
				Jogo jogo = jogoDao.obterPorId(Jogo.class,
						idJogo);
				getInstance().setJogo(jogo);
			} else {
				getInstance().setJogo(null);
			}
		}
	}	
	
	/**
	 * Cria uma nova instância para o bean
	 */
	@Override
	protected Grupo getNovaInstancia() {
		Grupo instance = new Grupo();
		return instance;
	}

	@Override
	public String getPersonalizarId() {
		return "salvar";
	}
	
	//Métodos de manipulação do banco de dados (inclusão e alteração de entradas)
	@Override
	protected void incluir() {
		
		controller.incluir((Grupo) instance);
		jogo.adicionarGrupo((Grupo) instance);
		trancarCampos = false;
		//Força o refresh da página
		FacesContext context = FacesContext.getCurrentInstance();
		String viewId = context.getViewRoot().getViewId();
		ViewHandler handler = context.getApplication().getViewHandler();
		UIViewRoot root = handler.createView(context, viewId);
		root.setViewId(viewId);
		context.setViewRoot(root);
	}

	@Override
	protected void alterar() {
		controller.alterar((Grupo) instance);
		trancarCampos = false;
	}
}
