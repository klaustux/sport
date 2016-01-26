package lt.eimis.util;

import lt.eimis.entity.League;
import lt.eimis.entity.Player;
import lt.eimis.entity.SportConstants;
import lt.eimis.entity.StatisticsRow;
import lt.eimis.entity.Team;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StatsGenerator {
	public static final String DELIMITER = ",";

	public static void generateTeamStatOutput(StatisticsRow row) {
		StringBuffer sb = new StringBuffer();
		sb.append(row.getTeamName()).append(DELIMITER);
		for (String league : row.getLeagues()) {
			sb.append(league).append(DELIMITER);
		}
		sb.append(getResultString(row.getSportId(), row.getGames(),
				row.getResultInt()));
		row.setOutput(sb.toString());
		return;
	}

	public static String getResultString(int sportId,
										 int gamesPlayed,
										 int score) {
		if (sportId == SportConstants.SPORT_ID_BASKETBALL) {
			return String.valueOf(score);
		}
		else if (sportId == SportConstants.SPORT_ID_FOOTBALL) {
			if (gamesPlayed > 0) {
				float f = score;
				f = f / gamesPlayed;
				return new DecimalFormat("#.##").format(f);
			}
			else {
				return "0";
			}
		}
		else {
			throw new RuntimeException(
					"Unknown sport while generating report!");
		}
	}

	public static StatisticsRow generateTeamStats(Team team) {
		int points = 0;
		for (Player player : team.getPlayers()) {
			points += player.getPoints();
		}
		StatisticsRow row = new StatisticsRow();
		row.setTeamName(team.getTeamName());
		row.setStatisticsType(StatisticsRow.TEAM_STATS);
		row.setResultInt(points);
		row.setGames(team.getGamesPlayed());
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
			StatisticsRow statRow = generatePlayerStats(player);
			statRow.setSportId(sportId);
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
		sb.append(getResultString(row.getSportId(), row.getGames(),
				row.getResultInt()));
		row.setOutput(sb.toString());
		return;
	}

	public static StatisticsRow generatePlayerStats(Player player) {
		StatisticsRow row = new StatisticsRow();
		row.setFirstName(player.getFirstName());
		row.setLastName(player.getLastName());
		row.setStatisticsType(StatisticsRow.PLAYER_STATS);
		row.setResultInt(player.getPoints());
		row.setGames(player.getGamesPlayed());
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
			StatisticsRow statRow = generateTeamStats(team);
			statRow.setSportId(sportId);
			StatsGenerator.generateTeamStatOutput(statRow);
			stats.add(statRow);
		}
		return stats;
	}

	public static Set<StatisticsRow> generateLeaguesStats(List<League> leagues, int sportId) {
		Set<StatisticsRow> stats = new HashSet<>(0);
		for (League league : leagues) {
			StatisticsRow statRow = generateLeagueStats(league);
			statRow.setSportId(sportId);
			StatsGenerator.generateLeagueStatOutput(statRow);
			stats.add(statRow);
		}
		return stats;
	}

	private static StatisticsRow generateLeagueStats(League league) {
		StatisticsRow row = new StatisticsRow();
		row.setLeagueName(league.getName());
		row.setStatisticsType(StatisticsRow.LEAGUE_STATS);
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

		sb.append(getResultString(row.getSportId(), row.getGames(),
				row.getResultInt()));
		row.setOutput(sb.toString());
		return;
	}
}
