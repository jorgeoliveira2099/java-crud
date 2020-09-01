package br.com.localhost.util;



import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

import br.com.localhost.bean.LoginMbean;
import br.com.localhost.model.Usuario;

public class Autenticacao implements PhaseListener {
	private static final long serialVersionUID = 2671096626940408913L;

	@Override
	public void afterPhase(PhaseEvent event) {

		String paginaAtual = Faces.getViewId();
		Boolean paginaPublica = paginaAtual.contains("telaInicial.xhtml");
		if (!paginaPublica) {
			
			LoginMbean loginMbean = Faces.getSessionAttribute("loginMbean");
			
			if (loginMbean == null) {
				Faces.navigate("/telaInicial.xhtml");
				return;
			} else {
				Usuario usuario = loginMbean.getUsuarioAutenticado();
				if (usuario == null) {
					Faces.navigate("/telaInicial.xhtml");
					return;
				}
				
			}
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}