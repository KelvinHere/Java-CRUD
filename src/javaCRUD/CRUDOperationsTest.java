package javaCRUD;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.hsqldb.util.DatabaseManagerSwing;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class CRUDOperationsTest {

	private static final double INITIAL_NET_COST = 19.99;
	private static final String INITIAL_DESCRIPTION = "MY DESCRIUPTION";
	private static final String INITIAL_SKU = "MYSKU";
	
	private static final double NEW_NET_COST = 29.99;
	private static final String NEW_DESCRIPTION = "MY NEW DESCRIUPTION";
	private static final String NEW_SKU = "MYNEWSKU";
	
	private static final String COL_NAME_SKU = "SKU";
	private static final String COL_NAME_DESCRIPTION = "Description";
	private static final String COL_NAME_NET_COST = "Net_Cost";
	
	ItemManager im;
	
	
	@AfterEach
	public void resetDatabase() {
		im.crud.deleteAll();
	}
	
	@Test
	void createAndReadLineItem() throws SQLException {
		im = setUp();
		LineItem expectedLineItem = new LineItem(INITIAL_SKU, INITIAL_DESCRIPTION, INITIAL_NET_COST);
		
		im.crud.create(expectedLineItem);
		
		ResultSet rs = im.crud.readBySku(INITIAL_SKU);
		LineItem actualLineItem = new LineItem(rs);
		assertTrue(lineItemsMatch(expectedLineItem, actualLineItem));
	}
	
	@Test
	void createAndDeleteLineItem() throws SQLException{
		Boolean hasNoData;
		ResultSet rs;
		im = setUp();
		LineItem expectedLineItem = new LineItem(INITIAL_SKU, INITIAL_DESCRIPTION, INITIAL_NET_COST);
		
		//Result exist
		im.crud.create(expectedLineItem);
		rs = im.crud.readBySku(INITIAL_SKU);
		hasNoData = !rs.next();
		assertFalse(hasNoData);
		
		//Result does not exist
		im.crud.deleteBySku(INITIAL_SKU);
		rs = im.crud.readBySku(INITIAL_SKU);
		hasNoData = !rs.next();
		assertTrue(hasNoData);
	}
	
	
	@Test
	void update() throws SQLException {
		im = setUp();
		LineItem unUpdatedLineItem = new LineItem(INITIAL_SKU, INITIAL_DESCRIPTION, INITIAL_NET_COST);
		unUpdatedLineItem.printLineItem();
		im.crud.create(unUpdatedLineItem);
		

		LineItem updatedLineItem = new LineItem(NEW_SKU, NEW_DESCRIPTION, NEW_NET_COST);
		updatedLineItem.printLineItem();
		im.crud.updateBySku(INITIAL_SKU, updatedLineItem);
		
		ResultSet rsl = im.crud.readBySku(NEW_SKU);
		LineItem actualLineItem = new LineItem(rsl);
		actualLineItem.printLineItem();
		assertTrue(lineItemsMatch(updatedLineItem, actualLineItem));
	}

	
	private ItemManager setUp() {
		im = new ItemManager();
		return im;
	}
	
	
	private Boolean lineItemsMatch(LineItem expectedLineItem, LineItem actualLineItem) {
		Boolean match = true;
		if (!expectedLineItem.getSku().equals(actualLineItem.getSku())) {
			match = false;
		}
		if (!expectedLineItem.getDescription().equals(actualLineItem.getDescription())) {
			match = false;
		}
		if (!expectedLineItem.getNetCost().equals(actualLineItem.getNetCost())) {
			match = false;
		}
		return match;
	}
}

