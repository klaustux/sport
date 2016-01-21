package lt.eimis.persistence.dao;

import lt.eimis.entity.Team;
import lt.eimis.persistence.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class TeamDAO {
	public List<Team> listTeams()
	{
		EntityManager entityManager =  HibernateUtil.getEntityManager();
		entityManager.getTransaction().begin();
		List<Team> teams = entityManager.createQuery("from Team", Team.class ).getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		return teams;
	}

	public void save(Team team) {
		EntityManager entityManager =  HibernateUtil.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(team);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}