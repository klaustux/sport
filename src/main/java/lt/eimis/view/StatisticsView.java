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
import java.util.ArrayList;
import java.util.Collections;
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

    private List<StatisticsRow> sortedStats = new ArrayList<>();

    public List<StatisticsRow> getSortedStats() {
        return sortedStats;
    }

    public void setSortedStats(List<StatisticsRow> sortedStats) {
        this.sortedStats = sortedStats;
    }

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

        if (teamDAO != null ? !teamDAO.equals(that.teamDAO)
                            : that.teamDAO != null) {
            return false;
        }
        if (leagueDAO != null ? !leagueDAO.equals(that.leagueDAO)
                              : that.leagueDAO != null) {
            return false;
        }
        if (playerDAO != null ? !playerDAO.equals(that.playerDAO)
                              : that.playerDAO != null) {
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
        return "StatisticsView{" + "teamDAO=" + teamDAO + ", leagueDAO="
               + leagueDAO + ", playerDAO=" + playerDAO + ", stats=" + stats
               + '}';
    }

    public void generateTeamStats(int sportId) {
        List<Team> teams = teamDAO.getListBySport(sportId);
        stats = StatsGenerator.generateTeamsStats(teams, sportId);
        prepareStatistics();
    }

    public void generatePlayerStats(int sportId) {
        List<Player> players = playerDAO.getListBySport(sportId);
        stats = StatsGenerator.generatePlayersStats(players, sportId);
        prepareStatistics();
    }

    public void onCreateBasketTeamStats() {
        generateTeamStats(SportConstants.SPORT_ID_BASKETBALL);
    }

    public void onCreateFootballTeamStats() {
        generateTeamStats(SportConstants.SPORT_ID_FOOTBALL);
    }

    public void onCreateBasketPlayersStats() {
        generatePlayerStats(SportConstants.SPORT_ID_BASKETBALL);
    }

    public void onCreateFootballPlayerStats() {
        generatePlayerStats(SportConstants.SPORT_ID_FOOTBALL);
    }

    public void onCreateFootballLeagueStats() {
        generateLeagueStats(SportConstants.SPORT_ID_FOOTBALL);
    }

    public void onCreateBasketballLeagueStats() {
        generateLeagueStats(SportConstants.SPORT_ID_BASKETBALL);
    }

    private void generateLeagueStats(int sportId) {
        List<League> leagues = leagueDAO.getListBySport(sportId);
        stats = StatsGenerator.generateLeaguesStats(leagues, sportId);
        prepareStatistics();
    }

    public void prepareStatistics() {
        sortedStats = new ArrayList<>(stats);
        Collections.sort(sortedStats);
        Collections.reverse(sortedStats);

        // nupjauti iki 10
        if (sortedStats.size() > 10) {
            sortedStats.subList(10, sortedStats.size()).clear();
        }

        for (int i = 0; i < sortedStats.size(); i++) {
            int begin = i;
            int end = i;
            while (end < sortedStats.size() - 1 && sortedStats.get(end + 1)
                    .getSortKey() == sortedStats.get(i).getSortKey()) {
                end++;
            }
            while (begin > 0 && sortedStats.get(begin - 1)
                    .getSortKey() == sortedStats.get(i).getSortKey()) {
                begin--;
            }
            if (begin != end) {
                sortedStats.get(i).setPrefix(begin + 1 + "-" + (end + 1));
            }
            else {
                sortedStats.get(i).setPrefix(String.valueOf(i + 1));
            }
        }

    }
}
