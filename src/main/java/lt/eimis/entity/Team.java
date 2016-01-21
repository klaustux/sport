package lt.eimis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table( name = "TEAMS" )
public class Team implements Serializable {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "team_id", unique = true, nullable = false)
	private int teamId;

	@Column(name = "team_name", unique = false, nullable = false, length = 50)
	private String teamName;

	@Column(name = "team_sport_id", nullable = false)
	private int sportId;

	@Column(name = "team_games_played", nullable = false)
	private int gamesPlayed;

	public int getTeamId() {
		return teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public int getSportId() {
		return sportId;
	}

  	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}


	public void setSportId(int sportId) {
		this.sportId = sportId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Team team = (Team) o;

		if (teamId != team.teamId) {
			return false;
		}
		if (sportId != team.sportId) {
			return false;
		}
		if (gamesPlayed != team.gamesPlayed) {
			return false;
		}
		return teamName.equals(team.teamName);

	}

	@Override
	public int hashCode() {
		int result = teamId;
		result = 31 * result + teamName.hashCode();
		result = 31 * result + sportId;
		result = 31 * result + gamesPlayed;
		return result;
	}

	public Team() {
	}

	public Team(String teamName, int sportId, int gamesPlayed) {
		this.teamName = teamName;
		this.sportId = sportId;
		this.gamesPlayed = gamesPlayed;
	}

	public Team(int teamId, String teamName, int sportId, int gamesPlayed) {
		this.teamName = teamName;
		this.sportId = sportId;
		this.gamesPlayed = gamesPlayed;
	}


	public int getGamesPlayed() {
		return gamesPlayed;
	}

	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}

	@Override
	public String toString() {
		return "Team{" +
				"teamId=" + teamId +
				", teamName='" + teamName + '\'' +
				", sportId=" + sportId +
				", gamesPlayed=" + gamesPlayed +
				'}';
	}
}
