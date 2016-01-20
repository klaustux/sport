package lt.eimis.bean;

import lt.eimis.entity.League;
import lt.eimis.service.LeagueService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class LeagueView implements Serializable {


	private List<League> leagues;

	@ManagedProperty("#{leagueService}")
	private LeagueService leagueService;


	@PostConstruct
	public void init() {
		leagues = leagueService.getLeagues();
	}

	public List<League> getLeagues(){
		return leagues;
	}

	public void setLeagueService(LeagueService service) {
		this.leagueService = service;
	}
}