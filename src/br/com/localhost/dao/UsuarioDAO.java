package br.com.localhost.dao;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.localhost.model.Usuario;

public class UsuarioDAO extends GenericDAO {
	
	
	public void salvar (Usuario usuario) {
		try {
			iniciarTransacao();
			getManager().persist(usuario);
			encerrarTransacao();
		} catch (RuntimeException erro) {
			reverterTransacao();
			throw erro;
		} finally {
			fecharConexao();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> listar () throws RuntimeException {
		List<Usuario> colecaoUsuario = new ArrayList<Usuario>(); 
		try {
			Criteria criteria = criarCriteria(Usuario.class);
			colecaoUsuario =  criteria.list();
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			fecharConexao();
		} 
		return colecaoUsuario;
	}
	
	public Usuario buscar (Integer codObjeto) {
		Usuario usuario = new Usuario();
		try {
			Criteria criteria = criarCriteria(Usuario.class);
			usuario = (Usuario) criteria.add(Restrictions.eq("codObjeto", codObjeto)).uniqueResult();
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			fecharConexao();
		}
		return usuario;
	}
	
	public void excluir (Usuario usuario) {
		try {
			iniciarTransacao();
			Query query = getManager().createNativeQuery("DELETE FROM Usuario WHERE cod_objeto = " + usuario.getCodObjeto());
			query.executeUpdate();
			encerrarTransacao();
		} catch (RuntimeException erro) {
			reverterTransacao();
			throw erro;
		} finally {
			fecharConexao();
		}
	}
	
	public void alterar (Usuario usuario) {
		try {
			iniciarTransacao();
			getManager().merge(usuario);
			encerrarTransacao();
		} catch (RuntimeException erro) {
			reverterTransacao();
			throw erro;
		} finally {
			fecharConexao();
		}
	}
	
	public Boolean verificaSeExisteEmailCadastrado(String email) {
		Usuario usuario = new Usuario();
		try {
			Criteria criteria = criarCriteria(Usuario.class);
			usuario = (Usuario) criteria.add(Restrictions.eq("email", email)).uniqueResult();
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			fecharConexao();
		}
		if (usuario != null)
			return true;
		else
			return false;

	}
	
	public Usuario autenticarUsuario (String email, String senha) {
		Usuario usuario = new Usuario();
		try {
			Criteria criteria = criarCriteria(Usuario.class);
			usuario = (Usuario) criteria.add(Restrictions.eq("email", email)).add(Restrictions.eq("senha", senha)).uniqueResult();
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			fecharConexao();
		}
		return usuario;

	}

}