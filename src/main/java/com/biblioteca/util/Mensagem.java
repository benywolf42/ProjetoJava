package com.biblioteca.util;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class Mensagem implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void add(String msg, Severity severity){
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage mensagem = new FacesMessage(msg);
		mensagem.setSeverity(severity);
		context.addMessage(null, mensagem);
	}
	
	public void info(String msg){
		add(msg,FacesMessage.SEVERITY_INFO);
	}
	
	public void error(String msg){
		add(msg,FacesMessage.SEVERITY_ERROR);
	}
	
}
