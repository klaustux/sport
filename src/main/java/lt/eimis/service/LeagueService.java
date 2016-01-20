package lt.eimis.service;


import lt.eimis.entity.League;
import lt.eimis.entity.SportConstants;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;


@ManagedBean(name = "leagueService")
@ApplicationScoped
public class LeagueService {

	public List<League> getLeagues() {

		List<League> leagues = new ArrayList<League>();
		leagues.add(new League(1, "LKL", SportConstants.SPORT_ID_BASKETBALL));
		leagues.add(new League(2, "NKL", SportConstants.SPORT_ID_BASKETBALL));
//		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
//		em.getTransaction().begin();
//		leagues = em.createQuery("from leagues", League.class).getResultList();
//		em.getTransaction().commit();
//		em.close();
//		PersistenceManager.INSTANCE.close();
		return leagues;
	}
}