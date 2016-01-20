package lt.eimis.editor;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "editor")
public class LeagueEditor {

		private String value = "This editor is provided by PrimeFaces";

		public String getValue() {
//			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//			Transaction transaction = session.beginTransaction();
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
