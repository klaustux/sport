package lt.eimis.persistence.dao;

import lt.eimis.entity.Team;
import lt.eimis.persistence.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class TeamDAO {

//} extends GenericDAO<Team> {

	private static final long serialVersionUID = 1L;

//		public TeamDAO() {
//			super(Team.class);
//		}
//
//		public void delete(Team team) {
//			super.delete(team.getTeamId(), Team.class);
//		}


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

	public void deleteById(int teamId) {
		EntityManager entityManager =  HibernateUtil.getEntityManager();
		Team team = entityManager.find(Team.class, teamId);
		entityManager.getTransaction().begin();
		entityManager.remove(team);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public void addTeam(Team team) {
		save(team);
	}
}