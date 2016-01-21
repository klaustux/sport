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
//
//	public String saveLeague(){
//		LeagueDAO leagueDAO = new LeagueDAO();
//		League league = new League();
//		leagueDAO.save(league);
//		return "output";
//	}
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
		return new LeagueDAO().listLeagues();
	}
}
