package br.cefetrj.jogai.dominio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.cefetrj.jogai.infra.TipoGrupoDaoJpa;

public class Main {

	public static void main(String[] args) throws java.text.ParseException {
		Main instance = new Main();

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("JOGAI_PU");

		EntityManager em = emf.createEntityManager();
		TipoGrupoDaoJpa dao = new TipoGrupoDaoJpa();
		Grupo a = new Grupo(dao.getTipoGrupoPorNome("Lavras"));
		Grupo b = new Grupo(dao.getTipoGrupoPorNome("Ourives"));
		Grupo c = new Grupo(dao.getTipoGrupoPorNome("Joalheria"));

		EntityTransaction tx = em.getTransaction();

		tx.begin();
		em.persist(a);
		em.persist(b);
		em.persist(c);
		tx.commit();
		em.close();

		System.exit(0);
	}

}