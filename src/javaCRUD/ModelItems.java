package javaCRUD;


public class ModelItems {
	public static String itemModel() {
		String model = "CREATE TABLE IF NOT EXISTS items("
				+ "SKU VARCHAR(50) NOT NULL UNIQUE, "
				+ "Description VARCHAR(255), "
				+ "Net_Cost DECIMAL(15,2))";
		return model;
	}
}

