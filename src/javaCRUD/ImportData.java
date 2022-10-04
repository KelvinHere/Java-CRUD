package javaCRUD;

import java.sql.Connection;

public class ImportData {
	
	public static void createPlaceholders(Connection conn) {
		CRUDOperations crud = new CRUDOperations(conn);

		LineItem item = new LineItem("AYHB", "Sonas Sink", 190.50);
		crud.create(item);
		
		LineItem item2 = new LineItem("QKUL", "Sonas Toilet", 290.50);
		crud.create(item2);
		
		LineItem item3 = new LineItem("KUBB", "Sonas Bath", 590.50);
		crud.create(item3);
		
		LineItem item4 = new LineItem("NUNF", "Aqualla Sink", 120.50);
		crud.create(item4);
		
		LineItem item5 = new LineItem("OKIE", "Aqualla Toilet", 220.50);
		crud.create(item5);
		
		LineItem item6 = new LineItem("RKOV", "Aqualla Bath", 1290.50);
		crud.create(item6);
		
		LineItem item7 = new LineItem("ABGQ", "Roca Sink", 90.50);
		crud.create(item7);		
	}
}
