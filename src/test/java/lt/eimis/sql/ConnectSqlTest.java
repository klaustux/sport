package lt.eimis.sql;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class ConnectSqlTest {

	@Test
	public void connectTest() throws ClassNotFoundException,
			SQLException, IllegalAccessException, InstantiationException {
		String url = "jdbc:mysql://localhost/sportas";
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection(url, "sportas", "Sportas9..");
	}

	@Test
	public void failConnectTest() throws ClassNotFoundException,
			SQLException, IllegalAccessException, InstantiationException {
		String url = "jdbc:mysql://localhost/sportas";
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		try {
			Connection conn = DriverManager.getConnection(url, "sportas", "nenene");
			fail("Exception expected");
		} catch (SQLException se) {
			assertEquals("Access denied for user 'sportas'@'localhost' (using password: YES)", se.getMessage());
		}
	}
}
