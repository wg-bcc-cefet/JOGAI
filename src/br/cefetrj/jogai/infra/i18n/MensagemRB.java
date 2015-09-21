package br.cefetrj.jogai.infra.i18n;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class MensagemRB {

	static ResourceBundle rb =  ResourceBundle.getBundle("br.cefetrj.jogai.infra.i18n.Mensagem");	
	
	public MensagemRB(){
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
