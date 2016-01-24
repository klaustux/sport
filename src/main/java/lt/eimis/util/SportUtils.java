package lt.eimis.util;

import lt.eimis.entity.SportConstants;

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
}
