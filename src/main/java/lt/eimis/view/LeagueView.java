package lt.eimis.view;

import lt.eimis.entity.League;
import lt.eimis.entity.Team;
import lt.eimis.persistence.dao.LeagueDAO;
import lt.eimis.persistence.dao.TeamDAO;
import lt.eimis.util.SportUtils;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "leagueView")
@ViewScoped
public class LeagueView implements Serializable {

    ListDataModel<League> wrappedLeagues = new ListDataModel<>();
    private List<League> leagues;
    private String newLeagueName;
    private int newLeagueGames;
    private int newLeagueSport;
    private String[] teams;
    private League selectedLeague;
    @ManagedProperty("#{leagueBean}")
    private LeagueDAO leagueDAO;
    @ManagedProperty("#{teamBean}")
    private TeamDAO teamDAO;
    private List<Team> listTeamsBySport = new ArrayList<>();
	private int editId;
	private String sportName;

	public String getSportName() {
		return SportUtils.getSportName(newLeagueSport);
	}

	public void setSportName(String sportName) {
	}

	public int getEditId() {
		return editId;
	}

	public void setEditId(int editId) {
		League league = leagueDAO.find(editId);
		newLeagueName = league.getName();
		newLeagueSport = league.getSportId();
		this.editId = editId;
	}

	public ListDataModel<League> getWrappedLeagues() {
        return wrappedLeagues;
    }

    public void setWrappedLeagues(ListDataModel<League> wrappedLeagues) {
        this.wrappedLeagues = wrappedLeagues;
    }

    public League getSelectedLeague() {
        return selectedLeague;
    }

    public void setSelectedLeague(League selectedLeague) {
        this.selectedLeague = selectedLeague;
    }

    public List<Team> getListTeamsBySport() {
        return listTeamsBySport;
    }

    public void setListTeamsBySport(List<Team> listTeamsBySport) {
        this.listTeamsBySport = listTeamsBySport;
    }

    @PostConstruct
    public void init() {
        leagues = leagueDAO.getList();
		wrappedLeagues = new ListDataModel<League>(leagues);
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
		wrappedLeagues = new ListDataModel<League>(leagues);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled",
                ((League) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String onRowAdd() {
        League newLeague = new League(0, newLeagueName, newLeagueSport);
        if (teams != null) {
            for (String teamId : teams) {
                Integer id = Integer.valueOf(teamId);
                Team team = teamDAO.find(id);
                newLeague.getTeams().add(team);
            }
        }

        leagueDAO.save(newLeague);
        leagues = leagueDAO.getList();
		wrappedLeagues = new ListDataModel<League>(leagues);
        return "success";
    }

	public String onRowUpdate() {
		League newLeague = leagueDAO.find(editId);
		newLeague.setName(newLeagueName);
//		if (teams != null) {
//			for (String teamId : teams) {
//				Integer id = Integer.valueOf(teamId);
//				Team team = teamDAO.find(id);
//				newLeague.getTeams().add(team);
//			}
//		}

		leagueDAO.save(newLeague);
		leagues = leagueDAO.getList();
		wrappedLeagues = new ListDataModel<League>(leagues);
		return "leagues";
	}


	public void onSportChange() {
        listTeamsBySport = teamDAO.getListBySport(newLeagueSport);
    }

    public void onDelete() {
        if (selectedLeague == null) {
            FacesMessage msg = new FacesMessage(
                    "Pasirinkite lygą prieš trindami", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }
        try {
            leagueDAO.deleteById(selectedLeague.getLeagueId());
        }
        catch (Exception ex) {
            FacesMessage msg = new FacesMessage(
                    "Negalima trinti lygos su komandomis", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }
        leagues = leagueDAO.getList();
		wrappedLeagues = new ListDataModel<League>(leagues);
    }

    public String onEditNew() {

        if (selectedLeague == null) {
            FacesMessage msg = new FacesMessage("Pasirinkite lygą redagavimui", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
		editId = selectedLeague.getLeagueId();
		return "editLeague?faces-redirect=true&includeViewParams=true";
    }


	public void onRowSelect(SelectEvent event) {
	}

}
