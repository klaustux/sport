package lt.eimis.entity;

import lt.eimis.util.SportPosition;

public class SportConstants {
	public static final int SPORT_ID_BASKETBALL = 0;
	public static final int SPORT_ID_FOOTBALL = 1;
	public static final String SPORT_NAME_BASKETBALL = "Krepšinis";
	public static final String SPORT_NAME_FOOTBALL = "Futbolas";

	public static final String POSITION_NAME_BASKETBALL_CENTER = "Centras";
	public static final String POSITION_NAME_BASKETBALL_FORWARD = "Puolėjas";
	public static final String POSITION_NAME_BASKETBALL_GUARD = "Gynėjas";

	public static final int POSITION_ID_BASKETBALL_CENTER = 0;
	public static final int POSITION_ID_BASKETBALL_FORWARD = 1;
	public static final int  POSITION_ID_BASKETBALL_GUARD = 2;


	public static final String POSITION_NAME_FOOTBALL_GOALKEEPER = "Vartininkas";
	public static final String POSITION_NAME_FOOTBALL_FORWARD = "Puolėjas";
	public static final String POSITION_NAME_FOOTBALL_GUARD = "Gynėjas";
	public static final String POSITION_NAME_FOOTBALL_MIDFIELDER = "Saugas";
	public static final int POSITION_ID_FOOTBALL_GOALKEEPER = -1;
	public static final int POSITION_ID_FOOTBALL_FORWARD = 1;
	public static final int POSITION_ID_FOOTBALL_GUARD =  2;
	public static final int POSITION_ID_FOOTBALL_MIDFIELDER = 3;

	public static final SportPosition POSITION_BASKETBALL_CENTER =
			new SportPosition(POSITION_ID_BASKETBALL_CENTER, POSITION_NAME_BASKETBALL_CENTER);
	public static final SportPosition POSITION_BASKETBALL_GUARD =
			new SportPosition(POSITION_ID_BASKETBALL_GUARD, POSITION_NAME_BASKETBALL_GUARD);
	public static final SportPosition POSITION_BASKETBALL_FORWARD =
			new SportPosition(POSITION_ID_BASKETBALL_FORWARD, POSITION_NAME_BASKETBALL_FORWARD);

	public static final SportPosition POSITION_FOOTBALL_GOALKEEPER =
			new SportPosition(POSITION_ID_FOOTBALL_GOALKEEPER, POSITION_NAME_FOOTBALL_GOALKEEPER);
	public static final SportPosition POSITION_FOOTBALL_GUARD =
			new SportPosition(POSITION_ID_FOOTBALL_GUARD, POSITION_NAME_FOOTBALL_GUARD);
	public static final SportPosition POSITION_FOOTBALL_FORWARD =
			new SportPosition(POSITION_ID_FOOTBALL_FORWARD, POSITION_NAME_FOOTBALL_FORWARD);
	public static final SportPosition POSITION_FOOTBALL_MIDFIELDER =
			new SportPosition(POSITION_ID_FOOTBALL_MIDFIELDER, POSITION_NAME_FOOTBALL_MIDFIELDER);

}
