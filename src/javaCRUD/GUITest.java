package javaCRUD;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JTable;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class GUITest {
	
	private static final String ITEM_1_SKU = "ITEM1SKU";
	private static final String ITEM_1_DESCRIPTION = "ITEM 1 DESCRIUPTION";
	private static final double ITEM_1_NET_COST = 19.99;

	
	ItemManager im;

	
	@AfterEach
	public void resetDatabase() {
		im.crud.deleteAll();
	}

	
	@Test
	void updateDataTableInsertsCreatedItemIntoTable() {
		im = setUp();
		LineItem item1 = new LineItem(ITEM_1_SKU, ITEM_1_DESCRIPTION, ITEM_1_NET_COST);
		im.crud.create(item1);
		im.gui.updateDataTable("");
		JTable dataTable = im.gui.getDataTable();
		String tableSku = dataTable.getValueAt(0, 0).toString();
		String tableDescription = dataTable.getValueAt(0, 1).toString();
		String tableNetCost = dataTable.getValueAt(0, 2).toString();
		
		assertEquals(item1.getSku(), tableSku);
		assertEquals(item1.getDescription(), tableDescription);
		assertEquals(item1.getNetCost().toString(), tableNetCost);
	}
	
	
	private ItemManager setUp() {
		im = new ItemManager();
		return im;
	}
}
