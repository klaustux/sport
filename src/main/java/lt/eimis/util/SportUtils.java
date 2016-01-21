package lt.eimis.util;

import lt.eimis.entity.SportConstants;

/**
 * Created by eimis on 21/01/16.
 */
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
}
