package lt.eimis.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

import lt.eimis.util.SportUtils;

@Entity
@Table(name = "leagues")
public class League implements Serializable {

    private int leagueId;

    private String name;

    private int sportId;

    private Set<Team> teams = new HashSet<Team>(0);

    private transient String sportName;

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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "league_id", unique = true, nullable = false)
    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int id) {
        this.leagueId = id;
    }

    @Column(name = "league_name")
    public String getName() {
        return name;
    }

    public void setName(String leagueName) {
        this.name = leagueName;
    }

    @Column(name = "sport_id")
    public int getSportId() {
        return sportId;
    }

    public void setSportId(int leagueSportId) {
        this.sportId = leagueSportId;
    }

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "leagues")
    public Set<Team> getTeams() {
        return this.teams;
    }

//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "teams_leagues", joinColumns = { @JoinColumn(name = "league_id", nullable = false, updatable = false) },
//            inverseJoinColumns = { @JoinColumn(name = "team_id", nullable = false, updatable = false) })
//    public Set<Team> getTeams() {
//        return this.teams;
//    }




    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    @Transient
    public String getSportName() {
        return SportUtils.getSportName(sportId);
    }

    public void setSportName() {
    }
}
