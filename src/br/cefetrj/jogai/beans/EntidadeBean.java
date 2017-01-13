package br.cefetrj.jogai.beans;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;

import br.cefetrj.jogai.dominio.EntidadeSatelite;
import br.cefetrj.jogai.dominio.EntidadeSateliteDataModel;
import br.cefetrj.jogai.infra.FacesMessagesUtil;
import br.cefetrj.jogai.infra.i18n.MensagemRB;

/**
* Classe EntidadeBean
* A classe EntidadeBean é uma classe abstrata criada para lidar com objetos que são considerados entidades (a maior parte das classes do package Domínio)
* Ela foi reaproveitada de um projeto anterior do professor Bezerra, e por isso, caso seja encontrada alguma coisa necessária para código futuro aqui dentro, veja antes com ele
*
**/
public abstract class EntidadeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	protected EnumEstadoCadastroPoco operacaoSolicitada;

	protected Boolean trancarCampos = true;

	protected EntidadeSatelite instance = null;

	protected List<EntidadeSatelite> sequencerList;

	protected Integer indiceItemSelecionado;

	protected EntidadeSateliteDataModel entidadesModel;

	public EntidadeBean() {

	}

	public EntidadeSateliteDataModel getEntidadesModel() {
		return entidadesModel;
	}

	/**
	 * Esse método é uma callback para o evento gerado pelo usuário e
	 * correspondente à seleção de uma nova entidade na grade de entidades do
	 * formulário.
	 * 
	 * @param event
	 */
	public void selecionarEntidade(SelectEvent event) {
		EntidadeSatelite other = (EntidadeSatelite) event.getObject();
		List<EntidadeSatelite> lista = getSequencerList();
		int i = 0;
		for (EntidadeSatelite entidadeSatelite : lista) {
			if (entidadeSatelite.equals(other)) {
				indiceItemSelecionado = i;
				instance = entidadeSatelite;
				break;
			}
			i++;
		}
	}

	@PostConstruct
	protected void initParams() {
		wire();
		entidadesModel = new EntidadeSateliteDataModel(sequencerList);
	}

	protected void wire() {
		if (instance == null) {
			instance = getNovaInstancia();
			this.setSequencerList(getListaEntidades());
			if (sequencerList != null) {
				this.indiceItemSelecionado = 0;
				if (sequencerList.size() > 0) {
					this.instance = sequencerList
							.get(this.indiceItemSelecionado);
				}
			}
		}
	}

	protected abstract List<EntidadeSatelite> getListaEntidades();

	protected abstract EntidadeSatelite getNovaInstancia();

	public abstract void setSequencerList(List<EntidadeSatelite> lista);

	public abstract List<EntidadeSatelite> getSequencerList();

	public void instanciador(Boolean novo) {
		if (novo) {
			instance = getNovaInstancia();
		} else {
			if (sequencerList.size() > 0) {
				instance = sequencerList.get(0);
			} else {
				instance = getNovaInstancia();
			}
		}
	}

	public abstract String validarCamposParaInclusao();

	public abstract String validarCamposParaEdicao();

	protected abstract void incluir();

	protected abstract void alterar();

	public int excluir() {
		try {
			int indiceItemRemovido = realizarExclusao();
			this.instanciador(false);
			if (sequencerList.size() > 0) {
				indiceItemSelecionado = 0;
			} else {
				indiceItemSelecionado = null;
			}

			String msg = "Exclusão realizada com sucesso.";
			FacesMessagesUtil.informar(msg);
			return indiceItemRemovido;
		} catch (RuntimeException ex) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Erro interno: exclusão não realizada.", ex
									.getMessage()));
			return -1;
		}
	}

	protected void excluirEntidade() {
		// TODO Auto-generated method stub

	}

	protected int realizarExclusao() {
		int i = 0;
		int indiceItemRemovido = 0;
		Iterator<EntidadeSatelite> it = sequencerList.iterator();
		while (it.hasNext()) {
			EntidadeSatelite item = it.next();
			if (item.getId().equals(instance.getId())) {
				indiceItemRemovido = i;
				break;
			}
			i++;
		}

		sequencerList.remove(indiceItemRemovido);
		excluirEntidade();

		return indiceItemRemovido;
	}

	String erro = null;

	public void salvar() {
		erro = null;
		try {
			if (this.operacaoSolicitada == EnumEstadoCadastroPoco.ALTERACAO_SOLICITADA) {
				erro = validarCamposParaEdicao();
			} else if (this.operacaoSolicitada == EnumEstadoCadastroPoco.INCLUSAO_SOLICITADA) {
				erro = validarCamposParaInclusao();
			} else {
				/**
				 * Estado inválido para chamar esse método.
				 */
				return;
			}

			if (erro == null) {
				if (operacaoSolicitada == EnumEstadoCadastroPoco.INCLUSAO_SOLICITADA) {
					this.incluir();
					sequencerList.add(instance);
					// indiceItemSelecionado = sequencerList.size() - 1;
					indiceItemSelecionado = 0; // seleção volta para o primeiro
												// item da lista.
				} else {
					this.alterar();
				}
				reordenarListaEntidades();
				this.operacaoSolicitada = EnumEstadoCadastroPoco.VISUALIZACAO_COM_POCO;
				this.instanciador(false);
				trancarCampos = true;
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, MensagemRB
								.getTexto("operacaoSucesso"), ""));
			} else {
				if (operacaoSolicitada != EnumEstadoCadastroPoco.INCLUSAO_SOLICITADA
						&& getInstance() != null) {
					restaurarEstadoAnterior();
				}
				FacesContext.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_ERROR,
										erro, ""));
			}
		} catch (RuntimeException ex) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, ex
							.getMessage(), ""));
			System.out.println(ex.getMessage());
		} catch (Exception e) {
			if (erro == null) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								MensagemRB.getTexto("erro"), e.getMessage()));
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, erro, e
								.getMessage()));
			}
		}
	}

	/**
	 * O propósito desse método é permitir às subclasses de
	 * <code>EntidadeSateliteBean</code> modificar a ordem de apresentação da
	 * lista de entidades. Esse método não tem implementação nesta classe,
	 * propositalmente. Ele é eventualmente implementado por alguma subclasse
	 * que precise fazê-lo.
	 * 
	 * @see <code>AnalisesBean</code>
	 */
	protected void reordenarListaEntidades() {
		/**
		 * Sem implementação, propositalmente. Eventualmente implmentado por
		 * alguma subclasse.
		 */
	}

	protected void restaurarEstadoAnterior() {
		// TODO
	}

	public void cancelar() {
		trancarCampos = true;
		if (this.operacaoSolicitada == EnumEstadoCadastroPoco.ALTERACAO_SOLICITADA) {
			restaurarEstadoAnterior();
		}
		this.instanciador(false);
		this.operacaoSolicitada = EnumEstadoCadastroPoco.VISUALIZACAO_COM_POCO;
	}

	public String pegaComboString(List combobox, Integer index) {
		String ret = "";
		java.util.Iterator it = combobox.iterator();
		while (it.hasNext()) {
			SelectItem si = (SelectItem) it.next();
			if (((Integer) si.getValue()).intValue() == index) {
				ret = si.getLabel();
				break;
			}
		}
		return ret;
	}

	public Boolean getTrancarCampos() {
		return trancarCampos;
	}

	public void setTrancarCampos(Boolean trancarCampos) {
		this.trancarCampos = trancarCampos;
	}

	public Boolean getTrancarAlteracao() {
		if (operacaoSolicitada == EnumEstadoCadastroPoco.INCLUSAO_SOLICITADA) {
			return true;
		} else if (operacaoSolicitada == EnumEstadoCadastroPoco.ALTERACAO_SOLICITADA) {
			return true;
		} else if (sequencerList == null) {
			return true;
		} else if (sequencerList.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean getTrancarInclusao() {
		if (operacaoSolicitada == EnumEstadoCadastroPoco.INCLUSAO_SOLICITADA) {
			return true;
		} else if (operacaoSolicitada == EnumEstadoCadastroPoco.ALTERACAO_SOLICITADA) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean getTrancarExclusao() {
		if (sequencerList != null && sequencerList.size() > 0) {
			if (operacaoSolicitada == EnumEstadoCadastroPoco.INCLUSAO_SOLICITADA) {
				return true;
			} else if (operacaoSolicitada == EnumEstadoCadastroPoco.ALTERACAO_SOLICITADA) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	public void btIncluir() {
		this.operacaoSolicitada = EnumEstadoCadastroPoco.INCLUSAO_SOLICITADA;
		instance = getNovaInstancia();
		trancarCampos = false;
	}

	public void btAlterar() {
		this.operacaoSolicitada = EnumEstadoCadastroPoco.ALTERACAO_SOLICITADA;
		if (this.sequencerList.size() != 0) {
			trancarCampos = false;
		}
	}

	public abstract EntidadeSatelite getInstance();

	public void setInstance(EntidadeSatelite instance) {
		if (instance != null) {
			this.instance = instance;
		}
	}

	public String getTotalRegistro() {
		return "Total de registro(s): " + sequencerList.size();
	}

	/**
	 * Método criado para evitar o nome do id do componente botao ser duplicado
	 * nas paginas, para não ocorrer erro na view.
	 * 
	 * @return
	 */
	public abstract String getPersonalizarId();

	public String getHabilitarEnter() {
		String msg = new String();
		if (operacaoSolicitada == EnumEstadoCadastroPoco.INCLUSAO_SOLICITADA
				|| operacaoSolicitada == EnumEstadoCadastroPoco.ALTERACAO_SOLICITADA) {
			msg = getPersonalizarId();
		}
		return msg;
	}

	public Boolean getPermitirEnter() {
		if (operacaoSolicitada == EnumEstadoCadastroPoco.INCLUSAO_SOLICITADA
				|| operacaoSolicitada == EnumEstadoCadastroPoco.ALTERACAO_SOLICITADA) {
			return true;
		} else {
			return false;
		}
	}

	public String getMensagemErroValidacao() {
		return erro;
	}
}
