package lt.eimis.util;

import lt.eimis.entity.League;
import lt.eimis.entity.Player;
import lt.eimis.entity.SportConstants;
import lt.eimis.entity.StatisticsRow;
import lt.eimis.entity.Team;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by eimis on 26/01/16.
 */
public class StatsGeneratorTest {

	private Team team;
	private Player player1;
	private League league;

	@Before
	public void setup(){
		team = new Team();
		team.setTeamName("Komanda");
		team.setSportId(SportConstants.SPORT_ID_FOOTBALL);
		team.setGamesPlayed(3);
		player1 = new Player();
		player1.setPoints(1);
		player1.setGamesPlayed(1);
		player1.setFirstName("F");
		player1.setLastName("N");
		player1.setGamesPlayed(3);
		player1.setTeam(team);
		Set<Player> playerSet = new HashSet<>();
		playerSet.add(player1);
		team.setPlayers(playerSet);

		league = new League();
		league.setName("lyga1");
		Set<Team> teams = new HashSet<>();
		teams.add(team);
		league.setTeams(teams);
	}


	@Test
	public void testGenerateTeamStatOutput() throws Exception {
		String expectedResult = "Komanda,0.33";
		StatisticsRow row = StatsGenerator.generateTeamStats(team);
		row.setSportId(SportConstants.SPORT_ID_FOOTBALL);
		StatsGenerator.generateTeamStatOutput(row);
		assertEquals(expectedResult, row.getOutput());
	}

	@Test
	public void testGeneratePlayerStatOutput() throws Exception {
		String expectedResult = "F,N,0.33";
		StatisticsRow row = StatsGenerator.generatePlayerStats(player1);
		row.setSportId(SportConstants.SPORT_ID_FOOTBALL);
		StatsGenerator.generatePlayerStatOutput(row);
		assertEquals(expectedResult, row.getOutput());
	}

	@Test
	public void testGetResultString(){
		String expected = "0.33";
		int sport = 1;
		int games = 3;
		int score = 1;
		String result = StatsGenerator.getResultString(sport, games, score);
		assertEquals(expected, result);
	}

	@Test
	public void leagueGeneratorTest(){
		List<League> leagues = new ArrayList<League>();
		leagues.add(league);
		int sportId = 1;
		StatisticsRow row = StatsGenerator.generateLeaguesStats(leagues, sportId).iterator().next();
		assertEquals(3, row.getGames());
		assertEquals(1, row.getResultInt());
	}

	@Test
	public void leagueOutputTest(){
		String expectedResult = "lyga1,0.33";
		List<League> leagues = new ArrayList<League>();
		leagues.add(league);
		int sportId = 1;
		StatisticsRow row = StatsGenerator.generateLeaguesStats(leagues, sportId).iterator().next();
		row.setSportId(SportConstants.SPORT_ID_FOOTBALL);
		StatsGenerator.generateLeagueStatOutput(row);
		assertEquals(expectedResult, row.getOutput());
	}
}