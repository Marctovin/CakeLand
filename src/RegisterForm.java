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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class RegisterForm extends JFrame implements ActionListener{
	
	JPanel northPanel, centerPanel, southPanel, usernamePanel, emailPanel, genderPanel, dobPanel, phonePanel, addressPanel, passwordPanel, conPasswordPanel, buttonPanel ;
	JLabel labelTitle, labelHeader, labelUsername, labelEmail, labelGender, labelDOB, label1, label2, labelPhone, labelAddress, labelPassword, labelConPassword;
	JTextField username, email, dd, mm, yyyy, phone;
	JTextArea address;
	JPasswordField password, confirmPassword;
	JCheckBox term;
	JButton register, login;
	JRadioButton radioMale, radioFemale;
	ButtonGroup groupGender;
	
	Connect con = new Connect();
	
	HashMap<Character, Integer> checkDuplicate = new HashMap<Character, Integer>();
	
	public RegisterForm() {
		//NORTH PANEL
		northPanel = new JPanel(new GridLayout(2, 1));
		
		labelTitle = new JLabel("CakeLAnd");
		labelTitle.setFont(new Font("Verdana", Font.BOLD, 24));
		labelTitle.setForeground(Color.decode("#964b00"));
		labelTitle.setBorder(new EmptyBorder(20, 1, 10, 1));
		
		labelHeader = new JLabel("Create your own account!");
		labelHeader.setFont(new Font("Verdana", Font.BOLD, 18));
		labelHeader.setForeground(Color.decode("#1e3f66"));
		
		labelTitle.setHorizontalAlignment(JLabel.CENTER);
		labelHeader.setHorizontalAlignment(JLabel.CENTER);
		
		northPanel.add(labelTitle);
		northPanel.add(labelHeader);
		northPanel.setBackground(Color.pink);
		
		//CENTER PANEL
		centerPanel = new JPanel(new GridBagLayout());
		
		labelUsername = new JLabel("Username");
		labelUsername.setFont(new Font("Verdana", Font.BOLD, 12));
		labelUsername.setForeground(Color.decode("#1e3f66"));
		
		username = new JTextField(20);
		username.setBorder(new EmptyBorder(5, 5, 5, 5));
		username.setBackground(Color.decode("#ffe0e5"));
//		username.setForeground(new Color(14, 34, 92));
		username.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		usernamePanel = new JPanel();
		usernamePanel.setBackground(Color.pink);
		usernamePanel.add(username);
		
		labelEmail = new JLabel("Email");
		labelEmail.setFont(new Font("Verdana", Font.BOLD, 12));
		labelEmail.setForeground(Color.decode("#1e3f66"));
		
		email = new JTextField(20);
		email.setBorder(new EmptyBorder(5, 5, 5, 5));
		email.setBackground(Color.decode("#ffe0e5"));
//		email.setForeground(new Color(14, 34, 92));
		email.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		emailPanel = new JPanel();
		emailPanel.setBackground(Color.pink);
		emailPanel.add(email);
		
		labelGender = new JLabel("Gender");
		labelGender.setFont(new Font("Verdana", Font.BOLD, 12));
		labelGender.setForeground(Color.decode("#1e3f66"));
		
		//Button Group
		radioMale = new JRadioButton("Male");
		radioMale.setBackground(Color.PINK);
		radioMale.setFont(new Font("Verdana", Font.BOLD, 11));
		radioMale.setForeground(Color.decode("#1e3f66"));
		radioMale.setActionCommand("Male");
		
		radioFemale = new JRadioButton("Female");
		radioFemale.setBackground(Color.PINK);
		radioFemale.setFont(new Font("Verdana", Font.BOLD, 11));
		radioFemale.setForeground(Color.decode("#1e3f66"));
		radioFemale.setActionCommand("Female");
		
		groupGender = new ButtonGroup();
		groupGender.add(radioMale);
		groupGender.add(radioFemale);
		
		genderPanel = new JPanel();
		genderPanel.setBackground(Color.pink);
		genderPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		genderPanel.add(radioMale);
		genderPanel.add(radioFemale);
		
		labelDOB = new JLabel("Date of Birth");
		labelDOB.setFont(new Font("Verdana", Font.BOLD, 12));
		labelDOB.setForeground(Color.decode("#1e3f66"));
		
		dd = new JTextField(5);
		dd.setBorder(new EmptyBorder(5, 6, 5, 6));
		dd.setBackground(Color.decode("#ffe0e5"));
//		dd.setForeground(new Color(14, 34, 92));
		dd.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		label1 = new JLabel("-");
		label1.setFont(new Font("Verdana", Font.BOLD, 12));
		label1.setForeground(Color.decode("#1e3f66"));
		
		mm = new JTextField(5);
		mm.setBorder(new EmptyBorder(5, 6, 5, 6));
		mm.setBackground(Color.decode("#ffe0e5"));
//		mm.setForeground(new Color(14, 34, 92));
		mm.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		label2 = new JLabel("-");
		label2.setFont(new Font("Verdana", Font.BOLD, 12));
		label2.setForeground(Color.decode("#1e3f66"));
		
		yyyy = new JTextField(5);
		yyyy.setBorder(new EmptyBorder(5, 6, 5, 6));
		yyyy.setBackground(Color.decode("#ffe0e5"));
//		yyyy.setForeground(new Color(14, 34, 92));
		yyyy.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		dobPanel = new JPanel();
		dobPanel.setBackground(Color.pink);
		dobPanel.add(dd);
		dobPanel.add(label1);
		dobPanel.add(mm);
		dobPanel.add(label2);
		dobPanel.add(yyyy);
		
		labelPhone = new JLabel("Phone Number");
		labelPhone.setFont(new Font("Verdana", Font.BOLD, 12));
		labelPhone.setForeground(Color.decode("#1e3f66"));
		
		phone = new JTextField(20);
		phone.setBorder(new EmptyBorder(5, 5, 5, 5));
		phone.setBackground(Color.decode("#ffe0e5"));
//		phone.setForeground(new Color(14, 34, 92));
		phone.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		phonePanel = new JPanel();
		phonePanel.setBackground(Color.pink);
		phonePanel.add(phone);
		
		labelAddress = new JLabel("Address");
		labelAddress.setFont(new Font("Verdana", Font.BOLD, 12));
		labelAddress.setForeground(Color.decode("#1e3f66"));
		
		address = new JTextArea(5, 20);
		address.setBorder(new EmptyBorder(5, 5, 5, 5));
		address.setBackground(Color.decode("#ffe0e5"));
//		address.setForeground(new Color(14, 34, 92));
		address.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		addressPanel = new JPanel();
		addressPanel.setBackground(Color.pink);
		addressPanel.add(address);
		
		labelPassword = new JLabel("Password");
		labelPassword.setFont(new Font("Verdana", Font.BOLD, 12));
		labelPassword.setForeground(Color.decode("#1e3f66"));
		
		password = new JPasswordField(20);
		password.setBorder(new EmptyBorder(5, 5, 5, 5));
		password.setBackground(Color.decode("#ffe0e5"));
//		password.setForeground(new Color(14, 34, 92));
		password.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		passwordPanel = new JPanel();
		passwordPanel.setBackground(Color.pink);
		passwordPanel.add(password);
		
		labelConPassword = new JLabel("Confirm Password");
		labelConPassword.setFont(new Font("Verdana", Font.BOLD, 12));
		labelConPassword.setForeground(Color.decode("#1e3f66"));
		
		confirmPassword = new JPasswordField(20);
		confirmPassword.setBorder(new EmptyBorder(5, 5, 5, 5));
		confirmPassword.setBackground(Color.decode("#ffe0e5"));
//		confirmPassword.setForeground(new Color(14, 34, 92));
		confirmPassword.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		conPasswordPanel = new JPanel();
		conPasswordPanel.setBackground(Color.pink);
		conPasswordPanel.add(confirmPassword);

		GridBagConstraints c = new GridBagConstraints();
	    c.insets = new Insets(1, 20, 1, 20);

		c.anchor = GridBagConstraints.WEST;
		
		c.gridx = 0;
		c.gridy = 0;
		centerPanel.add(labelUsername, c);
		
		c.gridx = 1;
		centerPanel.add(usernamePanel, c);
		
		c.gridx = 0;
		c.gridy = 1;
		centerPanel.add(labelEmail, c);
		
		c.gridx= 1;
		centerPanel.add(emailPanel, c);
		
		c.gridx = 0;
		c.gridy = 2;
		centerPanel.add(labelGender, c);
		
		c.gridx = 1;
		centerPanel.add(genderPanel, c);
		
		c.gridx = 0;
		c.gridy = 3;
		centerPanel.add(labelDOB, c);
		
		c.gridx = 1;
		centerPanel.add(dobPanel, c);
		
		c.gridx = 0;
		c.gridy = 4;
		centerPanel.add(labelPhone, c);
		
		c.gridx = 1;
		centerPanel.add(phonePanel, c);
		
		c.gridx = 0;
		c.gridy = 5;
		centerPanel.add(labelAddress, c);
		
		c.gridx = 1;
		centerPanel.add(addressPanel, c);
		
		c.gridx = 0;
		c.gridy = 6;
		centerPanel.add(labelPassword, c);
		
		c.gridx = 1;
		centerPanel.add(passwordPanel, c);
		
		c.gridx = 0;
		c.gridy = 7;
		centerPanel.add(labelConPassword, c);
		
		c.gridx = 1;
		centerPanel.add(conPasswordPanel, c);
		
		centerPanel.setBackground(Color.pink);
		
		//SOUTH PANEL
		southPanel = new JPanel(new GridLayout(3, 1));
		term = new JCheckBox("Agree to Terms & Conditions");
		term.setFont(new Font("Verdana", Font.BOLD, 11));
		term.setForeground(Color.decode("#1e3f66"));
		term.setHorizontalAlignment(term.CENTER);
		term.setBackground(Color.PINK);
		
		buttonPanel = new JPanel();
		register = new JButton("Register");
		register.setBackground(new Color(255, 217, 229));
		register.setForeground(new Color(14, 34, 92));
		register.setFont(new Font("Verdana", Font.BOLD, 18));
		register.setBorder(new EmptyBorder(10, 20, 10, 20));
		buttonPanel.add(register);
		buttonPanel.setBackground(Color.pink);
		register.addActionListener(this);
		
		login = new JButton("Already have an account?Login");
		login.setBackground(Color.pink);
		login.setForeground(Color.blue);
		login.setFont(new Font("Calibri", Font.ITALIC, 12));
		login.setBorder(new EmptyBorder(10, 20, 10, 20));
		login.setHorizontalAlignment(login.RIGHT);
		login.addActionListener(this);
		
		southPanel.add(term);
		southPanel.add(buttonPanel);
		southPanel.add(login);
		
		//Main Frame
		this.add(northPanel, BorderLayout.NORTH);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(southPanel, BorderLayout.SOUTH);
		init();
	}

	public static void main(String[] args) {
		new RegisterForm();
	}

	void init() {
		setTitle("CakeLAnd");
//		setSize(500,900);
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	void validateReg() {
		int numOfDot = 0;
		String[] back = null;
		boolean validated = false;
		
		try {
			back = email.getText().split("@")[1].split("\\.");
		} catch (Exception e) {
	
		}

		countMap(email.getText());
		
		if(username.getText().length() < 5 || username.getText().length() > 15) {
			UIManager.put("OptionPane.background",new Color(255, 217, 229));
			UIManager.put("Panel.background",new Color(255, 217, 229));
			JOptionPane.showMessageDialog(this, "Username must be 5 - 15 characters", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		else if(email.getText().contains("@.") || email.getText().contains(".@")) {
			UIManager.put("OptionPane.background",new Color(255, 217, 229));
			UIManager.put("Panel.background",new Color(255, 217, 229));
			JOptionPane.showMessageDialog(this, "Character @ must no be next to .", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if(email.getText().startsWith("@") || email.getText().endsWith("@")) {
			UIManager.put("OptionPane.background",new Color(255, 217, 229));
			UIManager.put("Panel.background",new Color(255, 217, 229));
			JOptionPane.showMessageDialog(this, "Must not starts or ends with @", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if(email.getText().startsWith(".") || email.getText().endsWith(".")) {
			UIManager.put("OptionPane.background",new Color(255, 217, 229));
			UIManager.put("Panel.background",new Color(255, 217, 229));
			JOptionPane.showMessageDialog(this, "Must not starts or ends with .", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if (checkDuplicate.get('@') > 1) {
			UIManager.put("OptionPane.background",new Color(255, 217, 229));
			UIManager.put("Panel.background",new Color(255, 217, 229));
			JOptionPane.showMessageDialog(this, "Must contain exactly one @", "Error", JOptionPane.ERROR_MESSAGE);
			checkDuplicate.clear();
		}
		else if (back == null) {
			UIManager.put("OptionPane.background",new Color(255, 217, 229));
			UIManager.put("Panel.background",new Color(255, 217, 229));
			JOptionPane.showMessageDialog(this, "Email cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if (back.length > 3) {
			numOfDot = back.length;
			
			UIManager.put("OptionPane.background",new Color(255, 217, 229));
			UIManager.put("Panel.background",new Color(255, 217, 229));
			JOptionPane.showMessageDialog(this, "Must contain exactly one '.' for separating [provider] and [domain]", "Error", JOptionPane.ERROR_MESSAGE);

		} 
		else if (isWronglyFormatted(back)) {
			UIManager.put("OptionPane.background",new Color(255, 217, 229));
			UIManager.put("Panel.background",new Color(255, 217, 229));
			JOptionPane.showMessageDialog(this, "Domain must be 'com' or 'co.id'. ", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		else if(phone.getText().length() < 10 || phone.getText().length() > 12) {
			UIManager.put("OptionPane.background",new Color(255, 217, 229));
			UIManager.put("Panel.background",new Color(255, 217, 229));
			JOptionPane.showMessageDialog(this, "Phone Number must consist only 10 - 12 digits only.", "Error", JOptionPane.ERROR_MESSAGE);
			
		}	
		else if (dd.getText().equals("") || mm.getText().equals("") || yyyy.getText().equals("")) {
			UIManager.put("OptionPane.background",new Color(255, 217, 229));
			UIManager.put("Panel.background",new Color(255, 217, 229));
			JOptionPane.showMessageDialog(this, "Date of Birth must not be empty.", "Error", JOptionPane.ERROR_MESSAGE);
			
		}
		
		else if (Integer.parseInt(dd.getText()) < 1 || Integer.parseInt(dd.getText()) > 31) {
			UIManager.put("OptionPane.background",new Color(255, 217, 229));
			UIManager.put("Panel.background",new Color(255, 217, 229));
			JOptionPane.showMessageDialog(this, "Date cannot be less than 1 or more than 31", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if (Integer.parseInt(mm.getText()) < 1 || Integer.parseInt(mm.getText()) > 12) {
			UIManager.put("OptionPane.background",new Color(255, 217, 229));
			UIManager.put("Panel.background",new Color(255, 217, 229));
			JOptionPane.showMessageDialog(this, "Month cannot be less than 1 or more than 12", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if (Integer.parseInt(yyyy.getText()) > 2022) {
			UIManager.put("OptionPane.background",new Color(255, 217, 229));
			UIManager.put("Panel.background",new Color(255, 217, 229));
			JOptionPane.showMessageDialog(this, "Year of birth cannot be more than the current year", "Error", JOptionPane.ERROR_MESSAGE);
		}

		else if (!address.getText().endsWith(" Street")) {
			UIManager.put("OptionPane.background",new Color(255, 217, 229));
			UIManager.put("Panel.background",new Color(255, 217, 229));
			JOptionPane.showMessageDialog(this, "Address must ends with ' Street' ", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if (!passwordIsAlphaNum()) {
			UIManager.put("OptionPane.background",new Color(255, 217, 229));
			UIManager.put("Panel.background",new Color(255, 217, 229));
			JOptionPane.showMessageDialog(this, "Password must be alphanumeric", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if (!(Arrays.equals(password.getPassword(), confirmPassword.getPassword()))) {
			UIManager.put("OptionPane.background",new Color(255, 217, 229));
			UIManager.put("Panel.background",new Color(255, 217, 229));
			JOptionPane.showMessageDialog(this, "Confirm Password must have the same content as Password.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if (groupGender.getSelection() == null) {
			UIManager.put("OptionPane.background",new Color(255, 217, 229));
			UIManager.put("Panel.background",new Color(255, 217, 229));
			JOptionPane.showMessageDialog(this, "Gender must be selected either \"Male\" or \"Female\" ", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if (!term.isSelected()) {
			UIManager.put("OptionPane.background",new Color(255, 217, 229));
			UIManager.put("Panel.background",new Color(255, 217, 229));
			JOptionPane.showMessageDialog(this, "You must Agree to Terms and Condition", "Error", JOptionPane.ERROR_MESSAGE);
		}

		else {
			Random rand = new Random();;
			 
			String userId = "U" + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10);
			String userName = username.getText();
			String userEmail = email.getText();
			String userPassword = new String(password.getPassword());
			String userGender = groupGender.getSelection().getActionCommand();
			String userDob = yyyy.getText() + "-" + mm.getText() + "-" + dd.getText();
			String userPhone = phone.getText();
			String userAddress = address.getText();
			String userRole = "User";
			
			String queryUsernameValidate = "SELECT * FROM user WHERE Username = '"+userName+"'";
			String queryPhoneValidate = "SELECT * FROM user WHERE UserPhoneNumber = '"+userPhone+"'";
			
			String queryIdValidate = "SELECT * FROM user WHERE UserID = '"+userId+"'";
						
			ResultSet rs = con.executeQuery(queryUsernameValidate);
			
			try {
				
				if (rs.next()) {
					UIManager.put("OptionPane.background",new Color(255, 217, 229));
					UIManager.put("Panel.background",new Color(255, 217, 229));
					JOptionPane.showMessageDialog(this, "Username already exist!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
					
				rs = con.executeQuery(queryPhoneValidate);
					
				if (rs.next()) {
					UIManager.put("OptionPane.background",new Color(255, 217, 229));
					UIManager.put("Panel.background",new Color(255, 217, 229));
					JOptionPane.showMessageDialog(this, "Phone Number already exist!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
					
				rs = con.executeQuery(queryIdValidate);
				
				while (rs.next()) {
					if (rs.getString("UserID").equals(userId)) {
						userId = "U" + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10);
						rs.beforeFirst();
	
					}
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			
			try {
				String query = "INSERT INTO user VALUES('"+userId+"', '"+userName+"', '"+userEmail+"',"
						+ " '"+userPassword+"', '"+userGender+"', '"+userDob+"', '"+userPhone+"', "
						+ "'"+userAddress+"', '"+userRole+"')";

				con.executeUpdate(query);	
					
				UIManager.put("OptionPane.background",new Color(255, 217, 229));
				UIManager.put("Panel.background",new Color(255, 217, 229));
				JOptionPane.showMessageDialog(this, "Register Success!", "Success", JOptionPane.INFORMATION_MESSAGE);

				this.dispose();
				new LoginForm();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} 
	}
	
	void countMap(String phrase) {
		checkDuplicate.put('@', 0);
		checkDuplicate.put('.', 0);
		for (int i = 0; i < phrase.length(); i++) {
				char c = phrase.charAt(i);
				if(c == '@') {
					checkDuplicate.put('@', checkDuplicate.get('@') + 1);
				}
				else if(c == '.') {
					checkDuplicate.put('.', checkDuplicate.get('.') + 1);
				}
			}
	}
	
	boolean isWronglyFormatted(String[] back) {
		
		int numOfDot = back.length - 1;
		if (numOfDot == 1) {

			if (!(email.getText().split("@")[1].split("\\.")[1].equalsIgnoreCase("com"))) {
				return true;

			}
		}
		else if (numOfDot == 2) {

			if (!(email.getText().split("@")[1].split("\\.")[1].equals("co")) || !(email.getText().split("@")[1].split("\\.")[2].equals("id"))) {
				return true;
			}
		}
		else {
			return false;
		}
		return false;
		
	}
	
	boolean passwordIsAlphaNum() {
		
		//abcde123
		int countAlpha = 0;
		int countNum = 0;
		for (int i = 0; i < password.getPassword().length; i++) {
			char p = password.getPassword()[i];
			if (isAlphabet(p)) {
				countAlpha++;
			}
			else if (isNumeric(p)) {
				countNum++;
			}
		}
		if (countAlpha > 0 && countNum > 0) {
			return true;
		} else {
			return false;
		}
		
	}
	
	boolean isAlphabet(char c) {
		return((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'));
	}
	
	boolean isNumeric(char c) {
		return (c >= '0' || c <= '9');
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == register) {
			validateReg();

		}else if(e.getSource() == login) {
			this.setVisible(false);
			new LoginForm();
		}

	}
	
}
