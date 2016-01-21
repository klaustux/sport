package lt.eimis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table( name = "TEAMS" )
public class Team implements Serializable {

	private int teamId;
	private String teamName;
	private int sportId;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "team_id", unique = true, nullable = false)
	public int getTeamId() {
		return teamId;
	}

	@Column(name = "team_name", unique = false, nullable = false, length = 50)
	public String getTeamName() {
		return teamName;
	}

	@Id
	@Column(name = "team_sport_id", nullable = false)
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
		return teamName != null ? teamName.equals(team.teamName) : team.teamName == null;

	}

	@Override
	public int hashCode() {
		int result = teamId;
		result = 31 * result + (teamName != null ? teamName.hashCode() : 0);
		result = 31 * result + sportId;
		return result;
	}

	@Override
	public String toString() {
		return "Team{" +
				"teamId=" + teamId +
				", teamName='" + teamName + '\'' +
				", sportId=" + sportId +
				'}';
	}
}
