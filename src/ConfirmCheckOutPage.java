import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ConfirmCheckOutPage extends JFrame implements ActionListener{

	JPanel northPanel, centerPanel, southPanel;
	JButton checkOutBtn, mainMenuBtn;
	JTable orderTable;
	DefaultTableModel dtm;
	JScrollPane orderSP;
	JLabel pageTitle, yourOrder, totalOrder, pickUpDateLabel;
	JTextField totalField, dateField;
	
	Connect con = new Connect();
	String pickUpDate;
	
	String id;
	String username;
	String role;
	
	public ConfirmCheckOutPage(String id, String username, String role) {
		this.id = id;
		this.username = username;
		this.role = role;
		
		//NORTH PANEL
		northPanel = new JPanel();
		
		pageTitle = new JLabel("CakeLAnd");
		pageTitle.setFont(new Font("Verdana", Font.BOLD, 24));
		pageTitle.setForeground(new Color(130, 70, 77));
		
		northPanel.add(pageTitle);
		northPanel.setBackground(Color.PINK);
		
		//CENTER PANEL
		centerPanel = new JPanel();
		
		yourOrder = new JLabel("Your Order");
		yourOrder.setFont(new Font("Verdana", Font.BOLD, 24));
		yourOrder.setForeground(new Color(27, 44, 99));
		
		centerPanel.add(yourOrder);
		centerPanel.setBackground(Color.PINK);
		
		//SOUTH PANEL
		southPanel = new JPanel(new GridBagLayout());
		
		orderTable = new JTable() {
			 @Override
	            public boolean isCellEditable(int row, int column) {
	                return false;
	            }
		};
		
		String header[] = {"Cake Name", "Cake Shape", "Cake Size", "Cake Price", "Quantity"};
		
		dtm = new DefaultTableModel(header, 0);
		orderTable.setModel(dtm);
		
		//Load Data
		String query = "SELECT CakeName, CakeShape, CakeSize, CakePrice, Quantity FROM cart JOIN cake ON cart.CakeID = cake.CakeID JOIN user ON user.UserID = cart.UserID WHERE user.UserID = '"+id+"'";
		
		ResultSet rs = con.executeQuery(query);
		
		try {
			while(rs.next()) {
				String name = rs.getString("CakeName"); 
				String shape = rs.getString("CakeShape");
				String size = rs.getString("CakeSize");
				int price = rs.getInt("CakePrice");
				int quantity = rs.getInt("Quantity");

				Vector<Object> data = new Vector<>();
				data.add(name);
				data.add(shape);
				data.add(size);
				data.add(price);
				data.add(quantity);
				
				dtm.addRow(data);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		orderSP = new JScrollPane(orderTable);
		orderSP.setPreferredSize(new Dimension(450, 200));

		orderSP.setBackground(Color.PINK);
		
		orderTable.setBackground(new Color(255, 224, 229));
		orderTable.getTableHeader().setBackground(new Color(250, 177, 202));
		orderTable.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 12));
		orderTable.getTableHeader().setForeground(new Color(152, 84, 98));
		orderTable.setForeground(new Color(179, 124, 134));
		orderTable.setFont(new Font("Verdana", Font.BOLD, 10));
		
		totalOrder = new JLabel("Total");
		totalOrder.setForeground(new Color(14, 34, 92));
	    totalOrder.setFont(new Font("Verdana", Font.BOLD, 12));
	        
		totalField = new JTextField(8);
		totalField.setEditable(false);
		totalField.setText("Rp " + totalPrice());
		totalField.setBackground(new Color(255, 217, 229));
		totalField.setForeground(new Color(14, 34, 92));
		totalField.setFont(new Font("Verdana", Font.BOLD, 12));
		
		pickUpDateLabel = new JLabel("Pick Up Date");
		pickUpDateLabel.setForeground(new Color(14, 34, 92));
		pickUpDateLabel.setFont(new Font("Verdana", Font.BOLD, 12)); 
		
		dateField = new JTextField(8);
		dateField.setEditable(false);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 3);
		pickUpDate = dateFormat.format(cal.getTime());
		
		dateField.setText(pickUpDate);
		dateField.setBackground(new Color(255, 217, 229));
		dateField.setForeground(new Color(14, 34, 92));
		dateField.setFont(new Font("Verdana", Font.BOLD, 12));

		checkOutBtn = new JButton("Check Out");
		checkOutBtn.setBackground(new Color(255, 217, 229));
		checkOutBtn.setForeground(new Color(14, 34, 92));
		checkOutBtn.setFont(new Font("Verdana", Font.BOLD, 14));
		checkOutBtn.addActionListener(this);
		
		mainMenuBtn = new JButton("Back to Main Menu");
		mainMenuBtn.setBackground(new Color(255, 217, 229));
		mainMenuBtn.setForeground(new Color(14, 34, 92));
		mainMenuBtn.setFont(new Font("Verdana", Font.BOLD, 14));
		mainMenuBtn.addActionListener(this);
		
		checkOutBtn.setBorder(new EmptyBorder(5, 10, 5, 10));
        mainMenuBtn.setBorder(new EmptyBorder(5, 10, 5, 10));
        
		GridBagConstraints c = new GridBagConstraints();

		c.insets = new Insets(8, 10, 10, 10);

		c.anchor = GridBagConstraints.WEST;

		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 4;
		southPanel.add(orderSP, c);

		c.gridx = 1;
		c.gridheight = 1;
		southPanel.add(totalOrder, c);

		c.gridx = 2;
		southPanel.add(totalField, c);

		c.gridx = 1;
		c.gridy = 1;
		southPanel.add(pickUpDateLabel, c);
		
		c.gridx = 2;
		southPanel.add(dateField, c);
	
		c.anchor = GridBagConstraints.CENTER;

		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 2;
		c.ipadx = 20;
		c.ipady = 10;
		southPanel.add(checkOutBtn, c);
		
		c.gridy = 3;
		c.ipadx = 10;
		c.ipady = 10;
		southPanel.add(mainMenuBtn, c);
		
		southPanel.setBackground(Color.PINK);
		
		//Main Frame
		this.add(northPanel, BorderLayout.NORTH);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(southPanel, BorderLayout.SOUTH);
		
		init();
	}

	void init() {
		this.setTitle("CakeLAnd");
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	String totalPrice() {
		int price, total = 0;
		String totalPrice;
		for (int i = 0; i < orderTable.getRowCount(); i++) {
			price = (int)dtm.getValueAt(i, 3) * (int)dtm.getValueAt(i, 4);
			
			total = total + price;
		}
		totalPrice = Integer.toString(total);
		return totalPrice;
	}
	
	public static void main(String[] args) {
		new ConfirmCheckOutPage("test", "test", "User");
	}
	
	void deleteConfirmCart(String userId) {
		PreparedStatement preparedStatement;
		String query = "DELETE FROM cart WHERE UserID = ?";
		
		try {
			preparedStatement = con.connection.prepareStatement(query);

			preparedStatement.setString(1, userId);
			
			preparedStatement.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == checkOutBtn) {
			
			Object header[] = {"Cake ID", "Quantity"};
			Object transDetail[][] = {};
			
			DefaultTableModel dtm3 = new DefaultTableModel(transDetail, header);
			
			String userId = id;
			
			Random rand = new Random();
			String transactionId = "T" + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10);
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String transDate = dateFormat.format(new Date());
			
			String cakeId = "";
			int quantity = 0;
			
			String queryIdTrans = "SELECT * FROM transactionheader WHERE TransactionID = '"+transactionId+"'";
			ResultSet rs = con.executeQuery(queryIdTrans);
			
			try {
				while (rs.next()) {
					if (rs.getString("TransactionID").equals(transactionId)) {
						transactionId = "T" + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10);
						rs.beforeFirst();
					}
				}
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}

			try {
				String queryInsertTrans = "INSERT INTO transactionheader VALUES('"+transactionId+"', '"+userId+"', '"+transDate+"', '"+pickUpDate+"')";
				con.executeUpdate(queryInsertTrans);
				
			} catch (Exception e2) {
				// TODO: handle exception
			}

			try {
				String query = "SELECT cr.CakeID, cr.Quantity FROM cart cr JOIN user u ON u.UserID = cr.UserID JOIN transactionheader th ON th.UserID = u.UserID WHERE th.TransactionID = '"+transactionId+"' AND th.UserID = '"+id+"'";
				ResultSet rsVal = con.executeQuery(query);
				
				while(rsVal.next()) {
					cakeId = rsVal.getString("CakeID");
					quantity = rsVal.getInt("Quantity");
					dtm3.addRow(new Object[] 
							{cakeId, quantity}
					);
				}
			
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			try {
				for (int i = 0; i < dtm3.getRowCount(); i++) {
					String query = "INSERT INTO transactiondetail VALUES('"+transactionId+"', '"+dtm3.getValueAt(i, 0)+"', '"+dtm3.getValueAt(i, 1)+"')";
					con.executeUpdate(query);
					
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
			deleteConfirmCart(userId);
			totalField.setText("Rp 0");
			dtm.setRowCount(0);
			
			UIManager.put("OptionPane.background",new Color(255, 217, 229));
			UIManager.put("Panel.background",new Color(255, 217, 229));
			JOptionPane.showMessageDialog(this, "Transaction Successfull! Remember to pick up your order! :)", "Success", JOptionPane.INFORMATION_MESSAGE);

			new MainForm(id, username, role);
			this.dispose();
		}
		
		if (e.getSource() == mainMenuBtn) {
			
			new MainForm(id, username, role);
			this.dispose();
		}
	}

}
