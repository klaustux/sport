package lt.eimis.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StatisticsRow implements Serializable, Comparable<StatisticsRow> {

    public static final int TEAM_STATS = 0;

    public static final int PLAYER_STATS = 1;

    public static final int LEAGUE_STATS = 2;

    private String output;

    private int index;

    private String firstName;

    private String lastName;

    private String teamName;

    private String leagueName;

    private List<String> leagues = new ArrayList<>();

    private int resultInt;

    private int statisticsType;

    private int sportId;

    private int games;

    private float sortKey;

    public StatisticsRow() {
    }

    private static float average(StatisticsRow row) {
        if (row.games == 0) {
            return 0;
        }
        float f = row.getResultInt();
        return f / row.getGames();
    }

    public float getSortKey() {
        return sortKey;
    }

    public void setSortKey(float sortKey) {
        this.sortKey = sortKey;
    }

    public int getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
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

    public int getStatisticsType() {
        return statisticsType;
    }

    public void setStatisticsType(int statisticsType) {
        this.statisticsType = statisticsType;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
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
        if (statisticsType != that.statisticsType) {
            return false;
        }
        if (sportId != that.sportId) {
            return false;
        }
        if (games != that.games) {
            return false;
        }
        if (output != null ? !output.equals(that.output)
                           : that.output != null) {
            return false;
        }
        if (firstName != null ? !firstName.equals(that.firstName)
                              : that.firstName != null) {
            return false;
        }
        if (lastName != null ? !lastName.equals(that.lastName)
                             : that.lastName != null) {
            return false;
        }
        if (teamName != null ? !teamName.equals(that.teamName)
                             : that.teamName != null) {
            return false;
        }
        if (leagueName != null ? !leagueName.equals(that.leagueName)
                               : that.leagueName != null) {
            return false;
        }
        return leagues != null ? leagues.equals(that.leagues)
                               : that.leagues == null;

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
        result = 31 * result + statisticsType;
        result = 31 * result + sportId;
        result = 31 * result + games;
        return result;
    }

    @Override
    public String toString() {
        return "StatisticsRow{" + "output='" + output + '\'' + ", index="
               + index + ", firstName='" + firstName + '\'' + ", lastName='"
               + lastName + '\'' + ", teamName='" + teamName + '\''
               + ", leagueName='" + leagueName + '\'' + ", leagues=" + leagues
               + ", resultInt=" + resultInt + ", statisticsType="
               + statisticsType + ", sportId=" + sportId + ", games=" + games
               + '}';
    }

    @Override
    public int compareTo(StatisticsRow o) {
        if (sportId == SportConstants.SPORT_ID_BASKETBALL) {
            return resultInt - o.getResultInt();
        }
        if (sportId == SportConstants.SPORT_ID_FOOTBALL) {
            return Float.compare(average(this), average(o));
        }
        throw new RuntimeException("Wrong sport id!");
    }
}
