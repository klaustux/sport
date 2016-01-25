package lt.eimis.persistence.dao;

import lt.eimis.entity.Player;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "playerBean", eager = true)
@ViewScoped
public class PlayerDAO extends CommonDAO<Player>{
	public PlayerDAO(Class<Player> entityClass) {
		super(entityClass);
	}

	public PlayerDAO() {
		super(Player.class);
	}
}