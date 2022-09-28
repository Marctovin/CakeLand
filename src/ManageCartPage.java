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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ManageCartPage extends JFrame implements ActionListener, MouseListener{
	
	JPanel northPanel, centerPanel, southPanel;
	JButton mainMenuBtn, removeBtn, updateBtn, checkOutBtn, viewAllBtn;
	JLabel pageTitle, yourCart, quantityLable;
	JTable cartTable;
	DefaultTableModel dtm;
	JScrollPane cartSP;
	JSpinner quantitySpin;
	
	Connect con = new Connect();
	
	String id;
	String username;
	String role;
	
	public ManageCartPage(String id, String username, String role) {
		this.id = id;
		this.username = username;
		this.role = role;
		
		//NORTH PANEL
		northPanel = new JPanel();
		
		northPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		mainMenuBtn = new JButton("Back to Main Menu");
		mainMenuBtn.setBackground(new Color(255, 224, 229));
		mainMenuBtn.setForeground(new Color(94, 96, 135));
		mainMenuBtn.addActionListener(this);
		
		northPanel.add(mainMenuBtn);
		northPanel.setBackground(Color.PINK);
		
		//CENTER PANEL
		centerPanel = new JPanel(new GridLayout(2, 1));
		
		pageTitle = new JLabel("CakeLAnd");
		pageTitle.setFont(new Font("Verdana", Font.BOLD, 24));
		pageTitle.setForeground(new Color(130, 70, 77));
		
		yourCart = new JLabel("Your Cart");
		yourCart.setFont(new Font("Verdana", Font.BOLD, 24));
		yourCart.setForeground(new Color(27, 44, 99));
		
		pageTitle.setHorizontalAlignment(JLabel.CENTER);
		yourCart.setHorizontalAlignment(JLabel.CENTER);
		
		centerPanel.add(pageTitle);
		centerPanel.add(yourCart);
		centerPanel.setBackground(Color.PINK);
		
		//SOUTH PANEL
		southPanel = new JPanel(new GridBagLayout());
		
		cartTable = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        String header[] = {"Cake Name", "Cake Shape", "Cake Size", "Cake Price", "Quantity"};
 
        dtm = new DefaultTableModel(header, 0);
        
        cartTable.setModel(dtm);
        
        
        //Load Data
        String query = "SELECT CakeName, CakeShape, CakeSize, CakePrice, Quantity FROM cart JOIN cake ON cart.CakeID = cake.CakeID JOIN user ON user.UserID = cart.UserID WHERE user.UserID = '"+id+"'";
        
        ResultSet rs = con.executeQuery(query);
        
        try {
			while(rs.next()) {
				String name = rs.getString("CakeName"); // 1
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
			e.printStackTrace();
		}
        
        cartSP = new JScrollPane(cartTable); 
		cartSP.setPreferredSize(new Dimension(450, 170));
		
		cartSP.setBackground(Color.PINK);
		
		cartTable.setBackground(new Color(255, 224, 229));
		cartTable.getTableHeader().setBackground(new Color(250, 177, 202));
		cartTable.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 12));
		cartTable.getTableHeader().setForeground(new Color(152, 84, 98));
		cartTable.setForeground(new Color(179, 124, 134));
		cartTable.setFont(new Font("Verdana", Font.BOLD, 10));
		
        quantityLable = new JLabel("Quantity");
        quantitySpin = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        
        quantitySpin.setPreferredSize(new Dimension(120,20));
        
        removeBtn = new JButton("Remove From Cart");
        removeBtn.addActionListener(this);
        
        updateBtn = new JButton("Update Cake Order");
        updateBtn.addActionListener(this);
        
        checkOutBtn = new JButton("Check Out");
        checkOutBtn.addActionListener(this);
        
        viewAllBtn = new JButton("View All Menu");
        viewAllBtn.addActionListener(this);
        
        removeBtn.setBorder(new EmptyBorder(5, 5, 5, 5));
        updateBtn.setBorder(new EmptyBorder(5, 5, 5, 5));
        checkOutBtn.setBorder(new EmptyBorder(5, 5, 5, 5));
        viewAllBtn.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(10, 20, 8, 20);

        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 5;
        southPanel.add(cartSP, c);
        
        c.insets = new Insets(5, 5, 5, 5);
 
        c.gridx = 1;
        c.gridheight = 1;
        southPanel.add(quantityLable, c);

        c.gridx = 2;
        southPanel.add(quantitySpin, c);
        
        c.insets = new Insets(5, 5, 5, 5);
        
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        southPanel.add(removeBtn, c);
        
        c.gridy = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        southPanel.add(updateBtn, c);
        
        c.gridy = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        southPanel.add(checkOutBtn, c);
        
        c.gridy = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        southPanel.add(viewAllBtn, c);

        quantityLable.setForeground(new Color(14, 34, 92));
        quantityLable.setFont(new Font("Verdana", Font.BOLD, 12));
        
        quantitySpin.getEditor().getComponent(0).setBackground(new Color(255, 217, 229));
        quantitySpin.getEditor().getComponent(0).setForeground(new Color(14, 34, 92));
        quantitySpin.getEditor().getComponent(0).setFont(new Font("Verdana", Font.BOLD, 12));
        
        removeBtn.setBackground(new Color(255, 217, 229));
		removeBtn.setForeground(new Color(14, 34, 92));
		removeBtn.setFont(new Font("Verdana", Font.BOLD, 14));
        
		updateBtn.setBackground(new Color(255, 217, 229));
		updateBtn.setForeground(new Color(14, 34, 92));
		updateBtn.setFont(new Font("Verdana", Font.BOLD, 14));
		
		checkOutBtn.setBackground(new Color(255, 217, 229));
		checkOutBtn.setForeground(new Color(14, 34, 92));
		checkOutBtn.setFont(new Font("Verdana", Font.BOLD, 14));
	
		viewAllBtn.setBackground(new Color(255, 217, 229));
		viewAllBtn.setForeground(new Color(14, 34, 92));
		viewAllBtn.setFont(new Font("Verdana", Font.BOLD, 14));

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

	public static void main(String[] args) {
		new ManageCartPage("test", "test", "User");
	}

	void deleteFromCart(String userId, String cakeId) {
		PreparedStatement preparedStatement;
		String query = "DELETE FROM cart WHERE UserID = ? AND CakeID = ?";
		
		try {
			preparedStatement = con.connection.prepareStatement(query);

			preparedStatement.setString(1, userId);
			preparedStatement.setString(2, cakeId);
			
			preparedStatement.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void updateFromCart(String userId, String cakeId, int quantity) {
		PreparedStatement preparedStatement;
		String query = "UPDATE cart SET quantity = ? WHERE UserID = ? AND CakeID = ?";
		
		try {
			preparedStatement = con.connection.prepareStatement(query);
			
			preparedStatement.setInt(1, quantity);
			preparedStatement.setString(2, userId);
			preparedStatement.setString(3, cakeId);

			preparedStatement.execute();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == removeBtn) {
			int selectedRow = cartTable.getSelectedRow();
			if (selectedRow < 0) {
				UIManager.put("OptionPane.background",new Color(255, 217, 229));
				UIManager.put("Panel.background",new Color(255, 217, 229));
			
				JOptionPane.showMessageDialog(this, "Select cake you want to cancel!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else {
				String userId = id;
				String cakeId = "";
				String cakeName = cartTable.getValueAt(selectedRow, 0).toString();
				
				String query = "SELECT CakeID FROM cake WHERE CakeName = '"+cakeName+"'";
				
				ResultSet rs = con.executeQuery(query);
				
				try {
					while(rs.next()) {
						cakeId = rs.getString("CakeID");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				deleteFromCart(userId, cakeId);
				
				dtm.removeRow(selectedRow);
				UIManager.put("OptionPane.background",new Color(255, 217, 229));
				UIManager.put("Panel.background",new Color(255, 217, 229));
				
				JOptionPane.showMessageDialog(this, "Cake successfully remove from your cart!");
			}
		}
		
		if (e.getSource() == updateBtn) {
			int selectedRow = cartTable.getSelectedRow();
			if (selectedRow < 0) {

				UIManager.put("OptionPane.background",new Color(255, 217, 229));
				UIManager.put("Panel.background",new Color(255, 217, 229));
				
				JOptionPane.showMessageDialog(this, "Select cake you want to cancel!", "Error", JOptionPane.ERROR_MESSAGE);
				
			}
			else {
				String userId = id;
				String cakeId = "";
				String cakeName = cartTable.getValueAt(selectedRow, 0).toString();
				int quantity = (int) quantitySpin.getValue();
				
				String query = "SELECT CakeID FROM cake WHERE CakeName = '"+cakeName+"'";
				
				ResultSet rs = con.executeQuery(query);
				
				try {
					while(rs.next()) {
						cakeId = rs.getString("CakeID");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				updateFromCart(userId, cakeId, quantity);
				
				dtm.setValueAt(quantitySpin.getValue(), selectedRow, 4);
				
				UIManager.put("OptionPane.background",new Color(255, 217, 229));
				UIManager.put("Panel.background",new Color(255, 217, 229));
				
				JOptionPane.showMessageDialog(this, "Cake Successfully Updated!");
			}
		}
		
		if (e.getSource() == checkOutBtn) {
			int row = dtm.getRowCount();
			if (row < 1) {
				UIManager.put("OptionPane.background",new Color(255, 217, 229));
				UIManager.put("Panel.background",new Color(255, 217, 229));
				
				JOptionPane.showMessageDialog(this, "Your cart is empty!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else {
				this.dispose();
				new ConfirmCheckOutPage(id, username, role);
			}
		}
		
		if (e.getSource() == viewAllBtn) {
			this.dispose();
			new ViewAllMenu(id, username, role);
		}
		
		if (e.getSource() == mainMenuBtn) {
			this.dispose();
			new MainForm(id, username, role);
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
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
