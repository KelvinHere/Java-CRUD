package javaCRUD;

import java.sql.*;

import javax.swing.JOptionPane;

public class CRUDOperations {
	Connection conn;
	PreparedStatement ps;
	
	public CRUDOperations(Connection conn) {
		this.conn = conn;
	}

	
	public void create(String sku, String description, double netCost) {
		/* INSERT ITEMS */
		try {
			String sql = String.format("INSERT INTO items(SKU, Description, Net_Cost) VALUES(?,?,?)");
			this.ps = conn.prepareStatement(sql);
			this.ps.setString(1, sku);
			this.ps.setString(2, description);
			this.ps.setDouble(3, netCost);
			this.ps.execute();
		} catch (SQLIntegrityConstraintViolationException ed) {
			JOptionPane.showMessageDialog(null, "ERROR : Duplicate SKU - Creation aborted");
		} catch (SQLException e) {
			String errorCode = String.valueOf(e.getErrorCode());
			JOptionPane.showMessageDialog(null, "ERROR : Code " + errorCode);
		}
	}
	
		
	public ResultSet readBySku(String sku) {
		ResultSet rs = null;
		try {
			String sql = String.format("SELECT * FROM items WHERE SKU = '%s';", sku);
			PreparedStatement ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
		}catch (SQLException e) {
			System.out.println(e.toString() + " *CRUDOperations.readBySku");
		}
		return rs;
	}
	
	
	public ResultSet getAllLines() {
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM items;";
			PreparedStatement ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
		}catch (SQLException e) {
			System.out.println(e.toString() + " *CRUDOperations.getAllLines");
		}
		return rs;
	}
	
	
	public void deleteBySku(String sku) {
		try {
			String sql = String.format("DELETE FROM items WHERE SKU = '%s';", sku);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		}catch (SQLException e) {
			System.out.println(e.toString() + " *CRUDOperations.deleteBySku");
		}
	}
	
	
	public void updateBySku(String sku, String newSku, String newDescription, Double newNetCost) {
		try {
			String sql = String.format("SELECT * FROM items WHERE SKU = '%s';", sku);
			PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				rs.updateString("SKU", newSku);
				rs.updateString("Description", newDescription);
				rs.updateDouble("Net_Cost", newNetCost);
				rs.updateRow();
			}
		}catch (SQLException e) {
			System.out.println(e.toString() + " *CRUDOperations.updateBySku");
		}
	}
}
