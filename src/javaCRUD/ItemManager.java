package javaCRUD;

import java.sql.Connection;
import org.hsqldb.util.DatabaseManagerSwing;

public class ItemManager {
	Connection conn;
	CRUDOperations crud;
	GUI gui;
	
	
	public ItemManager() {
		this.conn = DatabaseConnect.connect();
		this.crud = new CRUDOperations(conn);
		gui = new GUI(this.conn, this.crud);
	}
	
	
	public static void main(String[] args) {
		ItemManager im = new ItemManager();

		/* Debug GUI database 	*/		
		//DatabaseManagerSwing manager = new DatabaseManagerSwing();
		//manager.main();
		//manager.connect(im.conn);
		//manager.start();
		/* End of debug GUI database */
	}
}
