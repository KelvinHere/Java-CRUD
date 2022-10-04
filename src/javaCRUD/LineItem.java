package javaCRUD;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LineItem {
	private String sku;
	private String description;
	private Double netCost;
	
	
	public LineItem(String sku, String description, Double netCost) {
		this.sku = sku;
		this.description = description;
		this.netCost = netCost;
	}
	
	
	public LineItem(ResultSet rs) {
		try {
			if (rs.next()) {
				this.sku = rs.getString("SKU");
				this.description = rs.getString("Description");
				this.netCost = rs.getDouble("Net_Cost");
			} else {
				System.out.println("No result (LineItemObject)");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public LineItem() {
		
	}
	
	
	public String getSku() {
		return sku;
	}
	
	
	public void setSku(String sku) {
		this.sku = sku;
	}
	
	
	public String getDescription() {
		return description;
	}
	
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public Double getNetCost() {
		return netCost;
	}
	
	
	public void setNetCost(Double netCost) {
		this.netCost = netCost;
	}
	
	
	public String getNetCostString() {
		return this.netCost.toString();
	}
	
	public void setNetCostFromString(String netCostString) {
		this.netCost = Double.parseDouble(netCostString);
	}
	
	public void printLineItem( ) {
		System.out.println("SKU: " + this.sku + " | Description: " + this.description + " | NetCost: " + this.netCost);
	}
	
}
