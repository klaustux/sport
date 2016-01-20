package lt.eimis.bean;

import lt.eimis.entity.League;
import lt.eimis.entity.SportConstants;

import java.util.ArrayList;
import java.util.List;

public class LeagueBean {
	private List<League> leagueList;
	public LeagueBean(){
		leagueList = new ArrayList<>();
		League league = new League();
		league.setLeagueId(100);
		league.setLeagueName("LKL");
		league.setLeagueSportId(SportConstants.SPORT_ID_BASKETBALL);
		leagueList.add(league);
	}

	public List<League> getLeagues(){
		return leagueList;
	}
}
