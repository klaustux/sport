package lt.eimis.view;

import lt.eimis.bean.TeamBean;
import lt.eimis.entity.Team;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

	@ManagedBean(name="teamEditView")
	@RequestScoped
	public class TeamView implements Serializable {

		private List<Team> teams;

		@ManagedProperty("#{teamBean}")
		private TeamBean service;

		@PostConstruct
		public void init() {
			teams = service.getTeams();
		}

		public List<Team> getTeams() {
			return teams;
		}

		public void setService(TeamBean service) {
			this.service = service;
		}

		public void onRowEdit(RowEditEvent event) {
			FacesMessage msg = new FacesMessage("Team Edited", ((Team) event.getObject()).getTeamName());
			FacesContext.getCurrentInstance().addMessage(null, msg);
			Team team = (Team) event.getObject();
			service.saveTeam(team);
		}

		public void deleteTeam(Team team) {
			FacesMessage msg = new FacesMessage("Team Deleted", team.getTeamName());
			FacesContext.getCurrentInstance().addMessage(null, msg);
			service.deleteTeam(team);
			teams = service.getTeams();
		}

		public void onRowCancel(RowEditEvent event) {
			FacesMessage msg = new FacesMessage("Edit Cancelled", ((Team) event.getObject()).getTeamName());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
