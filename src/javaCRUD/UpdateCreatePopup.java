package javaCRUD;

import javax.swing.*;

public class UpdateCreatePopup {
	private String originalSku;
	private String sku = "";
	private String description = "";
	private String netCostAsString = "";
	private Double netCost = null;
	private CRUDOperations crud;
	
	public UpdateCreatePopup(CRUDOperations crud, String sku, String description, String netCostAsString) {
		this.crud = crud;
		this.sku = sku;
		this.originalSku = sku;
		this.description = description;
		this.netCostAsString = netCostAsString;
	}
	
	public UpdateCreatePopup(CRUDOperations crud) {
		this.crud = crud;
	}
	
	public void update() {
		updateCreate("Update");
	}
	
	public void create() {
		updateCreate("Create");
	}
	
	public void updateCreate(String action) {
		//Create Option Pane
		JTextField skuField = new JTextField(sku);
		JTextField descriptionField = new JTextField(description);
		JTextField netCostField = new JTextField(netCostAsString);
		Object[] message = {
			"SKU (Text):", skuField,
			"Description (Text):", descriptionField,
			"NetCost (Decimal):", netCostField,
		};

		int option = JOptionPane.showConfirmDialog(null, message, "Create Item", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			// Text field inputs
			Boolean priceFormatError = false; 
			sku = skuField.getText();
			description = descriptionField.getText();
			try {
				netCost = Double.parseDouble(netCostField.getText());
			} catch (NumberFormatException e1) {
				priceFormatError = true;
			}
			
			// Update database and redraw table if inputs are valid
		    if (!skuField.getText().equals("") && !descriptionField.getText().equals("") && priceFormatError == false) {
		    	switch(action) {
		    	case "Update":
		    		crud.updateBySku(originalSku, sku, description, netCost);
		    		break;
		    	case "Create":
		    		crud.create(sku, description, netCost);
		    		break;
		    	}
		    	
		    } else {
		    	JOptionPane.showMessageDialog(null, "Input Error - Action Stopped");
		    }
		}
	}
}
