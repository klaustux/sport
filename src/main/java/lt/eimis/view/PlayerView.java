package lt.eimis.view;

import lt.eimis.entity.Player;
import lt.eimis.entity.Team;
import lt.eimis.persistence.dao.PlayerDAO;
import lt.eimis.persistence.dao.TeamDAO;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "playerView")
@RequestScoped
public class PlayerView implements Serializable {

    private List<Player> players;

    private String newPlayerFirstName;

    private String newPlayerLastName;

    private int newPlayerGames;

    private int newPlayerSport;

    private int newPlayerPoints;

    private int newPlayerPosition;

    private String team;

    @ManagedProperty("#{playerBean}")
    private PlayerDAO playerDAO;

    @ManagedProperty("#{teamBean}")
    private TeamDAO teamDAO;

    public int getNewPlayerPosition() {
        return newPlayerPosition;
    }

    public void setNewPlayerPosition(int newPlayerPosition) {
        this.newPlayerPosition = newPlayerPosition;
    }

    @PostConstruct
    public void init() {
        players = playerDAO.getList();
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

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
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

    public void onRowAdd() {
        if(team == null)
		{
			FacesMessage msg = new FacesMessage("Negalima sukurti žaidėjo be komandos", "Nėra komandų");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
        Integer id = Integer.valueOf(team);
        Team team = teamDAO.find(id);
        Player newPlayer = new Player(newPlayerFirstName, newPlayerLastName,
                newPlayerSport, team, newPlayerPosition, newPlayerPoints,
                newPlayerGames);
        playerDAO.add(newPlayer);
        players = playerDAO.getList();
    }
}
