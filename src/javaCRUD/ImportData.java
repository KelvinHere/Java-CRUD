package javaCRUD;

import java.sql.Connection;

public class ImportData {
	
	public static void createPlaceholders(Connection conn) {
		CRUDOperations crud = new CRUDOperations(conn);
		
		crud.create("AYHB", "Sonas Sink", 190.50);
		crud.create("QKUL", "Sonas Toilet", 290.50);
		crud.create("KUBB", "Sonas Bath", 590.50);
		crud.create("NUNF", "Aqualla Sink", 120.50);
		crud.create("OKIE", "Aqualla Toilet", 220.50);
		crud.create("RKOV", "Aqualla Bath", 1290.50);
		crud.create("ABGQ", "Roca Sink", 90.50);		
	}
}
