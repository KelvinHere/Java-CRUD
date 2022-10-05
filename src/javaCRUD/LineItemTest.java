package javaCRUD;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.sql.ResultSet;

class LineItemTest {

	private static final String ITEM_1_SKU = "ITEM1SKU";
	private static final String ITEM_1_DESCRIPTION = "ITEM 1 DESCRIUPTION";
	private static final double ITEM_1_NET_COST = 19.99;
	
	ItemManager im;

	
	@AfterEach
	public void resetDatabase() {
		if(im != null) {
			im.crud.deleteAll();
		}
	}

	
	@Test
	void ItemInfoOverLoadedConstructorReturnsAFilledLineItem() {
		LineItem item = new LineItem(ITEM_1_SKU, ITEM_1_DESCRIPTION, ITEM_1_NET_COST);
		assertEquals(item.getSku(), ITEM_1_SKU);
		assertEquals(item.getDescription(), ITEM_1_DESCRIPTION);
		assertEquals(item.getNetCost(), ITEM_1_NET_COST);
	}
	
	
	@Test
	void resultSetOverLoadedConstructorReturnsFirstResultAsLineItem() {
		im = setUp();
		LineItem item = new LineItem(ITEM_1_SKU, ITEM_1_DESCRIPTION, ITEM_1_NET_COST);
		im.crud.create(item);
		ResultSet rs= im.crud.readBySku(item.getSku());
		LineItem retrievedItem = new LineItem(rs);
		assertTrue(lineItemsMatch(item, retrievedItem));
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
