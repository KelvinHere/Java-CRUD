package javaCRUD;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.*;

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
	
	Connection conn;
	
	public GUI(Connection conn) {
		this.conn = conn;
		
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
        JTable dataTable = new JTable(TABLE_DATA, TABLE_HEADERS);
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
			System.out.println("Create");
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
		}
	}
}
