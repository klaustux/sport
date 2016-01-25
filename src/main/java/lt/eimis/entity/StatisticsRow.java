package lt.eimis.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eimis on 25/01/16.
 */
public class StatisticsRow implements Serializable {

	public static final int TEAM_STATS = 0;
	public final int PLAYER_STATS = 1;
	public final int LEAGUE_STATS = 2;

	private String output;
	private int index;
	private String firstName;
	private String lastName;
	private String teamName;
	private String leagueName;
	private List<String> leagues = new ArrayList<>();
	private int resultInt;
	private float resultFloat;
	private int statisticsType;

	public StatisticsRow() {
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getLeagueName() {
		return leagueName;
	}

	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}

	public List<String> getLeagues() {
		return leagues;
	}

	public void setLeagues(List<String> leagues) {
		this.leagues = leagues;
	}

	public int getResultInt() {
		return resultInt;
	}

	public void setResultInt(int resultInt) {
		this.resultInt = resultInt;
	}

	public float getResultFloat() {
		return resultFloat;
	}

	public void setResultFloat(float resultFloat) {
		this.resultFloat = resultFloat;
	}

	public int getStatisticsType() {
		return statisticsType;
	}

	public void setStatisticsType(int statisticsType) {
		this.statisticsType = statisticsType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		StatisticsRow that = (StatisticsRow) o;

		if (index != that.index) {
			return false;
		}
		if (resultInt != that.resultInt) {
			return false;
		}
		if (Float.compare(that.resultFloat, resultFloat) != 0) {
			return false;
		}
		if (statisticsType != that.statisticsType) {
			return false;
		}
		if (output != null ? !output.equals(that.output) : that.output != null) {
			return false;
		}
		if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) {
			return false;
		}
		if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) {
			return false;
		}
		if (teamName != null ? !teamName.equals(that.teamName) : that.teamName != null) {
			return false;
		}
		if (leagueName != null ? !leagueName.equals(that.leagueName) : that.leagueName != null) {
			return false;
		}
		return leagues != null ? leagues.equals(that.leagues) : that.leagues == null;

	}

	@Override
	public int hashCode() {
		int result = output != null ? output.hashCode() : 0;
		result = 31 * result + index;
		result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		result = 31 * result + (teamName != null ? teamName.hashCode() : 0);
		result = 31 * result + (leagueName != null ? leagueName.hashCode() : 0);
		result = 31 * result + (leagues != null ? leagues.hashCode() : 0);
		result = 31 * result + resultInt;
		result = 31 * result + (resultFloat != +0.0f ? Float.floatToIntBits(resultFloat) : 0);
		result = 31 * result + statisticsType;
		return result;
	}

	@Override
	public String toString() {
		return "StatisticsRow{" +
				"TEAM_STATS=" + TEAM_STATS +
				", PLAYER_STATS=" + PLAYER_STATS +
				", LEAGUE_STATS=" + LEAGUE_STATS +
				", output='" + output + '\'' +
				", index=" + index +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", teamName='" + teamName + '\'' +
				", leagueName='" + leagueName + '\'' +
				", leagues=" + leagues +
				", resultInt=" + resultInt +
				", resultFloat=" + resultFloat +
				", statisticsType=" + statisticsType +
				'}';
	}


	public void generateStatOutput(int sportId) {
		final String DELIMITER = ",";
		if (this.statisticsType == TEAM_STATS) {
			StringBuffer sb = new StringBuffer();
			sb.append(teamName).append(DELIMITER);
			for (String league : leagues) {
				sb.append(league).append(DELIMITER);
			}
			if (sportId == SportConstants.SPORT_ID_BASKETBALL) {
				sb.append(this.getResultInt());
			}
			else if (sportId == SportConstants.SPORT_ID_FOOTBALL) {
				sb.append(this.getResultFloat());
			}
			else {
				throw new RuntimeException("Unknown sport while generating report!");
			}
			output = sb.toString();
		}
	}
}
