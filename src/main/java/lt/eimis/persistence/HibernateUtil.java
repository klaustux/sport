package lt.eimis.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public enum HibernateUtil {
	INSTANCE;

	private EntityManagerFactory emFactory;

	private HibernateUtil() {
		emFactory = Persistence.createEntityManagerFactory("sportas");
	}

	public EntityManager getEntityManager() {
		return emFactory.createEntityManager();
	}

	public void close() {
		emFactory.close();
	}
}