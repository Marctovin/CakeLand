import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class TransactionForm extends JFrame implements ActionListener, MouseListener{

	JPanel northPanel, centerPanel, southPanel, selectedIDPanel, totalPanel;
	JLabel cakeLand, transactionHistory, selectedID, total;
	JTable headerTransactionTable, detailTransactionTable;
	DefaultTableModel dtm1, dtm2;
	JScrollPane headerSP, detailSP;
	JTextField selectedIDField, totalField;
	JButton backToMenu;
	
	Connect con = new Connect();
	
	String id;
	String username;
	String role;
	
	public TransactionForm(String id, String username, String role) {
		this.id = id;
		this.username = username;
		this.role = role;
		
		//NORTH PANEL
		northPanel = new JPanel(new GridLayout(2, 1));
		northPanel.setBorder(new EmptyBorder(20, 1, 1, 1));
		
		cakeLand = new JLabel("CakeLAnd");
		cakeLand.setFont(new Font("Verdana", Font.BOLD, 24));
		cakeLand.setForeground(new Color(130, 70, 77));
		
		transactionHistory = new JLabel("Transaction History");
		transactionHistory.setFont(new Font("Verdana", Font.BOLD, 24));
		transactionHistory.setForeground(new Color(27, 44, 99));
		
		cakeLand.setHorizontalAlignment(JLabel.CENTER);
		transactionHistory.setHorizontalAlignment(JLabel.CENTER);
		
		northPanel.add(cakeLand);
		northPanel.add(transactionHistory);
		northPanel.setBackground(Color.pink);
		
		//CENTER PANEL
		centerPanel = new JPanel(new GridBagLayout());
		centerPanel.setBackground(Color.PINK);
		
		headerTransactionTable = new JTable() {
			 @Override
	            public boolean isCellEditable(int row, int column) {
	                return false;
	            }
		};
		
		String header1[] = {"Transaction ID", "Transaction Date", "Pick Up Date"};

		dtm1 = new DefaultTableModel(header1, 0);
		headerTransactionTable.setModel(dtm1);
		
		//Load Data
		String query = "SELECT TransactionID, TransactionDate, PickUpDate FROM User u JOIN TransactionHeader th ON u.UserID = th.UserID WHERE th.UserID = '"+id+"'";
		
		ResultSet rs = con.executeQuery(query);
		
		try {
			while(rs.next()) {
				String transactionId = rs.getString("TransactionID");
				Date transactionDate = rs.getDate("TransactionDate");
				Date pickUpDate = rs.getDate("PickUpDate");
				
				Vector<Object> data = new Vector<>();
				data.add(transactionId);
				data.add(transactionDate);
				data.add(pickUpDate);
				
				dtm1.addRow(data);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		headerSP = new JScrollPane(headerTransactionTable);
		headerSP.setPreferredSize(new Dimension(500, 150));
		headerSP.setBackground(Color.pink);
		
		headerTransactionTable.setBackground(new Color(255, 224, 229));
		headerTransactionTable.getTableHeader().setBackground(new Color(250, 177, 202));
		headerTransactionTable.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 12));
		headerTransactionTable.getTableHeader().setForeground(new Color(152, 84, 98));
		headerTransactionTable.setForeground(new Color(179, 124, 134));
		headerTransactionTable.setFont(new Font("Verdana", Font.BOLD, 10));
		headerTransactionTable.addMouseListener(this);
		
		selectedID = new JLabel("Selected ID");
		selectedID.setForeground(new Color(14, 34, 92));
	    selectedID.setFont(new Font("Verdana", Font.BOLD, 12));
		
		selectedIDField = new JTextField(7);
		selectedIDField.setEditable(false);
		selectedIDField.setBorder(new EmptyBorder(5, 5, 5, 5));
		selectedIDField.setBackground(new Color(255, 217, 229));
		selectedIDField.setForeground(new Color(14, 34, 92));
		selectedIDField.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		selectedIDPanel = new JPanel();
		selectedIDPanel.setBackground(Color.pink);
		selectedIDPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		selectedIDPanel.add(selectedID);
		selectedIDPanel.add(selectedIDField);
		
		detailTransactionTable = new JTable() {
			 @Override
	            public boolean isCellEditable(int row, int column) {
	                return false;
	            }
		};
		
		String header2[] = {"Cake Name", "Cake Size", "Cake Shape", "Cake Price", "Quantity", "SubTotal"};
		
		dtm2 = new DefaultTableModel(header2, 0);
		detailTransactionTable.setModel(dtm2);
		
		detailSP = new JScrollPane(detailTransactionTable);
		detailSP.setPreferredSize(new Dimension(500, 150));
		detailSP.setBackground(Color.pink);
		
		detailTransactionTable.setBackground(new Color(255, 224, 229));
		detailTransactionTable.getTableHeader().setBackground(new Color(250, 177, 202));
		detailTransactionTable.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 12));
		detailTransactionTable.getTableHeader().setForeground(new Color(152, 84, 98));
		detailTransactionTable.setForeground(new Color(179, 124, 134));
		detailTransactionTable.setFont(new Font("Verdana", Font.BOLD, 10));
		
		total = new JLabel("Total");
		total.setForeground(new Color(14, 34, 92));
	    total.setFont(new Font("Verdana", Font.BOLD, 12));
		
		totalField = new JTextField(10);
		totalField.setEditable(false);
		totalField.setBorder(new EmptyBorder(5, 5, 5, 5));
		totalField.setBackground(new Color(255, 217, 229));
		totalField.setForeground(new Color(14, 34, 92));
		totalField.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		totalPanel = new JPanel();
		totalPanel.setBackground(Color.pink);
		totalPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		totalPanel.add(total);
		totalPanel.add(totalField);
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.WEST;
	    c.insets = new Insets(2, 20, 2, 20);
	    
	    c.gridx = 0;
	    c.gridy = 0;
	    centerPanel.add(headerSP, c);
	    
	    c.gridx = 0;
	    c.gridy = 1;
	    centerPanel.add(selectedIDPanel, c);
	    
	    c.gridx = 0;
	    c.gridy = 2;
	    centerPanel.add(detailSP, c);
	    
	    c.gridx = 0;
	    c.gridy = 3;
	    centerPanel.add(totalPanel, c);
		
		//SOUTH PANEL
		southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		southPanel.setBackground(Color.pink);
		southPanel.setBorder(new EmptyBorder(0, 0, 10, 20));
		
		backToMenu = new JButton("Back to Main Menu");
		backToMenu.setBackground(new Color(255, 224, 229));
		backToMenu.setForeground(new Color(94, 96, 135));
		backToMenu.addActionListener(this);
		
		southPanel.add(backToMenu);
		
		//Main Frame
		this.add(northPanel, BorderLayout.NORTH);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(southPanel, BorderLayout.SOUTH);
		
		
		init();
		
	}

	void init() {
		this.setTitle("CakeLAnd");
		this.pack();
//		this.setSize(800, 700);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new TransactionForm("test", "test", "User");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backToMenu) {
			this.dispose();
			new MainForm(id, username, role);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int selectedRow = headerTransactionTable.getSelectedRow();
		
		if (e.getSource() == headerTransactionTable) {
			if (selectedRow >= 0) {
				int totalSubTotal = 0;
				
				dtm2.setRowCount(0);
				selectedIDField.setText(dtm1.getValueAt(selectedRow, 0).toString());
				
				//Load Data
				
				String transactionId = selectedIDField.getText();
				
				String query2 = "SELECT CakeName, CakeSize, CakeShape, CakePrice, Quantity FROM transactionheader th JOIN transactiondetail td ON th.TransactionID = td.TransactionID JOIN cake c ON c.CakeID = td.CakeID WHERE td.TransactionID = '"+transactionId+"'";

				ResultSet rs = con.executeQuery(query2);
				

				try {
					while(rs.next()) {
						String cakeName = rs.getString("CakeName");
						String cakeSize = rs.getString("CakeSize");
						String cakeShape = rs.getString("CakeShape");
						int cakePrice = rs.getInt("CakePrice");
						int quantity = rs.getInt("Quantity");
						int subTotal = cakePrice*quantity;
						
						Vector<Object> data = new Vector<>();
						data.add(cakeName);
						data.add(cakeSize);
						data.add(cakeShape);
						data.add(cakePrice);
						data.add(quantity);
						data.add(subTotal);
						
						totalSubTotal += subTotal;
						dtm2.addRow(data);
					}
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				totalField.setText("Rp " + totalSubTotal);
			}
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
