package javaCRUD;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class CRUDOperationsTest {

	private static final double TEST_NET_COST = 19.99;
	private static final String TEST_DESCRIPTION = "MY DESCRIUPTION";
	private static final String TEST_SKU = "MYSKU";
	
	private static final double NEW_NET_COST = 29.99;
	private static final String NEW_DESCRIPTION = "MY NEW DESCRIUPTION";
	private static final String NEW_SKU = "MYNEWSKU";
	
	private static final String COL_NAME_SKU = "SKU";
	private static final String COL_NAME_DESCRIPTION = "Description";
	private static final String COL_NAME_NET_COST = "Net_Cost";
	
	
	@Test
	void createAndReadLineItem() throws SQLException {
		ItemManager im = setUp();
		im.crud.create(TEST_SKU, TEST_DESCRIPTION, TEST_NET_COST);
		ResultSet rs = im.crud.readBySku(TEST_SKU);

		while (rs.next()) {
			assertEquals(TEST_SKU, rs.getString(COL_NAME_SKU).toString());
			assertEquals(TEST_DESCRIPTION, rs.getString(COL_NAME_DESCRIPTION).toString());
			assertEquals(TEST_NET_COST, rs.getDouble(COL_NAME_NET_COST));
		}


	}
	
	@Test
	void createAndDeleteLineItem() throws SQLException{
		ItemManager im = setUp();
		im.crud.create(TEST_SKU, TEST_DESCRIPTION, TEST_NET_COST);
		im.crud.deleteBySku(TEST_SKU);
		ResultSet rs = im.crud.readBySku(TEST_SKU);
		Boolean hasNoData = !rs.next();
		assertTrue(hasNoData);
	}
	
	
	@Test
	void update() throws SQLException {
		ItemManager im = setUp();
		im.crud.create(TEST_SKU, TEST_DESCRIPTION, TEST_NET_COST);
		im.crud.updateBySku(TEST_SKU, NEW_SKU, NEW_DESCRIPTION, NEW_NET_COST);
		ResultSet rs = im.crud.readBySku(NEW_SKU);

		while (rs.next()) {
			assertEquals(NEW_SKU, rs.getString(COL_NAME_SKU).toString());
			assertEquals(NEW_DESCRIPTION, rs.getString(COL_NAME_DESCRIPTION).toString());
			assertEquals(NEW_NET_COST, rs.getDouble(COL_NAME_NET_COST));
		}

	}
	
	
	private ItemManager setUp() {
		ItemManager im = new ItemManager();
		return im;
	}
}

