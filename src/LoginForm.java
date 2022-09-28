import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class LoginForm extends JFrame implements ActionListener{
	
	JPanel northPanel, centerPanel, southPanel, buttonPanel;
	JLabel cakeLand, usernameLabel, passwordLabel;
	JTextField usernameField;
	JPasswordField passwordField;
	JButton loginButton, registerButton;
	
	Connect con = new Connect();

	public LoginForm() {
		
		//NORTH PANEL
		northPanel = new JPanel();
		northPanel.setBackground(Color.pink);
		
		JLabel cakeLand = new JLabel("CakeLAnd");
		cakeLand.setFont(new Font("Verdana", Font.BOLD, 24));
		cakeLand.setForeground(new Color(130, 70, 77));
		cakeLand.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		northPanel.add(cakeLand);
		
		//CENTER PANEL
		centerPanel = new JPanel(new GridBagLayout());
		centerPanel.setBackground(Color.pink);
		
		usernameLabel = new JLabel("Username");
		usernameLabel.setFont(new Font("Verdana", Font.BOLD, 12));
		usernameLabel.setForeground(new Color(19, 39, 96));
		
		usernameField = new JTextField(25);
		usernameField.setBackground(Color.pink);
		usernameField.setFont(new Font("Verdana", Font.PLAIN, 12));
		usernameField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(56, 46, 90)));
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Verdana", Font.BOLD, 12));
		passwordLabel.setForeground(new Color(19, 39, 96));
		
		passwordField = new JPasswordField(25);
		passwordField.setBackground(Color.pink);
		passwordField.setFont(new Font("Verdana", Font.PLAIN, 12));
		passwordField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(56, 46, 90)));
		
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.insets = new Insets(20, 10, 20, 10);
		
		c.gridx = 0;
		c.gridy = 0;
		centerPanel.add(usernameLabel, c);

		c.gridy = 1;
		centerPanel.add(usernameField, c);
		
		c.gridy = 2;
		centerPanel.add(passwordLabel, c);
		
		c.gridy = 3;
		centerPanel.add(passwordField, c);
		
		//SOUTH PANEL
		southPanel = new JPanel(new GridLayout(2, 1));
		southPanel.setBackground(Color.pink);
		
		loginButton = new JButton("Login");
		loginButton.setBackground(new Color(255, 217, 229));
		loginButton.setForeground(new Color(14, 34, 92));
		loginButton.setFont(new Font("Verdana", Font.BOLD, 18));
		loginButton.setBorder(new EmptyBorder(10, 20, 10, 20));
		loginButton.addActionListener(this);
		
		registerButton = new JButton("Don't have account? Register");
		registerButton.setBackground(Color.pink);
		registerButton.setForeground(Color.blue);
		registerButton.setFont(new Font("Verdana", Font.ITALIC, 11));
		registerButton.setBorder(BorderFactory.createEmptyBorder(15, 5, 10, 15));
		registerButton.setHorizontalAlignment(registerButton.RIGHT);
		registerButton.addActionListener(this);
		
		buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.pink);
		buttonPanel.add(loginButton);
		
		southPanel.add(buttonPanel);
		southPanel.add(registerButton);
		
		//Main Frame
		this.add(northPanel, BorderLayout.NORTH);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(southPanel, BorderLayout.SOUTH);
		
		init();
	}

	public static void main(String[] args) {
		new LoginForm();

	}
	
	void init() {
		setTitle("Login");
//		setSize(500,900);
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	void validateLogin() {
		if(usernameField.getText().equals("")) {
			UIManager.put("OptionPane.background",new Color(255, 217, 229));
			UIManager.put("Panel.background",new Color(255, 217, 229));
			
			JOptionPane.showMessageDialog(this, "Username cannot be empty! ", "Error", JOptionPane.ERROR_MESSAGE);
		
		}else if(passwordField.getPassword().length == 0) {
			UIManager.put("OptionPane.background",new Color(255, 217, 229));
			UIManager.put("Panel.background",new Color(255, 217, 229));
		
			JOptionPane.showMessageDialog(this, "Password cannot be empty! ", "Error", JOptionPane.ERROR_MESSAGE);
		
		}
		
		else {
			try {
				
				String queryValidate = "SELECT * FROM user WHERE Username = '"+usernameField.getText()+"' AND UserPassword = '"+ new String(passwordField.getPassword())+"'";
				ResultSet rs = con.executeQuery(queryValidate);
				
				rs.beforeFirst();
				if (!rs.next()) {
					UIManager.put("OptionPane.background",new Color(255, 217, 229));
					UIManager.put("Panel.background",new Color(255, 217, 229));
					JOptionPane.showMessageDialog(this, "Username/Password is wrong", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
						String id = rs.getString("UserID");
						String username = rs.getString("Username");
						String role = rs.getString("UserRole");
						
						UIManager.put("OptionPane.background",new Color(255, 217, 229));
						UIManager.put("Panel.background",new Color(255, 217, 229));
						JOptionPane.showMessageDialog(this, "Login Success!", "Success", JOptionPane.INFORMATION_MESSAGE);
						
						new MainForm(id, username, role);
						this.dispose();
				}
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == loginButton) {
		
			validateLogin();
			
		}else if (e.getSource() == registerButton) {
			//Page Register
			this.dispose();
			new RegisterForm();
		}
		
	}

}
