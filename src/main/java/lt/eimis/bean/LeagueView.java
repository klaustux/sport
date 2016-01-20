package lt.eimis.bean;

import lt.eimis.entity.League;
import lt.eimis.entity.SportConstants;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class LeagueView implements Serializable{
	private List<League> leagueList;

	public LeagueView() {
		leagueList = new ArrayList<>();
		League league = new League();
		league.setLeagueId(100);
		league.setLeagueName("LKL");
		league.setLeagueSportId(SportConstants.SPORT_ID_BASKETBALL);
		leagueList.add(league);
		League league2 = new League();
		league2.setLeagueId(101);
		league2.setLeagueName("NKL");
		leagueList.add(league2);
	}

	public List<League> getLeagues() {

		/*EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();

		leagueList =  em.createQuery("from leagues", League.class ).getResultList();

		em.getTransaction().commit();

		em.close();
		PersistenceManager.INSTANCE.close();
*/
		return leagueList;
	}
}
