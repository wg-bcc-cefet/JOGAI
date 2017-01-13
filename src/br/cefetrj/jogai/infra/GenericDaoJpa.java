package br.cefetrj.jogai.infra;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.cefetrj.jogai.dominio.Grupo;

public class GenericDaoJpa<T> {

	Logger logger = Logger.getLogger(GenericDaoJpa.class.getName());

	private static EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("JOGAI_PU");
	private static EntityManager entityManager = null;

	public GenericDaoJpa() {
		if (entityManager == null)
			entityManager = emf.createEntityManager();
	}

	public boolean incluir(T entidade) {
		EntityTransaction tx = null;
		try {
			tx = entityManager.getTransaction();
			tx.begin();
			entityManager.persist(entidade);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null && tx.isActive())
				tx.rollback();
			ex.printStackTrace();
			throw new DAOException("Erro na inclusão de objeto.", ex);
		}
		return true;
	}

	public boolean excluir(Class<T> c, Long id) {
		EntityTransaction tx = null;
		try {
			tx = entityManager.getTransaction();
			tx.begin();
			T entidade = entityManager.find(c, id);
			entityManager.remove(entidade);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null && tx.isActive())
				tx.rollback();
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean alterar(T entidade) {
		EntityTransaction tx = null;
		try {
			tx = entityManager.getTransaction();
			tx.begin();
			entidade = entityManager.merge(entidade);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null && tx.isActive())
				tx.rollback();
			throw new DAOException("Erro durante a inclus�o.", ex);
		}
		return true;
	}

	public T obterPorId(Class<T> c, Object id) throws DAOException {
		T entidade = null;

		try {
			logger.info("Resgatando entidade cujo id é " + id + " da classe: "
					+ c.getName());
			entidade = entityManager.find(c, id);
		} catch (RuntimeException ex) {
			throw new DAOException("Erro durante a pesquisa de objeto.", ex);
		}
		return entidade;
	}

	public List<T> obterTodos(Class<T> c) {
		logger.info("Resgatando todos as entidades da classe: " + c.getName());

		String entityName;

		entityName = c.getName().substring(c.getName().lastIndexOf('.') + 1);

		return obterEntidades("SELECT e FROM " + entityName + " e");
	}

	public List<T> obterEntidades(String queryString,
			final Object... positionalParams) {
		Query query = entityManager.createQuery(queryString);
		int i = 0;
		for (Object p : positionalParams) {
			query.setParameter(++i, p);
		}
		@SuppressWarnings("unchecked")
		List<T> l = query.getResultList();

		return l;
	}

	public T obterEntidade(String queryString, final Object... positionalParams) {
		Query query = entityManager.createQuery(queryString);
		int i = 0;
		for (Object p : positionalParams) {
			query.setParameter(++i, p);
		}
		@SuppressWarnings("unchecked")
		T entidade = (T) query.getSingleResult();

		return entidade;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	
}
