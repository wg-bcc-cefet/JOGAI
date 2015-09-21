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
import br.cefetrj.jogai.infra.ParticipanteDaoJpa;

/**
 * Bean ParticipantesInstituicaoBean - Bean que regula o CRUD de registro de participantes vinculados a uma instituição de ensino
 * @author JOGAI
 *
 */
@ManagedBean(name = "participanteBean")
@ViewScoped
public class ParticipantesInstituicaoBean extends EntidadeBean {

	/**
	 * Recupera a instituição de ensino do usuário logado
	 */
	InstituicaoEnsino ie = (InstituicaoEnsino) FacesContext
			.getCurrentInstance().getExternalContext().getSessionMap()
			.get("instituicaoensino");

	private static final long serialVersionUID = 4116256850266235254L;

	private ParticipanteDaoJpa controller;
	private InstituicaoEnsinoDaoJpa instituicaoController;

	public ParticipantesInstituicaoBean() {
		controller = new ParticipanteDaoJpa();
		instituicaoController = new InstituicaoEnsinoDaoJpa();
	}

	/**
	 * Recupera a instância (participante) atual
	 */
	@Override
	public Participante getInstance() {
		return (Participante) instance;
	}

	/**
	 * Define a instância atual
	 * @param instance
	 */
	public void setInstance(Participante instance) {
		if (instance != null) {
			this.instance = instance;
		}
	}

	/**
	 * Recupera uma lista de todos os participantes de uma dada instituição
	 */
	@Override
	protected List<EntidadeSatelite> getListaEntidades() {
		List<Participante> temp = controller
				.obterTodosDeInstituicao((InstituicaoEnsino) FacesContext
						.getCurrentInstance().getExternalContext()
						.getSessionMap().get("instituicaoensino"));
		ArrayList<EntidadeSatelite> lista = new ArrayList<EntidadeSatelite>();
		lista.addAll(temp);
		return lista;

	}
	
	//Métodos herdados da classe abstrata
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
		// NomeParticipante tipoSituacao = getInstance().getNomeParticipante();
		// if (tipoSituacao == null || tipoSituacao.getIdtNomeParticipante() ==
		// 0) {
		// componentes.setFocusNomeParticipante(true);
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

	/*
	 * public List<SelectItem> getNomeParticipante() { List<SelectItem> lista =
	 * new ArrayList<SelectItem>(); List<Participante> participantes =
	 * controller.obterTodos(); lista.add(new SelectItem("0", "")); for
	 * (Participante participante : participantes) { lista.add(new
	 * SelectItem(participante.getId(), participante.getNome())); } return
	 * lista; }
	 * 
	 * public List<SelectItem> getGeneroParticipante() { List<SelectItem> lista
	 * = new ArrayList<SelectItem>(); List<Participante> participantes =
	 * controller.obterTodos(); lista.add(new SelectItem("0", "")); for
	 * (Participante participante : participantes) { lista.add(new
	 * SelectItem(participante.getId(), participante.getGenero())); } return
	 * lista; }
	 * 
	 * public List<SelectItem> getEmailParticipante() { List<SelectItem> lista =
	 * new ArrayList<SelectItem>(); List<Participante> participantes =
	 * controller.obterTodos(); lista.add(new SelectItem("0", "")); for
	 * (Participante participante : participantes) { lista.add(new
	 * SelectItem(participante.getId(), participante.getEmail())); } return
	 * lista; }
	 */

	/**
	 * Recupera o ID da instância
	 * @return
	 */
	public Long getIdParticipante() {
		if (getInstance() != null) {
			return getInstance().getId();
		} else {
			return 0L;
		}
	}

	/**
	 * Recupera o nome da instância
	 * @return
	 */
	public String getNomeParticipante() {
		if (getInstance() != null) {
			if (getInstance().getNome() != null) {
				return getInstance().getNome();
			} else {
				return "";
			}
		}
		return null;
	}

	/**
	 * Recupera o gênero da instância
	 * @return
	 */
	public String getGeneroParticipante() {
		if (getInstance() != null) {
			if (getInstance().getGenero() != null) {
				return getInstance().getGenero();
			} else {
				return "";
			}
		}
		return null;
	}

	/**
	 * Recupera o e-mail da instância
	 * @return
	 */
	public String getEmailParticipante() {
		if (getInstance() != null) {
			if (getInstance().getEmail() != null) {
				return getInstance().getEmail();
			} else {
				return "";
			}
		}
		return null;
	}

	/**
	 * Define o nome da instância
	 * @param nomeParticipante
	 */
	public void setNomeParticipante(String nomeParticipante) {
		if (nomeParticipante != null) {
			if (nomeParticipante != "0") {
				getInstance().setNome(nomeParticipante);
			} else {
				getInstance().setNome(null);
			}
		}
	}

	/**
	 * Define o gênero da instância
	 * @param generoParticipante
	 */
	public void setGeneroParticipante(String generoParticipante) {
		if (generoParticipante != null) {
			if (generoParticipante != "0") {
				getInstance().setGenero(generoParticipante);
			} else {
				getInstance().setGenero(null);
			}
		}
	}

	/**
	 * Define o e-mail da instância
	 * @param emailParticipante
	 */
	public void setEmailParticipante(String emailParticipante) {
		if (emailParticipante != null) {
			if (emailParticipante != "0") {
				getInstance().setEmail(emailParticipante);
			} else {
				getInstance().setEmail(null);
			}
		}
	}

	/**
	 * Cria e retorna uma nova instância para o CRUD
	 */
	@Override
	protected Participante getNovaInstancia() {
		Participante instance = new Participante();
		return instance;
	}

	@Override
	public String getPersonalizarId() {
		return "salvar";
	}

	//Classes que lidam com o banco de dados - inserindo e alterando entradas
	@Override
	protected void incluir() {
		System.out.println(instance.getId());
		ie.adicionarParticipante((Participante) instance);

		ParticipanteDaoJpa dao = new ParticipanteDaoJpa();
		dao.incluir((Participante) instance);
		
		instituicaoController.alterar(ie);
		System.out.println(instance.getId());
		trancarCampos = false;
	}

	@Override
	protected void alterar() {
		controller.alterar((Participante) instance);
		trancarCampos = false;
	}

	@Override
	protected void excluirEntidade() {
		ie.removerParticipante(this.instance);
		this.controller.excluir((Participante) this.instance);
	}

}
