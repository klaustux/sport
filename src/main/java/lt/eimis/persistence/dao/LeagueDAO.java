package lt.eimis.persistence.dao;

import lt.eimis.entity.League;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "leagueBean", eager = true)
@RequestScoped
public class LeagueDAO extends CommonDAO<League> {
    public LeagueDAO(Class<League> entityClass) {
        super(entityClass);
    }

    public LeagueDAO() {
        super(League.class);
    }
}