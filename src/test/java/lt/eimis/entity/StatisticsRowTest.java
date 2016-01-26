package lt.eimis.entity;

import lt.eimis.util.StatsGenerator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by eimis on 26/01/16.
 */
public class StatisticsRowTest {

	private Team team;

	@Before
	public void setup(){
		team = new Team();
		team.setSportId(1);
		team.setGamesPlayed(10);
	}

	@Test
	public void testCompareTo() throws Exception {
		Player kudirka = new Player();
		kudirka.setFirstName("Vincas");
		kudirka.setLastName("Kudirka");
		kudirka.setGamesPlayed(6);
		kudirka.setPoints(132);
		kudirka.setSportId(1);
		kudirka.setTeam(team);

		Player jablonskis = new Player();
		jablonskis.setFirstName("Jonas");
		jablonskis.setLastName("Jablonskis");
		jablonskis.setGamesPlayed(5);
		jablonskis.setPoints(132);
		jablonskis.setSportId(1);
		jablonskis.setTeam(team);

		Player buga = new Player();
		buga.setFirstName("Kazys");
		buga.setLastName("Buga");
		buga.setGamesPlayed(3);
		buga.setPoints(66);
		buga.setSportId(1);
		buga.setTeam(team);

		StatisticsRow rowKudirka = StatsGenerator.collectPlayerStats(kudirka);
		StatisticsRow rowJablonskis = StatsGenerator.collectPlayerStats(jablonskis);
		StatisticsRow rowBuga = StatsGenerator.collectPlayerStats(buga);

		assertTrue("Kudirka is better scorer!", rowKudirka.compareTo(rowJablonskis)  < 0);

		assertTrue("Averages are not equal", rowKudirka.compareTo(rowBuga)  == 0);
	}
}