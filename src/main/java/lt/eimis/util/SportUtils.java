package lt.eimis.util;

import lt.eimis.entity.SportConstants;

import java.util.Arrays;
import java.util.List;

public class SportUtils {
	public static String getSportName(int sportId)
	{
		if(sportId == SportConstants.SPORT_ID_BASKETBALL){
			return SportConstants.SPORT_NAME_BASKETBALL;
		}
		if(sportId == SportConstants.SPORT_ID_FOOTBALL){
			return SportConstants.SPORT_NAME_FOOTBALL;
		}
		throw new RuntimeException("Not valid sport ID found!");
	}

	public static void validateSportName(String sportName) {
		if(sportName.equals(SportConstants.SPORT_NAME_BASKETBALL) ||
				sportName.equals(SportConstants.SPORT_NAME_FOOTBALL)){
			return;
		}
		throw new RuntimeException("Wrong sport name!");
	}

	public static String getPositionName(int sportId, int positionId) {
		if(sportId == SportConstants.SPORT_ID_BASKETBALL)
		{
			switch (positionId){
				case SportConstants.POSITION_ID_BASKETBALL_CENTER:
				return SportConstants.POSITION_NAME_BASKETBALL_CENTER;
				case SportConstants.POSITION_ID_BASKETBALL_FORWARD:
					return SportConstants.POSITION_NAME_BASKETBALL_FORWARD;
				case SportConstants.POSITION_ID_BASKETBALL_GUARD:
					return SportConstants.POSITION_NAME_BASKETBALL_GUARD;
			}
			throw new RuntimeException("Wrong position name in basketball!");
		}
		if(sportId == SportConstants.SPORT_ID_FOOTBALL)
		{
			switch (positionId){
				case SportConstants.POSITION_ID_FOOTBALL_FORWARD:
					return SportConstants.POSITION_NAME_FOOTBALL_FORWARD;
				case SportConstants.POSITION_ID_FOOTBALL_GOALKEEPER:
					return SportConstants.POSITION_NAME_FOOTBALL_GOALKEEPER;
				case SportConstants.POSITION_ID_FOOTBALL_GUARD:
					return SportConstants.POSITION_NAME_FOOTBALL_GUARD;
				case SportConstants.POSITION_ID_FOOTBALL_MIDFIELDER:
					return SportConstants.POSITION_NAME_FOOTBALL_MIDFIELDER;
			}
			throw new RuntimeException("Wrong position name in football!");
		}
		throw new RuntimeException("Wrong sport id in SportUtils!");
	}

	public static List<SportPosition> getPositionsBySport(int sportId)
	{
		if(sportId == SportConstants.SPORT_ID_BASKETBALL){
			return Arrays.asList(SportConstants.POSITION_BASKETBALL_CENTER, SportConstants.POSITION_BASKETBALL_FORWARD,
					SportConstants.POSITION_BASKETBALL_GUARD);
		}
		if(sportId == SportConstants.SPORT_ID_FOOTBALL){
			return Arrays.asList(SportConstants.POSITION_FOOTBALL_GOALKEEPER, SportConstants.POSITION_FOOTBALL_FORWARD,
					SportConstants.POSITION_FOOTBALL_GUARD, SportConstants.POSITION_FOOTBALL_MIDFIELDER);
		}
		throw new RuntimeException("Wrong sport id!");
	}

	public static int getDefaultPositionForSport(int sportId) {
		if(SportConstants.SPORT_ID_FOOTBALL == sportId){
			return SportConstants.POSITION_ID_FOOTBALL_FORWARD;
		}
		if(SportConstants.SPORT_ID_BASKETBALL == sportId){
			return SportConstants.POSITION_ID_BASKETBALL_CENTER;
		}
		throw new RuntimeException("Wrong sport id!");
	}
}
