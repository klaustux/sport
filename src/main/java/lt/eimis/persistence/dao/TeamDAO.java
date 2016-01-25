package lt.eimis.persistence.dao;

import lt.eimis.entity.Team;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "teamBean", eager = true)
@ViewScoped
public class TeamDAO extends CommonDAO<Team> {
    public TeamDAO(Class<Team> entityClass) {
        super(entityClass);
    }

    public TeamDAO() {
        super(Team.class);
    }
}