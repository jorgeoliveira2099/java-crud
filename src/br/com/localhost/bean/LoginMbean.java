package br.com.localhost.bean;


import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;

import br.com.localhost.controller.UsuarioControle;
import br.com.localhost.model.Usuario;
import br.com.localhost.util.Util;

@ManagedBean
@SessionScoped
public class LoginMbean {
	private String email;
	private String senha;
	private Usuario usuarioAutenticado;
	private Util util;

	private UsuarioControle usuarioControle = UsuarioControle.getInstance();
	
	@PostConstruct
	public void inicializar () {
		email = "";
		senha = "";
		usuarioAutenticado = null;
	}

	public void logar() {
		util = new Util();
		try {
			if (!email.equals("admin")) {
				usuarioAutenticado = usuarioControle.autenticar(email, senha);
				if (usuarioAutenticado == null) {
					util.getMenssagemAlerta("Seu email ou senha estão incorretos!");
				} else {
					Faces.redirect("menu.xhtml");
				}
			} else if (senha.equals("admin")) {
				usuarioAutenticado = new Usuario();
				usuarioAutenticado.setNome("Administrador");
				Faces.redirect("menu.xhtml");
			} else {
				util.getMenssagemAlerta("Seu email ou senha estão incorretos!");
			}
		} catch (IOException erro) {
			erro.printStackTrace();
			util.getMenssagemErro();
		}

	}
	
	public String retornaOPrimeroNome () {
		if (usuarioAutenticado != null) {
		String[] nome = usuarioAutenticado.getNome().trim().split(" ");
		return nome[0];
		} else {
			return "";
		}
	}
	
	public void deslogar () {
		usuarioAutenticado = null;
		Faces.navigate("/index.xhtml");
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario getUsuarioAutenticado() {
		return usuarioAutenticado;
	}

	public void setUsuarioAutenticado(Usuario usuarioAutenticado) {
		this.usuarioAutenticado = usuarioAutenticado;
	}

}