package lt.eimis.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
	private static EntityManagerFactory entityManagerFactory;

	public static EntityManager getEntityManager() {
		if(entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
		}
		return entityManagerFactory.createEntityManager();
	}
}