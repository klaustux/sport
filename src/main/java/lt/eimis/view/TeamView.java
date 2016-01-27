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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ManagedBean(name = "teamView")
@ViewScoped
public class TeamView implements Serializable {

	private List<Team> teams;

	private List<Team> persistentTeams;
	private String newTeamName;
	private int newTeamGames;
	private int newTeamSport;
	private Set<String> leagues = new HashSet<>();
	private Team selectedTeam;
	@ManagedProperty("#{teamBean}")
	private TeamDAO teamDAO;
	@ManagedProperty("#{leagueBean}")
	private LeagueDAO leagueDAO;
	private List<League> listLeaguesBySport = new ArrayList<>();
	private int editId;
	private String sportName;

	public List<Team> getPersistentTeams() {
		return persistentTeams;
	}

	public void setPersistentTeams(List<Team> persistentTeams) {
		this.persistentTeams = persistentTeams;
	}

	public Set<String> getLeagues() {
		return leagues;
	}

	public void setLeagues(Set<String> leagues) {
		this.leagues = leagues;
	}

	public int getEditId() {
		return editId;
	}

	public void setEditId(int editId) {
		Team team = teamDAO.find(editId);
		newTeamGames = team.getGamesPlayed();
		newTeamName = team.getTeamName();
		newTeamSport = team.getSportId();

		Set<String> leagueIds = new HashSet<>();
		for (League league : team.getLeagues()) {
			leagueIds.add(String.valueOf(league.getLeagueId()));
		}

		setLeagues(leagueIds);
		listLeaguesBySport = leagueDAO.getListBySport(newTeamSport);
		this.editId = editId;
	}

	public String getSportName() {
		return SportUtils.getSportName(newTeamSport);
	}

	public void setSportName(String sportName) {
	}

	public Team getSelectedTeam() {
		return selectedTeam;
	}

	public void setSelectedTeam(Team selectedTeam) {
		this.selectedTeam = selectedTeam;
	}

	public List<League> getlistLeaguesBySport() {
		return listLeaguesBySport;
	}

	public void setListLeaguesBySport(List<League> listLeaguesBySport) {
		this.listLeaguesBySport = listLeaguesBySport;
	}

	@PostConstruct
	public void init() {
		teams = teamDAO.getList();
		persistentTeams = teamDAO.getList();
		listLeaguesBySport = leagueDAO.getListBySport(newTeamSport);
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
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
		Team team = (Team) event.getObject();
		Set<League> leagueSet = new HashSet<>(0);
		for (String leagueId : leagues) {
			Integer id = Integer.valueOf(leagueId);
			League league = leagueDAO.find(id);
			leagueSet.add(league);
		}
		team.setLeagues(leagueSet);
		teamDAO.save(team);
	}

	public void deleteTeam(Team team) {
		teamDAO.deleteById(team.getTeamId());
		teams = teamDAO.getList();
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Neberedaguosim",
				((Team) event.getObject()).getTeamName());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onSportChange() {
		listLeaguesBySport = leagueDAO.getListBySport(newTeamSport);
	}

	public String onRowAdd() {
		Set<League> leagueSet = new HashSet<>(0);
		for (String leagueIdString : leagues) {
			Integer id = Integer.valueOf(leagueIdString);
			League leagueFound = leagueDAO.find(id);
			leagueSet.add(leagueFound);
		}
		Team newTeam = new Team(0, newTeamName, newTeamSport, newTeamGames,
				leagueSet);
		teamDAO.save(newTeam);
		teams = teamDAO.getList();
		return "success";
	}

	public String onUpdate() {
		Team team = teamDAO.find(Integer.valueOf(editId));

		Set<League> leagueSet = new HashSet<>(0);
		for (String leagueId : leagues) {
			Integer id = Integer.valueOf(leagueId);
			League league = leagueDAO.find(id);
			leagueSet.add(league);
		}

		team.setTeamName(newTeamName);
		team.setGamesPlayed(newTeamGames);
		team.setLeagues(leagueSet);
		teamDAO.save(team);
		teams = teamDAO.getList();
		return "teams";
	}

	public void onDelete() {
		if (selectedTeam == null) {
			FacesMessage msg = new FacesMessage(
					"Pasirinkite komandą prieš trindami", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		try {
			teamDAO.deleteById(selectedTeam.getTeamId());
		} catch (Exception ex) {
			FacesMessage msg = new FacesMessage(
					"Negalima trinti komandos su žaidėjais", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		teams = teamDAO.getList();
	}

	public String onEditNew() {

		if (selectedTeam == null) {
			FacesMessage msg = new FacesMessage("Pasirinkite lygą redagavimui",
					null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;
		}
		editId = selectedTeam.getTeamId();
		return "editTeam?faces-redirect=true&includeViewParams=true";
	}

	public void onRowSelect(SelectEvent event) {
		newTeamSport = selectedTeam.getSportId();
		listLeaguesBySport = leagueDAO.getListBySport(newTeamSport);
	}

	public void onRowEditInit(RowEditEvent event){
		Team team = (Team) event.getObject();
		listLeaguesBySport = leagueDAO.getListBySport(team.getSportId());
	}

}
