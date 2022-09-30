package javaCRUD;

import java.sql.Connection;
import org.hsqldb.util.DatabaseManagerSwing;

public class ItemManager {
	Connection conn;
	CRUDOperations crud;
	
	
	public ItemManager() {
		this.conn = DatabaseConnect.connect();
		crud = new CRUDOperations(conn);
	}
	
	
	public static void main(String[] args) {
		ItemManager runner = new ItemManager();

		/* Debug GUI database 	*/		
		DatabaseManagerSwing manager = new DatabaseManagerSwing();
		manager.main();
		manager.connect(runner.conn);
		manager.start();
		/* End of debug GUI database */
		
		runner.crud.insertLineItem("ADFA", "Roca Sink", 192.99);
	}
}
