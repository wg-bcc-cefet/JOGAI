package br.cefetrj.jogai.dominio;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

/**
 * Data Model da EntidadeSatelite
 * @author JOGAI
 *
 */
public class EntidadeSateliteDataModel extends ListDataModel<EntidadeSatelite>
		implements SelectableDataModel<EntidadeSatelite> {

	public EntidadeSateliteDataModel() {

	}

	public EntidadeSateliteDataModel(List<EntidadeSatelite> data) {
		super(data);
	}

	@Override
	public EntidadeSatelite getRowData(String rowKey) {

		List<EntidadeSatelite> entidades = (List<EntidadeSatelite>) getWrappedData();

		for (EntidadeSatelite entidade : entidades) {
			if (entidade.getId().equals(rowKey)) {
				return entidade;
			}
		}

		return null;
	}

	@Override
	public Object getRowKey(EntidadeSatelite project) {
		return project.getId();
	}
}