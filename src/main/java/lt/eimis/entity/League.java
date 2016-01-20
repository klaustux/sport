package lt.eimis.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="leagues")
public class League {
	private int leagueId;
	private String leagueName;
	private int leagueSportId;

	@Id
	@GeneratedValue
	@Column(name = "league_id", nullable = false)
	public int getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(int leagueId) {
		this.leagueId = leagueId;
	}

	@Basic
	@Column(name = "league_name", nullable = false, length = 50)
	public String getLeagueName() {
		return leagueName;
	}

	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}

	@Basic
	@Column(name = "league_sport_id", nullable = false)
	public int getLeagueSportId() {
		return leagueSportId;
	}

	public void setLeagueSportId(int leagueSportId) {
		this.leagueSportId = leagueSportId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		League leagues = (League) o;

		if (leagueId != leagues.leagueId) {
			return false;
		}
		if (leagueSportId != leagues.leagueSportId) {
			return false;
		}
		if (leagueName != null ? !leagueName.equals(leagues.leagueName) : leagues.leagueName != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = leagueId;
		result = 31 * result + (leagueName != null ? leagueName.hashCode() : 0);
		result = 31 * result + leagueSportId;
		return result;
	}
}
