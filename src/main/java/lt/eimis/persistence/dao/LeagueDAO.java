package lt.eimis.persistence.dao;


import em.Event;
import lt.eimis.entity.League;
import lt.eimis.persistence.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LeagueDAO {
//	public List<League> listLeagues()
//	{
//		EntityManager entityManager =  HibernateUtil.getEntityManager();
//		entityManager.getTransaction().begin();
//		List<League> leagues = entityManager.createQuery( "from leagues", League.class ).getResultList();
//		entityManager.getTransaction().commit();
//		entityManager.close();
//		return leagues;
//	}

	public List<League> listLeagues()
	{
		EntityManager entityManager = HibernateUtil.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist( new Event( "Our very first event!", new Date() ) );
		entityManager.persist( new Event( "A follow up event", new Date() ) );
		entityManager.getTransaction().commit();
		entityManager.close();

		// now lets pull events from the database and list them
		entityManager = HibernateUtil.getEntityManager();
		entityManager.getTransaction().begin();
		List<Event> result = entityManager.createQuery( "from Event", Event.class ).getResultList();
		for ( Event event : result ) {
			System.out.println( "Event (" + event.getDate() + ") : " + event.getTitle() );
		}
		entityManager.getTransaction().commit();
		entityManager.close();
		return new ArrayList<>();
	}
}