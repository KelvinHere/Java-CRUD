package javaCRUD;

import javax.swing.*;

public class UpdateCreatePopup {
	private String originalSku;
	private String sku = "";
	private String description = "";
	private String netCostAsString = "";
	private Double netCost = null;
	private CRUDOperations crud;
	
	Boolean priceFormatError = false;
	JTextField skuField = new JTextField();
	JTextField descriptionField = new JTextField();
	JTextField netCostField = new JTextField();
	Object[] message = {
			"SKU (Text):", skuField,
			"Description (Text):", descriptionField,
			"NetCost (Decimal):", netCostField,
		};
	
	
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
		displayPopup("Update");
	}
	
	
	public void create() {
		displayPopup("Create");
	}
	
	
	private void displayPopup(String action) {
		//Create OptionPane fields contents and display
		skuField.setText(sku);
		descriptionField.setText(description);
		netCostField.setText(netCostAsString);
		int option = JOptionPane.showConfirmDialog(null, this.message, "Create Item", JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			updateDB(action);
		}
	}
	
	
	public void updateDB(String action) {
		// Retrieve data from options pane text fields
		String errorMessage = "Error in SKU or Description";
		sku = skuField.getText();
		description = descriptionField.getText();
		try {
			netCost = Double.parseDouble(netCostField.getText());
		} catch (NumberFormatException e1) {
			priceFormatError = true;
			errorMessage = "Error Net_Cost needs to be decimal";
		}

		// Update database inputs are valid
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
    		JOptionPane.showMessageDialog(null, "Input Error - " + errorMessage);
	    }
	}
}
