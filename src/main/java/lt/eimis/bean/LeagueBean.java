package lt.eimis.bean;


import lt.eimis.entity.League;
import lt.eimis.persistence.dao.LeagueDAO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean(name = "leagueBean", eager = true)
@RequestScoped
public class LeagueBean {
	private String name;
	private int sport;

	public String saveLeague(){
//		LeagueDAO userDao = new LeagueDAO();
//		Integer id = userDao.getId();
//		League league = new League(id, name, sport);
//		userDao.save(league);
		System.out.println("League successfully saved.");
		return "output";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getSport() {
		return sport;
	}

	public void setSport(int sport) {
		this.sport = sport;
	}

	public List<League> getLeagues(){
		LeagueDAO dao = new LeagueDAO();

		return dao.listLeagues();
		//		List<League> list = new ArrayList<>();

//		League league1 = new League(1, "a", SportConstants.SPORT_ID_BASKETBALL);
//		League league2 = new League(2, "b", SportConstants.SPORT_ID_BASKETBALL);
//		list.add(league1);
//		list.add(league2);

//		return list;
	}

	public String getString(){
		return "StriNg";
	}
}
