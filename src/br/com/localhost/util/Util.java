package br.com.localhost.util;


import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

public class Util {
	
	public void getMenssagemInfor (String menssagem) {
		FacesContext.getCurrentInstance().addMessage(
				null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", menssagem));
	}
	
	public void getMenssagemAlerta (String menssagem) {
		   FacesContext.getCurrentInstance().addMessage(
				   null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!", menssagem));
	}
	
	public void getMenssagemErro () {
		FacesContext.getCurrentInstance().addMessage(
				null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Entre em contato com o administrador"));
	}
	public void exibirDialog (String tipo) {
		PrimeFaces current = PrimeFaces.current();
		if (tipo.equals("alerta")) {
		current.executeScript("PF('DialogVarAlerta').show();");
		}
		if (tipo.equals("confirmacao")) {
			current.executeScript("PF('DialogVarConfirmacao').show();");
		}
		if (tipo.equals("erro")) {
			current.executeScript("PF('DialogVarErro').show();");
		}
	}
	
	public void exibirDialogPF (String idDialog) {
		PrimeFaces current = PrimeFaces.current();
		current.executeScript("PF('" + idDialog + "').show();");	
	}
	
	public void fecharDialogPF (String idDialog) {
		PrimeFaces current = PrimeFaces.current();
		current.executeScript("PF('" + idDialog + "').hide();");	
	}
	
	

}