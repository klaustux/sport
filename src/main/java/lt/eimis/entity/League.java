package lt.eimis.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table( name = "LEAGUES" )
public class League implements Serializable{
	@Id
	@Column(name="league_id")
	private int leagueId;
	@Column(name = "league_name")
	private String leagueName;
	@Column(name = "league_sport_id")
	private int leagueSportId;

	public int getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(int leagueId) {
		this.leagueId = leagueId;
	}

	public String getLeagueName() {
		return leagueName;
	}

	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}

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

	public League() {
	}

	public League(int leagueId, String leagueName, int leagueSportId) {
		this.leagueId = leagueId;
		this.leagueName = leagueName;
		this.leagueSportId = leagueSportId;
	}
}
