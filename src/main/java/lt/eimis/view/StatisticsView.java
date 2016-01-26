package lt.eimis.view;

import lt.eimis.entity.League;
import lt.eimis.entity.Player;
import lt.eimis.entity.SportConstants;
import lt.eimis.entity.StatisticsRow;
import lt.eimis.entity.Team;
import lt.eimis.persistence.dao.LeagueDAO;
import lt.eimis.persistence.dao.PlayerDAO;
import lt.eimis.persistence.dao.TeamDAO;
import lt.eimis.util.StatsGenerator;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ManagedBean(name = "statisticsView")
@ViewScoped
public class StatisticsView implements Serializable {

	@ManagedProperty("#{teamBean}")
	private TeamDAO teamDAO;

	@ManagedProperty("#{leagueBean}")
	private LeagueDAO leagueDAO;

	@ManagedProperty("#{playerBean}")
	private PlayerDAO playerDAO;

	private Set<StatisticsRow> stats = new HashSet<>(0);

	public TeamDAO getTeamDAO() {
		return teamDAO;
	}

	public void setTeamDAO(TeamDAO teamDAO) {
		this.teamDAO = teamDAO;
	}

	public LeagueDAO getLeagueDAO() {
		return leagueDAO;
	}

	public void setLeagueDAO(LeagueDAO leagueDAO) {
		this.leagueDAO = leagueDAO;
	}

	public PlayerDAO getPlayerDAO() {
		return playerDAO;
	}

	public void setPlayerDAO(PlayerDAO playerDAO) {
		this.playerDAO = playerDAO;
	}

	public Set<StatisticsRow> getStats() {
		return stats;
	}

	public void setStats(Set<StatisticsRow> stats) {
		this.stats = stats;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		StatisticsView that = (StatisticsView) o;

		if (teamDAO != null ? !teamDAO.equals(that.teamDAO) : that.teamDAO != null) {
			return false;
		}
		if (leagueDAO != null ? !leagueDAO.equals(that.leagueDAO) : that.leagueDAO != null) {
			return false;
		}
		if (playerDAO != null ? !playerDAO.equals(that.playerDAO) : that.playerDAO != null) {
			return false;
		}
		return stats != null ? stats.equals(that.stats) : that.stats == null;

	}

	@Override
	public int hashCode() {
		int result = teamDAO != null ? teamDAO.hashCode() : 0;
		result = 31 * result + (leagueDAO != null ? leagueDAO.hashCode() : 0);
		result = 31 * result + (playerDAO != null ? playerDAO.hashCode() : 0);
		result = 31 * result + (stats != null ? stats.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "StatisticsView{" +
				"teamDAO=" + teamDAO +
				", leagueDAO=" + leagueDAO +
				", playerDAO=" + playerDAO +
				", stats=" + stats +
				'}';
	}

	public void generateTeamStats(int sportId){
		List<Team> teams = teamDAO.getListBySport(sportId);
		stats = StatsGenerator.generateTeamsStats(teams, sportId);
	}


	public void generatePlayerStats(int sportId){
		List<Player> players = playerDAO.getListBySport(sportId);
		stats = StatsGenerator.generatePlayersStats(players, sportId);
	}

	public void onCreateBasketTeamStats(){
		generateTeamStats(SportConstants.SPORT_ID_BASKETBALL);
	}

	public void onCreateFootballTeamStats(){
		generateTeamStats(SportConstants.SPORT_ID_FOOTBALL);
	}

	public void onCreateBasketPlayersStats(){
		generatePlayerStats(SportConstants.SPORT_ID_BASKETBALL);
	}

	public void onCreateFootballPlayerStats(){
		generatePlayerStats(SportConstants.SPORT_ID_FOOTBALL);
	}

	public void onCreateFootballLeagueStats(){
		generateLeagueStats(SportConstants.SPORT_ID_FOOTBALL);
	}

	public void onCreateBasketballLeagueStats(){
		generateLeagueStats(SportConstants.SPORT_ID_BASKETBALL);
	}

	private void generateLeagueStats(int sportId) {
		List<League> leagues = leagueDAO.getListBySport(sportId);
		stats = StatsGenerator.generateLeaguesStats(leagues, sportId);
	}


}
