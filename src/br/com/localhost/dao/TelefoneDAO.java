package br.com.localhost.dao;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.localhost.model.Telefone;
import br.com.localhost.model.Usuario;

public class TelefoneDAO extends GenericDAO {
	
	
	public void salvar (Telefone telefone) {
		try {
			iniciarTransacao();
			getManager().persist(telefone);
			encerrarTransacao();
		} catch (RuntimeException erro) {
			reverterTransacao();
			throw erro;
		} finally {
			fecharConexao();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Telefone> listar () throws RuntimeException {
		List<Telefone> colecaoTelefone = new ArrayList<Telefone>(); 
		try {
			Criteria criteria = criarCriteria(Telefone.class);
			colecaoTelefone =  criteria.list();
			encerrarTransacao();
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			fecharConexao();
		} 
		return colecaoTelefone;
	}
	
	public Telefone buscar (Integer codObjeto) {
		Telefone telefone = new Telefone();
		try {
			Criteria criteria = criarCriteria(Telefone.class);
			telefone = (Telefone) criteria.add(Restrictions.eq("codObjeto", codObjeto)).uniqueResult();
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			fecharConexao();
		}
		return telefone;
	}
	
	public void excluir (Telefone telefone) {
		try {
			iniciarTransacao();
			Query query = getManager().createNativeQuery("DELETE FROM Usuario WHERE cod_objeto = " + telefone.getCodObjeto());
			query.executeUpdate();
			encerrarTransacao();
		} catch (RuntimeException erro) {
			reverterTransacao();
			throw erro;
		} finally {
			fecharConexao();
		}
	}
	
	public void alterar (Telefone telefone) {
		try {
			iniciarTransacao();
			getManager().merge(telefone);
			encerrarTransacao();
		} catch (RuntimeException erro) {
			reverterTransacao();
			throw erro;
		} finally {
			fecharConexao();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Telefone> listarPorUsuario (Usuario usuario){
		List<Telefone> colecaoTelefone = new ArrayList<Telefone>(); 
		try {
			Criteria criteria = criarCriteria(Telefone.class);
			colecaoTelefone =  criteria.add(Restrictions.eq("usuario", usuario)).list();
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			fecharConexao();
		} 
		return colecaoTelefone;
	}
}