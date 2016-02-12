package lt.eimis.util;

import lt.eimis.entity.League;
import lt.eimis.entity.Player;
import lt.eimis.entity.SportConstants;
import lt.eimis.entity.StatisticsRow;
import lt.eimis.entity.Team;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.*;

public class StatsGenerator {
    public static final String DELIMITER = ",";

    public static void generateTeamStatOutput(StatisticsRow row) {
        StringBuffer sb = new StringBuffer();
        sb.append(row.getTeamName()).append(DELIMITER);
        for (String league : row.getLeagues()) {
            sb.append(league).append(DELIMITER);
        }
        sb.append(getResultString(row));
        row.setOutput(sb.toString());
        return;
    }

    public static String getResultString(StatisticsRow row) {
        if (row.getSportId() == SportConstants.SPORT_ID_BASKETBALL) {
            row.setSortKey(row.getResultInt());
            return String.valueOf(row.getResultInt());
        }
        else if (row.getSportId() == SportConstants.SPORT_ID_FOOTBALL) {
            if (row.getGames() > 0) {
                float f = row.getResultInt();
                f = f / row.getGames();
                row.setSortKey(f);
                DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
                otherSymbols.setDecimalSeparator('.');
                otherSymbols.setGroupingSeparator(' ');
                DecimalFormat df = new DecimalFormat("#.##", otherSymbols);

                String result = df.format(f);
                return result;
            }
            else {
                row.setSortKey(0);
                return "0";
            }
        }
        else {
            throw new RuntimeException(
                    "Unknown sport while generating report!");
        }
    }

    public static StatisticsRow collectTeamStats(Team team) {
        int points = 0;
        for (Player player : team.getPlayers()) {
            points += player.getPoints();
        }
        StatisticsRow row = new StatisticsRow();
        row.setTeamName(team.getTeamName());
        row.setStatisticsType(StatisticsRow.TEAM_STATS);
        row.setResultInt(points);
        row.setGames(team.getGamesPlayed());
        row.setSportId(team.getSportId());
        List<String> leagueNames = new ArrayList<>();
        for (League league : team.getLeagues()) {
            leagueNames.add(league.getName());
        }
        row.setLeagues(leagueNames);
        return row;
    }

    public static Set<StatisticsRow> generatePlayersStats(List<Player> players,
                                                          int sportId) {
        Set<StatisticsRow> stats = new HashSet<>(0);
        for (Player player : players) {
            StatisticsRow statRow = collectPlayerStats(player);
            StatsGenerator.generatePlayerStatOutput(statRow);
            stats.add(statRow);
        }
        return stats;
    }

    public static void generatePlayerStatOutput(StatisticsRow row) {
        StringBuffer sb = new StringBuffer();
        sb.append(row.getFirstName()).append(DELIMITER);
        sb.append(row.getLastName()).append(DELIMITER);
        for (String league : row.getLeagues()) {
            sb.append(league).append(DELIMITER);
        }
        sb.append(getResultString(row));
        row.setOutput(sb.toString());
        return;
    }

    public static StatisticsRow collectPlayerStats(Player player) {
        StatisticsRow row = new StatisticsRow();
        row.setFirstName(player.getFirstName());
        row.setLastName(player.getLastName());
        row.setStatisticsType(StatisticsRow.PLAYER_STATS);
        row.setResultInt(player.getPoints());
        row.setGames(player.getGamesPlayed());
        row.setSportId(player.getSportId());
        List<String> leagueNames = new ArrayList<>();
        if (player.getTeam().getLeagues() != null) {
            for (League league : player.getTeam().getLeagues()) {
                leagueNames.add(league.getName());
            }
        }
        row.setLeagues(leagueNames);
        return row;
    }

    public static Set<StatisticsRow> generateTeamsStats(List<Team> teams,
                                                        int sportId) {
        Set<StatisticsRow> stats = new HashSet<>(0);
        for (Team team : teams) {
            StatisticsRow statRow = collectTeamStats(team);
            StatsGenerator.generateTeamStatOutput(statRow);
            stats.add(statRow);
        }
        return stats;
    }

    public static Set<StatisticsRow> generateLeaguesStats(List<League> leagues,
                                                          int sportId) {
        Set<StatisticsRow> stats = new HashSet<>(0);
        for (League league : leagues) {
            StatisticsRow statRow = collectLeagueStats(league);
            StatsGenerator.generateLeagueStatOutput(statRow);
            stats.add(statRow);
        }
        return stats;
    }

    private static StatisticsRow collectLeagueStats(League league) {
        StatisticsRow row = new StatisticsRow();
        row.setLeagueName(league.getName());
        row.setStatisticsType(StatisticsRow.LEAGUE_STATS);
        row.setSportId(league.getSportId());
        int score = 0;
        int gamesPlayed = 0;
        if (league.getTeams() != null) {
            for (Team team : league.getTeams()) {
                gamesPlayed += team.getGamesPlayed();
                if (team.getPlayers() != null) {
                    for (Player player : team.getPlayers()) {
                        score += player.getPoints();
                    }
                }
            }
        }
        row.setResultInt(score);
        row.setGames(gamesPlayed);
        return row;
    }

    public static void generateLeagueStatOutput(StatisticsRow row) {
        StringBuffer sb = new StringBuffer();
        sb.append(row.getLeagueName()).append(DELIMITER);

        sb.append(getResultString(row));
        row.setOutput(sb.toString());
        return;
    }
}
