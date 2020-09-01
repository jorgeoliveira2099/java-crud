package br.com.localhost.controller;


import java.util.List;

import br.com.localhost.dao.UsuarioDAO;
import br.com.localhost.model.Usuario;

public class UsuarioControle {
	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	public static UsuarioControle getInstance() {
		UsuarioControle usuarioControle = new UsuarioControle();
		return usuarioControle;
	}

	public void salvar(Usuario usuario) {
		usuarioDAO.salvar(usuario);
	}

	public List<Usuario> listarUsuarios() {
		return usuarioDAO.listar();
	}

	public void alterarUsuario(Usuario usuario) {
		usuarioDAO.alterar(usuario);
	}

	public void excluirUsuario(Usuario usuario) {
		usuarioDAO.excluir(usuario);
	}

	public Boolean verificaSeExisteEmail(String email) {
		return usuarioDAO.verificaSeExisteEmailCadastrado(email);
	}

	public Usuario autenticar(String email, String senha) {
		return usuarioDAO.autenticarUsuario(email, senha);
	}
}