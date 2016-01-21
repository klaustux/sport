package lt.eimis.persistence.dao;

import lt.eimis.entity.League;
import lt.eimis.persistence.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class LeagueDAO {
	public List<League> listLeagues()
	{
		EntityManager entityManager =  HibernateUtil.getEntityManager();
		entityManager.getTransaction().begin();
		List<League> leagues = (List<League>)entityManager.createQuery("from League", League.class ).getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		return leagues;
	}
}