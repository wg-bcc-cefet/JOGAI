package br.cefetrj.jogai.infra.i18n;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class TextoRB {

	static ResourceBundle rb =  ResourceBundle.getBundle("cprm.siagas.i18n.Texto");	
	
	public TextoRB(){
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
