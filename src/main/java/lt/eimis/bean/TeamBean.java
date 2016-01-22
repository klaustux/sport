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

	public void saveTeam(Team team){
		new TeamDAO().save(team);
	}

	public void deleteTeam(Team team){
		new TeamDAO().deleteById(team.getTeamId());
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

	public void addTeam(Team team) {
		new TeamDAO().addTeam(team);
	}
}
