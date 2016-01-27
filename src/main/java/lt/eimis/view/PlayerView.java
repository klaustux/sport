package lt.eimis.view;

import lt.eimis.entity.Player;
import lt.eimis.entity.Team;
import lt.eimis.persistence.dao.PlayerDAO;
import lt.eimis.persistence.dao.TeamDAO;
import lt.eimis.util.SportPosition;
import lt.eimis.util.SportUtils;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "playerView")
@ViewScoped
public class PlayerView implements Serializable {

    private List<Player> players;

    private String newPlayerFirstName;

    private String newPlayerLastName;

    private int newPlayerGames;

    private int newPlayerSport;

    private int newPlayerPoints;

    private int newPlayerPosition;

    private String teamString;

    private List<Team> teamList = new ArrayList<>();

    private List<SportPosition> positionList = new ArrayList<>();

    @ManagedProperty("#{playerBean}")
    private PlayerDAO playerDAO;

    @ManagedProperty("#{teamBean}")
    private TeamDAO teamDAO;

    private Player selectedPlayer;

	private String sportName;

	public String getSportName() {
		return SportUtils.getSportName(newPlayerSport);
	}

	public void setSportName(String sportName) {
	}


	public int getEditId() {
		return editId;
	}

	public void setEditId(int editId) {
		Player player  = playerDAO.find(editId);
		this.setNewPlayerFirstName(player.getFirstName());
		this.setNewPlayerLastName(player.getLastName());
		setNewPlayerGames(player.getGamesPlayed());
		setNewPlayerPoints(player.getPoints());
		setNewPlayerPosition(player.getPositionId());
		setNewPlayerSport(player.getSportId());
		teamList = teamDAO.getListBySport(player.getSportId());
		positionList = SportUtils.getPositionsBySport(player.getSportId());
		setTeamString(String.valueOf(player.getTeam().getTeamId()));
		this.editId = editId;
	}

	public Player getSelectedPlayer() {
		return selectedPlayer;
	}

	public void setSelectedPlayer(Player selectedPlayer) {
		this.selectedPlayer = selectedPlayer;
	}

	private int editId;

    public List<SportPosition> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<SportPosition> positionList) {
        this.positionList = positionList;
    }

    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }

    public int getNewPlayerPosition() {
        return newPlayerPosition;
    }

    public void setNewPlayerPosition(int newPlayerPosition) {
        this.newPlayerPosition = newPlayerPosition;
    }

    @PostConstruct
    public void init() {
        refresh();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public String getNewPlayerFirstName() {
        return newPlayerFirstName;
    }

    public void setNewPlayerFirstName(String newPlayerFirstName) {
        this.newPlayerFirstName = newPlayerFirstName;
    }

    public String getNewPlayerLastName() {
        return newPlayerLastName;
    }

    public void setNewPlayerLastName(String newPlayerLastName) {
        this.newPlayerLastName = newPlayerLastName;
    }

    public int getNewPlayerPoints() {
        return newPlayerPoints;
    }

    public void setNewPlayerPoints(int newPlayerPoints) {
        this.newPlayerPoints = newPlayerPoints;
    }

    public int getNewPlayerGames() {
        return newPlayerGames;
    }

    public void setNewPlayerGames(int newPlayerGames) {
        this.newPlayerGames = newPlayerGames;
    }

    public int getNewPlayerSport() {
        return newPlayerSport;
    }

    public void setNewPlayerSport(int newPlayerSport) {
        this.newPlayerSport = newPlayerSport;
    }

    public String getTeamString() {
        return teamString;
    }

    public void setTeamString(String teamString) {
        this.teamString = teamString;
    }

    public PlayerDAO getPlayerDAO() {
        return playerDAO;
    }

    public void setPlayerDAO(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    public TeamDAO getTeamDAO() {
        return teamDAO;
    }

    public void setTeamDAO(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }

    public void onRowEdit(RowEditEvent event) {
        Player player = (Player) event.getObject();
        playerDAO.save(player);
    }

    public void deletePlayer(Player player) {
        playerDAO.deleteById(player.getId());
        players = playerDAO.getList();
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled",
                ((Player) event.getObject()).getLastName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String onRowAdd() {
        if (teamString == null) {
            FacesMessage msg = new FacesMessage(
                    "Negalima sukurti žaidėjo be komandos", "Nėra komandų");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
        Integer id = Integer.valueOf(teamString);
        Team team = teamDAO.find(id);
		if(team.getGamesPlayed() < newPlayerGames)
		{
			FacesMessage msg = new FacesMessage(
					"Neleisim žaidėjui sužaist daugiau rungtynių už komandą", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;
		}

		if(0 == newPlayerGames && newPlayerPoints > 0)
		{
			FacesMessage msg = new FacesMessage(
					"Pradžiai sužaisk bent vienas rungtynes", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;
		}

        Player newPlayer = new Player(newPlayerFirstName, newPlayerLastName,
                newPlayerSport, team, newPlayerPosition, newPlayerPoints,
                newPlayerGames);
        playerDAO.save(newPlayer);
        refresh();
		return "success";
    }

	public String onRowUpdate() {
		Team team = teamDAO.find(Integer.valueOf(teamString));
		if(team.getGamesPlayed() < newPlayerGames)
		{
			FacesMessage msg = new FacesMessage(
					"Neleisim žaidėjui sužaist daugiau rungtynių už komandą", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;
		}

		if(0 == newPlayerGames && newPlayerPoints > 0)
		{
			FacesMessage msg = new FacesMessage(
					"Pradžiai sužaisk bent vienas rungtynes", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;
		}

		Player player = playerDAO.find(editId);
		player.setFirstName(newPlayerFirstName);
		player.setLastName(newPlayerLastName);
		player.setGamesPlayed(newPlayerGames);
		player.setPoints(newPlayerPoints);
		player.setPositionId(newPlayerPosition);
		player.setTeam(team);

		playerDAO.save(player);
		players = playerDAO.getList();
		return "players";
	}


    public void onSportChange() {
        refresh();
    }

    private void refresh() {
        players = playerDAO.getList();
        teamList = teamDAO.getListBySport(newPlayerSport);
        positionList = SportUtils.getPositionsBySport(newPlayerSport);
        newPlayerPosition = SportUtils
                .getDefaultPositionForSport(newPlayerSport);

    }

    public void onDelete() {
        if (selectedPlayer == null) {
            FacesMessage msg = new FacesMessage(
                    "Pasirinkite žaidėją prieš trindami", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }
        playerDAO.deleteById(selectedPlayer.getId());
        players = playerDAO.getList();
    }

    public String onEditNew() {

        if (selectedPlayer == null) {
            FacesMessage msg = new FacesMessage("Pasirinkite žaidėją redagavimui",
                    null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
        editId = selectedPlayer.getId();
        return "editPlayer?faces-redirect=true&includeViewParams=true";
    }

}
