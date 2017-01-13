package br.cefetrj.jogai.infra;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesMessagesUtil {

	public static void informarErro(String msg) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, ""));
	}

	public static void informarErro(String msg, String msg2) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg2));
	}
	
	public static void informar(String msg) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, ""));
	}

}
