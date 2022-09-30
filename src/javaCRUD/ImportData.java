package javaCRUD;

import java.sql.Connection;

public class ImportData {
	
	public static void createPlaceholders(Connection conn) {
		CRUDOperations crud = new CRUDOperations(conn);
		
		crud.create("AB", "Sonas Sink", 190.50);
		crud.create("QL", "Sonas Toilet", 290.50);
		crud.create("BB", "Sonas Bath", 590.50);
		crud.create("NF", "Aqualla Sink", 120.50);
		crud.create("OE", "Aqualla Toilet", 220.50);
		crud.create("RV", "Aqualla Bath", 1290.50);
		crud.create("AQ", "Roca Sink", 90.50);		
	}
}
