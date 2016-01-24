package lt.eimis.entity;

import lt.eimis.util.SportUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table( name = "leagues" )
public class League implements Serializable{

	private int id;
	private String name;
	private int sportId;
	private Set<Team> teams = new HashSet<Team>(0);
	private transient String sportName;

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "league_id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "league_name")
	public String getName() {
		return name;
	}

	public void setName(String leagueName) {
		this.name = leagueName;
	}

	@Column(name = "league_sport_id")
	public int getSportId() {
		return sportId;
	}

	public void setSportId(int leagueSportId) {
		this.sportId = leagueSportId;
	}

	public League() {
	}

	public League(int leagueId, String leagueName, int leagueSportId) {
		this.name = leagueName;
		this.sportId = leagueSportId;
	}

	public League(String leagueName, int leagueSportId) {
		this.name = leagueName;
		this.sportId = leagueSportId;
	}

	public League(int id, String name, int sportId, Set<Team> teams) {
		this.name = name;
		this.sportId = sportId;
		this.teams = teams;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "leagues")
	public Set<Team> getTeams() {
		return this.teams;
	}

	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}

	@Transient
	public String getSportName(){
		return SportUtils.getSportName(sportId);
	}

	public void setSportName(){}


}
