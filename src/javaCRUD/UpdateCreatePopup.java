package javaCRUD;

import javax.swing.*;

public class UpdateCreatePopup {
	private String originalSku;
	private String sku = "";
	private String description = "";
	private String netCostAsString = "";
	private Double netCost = null;
	private CRUDOperations crud;
	
		JTextField skuField = new JTextField();
	JTextField descriptionField = new JTextField();
	JTextField netCostField = new JTextField();
	Object[] message = {
			"SKU (Text):", skuField,
			"Description (Text):", descriptionField,
			"NetCost (Decimal):", netCostField,
		};
	
	
	public UpdateCreatePopup(CRUDOperations crud, LineItem item) {
		this.crud = crud;
		this.sku = item.getSku();
		this.originalSku = item.getSku();
		this.description = item.getDescription();
		this.netCostAsString = item.getNetCostString();
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
		int option = JOptionPane.showConfirmDialog(null, this.message, action, JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			updateDB(action);
		}
	}
	
	
	public void updateDB(String action) {
		// Retrieve data from options pane text fields
		String errorMessage = "Error in SKU or Description";
		Boolean priceFormatError = false;
		LineItem item = new LineItem();
		item.setSku(skuField.getText());
		item.setDescription(descriptionField.getText());
		try {
			item.setNetCost(Double.parseDouble(netCostField.getText()));
		} catch (NumberFormatException e1) {
			priceFormatError = true;
			errorMessage = "Error Net_Cost needs to be decimal";
		}

		// Update database inputs are valid
	    if (!skuField.getText().equals("") && !descriptionField.getText().equals("") && priceFormatError == false) {
	    	switch(action) {
	    	case "Update":
	    		crud.updateBySku(originalSku, item);
	    		break;
	    	case "Create":
	    		crud.create(item);
	    		break;
	    	}
	    } else {
    		JOptionPane.showMessageDialog(null, "Input Error - " + errorMessage);
	    }
	}
}
