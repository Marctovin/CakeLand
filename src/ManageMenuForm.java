import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class ManageMenuForm extends JFrame implements ActionListener, ItemListener {

	JPanel northPanel, centerPanel, southPanel, panelLabel, panelTF, panelButton, mainMenuBtnPanel;
	JButton mainMenuBtn, removeBtn, addBtn;
	JLabel pageTitle, cakeList, cakeName, cakePrice, shape, oval, rect;
	JTextField cakeNameTF, cakePriceTF;
	JTable table;
	DefaultTableModel dtm;
	JScrollPane sp;
	JComboBox<String> chooseShape, chooseOval, chooseRect;

	Vector<String> vShape = new Vector<>();
	Vector<String> vOval = new Vector<>();
	Vector<String> vRect = new Vector<>();

	String id;
	String username;
	String role;
	
	String newCakeID;
	
	Connect con = new Connect();
	
	public ManageMenuForm(String id, String username, String role) {
		this.id = id;
		this.username = username;
		this.role = role;
		
		// NORTH PANEL
		northPanel = new JPanel(new GridLayout(3, 1));

		mainMenuBtnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		mainMenuBtn = new JButton("Back to Main Menu");
		mainMenuBtn.setBackground(new Color(255, 217, 229));
		mainMenuBtn.setForeground(new Color(14, 34, 92));
		mainMenuBtn.setFont(new Font("Verdana", Font.BOLD, 12));
		mainMenuBtnPanel.setBackground(Color.pink);
		mainMenuBtnPanel.add(mainMenuBtn);
		
		pageTitle = new JLabel("CakeLAnd");
		pageTitle.setFont(new Font("Verdana", Font.BOLD, 24));
		pageTitle.setForeground(new Color(130, 70, 77));

		cakeList = new JLabel("Cake List");
		cakeList.setFont(new Font("Verdana", Font.BOLD, 24));
		cakeList.setForeground(new Color(27, 44, 99));

		mainMenuBtn.setHorizontalAlignment(JButton.RIGHT);
		pageTitle.setHorizontalAlignment(JLabel.CENTER);
		cakeList.setHorizontalAlignment(JLabel.CENTER);

		mainMenuBtn.addActionListener(this);

		northPanel.add(mainMenuBtnPanel);
		northPanel.add(pageTitle);
		northPanel.add(cakeList);
		northPanel.setBackground(Color.PINK);

		// CENTER PANEL
		centerPanel = new JPanel();

		table = new JTable() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		String[] header = {"Cake ID", "Cake Name", "Cake Price", "Cake Shape", "Cake Size" };

		dtm = new DefaultTableModel(header, 0);
		table.setModel(dtm);
		
		//Load Data
		String query = "SELECT CakeID, CakeName, CakePrice, CakeShape, CakeSize FROM cake";
		
		ResultSet rs = con.executeQuery(query);
		
		try {
			while(rs.next()) {
				String cakeId = rs.getString("CakeID");
				String cakeName = rs.getString("CakeName");
				int cakePrice = rs.getInt("CakePrice");
				String cakeShape = rs.getString("CakeShape");
				String cakeSize = rs.getString("CakeSize");
				
				Vector<Object> data = new Vector<>();
				data.add(cakeId);
				data.add(cakeName);
				data.add(cakePrice);
				data.add(cakeShape);
				data.add(cakeSize);
				
				dtm.addRow(data);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sp = new JScrollPane(table);
		sp.setPreferredSize(new Dimension(450, 170));
		table.setBackground(new Color(255, 224, 229));
		sp.setBackground(Color.PINK);
		centerPanel.add(sp);
		centerPanel.setBackground(Color.PINK);

		table.getTableHeader().setBackground(new Color(250, 177, 202));
		table.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 12));
		table.getTableHeader().setForeground(new Color(152, 84, 98));
		table.setForeground(new Color(179, 124, 134));
		table.setFont(new Font("Verdana", Font.BOLD, 11));
		table.setRowHeight(20);
		
		// SOUTH PANEL
		southPanel = new JPanel(new GridLayout(1, 3));

		panelLabel = new JPanel(new GridLayout(5, 1));
		
		cakeName = new JLabel("Cake Name");
		cakeName.setBackground(new Color(255, 217, 229));
		cakeName.setForeground(new Color(14, 34, 92));
		cakeName.setFont(new Font("Verdana", Font.BOLD, 12));
		
		cakePrice = new JLabel("Cake Price");
		cakePrice.setBackground(new Color(255, 217, 229));
		cakePrice.setForeground(new Color(14, 34, 92));
		cakePrice.setFont(new Font("Verdana", Font.BOLD, 12));
		
		shape = new JLabel("Shape");
		shape.setBackground(new Color(255, 217, 229));
		shape.setForeground(new Color(14, 34, 92));
		shape.setFont(new Font("Verdana", Font.BOLD, 12));
		
		oval = new JLabel("Oval Size");
		oval.setBackground(new Color(255, 217, 229));
		oval.setForeground(new Color(14, 34, 92));
		oval.setFont(new Font("Verdana", Font.BOLD, 12));
		
		rect = new JLabel("Rectangle Size");
		rect.setBackground(new Color(255, 217, 229));
		rect.setForeground(new Color(14, 34, 92));
		rect.setFont(new Font("Verdana", Font.BOLD, 12));
		
		panelLabel.add(cakeName);
		panelLabel.add(cakePrice);
		panelLabel.add(shape);
		panelLabel.add(oval);
		panelLabel.add(rect);
		
		southPanel.add(panelLabel);
		
		panelLabel.setBackground(Color.PINK);

		panelTF = new JPanel(new GridLayout(5, 1));
		panelTF.setBorder(new EmptyBorder(1, 1, 1, 1));
		panelTF.setBackground(Color.pink);
		
		cakeNameTF = new JTextField(10);
		cakeNameTF.setBackground(Color.decode("#ffe0e5"));
		cakeNameTF.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		cakePriceTF = new JTextField(10);
		cakePriceTF.setBackground(Color.decode("#ffe0e5"));
		cakePriceTF.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		vShape.add("Oval");
		vShape.add("Rectangle");
		chooseShape = new JComboBox<>(vShape);
		chooseShape.setBackground(Color.decode("#ffe0e5"));
		chooseShape.addItemListener(this);
		
		vOval.add("15 cm");
		vOval.add("20 cm");
		vOval.add("25 cm");
		chooseOval = new JComboBox<>(vOval);
		chooseOval.setBackground(Color.decode("#ffe0e5"));

		vRect.add("10 x 10 cm");
		vRect.add("20 x 20 cm");
		vRect.add("30 x 30 cm");
		chooseRect = new JComboBox<>(vRect);
		chooseRect.setBackground(Color.decode("#ffe0e5"));

		panelTF.add(cakeNameTF);
		panelTF.add(cakePriceTF);
		panelTF.add(chooseShape);
		panelTF.add(chooseOval);
		panelTF.add(chooseRect);
		southPanel.add(panelTF);

		panelButton = new JPanel(new GridLayout(2, 1));
		removeBtn = new JButton("Remove Cake");
		removeBtn.setBackground(new Color(255, 217, 229));
		removeBtn.setForeground(new Color(14, 34, 92));
		removeBtn.setFont(new Font("Verdana", Font.BOLD, 14));
		removeBtn.addActionListener(this);

		addBtn = new JButton("Add Cake");
		addBtn.setBackground(new Color(255, 217, 229));
		addBtn.setForeground(new Color(14, 34, 92));
		addBtn.setFont(new Font("Verdana", Font.BOLD, 14));
		addBtn.addActionListener(this);

		panelButton.add(removeBtn);
		panelButton.add(addBtn);
		southPanel.add(panelButton);

		// Main Frame
		this.add(northPanel, BorderLayout.NORTH);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(southPanel, BorderLayout.SOUTH);

		this.setTitle("CakeLAnd");
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		new ManageMenuForm("test", "test", "User");
	}

	boolean comboBoxValidate() {
		Random rand = new Random();
		String cakeIdFormat;	
		
		if (chooseShape.getSelectedItem().equals("Oval")) {
			
			if (chooseOval.getSelectedItem().equals("15 cm")) {
				cakeIdFormat = "COF";
				
				newCakeID = "C" + "O" + "F" + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10);
				
				validateDuplicateID(cakeIdFormat);
			} 
			else if (chooseOval.getSelectedItem().equals("20 cm")) {
				cakeIdFormat = "CON";
				
				newCakeID = "C" + "O" + "N" + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10);
				
				validateDuplicateID(cakeIdFormat);
			}
			else if (chooseOval.getSelectedItem().equals("25 cm")) {
				cakeIdFormat = "COV";
				
				newCakeID = "C" + "O" + "V" + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10);
				
				validateDuplicateID(cakeIdFormat);
			}
			return true;
			
		} 
		else if (chooseShape.getSelectedItem().equals("Rectangle")) {
			
			if (chooseRect.getSelectedItem().equals("10 x 10 cm")) {
				cakeIdFormat = "CRT";
				
				newCakeID = "C" + "R" + "T" + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10);
				
				validateDuplicateID(cakeIdFormat);
			} 
			else if (chooseRect.getSelectedItem().equals("20 x 20 cm")) {
				cakeIdFormat = "CRW";
				
				newCakeID = "C" + "R" + "W" + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10);
				
				validateDuplicateID(cakeIdFormat);
			} 
			else if (chooseRect.getSelectedItem().equals("30 x 30 cm")) {
				cakeIdFormat = "CRH";
				
				newCakeID = "C" + "R" + "H" + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10);
				
				validateDuplicateID(cakeIdFormat);
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	void validateDuplicateID(String cakeIdFormat) {
		Random rand = new Random();
		
		String query = "SELECT * FROM cake WHERE CakeID = '"+newCakeID+"'";
		
		ResultSet rs = con.executeQuery(query);
		
		try {
			while(rs.next()) {
				if (rs.getString("CakeID").equals(newCakeID)) {
					
					newCakeID = cakeIdFormat + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10);
					
					rs.beforeFirst();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void removeCake(String cakeId) {
		PreparedStatement preparedStatement;
		String query = "DELETE FROM cake WHERE CakeID = ?";
		
		try {
			preparedStatement = con.connection.prepareStatement(query);

			preparedStatement.setString(1, cakeId);
			
			preparedStatement.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == removeBtn) {
			int selectedRow = table.getSelectedRow();
			if (selectedRow < 0) {
				JOptionPane.showMessageDialog(this, "Select cake you want to remove!", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				String cakeId = dtm.getValueAt(selectedRow, 0).toString();
				
				removeCake(cakeId);
				
				dtm.removeRow(selectedRow);
				
				UIManager.put("OptionPane.background",new Color(255, 217, 229));
				UIManager.put("Panel.background",new Color(255, 217, 229));
				JOptionPane.showMessageDialog(this, "Cake Successfully Removed!", "Success", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		if (e.getSource() == addBtn) {
		
			
			if (!cakeNameTF.getText().endsWith(" Cake")) {
				UIManager.put("OptionPane.background",new Color(255, 217, 229));
				UIManager.put("Panel.background",new Color(255, 217, 229));
				JOptionPane.showMessageDialog(this, "Cake Name must ends with Cake");
			} 
			else if (cakePriceTF.getText().isEmpty()) {
				UIManager.put("OptionPane.background",new Color(255, 217, 229));
				UIManager.put("Panel.background",new Color(255, 217, 229));
				JOptionPane.showMessageDialog(this, "Price cannot be empty!");
			} 
			else if (Integer.parseInt(cakePriceTF.getText()) < 100000 || Integer.parseInt(cakePriceTF.getText()) > 500000) {
				UIManager.put("OptionPane.background",new Color(255, 217, 229));
				UIManager.put("Panel.background",new Color(255, 217, 229));
				JOptionPane.showMessageDialog(this, "New Cake Price must between 100000 - 500000");
			}
			else if (comboBoxValidate() == true) {
				try {
					String cakeId = newCakeID;
					String cakeName = cakeNameTF.getText();
					int cakePrice = Integer.parseInt(cakePriceTF.getText());
					String cakeSize = null;
					String cakeShape = chooseShape.getSelectedItem().toString();
					
					if (chooseShape.getSelectedItem().toString().equals("Oval")) {
						cakeSize = chooseOval.getSelectedItem().toString();
					}
					else if (chooseShape.getSelectedItem().toString().equals("Rectangle")) {
						cakeSize = chooseRect.getSelectedItem().toString();
					}
					
					String query = "INSERT INTO cake VALUES('"+cakeId+"', '"+cakeName+"', '"+cakePrice+"', '"+cakeSize+"', '"+cakeShape+"')";
					con.executeUpdate(query);
					
					UIManager.put("OptionPane.background",new Color(255, 217, 229));
					UIManager.put("Panel.background",new Color(255, 217, 229));
					JOptionPane.showMessageDialog(this, "Cake successfully inputed to the database!", "Success", JOptionPane.INFORMATION_MESSAGE);
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}

		}

		if (e.getSource() == mainMenuBtn) {
			this.dispose();
			new MainForm(id, username, role);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getItemSelectable() == chooseShape) {
			
			if (chooseShape.getSelectedItem().toString().equals("Oval")) {
				chooseRect.setEnabled(false);
				chooseOval.setEnabled(true);
			}
			else if (chooseShape.getSelectedItem().toString().equals("Rectangle")) {
				chooseRect.setEnabled(true);
				chooseOval.setEnabled(false);
			}
		}
		
	}

}
