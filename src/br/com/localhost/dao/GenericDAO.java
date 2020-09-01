package br.com.localhost.dao;


import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.com.localhost.model.EntidadeBase;
import br.com.localhost.util.JPAUtil;

public class GenericDAO {

	public EntityManager getManager() {
		return JPAUtil.getEntityManager();
	}

	public void iniciarTransacao() {
		this.getManager().getTransaction().begin();
	}

	public void encerrarTransacao() {
		this.getManager().getTransaction().commit();
		fecharConexao();
	}

	public void fecharConexao() {
		this.getManager().close();
	}

	public void reverterTransacao() {
		if (getManager().isOpen() && getManager().getTransaction().isActive())
			this.getManager().getTransaction().rollback();
		fecharConexao();
	}

	public Criteria criarCriteria(Class<?> classeEntidade) {
		return getSession().createCriteria(classeEntidade);
	}


	protected Session getSession() {
		return (Session) this.getManager().unwrap(Session.class);
	}

	@SuppressWarnings("unchecked")
	public <T extends EntidadeBase> T obter(Class<T> c, Serializable id) {
		return (T) getSession().get(c, id);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends EntidadeBase> List<T> listarTodos(Class<T> classeEntidade){
		List<T> lista = criarCriteria(classeEntidade).list();
		fecharConexao();
		return lista;
		
	}
}
