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

@ManagedBean(name = "leagueView")
@ViewScoped
public class LeagueView implements Serializable {

    private List<League> leagues;

    private String newLeagueName;

    private int newLeagueGames;

    private int newLeagueSport;

    private String[] teams;

	public League getSelectedLeague() {
		return selectedLeague;
	}

	public void setSelectedLeague(League selectedLeague) {
		this.selectedLeague = selectedLeague;
	}

	private League selectedLeague;

    @ManagedProperty("#{leagueBean}")
    private LeagueDAO leagueDAO;

    @ManagedProperty("#{teamBean}")
    private TeamDAO teamDAO;

    private List<Team> listTeamsBySport = new ArrayList<>();

    public List<Team> getListTeamsBySport() {
        return listTeamsBySport;
    }

    public void setListTeamsBySport(List<Team> listTeamsBySport) {
        this.listTeamsBySport = listTeamsBySport;
    }

    @PostConstruct
    public void init() {
        leagues = leagueDAO.getList();
        listTeamsBySport = teamDAO.getListBySport(newLeagueSport);
    }

    public List<League> getLeagues() {
        return leagues;
    }

    public void setLeagues(List<League> leagues) {
        this.leagues = leagues;
    }

    public String getNewLeagueName() {
        return newLeagueName;
    }

    public void setNewLeagueName(String newLeagueName) {
        this.newLeagueName = newLeagueName;
    }

    public int getNewLeagueGames() {
        return newLeagueGames;
    }

    public void setNewLeagueGames(int newLeagueGames) {
        this.newLeagueGames = newLeagueGames;
    }

    public int getNewLeagueSport() {
        return newLeagueSport;
    }

    public void setNewLeagueSport(int newLeagueSport) {
        this.newLeagueSport = newLeagueSport;
    }

    public String[] getTeams() {
        return teams;
    }

    public void setTeams(String[] teams) {
        this.teams = teams;
    }

    public LeagueDAO getLeagueDAO() {
        return leagueDAO;
    }

    public void setLeagueDAO(LeagueDAO leagueDAO) {
        this.leagueDAO = leagueDAO;
    }

    public TeamDAO getTeamDAO() {
        return teamDAO;
    }

    public void setTeamDAO(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }

    public void onRowEdit(RowEditEvent event) {
        League league = (League) event.getObject();
        leagueDAO.save(league);
    }

    public void deleteLeague(League league) {
        leagueDAO.deleteById(league.getLeagueId());
        leagues = leagueDAO.getList();
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled",
                ((League) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String onRowAdd() {
        Set<Team> teamSet = new HashSet<>(0);
        for (String teamId : teams) {
            Integer id = Integer.valueOf(teamId);
            Team team = teamDAO.find(id);
            teamSet.add(team);
        }
        League newLeague = new League(0, newLeagueName, newLeagueSport,
                teamSet);
        leagueDAO.add(newLeague);
        leagues = leagueDAO.getList();
		return "success";
    }

    public void onSportChange() {
        listTeamsBySport = teamDAO.getListBySport(newLeagueSport);
    }

	public void onDelete(){
		if(selectedLeague == null)
		{
			FacesMessage msg = new FacesMessage("Pasirinkite lygą prieš trindami", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		try {
			leagueDAO.deleteById(selectedLeague.getLeagueId());
		}
		catch(Exception ex){
			FacesMessage msg = new FacesMessage("Negalima trinti lygos su komandomis", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		leagues = leagueDAO.getList();
	}

}
