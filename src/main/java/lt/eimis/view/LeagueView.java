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
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ManagedBean(name = "leagueView")
@RequestScoped
public class LeagueView implements Serializable {

    private List<League> leagues;

    private String newLeagueName;

    private int newLeagueGames;

    private int newLeagueSport;

    private String[] teams;

    @ManagedProperty("#{leagueBean}")
    private LeagueDAO leagueDAO;

    @ManagedProperty("#{teamBean}")
    private TeamDAO teamDAO;

    @PostConstruct
    public void init() {
        leagues = leagueDAO.getList();
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
        leagueDAO.deleteById(league.getId());
        leagues = leagueDAO.getList();
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled",
                ((League) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowAdd() {
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
    }
}
