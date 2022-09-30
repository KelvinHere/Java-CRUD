package javaCRUD;

import java.awt.*;
import javax.swing.*;

public class GUI {
	final static int FRAME_WIDTH = 1280;
	final static int FRAME_HEIGHT = 720;
	final static Object[] TABLE_HEADERS = {"SKU", "Description", "Net Cost"};
	final static Object[][] TABLE_DATA = {};
	JFrame frame;
	
	public GUI() {
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
        JButton createButton = new JButton("Create");
        buttonsPanel.add(createButton);
        JButton readButton = new JButton("Read");
        buttonsPanel.add(readButton);
        JButton updateButton = new JButton("Update");
        buttonsPanel.add(updateButton);
        JButton deleteButton = new JButton("Delete");
        buttonsPanel.add(deleteButton);
        JButton importButton = new JButton("Import Items");
        buttonsPanel.add(importButton);
        frame.setVisible(true);
	}
}
