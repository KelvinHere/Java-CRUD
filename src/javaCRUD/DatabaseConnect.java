package javaCRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnect {
	   //static final String DB = "jdbc:hsqldb:file:data/db";
	   static final String DB = "jdbc:hsqldb:mem:data/db";
	   static final String USER_NAME = "admin";
	   static final String PASSWORD = "";

	public static Connection connect() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB, USER_NAME, PASSWORD);
			Statement stmnt = conn.createStatement();
			stmnt.execute(ModelItems.itemModel());
			
		} catch (SQLException e ) {
			System.out.println(e.getMessage() + " *Database connection class");
		}
		return conn;
	}
}
