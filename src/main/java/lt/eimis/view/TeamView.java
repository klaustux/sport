package lt.eimis.view;

import lt.eimis.entity.League;
import lt.eimis.entity.Team;
import lt.eimis.persistence.dao.LeagueDAO;
import lt.eimis.persistence.dao.TeamDAO;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ManagedBean(name = "teamView")
@ViewScoped
public class TeamView implements Serializable {

    private List<Team> teams;

    private String newTeamName;

    private int newTeamGames;

    private int newTeamSport;

    private String[] leagues;

    @ManagedProperty("#{teamBean}")
    private TeamDAO teamBean;

    @ManagedProperty("#{leagueBean}")
    private LeagueDAO leagueBean;

	private List<League> listBySport = new ArrayList<>();

	public List<League> getListBySport() {
		return listBySport;
	}

	public void setListBySport(List<League> listBySport) {
		this.listBySport = listBySport;
	}

	@PostConstruct
    public void init() {
        teams = teamBean.getList();
		listBySport = leagueBean.getListBySport(newTeamSport);
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeamBean(TeamDAO teamBean) {
        this.teamBean = teamBean;
    }

    public String getNewTeamName() {
        return newTeamName;
    }

    public void setNewTeamName(String newTeamName) {
        this.newTeamName = newTeamName;
    }

    public int getNewTeamGames() {
        return newTeamGames;
    }

    public void setNewTeamGames(int newTeamGames) {
        this.newTeamGames = newTeamGames;
    }

    public int getNewTeamSport() {
        return newTeamSport;
    }

    public void setNewTeamSport(int newTeamSport) {
        this.newTeamSport = newTeamSport;
    }

    public LeagueDAO getLeagueBean() {
        return leagueBean;
    }

    public void setLeagueBean(LeagueDAO leagueBean) {
        this.leagueBean = leagueBean;
    }

    public String[] getLeagues() {
        return leagues;
    }

    public void setLeagues(String[] leagues) {
        this.leagues = leagues;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public TeamDAO getTeamBean() {
        return teamBean;
    }

    public void onRowEdit(RowEditEvent event) {
        Team team = (Team) event.getObject();
        teamBean.save(team);
    }

    public void deleteTeam(Team team) {
        teamBean.deleteById(team.getTeamId());
        teams = teamBean.getList();
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled",
                ((Team) event.getObject()).getTeamName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

	public void onSportChange() {
		listBySport = leagueBean.getListBySport(newTeamSport);
 	}

    public void onRowAdd() {
        Set<League> leagueSet = new HashSet<>(0);
        for (String leagueId : leagues) {
            Integer id = Integer.valueOf(leagueId);
            League league = leagueBean.find(id);
            leagueSet.add(league);
        }
        Team newTeam = new Team(0, newTeamName, newTeamSport, newTeamGames,
                leagueSet);
        teamBean.save(newTeam);
        teams = teamBean.getList();
    }
}
