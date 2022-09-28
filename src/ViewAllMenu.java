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
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ViewAllMenu implements ActionListener {

	JButton Back, add, view;
	JLabel viewcake, viewlist, qty;
	JTable list;
	JScrollPane sp;
	JSpinner quant;
	JFrame frame;
	JPanel bacbut, mid, table, sppan;
	
	String id;
	String username;
	String role;

	Connect con = new Connect();
	
	public ViewAllMenu(String id, String username, String role) {
		this.id = id;
		this.username = username;
		this.role = role;
		
		frame = new JFrame();

		Back = new JButton("Back to Main Menu");
		Back.setForeground(new Color(43, 55, 108));
		Back.setBackground(new Color(245, 198, 211));
		Back.addActionListener(this);

		viewcake = new JLabel("CakeLAnd");
		viewcake.setFont(new Font("Verdana", Font.BOLD, 25));
		viewcake.setForeground(new Color(129, 69, 77));
		viewcake.setHorizontalAlignment(JLabel.CENTER);
		viewcake.setBorder(new EmptyBorder(5, 0, 5, 0));

		viewlist = new JLabel("Cake List");
		viewlist.setFont(new Font("Verdana", Font.BOLD, 25));
		viewlist.setForeground(new Color(43, 55, 108));
		viewlist.setHorizontalAlignment(JLabel.CENTER);
		viewlist.setBorder(new EmptyBorder(5, 0, 5, 0));

		qty = new JLabel("Quantity");
		qty.setFont(new Font("Verdana", Font.BOLD, 16));
		qty.setForeground(new Color(43, 55, 108));

		quant = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		quant.setPreferredSize(new Dimension(120, 30));
		quant.getEditor().getComponent(0).setBackground(new Color(255, 217, 229));
		quant.getEditor().getComponent(0).setForeground(new Color(14, 34, 92));
		quant.getEditor().getComponent(0).setFont(new Font("Verdana", Font.BOLD, 12));

		bacbut = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bacbut.add(Back);
		bacbut.setBackground(Color.pink);
		bacbut.setBorder(new EmptyBorder(18, 0, 0, 42));

		mid = new JPanel();
		mid.setLayout(new GridLayout(2, 1));
		mid.setBackground(Color.pink);

		mid.add(viewcake);
		mid.add(viewlist);

		frame.add(mid, BorderLayout.CENTER);
		frame.setBackground(Color.pink);

		table = new JPanel(new GridLayout(1, 2));
		table.setBackground(Color.pink);

		sppan = new JPanel(new GridBagLayout());
		sppan.setBackground(Color.pink);

		list = new JTable() {
			@Override
			public boolean isCellEditable(int row, int coliumn) {
				return false;
			}
		};

		String[] header = { "Cake Name", "Cake Price", "Cake Shape", "Cake Size" };
		

		list.setBackground(new Color(255, 224, 229));
		list.getTableHeader().setBackground(new Color(250, 177, 202));
		list.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 15));
		list.getTableHeader().setForeground(new Color(152, 84, 98));
		list.setForeground(new Color(179, 124, 134));
		list.setFont(new Font("Verdana", Font.BOLD, 11));
		list.setRowHeight(20);
		
		DefaultTableModel dtm = new DefaultTableModel(header, 0);
		list.setModel(dtm);
		
		//Load Data
		String query = "SELECT CakeName, CakePrice, CakeShape, CakeSize FROM cake";

		ResultSet rs = con.executeQuery(query);
		
		try {
			while(rs.next()) {
				String name = rs.getString("CakeName"); 
				int price = rs.getInt("CakePrice");
				String shape = rs.getString("CakeShape");
				String size = rs.getString("CakeSize");
			
				Vector<Object> data = new Vector<>();
				data.add(name);
				data.add(price);
				data.add(shape);
				data.add(size);
				
				dtm.addRow(data);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		add = new JButton("Add To Cart");
		add.setForeground(new Color(43, 55, 108));
		add.setBackground(new Color(245, 198, 211));
		add.setPreferredSize(new Dimension(160, 50));
		add.setFont(new Font("Verdana", Font.BOLD, 16));
		add.setBorder(new EmptyBorder(15, 1, 15, 1));
		add.addActionListener(this);

		view = new JButton("View Cart");
		view.setForeground(new Color(43, 55, 108));
		view.setBackground(new Color(245, 198, 211));
		view.setPreferredSize(new Dimension(160, 50));
		view.setFont(new Font("Verdana", Font.BOLD, 16));
		view.setBorder(new EmptyBorder(15, 1, 15, 1));
		view.addActionListener(this);

		sp = new JScrollPane(list);
		sp.setPreferredSize(new Dimension(400, 230));
		sp.setBorder(new EmptyBorder(22, 22, 22, 22));
		sp.setBackground(Color.pink);

		GridBagConstraints cing = new GridBagConstraints();
		cing.insets = new Insets(10, 10, 10, 0);

		cing.gridx = 1;
		cing.gridy = 0;
		sppan.add(qty, cing);

		cing.gridx = 2;
		cing.gridy = 0;
		sppan.add(quant, cing);

		cing.anchor = GridBagConstraints.CENTER;

		cing.insets = new Insets(10, 20, 10, 20);
		cing.gridx = 1;
		cing.gridy = 1;
		cing.gridwidth = 2;

		sppan.add(add, cing);

		cing.gridx = 1;
		cing.gridy = 2;
		cing.gridwidth = 2;

		sppan.add(view, cing);

		table.add(sp);
		table.add(sppan);
		frame.add(table, BorderLayout.SOUTH);
		frame.add(bacbut, BorderLayout.NORTH);

		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("CakeLAnd");
		frame.pack();
		frame.setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		new ViewAllMenu("test", "test", "User");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == add) {
			int r = list.getSelectedRow();
			if (r < 0) {
				UIManager.put("OptionPane.background", new Color(255, 217, 229));
				UIManager.put("Panel.background", new Color(255, 217, 229));
				JOptionPane.showMessageDialog(frame, "Select Cake To add!", "Error", 0);
			}
			else {
				String cakeId = "";
				String cakeName = list.getValueAt(r, 0).toString();
				String userId = id;
				int quantity = (int)quant.getValue();
				
				try {
					String query = "SELECT CakeID FROM cake WHERE CakeName = '"+cakeName+"'";
					
					ResultSet rs = con.executeQuery(query);
					
					while(rs.next()) {
						cakeId = rs.getString("CakeID");
					}
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				

				
				
				try {
					String queryVal = "SELECT * FROM cart JOIN user ON user.UserID = cart.UserID JOIN cake ON cake.CakeID = cart.CakeID "
							+ "WHERE cart.UserID = '"+userId+"' AND cart.CakeID = '"+cakeId+"' ";
					
					ResultSet rsVal = con.executeQuery(queryVal);
					
					if (rsVal.next()) {
						JOptionPane.showMessageDialog(frame, "This cake is already in your cart! If you want to change the quantity, do it on the update page!.", "Error", 0);
					}
					else {
						String queryIns = "INSERT INTO cart VALUES ('"+userId+"', '"+cakeId+"', '"+quantity+"')";
						con.executeUpdate(queryIns);
						
						JOptionPane.showMessageDialog(frame, "Cake Successfully added to Cart!", "Success", JOptionPane.INFORMATION_MESSAGE);
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		}
		if (e.getSource() == Back) {
			new MainForm(id, username, role);
			frame.dispose();
		
		}
		
		if (e.getSource() == view) {
			
			frame.dispose();
			new ManageCartPage(id, username, role);
		}

	}

}
