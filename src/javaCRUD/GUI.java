package javaCRUD;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GUI {
	final static int FRAME_WIDTH = 1280;
	final static int FRAME_HEIGHT = 720;
	final static Object[] TABLE_HEADERS = {"SKU", "Description", "Net Cost"};
	final static Object[][] TABLE_DATA = {};
	JFrame frame;
	JButton createButton;
	JButton readButton;
	JButton updateButton;
	JButton deleteButton;
	JButton importButton;
	
	DefaultTableModel model = new DefaultTableModel(TABLE_DATA, TABLE_HEADERS);
	JTable dataTable;
	
	Connection conn;
	CRUDOperations crud;
	
	public GUI(Connection conn, CRUDOperations crud) {
		this.conn = conn;
		this.crud = crud;
		
		frame = new JFrame("CRUD");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel);
        
        // Title panel
        JPanel titlePanel = new JPanel(new BorderLayout());
        mainPanel.add(BorderLayout.NORTH, titlePanel);
        JLabel titleLabel = new JLabel("Items Manager", SwingConstants.CENTER);
        titleLabel.setFont(new Font("TimesRoman", Font.BOLD, 35));
        titlePanel.add(BorderLayout.CENTER, titleLabel);
        
        // Display panel
        JPanel dataPanel = new JPanel(new BorderLayout());
        mainPanel.add(BorderLayout.CENTER, dataPanel);
        dataTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(dataTable);
        dataPanel.add(scrollPane);
        
        // CRUD buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        mainPanel.add(BorderLayout.SOUTH, buttonsPanel);
        createButton = new JButton("Create");
        buttonsPanel.add(createButton);
        readButton = new JButton("Read");
        buttonsPanel.add(readButton);
        updateButton = new JButton("Update");
        buttonsPanel.add(updateButton);
        deleteButton = new JButton("Delete");
        buttonsPanel.add(deleteButton);
        importButton = new JButton("Import Items");
        buttonsPanel.add(importButton);
        
        setListeners();
        
        frame.setVisible(true);
	}
	
	private void setListeners()  {
		createButton.addActionListener(new CreateButtonListener());
		readButton.addActionListener(new ReadButtonListener());
		updateButton.addActionListener(new UpdateButtonListener());
		deleteButton.addActionListener(new DeleteButtonListener());
		importButton.addActionListener(new ImportButtonListener());
	}
	
	private class CreateButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//Create Option Pane			
			JTextField skuField = new JTextField();
			JTextField descriptionField = new JTextField();
			JTextField netCostField = new JTextField();
			Object[] message = {
				"SKU (Text):", skuField,
				"Description (Text):", descriptionField,
				"NetCost (Decimal):", netCostField,
			};

			int option = JOptionPane.showConfirmDialog(null, message, "Create Item", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				// Text field inputs
				Boolean priceFormatError = false; 
				String sku = skuField.getText();
				String description = descriptionField.getText();
				Double netCost = null;
				try {
					netCost = Double.parseDouble(netCostField.getText());
				} catch (NumberFormatException e1) {
					priceFormatError = true;
				}
				
				// Update database and redraw table if inputs are valid
			    if (!skuField.getText().equals("") && !descriptionField.getText().equals("") && priceFormatError == false) {
			        crud.create(sku, description, netCost);
			    	System.out.println("Created!");
			    	updateDataTable();
			    } else {
			    	JOptionPane.showMessageDialog(null, "Input Error - Action Stopped");
			    }
			}
		}
	}
	
	
	private class ReadButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Read");
		}
	}
	
	
	private class UpdateButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Update");
		}
	}
	
	
	private class DeleteButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Delete");
		}
	}
	
	
	private class ImportButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Import");
			ImportData.createPlaceholders(conn);
			updateDataTable();
		}
	}
	
	
	private void updateDataTable() {
		// Clear table
		model.setRowCount(0);
		
		// Update table
		ResultSet rs = crud.getAllLines();
		
		try {
			while (rs.next()) {
				System.out.println(rs.getString("SKU").toString());
				String sku = rs.getString("SKU");
				String description = rs.getString("Description");
				Double netCost = rs.getDouble("Net_Cost");
				model.addRow(new Object[] {sku, description, netCost});
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
