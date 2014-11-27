package br.cefetrj.jogai.infra.i18n;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class ConstantesRB {

	static ResourceBundle rb =  ResourceBundle.getBundle("cprm.siagas.i18n.Constantes");	
	
	public ConstantesRB(){
		super();
	}
	
	public static String getTexto(String chave){
		return rb.getString(chave);
	}
	
	public static String getTexto(String chave, String[] params) {
		MessageFormat formatter = new MessageFormat(rb.getString(chave));
		return formatter.format(params);
	}
}
