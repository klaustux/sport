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

    private String team;

    private List<Team> teamList = new ArrayList<>();

    private List<SportPosition> positionList = new ArrayList<>();

    @ManagedProperty("#{playerBean}")
    private PlayerDAO playerDAO;

    @ManagedProperty("#{teamBean}")
    private TeamDAO teamDAO;

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
        if (team == null) {
            FacesMessage msg = new FacesMessage(
                    "Negalima sukurti žaidėjo be komandos", "Nėra komandų");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }
        Integer id = Integer.valueOf(team);
        Team team = teamDAO.find(id);
        Player newPlayer = new Player(newPlayerFirstName, newPlayerLastName,
                newPlayerSport, team, newPlayerPosition, newPlayerPoints,
                newPlayerGames);
        playerDAO.save(newPlayer);
        refresh();
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
}
