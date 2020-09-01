package br.com.localhost.controller;


import java.util.List;

import br.com.localhost.dao.TelefoneDAO;
import br.com.localhost.model.Telefone;
import br.com.localhost.model.Usuario;

public class TelefoneControle {
private TelefoneDAO telefoneDAO = new TelefoneDAO();
	
	public static TelefoneControle getInstance () {
		TelefoneControle telefoneControle = new TelefoneControle();
		return telefoneControle;
	}
	
	public void salvarTelefone (Telefone telefone) {
		telefoneDAO.salvar(telefone);
	}
	
	public List<Telefone> listarTelefones (){
		return telefoneDAO.listar();
	}
	
	public List<Telefone> listarTelefonesPorUsuario (Usuario usuario){
		return telefoneDAO.listarPorUsuario(usuario);
	}
	
	public void alterarTelefone (Telefone telefone) {
		telefoneDAO.alterar(telefone);
	}
	
	public void excluirTelefone (Telefone telefone) {
		telefoneDAO.excluir(telefone);
	}

}