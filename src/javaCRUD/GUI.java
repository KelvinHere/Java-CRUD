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
	
	private JFrame frame;
	private JButton createButton;
	private JButton readButton;
	private JButton updateButton;
	private JButton deleteButton;
	private JButton importButton;
	
	ImageIcon upIcon = new ImageIcon("images/up-arrow.png");
	ImageIcon downIcon = new ImageIcon("images/down-arrow.png");
	
	private JButton sortSkuAscButton;
	private JButton sortSkuDescButton;
	private JButton sortDescriptionAscButton;
	private JButton sortDescriptionDescButton;
	private JButton sortNetCostAscButton;
	private JButton sortNetCostDescButton;
	
	private DefaultTableModel model = new DefaultTableModel(TABLE_DATA, TABLE_HEADERS);
	private JTable dataTable;
	
	private Connection conn;
	private CRUDOperations crud;
	
	public GUI(Connection conn, CRUDOperations crud) {
		this.conn = conn;
		this.crud = crud;
		
		frame = new JFrame("CRUD");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // * Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel);
        
        // * Title panel
        JPanel titlePanel = new JPanel(new BorderLayout());
        mainPanel.add(BorderLayout.NORTH, titlePanel);
        JLabel titleLabel = new JLabel("Items Manager", SwingConstants.CENTER);
        titleLabel.setFont(new Font("TimesRoman", Font.BOLD, 35));
        titlePanel.add(BorderLayout.CENTER, titleLabel);
        
        // * Display panel
        
        // ** Sort Buttons Panel
        JPanel sortButtonPanel = new JPanel(new GridLayout(1,1));
        mainPanel.add(BorderLayout.NORTH, sortButtonPanel);
        sortSkuAscButton = new JButton(upIcon);
        sortButtonPanel.add(sortSkuAscButton);
        sortSkuDescButton = new JButton(downIcon);
        sortButtonPanel.add(sortSkuDescButton);
        
        sortDescriptionAscButton = new JButton(upIcon);
        sortButtonPanel.add(sortDescriptionAscButton);
        sortDescriptionDescButton = new JButton(downIcon);
        sortButtonPanel.add(sortDescriptionDescButton);
        
        sortNetCostAscButton = new JButton(upIcon);
        sortButtonPanel.add(sortNetCostAscButton);
        sortNetCostDescButton = new JButton(downIcon);
        sortButtonPanel.add(sortNetCostDescButton);
        
        // ** Data Table Panel
        JPanel dataPanel = new JPanel(new BorderLayout());
        mainPanel.add(BorderLayout.CENTER, dataPanel);
        dataTable = new JTable(model);
        dataTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(dataTable);
        dataPanel.add(scrollPane);
        
        // * CRUD buttons panel
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
		sortSkuAscButton.addActionListener(new sortSkuAscListener());
		sortSkuDescButton.addActionListener(new sortSkuDescListener());
		sortDescriptionAscButton.addActionListener(new sortDescriptionAscListener());
		sortDescriptionDescButton.addActionListener(new sortDescriptionDescListener());
		sortNetCostAscButton.addActionListener(new sortNetCostAscListener());
		sortNetCostDescButton.addActionListener(new sortNetCostDescListener());
	}
	
	
	private class sortSkuAscListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			updateDataTable("ORDER BY SKU ASC");
		}
	}
	
	private class sortSkuDescListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			updateDataTable("ORDER BY SKU DESC");
		}
	}
	
	private class sortDescriptionAscListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			updateDataTable("ORDER BY Description ASC");
		}
	}
	
	private class sortDescriptionDescListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			updateDataTable("ORDER BY Description DESC");
		}
	}
	
	private class sortNetCostAscListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			updateDataTable("ORDER BY Net_Cost ASC");
		}
	}
	
	private class sortNetCostDescListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			updateDataTable("ORDER BY Net_Cost DESC");
		}
	}
	
	private class CreateButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			UpdateCreatePopup updateCreatePopup = new UpdateCreatePopup(crud);
			updateCreatePopup.create();
			updateDataTable("");
		}
	}
	
	
	private class UpdateButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!dataTable.getSelectionModel().isSelectionEmpty()) {
				// Get selected sku values from database
				String selectedSku = dataTable.getValueAt(dataTable.getSelectedRow(), 0).toString();
				ResultSet rs = crud.readBySku(selectedSku); 
				LineItem item = new LineItem(rs);
				// Update item through pop-up
				UpdateCreatePopup updateCreatePopup = new UpdateCreatePopup(crud, item);
				updateCreatePopup.update();
				updateDataTable("");
			} else {
				JOptionPane.showMessageDialog(null, "Please select a row first");
			}
		}
	}
	
	
	private class DeleteButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!dataTable.getSelectionModel().isSelectionEmpty()) {
				String selectedSku = dataTable.getValueAt(dataTable.getSelectedRow(), 0).toString();
				crud.deleteBySku(selectedSku);
				updateDataTable("");
			} else {
				JOptionPane.showMessageDialog(null, "Please select a row first");
			}
		}
	}
	
	
	private class ReadButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ReadPopup readPopup = new ReadPopup(crud);
			readPopup.bySku();
		}
	}
	
	
	private class ImportButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ImportData.createPlaceholders(conn);
			updateDataTable("");
		}
	}
	
	
	public void updateDataTable(String order) {
		// Clear table
		model.setRowCount(0);
		// Update table
		ResultSet rs = crud.getAllLines(order);
		try {
			while (rs.next()) {
				model.addRow(new Object[] {rs.getString("SKU"), rs.getString("Description"), rs.getDouble("Net_Cost")});
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public JTable getDataTable() {
		return dataTable;
	}
}
