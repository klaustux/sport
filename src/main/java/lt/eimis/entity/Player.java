package lt.eimis.entity;

import lt.eimis.util.SportUtils;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by eimis on 24/01/16.
 */
	@Entity
	@Table( name = "players" )
	public class Player implements Serializable {

	private int id;
	private String firstName;
	private String lastName;
	private int sportId;
	private Team team;
	private transient String sportName;
	private int positionId;
	private transient String positionName;
	private int points;
	private int gamesPlayed;

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "player_id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "player_first_name", unique = false, nullable = false, length = 100)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "player_last_name", unique = false, nullable = false, length = 100)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "sport_id", nullable = false)
	public int getSportId() {
		return sportId;
	}

	public void setSportId(int sportId) {
		this.sportId = sportId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "team_id", nullable = false)
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@Transient
	public String getSportName() {
		return SportUtils.getSportName(sportId);
	}

	public void setSportName(String sportName){
	}

	public int getPositionId() {
		return positionId;
	}

	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}

	@Transient
	public String getPositionName() {
		return SportUtils.getPositionName(sportId, positionId);
	}

	public void setPositionName(String positionName) {
	}

	@Column(name = "player_points", nullable = false)
	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Column(name = "player_games_played", nullable = false)
	public int getGamesPlayed() {
		return gamesPlayed;
	}

	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}

	public Player(String firstName, String lastName, int sportId, Team team, int positionId, int points, int gamesPlayed) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.sportId = sportId;
		this.team = team;
		this.positionId = positionId;
		this.points = points;
		this.gamesPlayed = gamesPlayed;
	}

	public Player() {
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Player player = (Player) o;

		if (id != player.id) {
			return false;
		}
		if (sportId != player.sportId) {
			return false;
		}
		if (positionId != player.positionId) {
			return false;
		}
		if (points != player.points) {
			return false;
		}
		if (gamesPlayed != player.gamesPlayed) {
			return false;
		}
		if (!firstName.equals(player.firstName)) {
			return false;
		}
		if (!lastName.equals(player.lastName)) {
			return false;
		}
		if (!team.equals(player.team)) {
			return false;
		}
		if (sportName != null ? !sportName.equals(player.sportName) : player.sportName != null) {
			return false;
		}
		return positionName != null ? positionName.equals(player.positionName) : player.positionName == null;

	}


	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		result = 31 * result + sportId;
		result = 31 * result + (team != null ? team.hashCode() : 0);
		result = 31 * result + positionId;
		result = 31 * result + points;
		result = 31 * result + gamesPlayed;
		return result;
	}

	@Override
	public String toString() {
		return "Player{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", sportId=" + sportId +
				", team=" + team +
				", sportName='" + sportName + '\'' +
				", positionId=" + positionId +
				", positionName='" + positionName + '\'' +
				", points=" + points +
				", gamesPlayed=" + gamesPlayed +
				'}';
	}
}
