package guidesign;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Home1 {

	private JFrame frame;
	private JTextField untxt;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home1 window = new Home1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Home1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1054, 616);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel hdlbl = new JLabel("Dairy Management System");
		hdlbl.setFont(new Font("Tahoma", Font.BOLD, 24));
		hdlbl.setHorizontalAlignment(SwingConstants.CENTER);
		hdlbl.setBounds(42, 27, 889, 59);
		frame.getContentPane().add(hdlbl);

		JLabel sblbl = new JLabel("Login Here");
		sblbl.setHorizontalAlignment(SwingConstants.CENTER);
		sblbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		sblbl.setBounds(142, 113, 657, 41);
		frame.getContentPane().add(sblbl);

		JLabel ssblbl = new JLabel("Username");
		ssblbl.setHorizontalAlignment(SwingConstants.CENTER);
		ssblbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		ssblbl.setBounds(413, 198, 110, 27);
		frame.getContentPane().add(ssblbl);

		untxt = new JTextField();
		untxt.setBounds(413, 247, 128, 27);
		frame.getContentPane().add(untxt);
		untxt.setColumns(10);

		JLabel plbl = new JLabel("Password");
		plbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		plbl.setHorizontalAlignment(SwingConstants.CENTER);
		plbl.setBounds(413, 297, 110, 27);
		frame.getContentPane().add(plbl);

		JButton lgbtn = new JButton("Log In");
		lgbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = untxt.getText();
				String pass = passwordField.getText();
				if (user.length() == 0 || pass.length() == 0) {
					JFrame f = new JFrame();
					JOptionPane.showMessageDialog(f, "Invalid Username or Password");
				} else {
					try {

						String q1 = "SELECT * FROM logintb where username=? and password=?";
						PreparedStatement pst = Dbconn.con.prepareStatement(q1);
						pst.setString(1, untxt.getText());
						pst.setString(2, passwordField.getText());
						ResultSet rs = pst.executeQuery();
						int count = 0;
						while (rs.next()) {
							count = count + 1;
						}
						if (count == 1) {
							JOptionPane.showMessageDialog(null, "Valid Username and Password");
							frame.dispose();
							Dashboard window = new Dashboard();
							window.frmDashboard.setVisible(true);

						} else if (count > 1) {
							JOptionPane.showMessageDialog(null, "Duplicate Username and Password");
						} else {
							JOptionPane.showMessageDialog(null, "Invalid username or password");
						}
					}

					catch (Exception e2) {
						JOptionPane.showMessageDialog(null, e2);
					}
				}
			}
		});
		lgbtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		lgbtn.setBounds(434, 403, 89, 33);
		frame.getContentPane().add(lgbtn);

		passwordField = new JPasswordField();
		passwordField.setBounds(413, 337, 128, 27);
		frame.getContentPane().add(passwordField);
	}
}
