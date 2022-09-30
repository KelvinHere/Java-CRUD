package javaCRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CRUDOperations {
	Connection conn;
	PreparedStatement ps;
	
	public CRUDOperations(Connection conn) {
		this.conn = conn;
	}

	
	public void insertLineItem(String sku, String description, double netCost) {
		/* INSERT ITEMS */
		try {
			String sql = String.format("INSERT INTO items(SKU, Description, Net_Cost) VALUES(?,?,?)");
			System.out.println(sql);
			this.ps = conn.prepareStatement(sql);
			this.ps.setString(1, sku);
			this.ps.setString(2, description);
			this.ps.setDouble(3, netCost);
			this.ps.execute();
			System.out.println("Inserted");
		}catch (SQLException e) {
			System.out.println(e.toString());
		}
	}
	
		
	public void getRow(String sku) {
		/* GET ITEMS */
		try {
			String sql = String.format("SELECT * FROM items WHERE SKU = '%s';", sku);
			System.out.println(sql + "*get row*");
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				System.out.print(rs.getString(2) + " ");
				System.out.print(rs.getString(4) + " ");
				System.out.print(rs.getString(6) + " ");
				System.out.print(rs.getString(13) + " ");
				
			}
			
		}catch (SQLException e) {
			System.out.println(e.toString() + " runner class*");
		}
		
	}
}
