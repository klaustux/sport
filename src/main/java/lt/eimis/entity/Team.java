package lt.eimis.entity;

import lt.eimis.util.SportUtils;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table( name = "teams" )
public class Team implements Serializable {


	private int teamId;
	private String teamName;
	private int sportId;
	private int gamesPlayed;
	private Set<League> leagues = new HashSet<League>(0);
	private transient String sportName;

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "team_id", unique = true, nullable = false)
	public int getTeamId() {
		return teamId;
	}

	@Column(name = "team_name", unique = false, nullable = false, length = 50)
	public String getTeamName() {
		return teamName;
	}

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

	public Team(int teamId, String teamName, int sportId, int gamesPlayed, Set<League> leagues) {
		this.teamName = teamName;
		this.sportId = sportId;
		this.gamesPlayed = gamesPlayed;
		this.leagues = leagues;
	}

	@Column(name = "team_games_played", nullable = false)
	public int getGamesPlayed() {
		return gamesPlayed;
	}

	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}

	@Transient
	public String getSportName(){
		return SportUtils.getSportName(sportId);
	}

	public void setSportName(){}


	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "teams_leagues", joinColumns = {
			@JoinColumn(name = "team_id", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "league_id",
					nullable = false, updatable = false) })
	public Set<League> getLeagues() {
		return this.leagues;
	}

	public void setLeagues(Set<League> leagues) {
		this.leagues = leagues;
	}
}
