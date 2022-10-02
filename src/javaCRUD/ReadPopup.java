package javaCRUD;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ReadPopup {
	CRUDOperations crud;
	
	public ReadPopup(CRUDOperations crud) {
		this.crud = crud;
	}
	
	public void bySku() {
		JTextField skuField = new JTextField();
		Object[] message = { "SKU (Text):", skuField, };

		int option = JOptionPane.showConfirmDialog(null, message, "Retrieve by SKU", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			String sku = skuField.getText();
			ResultSet rs = crud.readBySku(sku);
			try {
				if (rs.next() == false) {
					JOptionPane.showMessageDialog(null, "Error : SKU not found");
				} else {
					String description = rs.getString("Description");
					Double netCost = rs.getDouble("Net_Cost");
					outputPopup(sku, description, netCost);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Error : Code " + e.getErrorCode());
				e.printStackTrace();
			}
		}
	}
	
	private void outputPopup(String sku, String description, Double netCost) {
		JTextArea resultTextField = new JTextArea(sku + "\n" + description + "\n" + netCost);
		JOptionPane.showMessageDialog(null, resultTextField);
	}
}
