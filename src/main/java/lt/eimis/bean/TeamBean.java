package lt.eimis.bean;


import lt.eimis.entity.Team;
import lt.eimis.persistence.dao.TeamDAO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean(name = "teamBean", eager = true)
@RequestScoped
public class TeamBean {
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

	public List<Team> getTeams(){
		return new TeamDAO().listTeams();
	}
}
