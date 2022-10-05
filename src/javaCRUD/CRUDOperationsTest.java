package javaCRUD;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class CRUDOperationsTest {

	private static final String INITIAL_SKU = "MYSKU";
	private static final String INITIAL_DESCRIPTION = "MY DESCRIUPTION";
	private static final double INITIAL_NET_COST = 19.99;
	
	private static final String NEW_SKU = "MYNEWSKU";
	private static final String NEW_DESCRIPTION = "MY NEW DESCRIUPTION";
	private static final double NEW_NET_COST = 29.99;
	
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
		im.crud.create(unUpdatedLineItem);
		
		LineItem updatedLineItem = new LineItem(NEW_SKU, NEW_DESCRIPTION, NEW_NET_COST);
		im.crud.updateBySku(INITIAL_SKU, updatedLineItem);
		
		ResultSet rsl = im.crud.readBySku(NEW_SKU);
		LineItem actualLineItem = new LineItem(rsl);
		assertTrue(lineItemsMatch(updatedLineItem, actualLineItem));
	}
	
	
	@Test 
	void itemWithDuplicateSkuWontOverwriteOriginal() {
		im = setUp();
		im.crud.testMode = true;
		
		LineItem item = new LineItem(INITIAL_SKU, INITIAL_DESCRIPTION, INITIAL_NET_COST);
		im.crud.create(item);
		LineItem itemDuplicateSku = new LineItem(INITIAL_SKU, NEW_DESCRIPTION, NEW_NET_COST);
		im.crud.create(itemDuplicateSku);
		ResultSet rs = im.crud.readBySku(INITIAL_SKU);
		LineItem retrievedItem = new LineItem(rs);
		
		assertFalse(lineItemsMatch(itemDuplicateSku, retrievedItem));
		assertTrue(lineItemsMatch(item, retrievedItem));
	}
	
	
	@Test
	void getAllLinesReturnsAllCreatedItems() throws SQLException {
		im = setUp();
		LineItem item1 = new LineItem(INITIAL_SKU, INITIAL_DESCRIPTION, INITIAL_NET_COST);
		im.crud.create(item1);
		LineItem item2 = new LineItem(NEW_SKU, NEW_DESCRIPTION, NEW_NET_COST);
		im.crud.create(item2);
		
		ResultSet rs = im.crud.getAllLines();
		int expectedLines = 2;
		int lineCount = 0;
		
		while (rs.next()) {
			lineCount ++;
		}
		
		assertEquals(lineCount, expectedLines);

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

