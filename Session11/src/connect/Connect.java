package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

	private final String NAME = "session11";
	private final String USER = "root";
	private final String PASSWORD = "";
	private final String HOST ="localhost:3306";
	private final String CONNECTION = String.format("jdbc:mysql://%s/%s", HOST, NAME);
	
	private Connection con;
	private static Connect instance;
	
	public static Connect getInstance() {
		if (instance == null) {
			instance = new Connect();
		}
		
		return instance;
	}
	
	private Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(CONNECTION, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConn() {
		return con;
	}

}
