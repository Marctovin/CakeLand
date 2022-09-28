import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainForm implements ActionListener {

	JFrame frame, frame2;
	JLabel Cakeland, hehe;
	JPanel pan1, pan2, pan3;
	JMenuBar baruser, barad;
	JMenu AccUser, trans, Accad, cake;
	JMenuItem item1, item2, item3, item4, item5, itemad1, itemad2, itemad3;
	
	String id;
	String username;
	String role;
	
	Connect con = new Connect();
	
	public MainForm(String id, String username, String role) {
		this.id = id;
		this.username = username;
		this.role = role;
		
		Cakeland = new JLabel("Welcome to CakeLAnd");
		Cakeland.setFont(new Font("Verdana", Font.BOLD, 55));
		Cakeland.setForeground(new Color(129, 69, 77));

		hehe = new JLabel("Hello hehehe!");
		hehe.setFont(new Font("Verdana", Font.BOLD, 20));
		hehe.setForeground(new Color(43, 55, 108));

		pan1 = new JPanel();
		pan1.setBackground(Color.pink);

		pan2 = new JPanel();
		pan2.setLayout(new BorderLayout());
		pan2.setBackground(Color.pink);

		pan3 = new JPanel();
		pan3.setLayout(new GridLayout(2, 1));
		pan3.setBorder(new EmptyBorder(120, 65, 140, 65));
		pan3.setBackground(Color.pink);

		frame = new JFrame();
		frame.getContentPane().setBackground(Color.pink);

		if (role.equals("User")) {

			baruser = new JMenuBar();
			baruser.setBackground(new Color(245, 198, 211));

			AccUser = new JMenu("Manage Account");
			AccUser.setFont(new Font("Verdana", Font.BOLD, 13));
			AccUser.setForeground(new Color(43, 55, 108));

			trans = new JMenu("Transaction");
			trans.setFont(new Font("Verdana", Font.BOLD, 13));
			trans.setForeground(new Color(43, 55, 108));

			item1 = new JMenuItem("Profile");
			item1.addActionListener(this);
			item2 = new JMenuItem("Logoff");
			item2.addActionListener(this);
			item3 = new JMenuItem("View All Menu");
			item3.addActionListener(this);
			item4 = new JMenuItem("Manage Cart");
			item4.addActionListener(this);
			item5 = new JMenuItem("View Transaction History");
			item5.addActionListener(this);

			AccUser.add(item1);
			AccUser.add(item2);
			trans.add(item3);
			trans.add(item4);
			trans.add(item5);

			baruser.add(AccUser);
			baruser.add(trans);

			pan1.add(Cakeland);

			pan2.add(hehe, BorderLayout.SOUTH);
			hehe.setHorizontalAlignment(JLabel.LEFT);
			pan2.setBackground(Color.pink);

			pan3.add(hehe);
			pan3.add(Cakeland);

			frame.setJMenuBar(baruser);

			frame.add(pan3);

			frame.setVisible(true);
			frame.setResizable(false);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setTitle("CakeLAnd");
			frame.pack();
			frame.setLocationRelativeTo(null);

		}

		if (role.equals("Admin")) {

			frame.setLayout(new BorderLayout());
			Cakeland.setHorizontalAlignment(JLabel.CENTER);
			frame.add(Cakeland, BorderLayout.CENTER);

			barad = new JMenuBar();
			barad.setBackground(new Color(245, 198, 211));

			Accad = new JMenu("Manage Account");
			Accad.setFont(new Font("Verdana", Font.BOLD, 13));
			Accad.setForeground(new Color(43, 55, 108));

			cake = new JMenu("Cake Menu");
			cake.setFont(new Font("Verdana", Font.BOLD, 13));
			cake.setForeground(new Color(43, 55, 108));

			itemad1 = new JMenuItem("Profile");
			itemad1.addActionListener(this);
			itemad2 = new JMenuItem("Logoff");
			itemad2.addActionListener(this);
			itemad3 = new JMenuItem("Manage Menu");
			itemad3.addActionListener(this);

			Accad.add(itemad1);
			Accad.add(itemad2);
			cake.add(itemad3);

			barad.add(Accad);
			barad.add(cake);

			frame.setJMenuBar(barad);

			frame.setVisible(true);
			frame.setResizable(false);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setTitle("CakeLAnd");
			frame.setSize(800, 450);
			frame.setLocationRelativeTo(null);

		}
	}

	public static void main(String[] args) {
		new MainForm("test", "test", "User");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == item1) {
            frame.dispose();
            new UpdateProfileForm(id, username, role);
        }

        if (e.getSource() == item2) {
            frame.dispose();
            new LoginForm();
        }

        if (e.getSource() == item3) {
            frame.dispose();
            new ViewAllMenu(id, username, role);
        }

        if (e.getSource() == item4) {
            frame.dispose();
            new ManageCartPage(id, username, role);
        }

        if (e.getSource() == item5) {
            frame.dispose();
            new TransactionForm(id, username, role);
        }

        if (e.getSource() == itemad1) {
            frame.dispose();
            new UpdateProfileForm(id, username, role);
        }

        if (e.getSource() == itemad2) {
            frame.dispose();
            new LoginForm();
        }

        if (e.getSource() == itemad3) {
            frame.dispose();
            new ManageMenuForm(id, username, role);
        }
	}

}
