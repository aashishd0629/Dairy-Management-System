package guidesign;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Nuser {

	private JTextField fnametxt;
	private JLabel lblAddress;
	private JTextField addresstxt;
	private JLabel lblMobile;
	private JTextField mobiletxt;
	private JTable utable;
	Statement stmt;

	JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton backbtn;
	private JButton ubtn;

	/**
	 * Launch the application.
	 */
	
	public void clear() {
		fnametxt.setText("");
		addresstxt.setText("");
		mobiletxt.setText("");

	}

	public void displayt() {
		DefaultTableModel sd = (DefaultTableModel) table.getModel();
		int rc = sd.getRowCount();
		// delete the previous table data
		while (rc-- != 0) {
			sd.removeRow(rc);
		}
		try {
			String q1 = "SELECT * FROM newuser";
			ResultSet rs = Dbconn.s.executeQuery(q1);
			while (rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				String address = rs.getString(3);
				String mobile = rs.getString(4);
				String[] data = { id, name, address, mobile };
				sd.addRow(data);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Nuser window = new Nuser();
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
	public Nuser() {
		initialize();
		displayt();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.BLUE);
		frame.setFont(new Font("SansSerif", Font.BOLD, 24));
		frame.setTitle("User Registration");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 1252, 556);
		frame.getContentPane().setLayout(null);

		// Title label
		JLabel nuser = new JLabel("Create New User");
		nuser.setBounds(5, 5, 541, 29);
		nuser.setVerticalAlignment(SwingConstants.TOP);
		nuser.setHorizontalAlignment(SwingConstants.CENTER);
		nuser.setFont(new Font("Tahoma", Font.BOLD, 24));
		frame.getContentPane().add(nuser);

		// Name label
		JLabel fname = new JLabel("Full Name");
		fname.setFont(new Font("Tahoma", Font.BOLD, 20));
		fname.setBounds(41, 103, 104, 29);
		frame.getContentPane().add(fname);

		// Name textfield
		fnametxt = new JTextField();
		fnametxt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fnametxt.setBounds(217, 103, 192, 28);
		frame.getContentPane().add(fnametxt);
		fnametxt.setColumns(10);

		// Address label
		lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAddress.setBounds(41, 164, 104, 29);
		frame.getContentPane().add(lblAddress);

		// Address textfield
		addresstxt = new JTextField();
		addresstxt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addresstxt.setColumns(10);
		addresstxt.setBounds(217, 164, 192, 28);
		frame.getContentPane().add(addresstxt);

		// Mobile no label
		lblMobile = new JLabel("Mobile");
		lblMobile.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMobile.setBounds(41, 219, 104, 29);
		frame.getContentPane().add(lblMobile);

		// Mobile no textfield
		mobiletxt = new JTextField();
		mobiletxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				mobiletxt.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent ke) {
						String value = mobiletxt.getText();
						int l = value.length();
						if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9'
								|| ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
							mobiletxt.setEditable(true);

						} else {
							mobiletxt.setEditable(false);

						}
						if (l > 9) {
							mobiletxt.setEditable(false);
						}
					}

				});
			}
		});
		mobiletxt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		mobiletxt.setColumns(10);
		mobiletxt.setBounds(217, 219, 192, 28);
		frame.getContentPane().add(mobiletxt);

		// Button for creating user.

		JButton createbtn = new JButton("Create");
		createbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fname = fnametxt.getText();
				String faddress = addresstxt.getText();
				String fmobile = mobiletxt.getText();
				if (fname.length() == 0 || faddress.length() == 0 || fmobile.length() == 0 && fmobile.length() > 10) {
					JFrame f = new JFrame();
					JOptionPane.showMessageDialog(f, "information Missing");
				} else {
					try {

						String sql = "INSERT INTO newuser (FullName, Address,Mobile) VALUES ('" + fname + "','"
								+ faddress + "','" + fmobile + "')";

						Dbconn.s.executeUpdate(sql);
						// String sql2="CREATE TABLE '"+fname+"'"();
						// con.close();
					} catch (Exception e1) {
						System.out.println(e1);
					}
					clear();
					displayt();
				}
			}
		});
		createbtn.setForeground(Color.BLUE);
		createbtn.setBackground(Color.GREEN);
		createbtn.setFont(new Font("Tahoma", Font.BOLD, 24));
		createbtn.setBounds(234, 308, 153, 37);
		frame.getContentPane().add(createbtn);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(545, 45, 633, 409);
		frame.getContentPane().add(scrollPane);

		table = new JTable();

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TableModel md = table.getModel();
				int i = table.getSelectedRow();
				fnametxt.setText(md.getValueAt(i, 1).toString());
				addresstxt.setText(md.getValueAt(i, 2).toString());
				mobiletxt.setText(md.getValueAt(i, 3).toString());
			}

		});
		scrollPane.setViewportView(table);
		table.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "SN", "Full Name", "Address", "Mobile" }));

		JButton delbtn = new JButton("Delete");
		delbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					TableModel md = table.getModel();
					int i = table.getSelectedRow();
					String a = md.getValueAt(i, 0).toString();

					String sql3 = "DELETE FROM newuser WHERE ID=" + a;

					Dbconn.s.executeUpdate(sql3);
					// String sql2="CREATE TABLE '"+fname+"'"();
					// con.close();
				} catch (Exception e1) {
					System.out.println(e1);
				}
				clear();

				displayt();
			}

		});
		delbtn.setBackground(Color.RED);
		delbtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		delbtn.setBounds(234, 400, 104, 29);
		frame.getContentPane().add(delbtn);
		
		backbtn = new JButton("Back");
		backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dashboard dbas= new Dashboard();
				dbas.frmDashboard.setVisible(true);
				frame.dispose();
			}
		});
		backbtn.setBackground(Color.RED);
		backbtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		backbtn.setBounds(94, 400, 104, 28);
		frame.getContentPane().add(backbtn);
		
		ubtn = new JButton("Update");
		ubtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableModel md = table.getModel();
				int i = table.getSelectedRow();
				String b = md.getValueAt(i, 0).toString();
				String x = fnametxt.getText();
				String y = addresstxt.getText();
				String z = mobiletxt.getText();
				if (x.isEmpty() && y.isEmpty() && z.isEmpty()) {
					JFrame f = new JFrame();
					JOptionPane.showMessageDialog(f, "Information Missing");
				} else {
					try {

						String sql4 = "UPDATE newuser SET FullName='" + x + "',Address='" + y + "',Mobile='" + z + "' WHERE ID='"+b+"'";

						Dbconn.s.executeUpdate(sql4);
						// String sql2="CREATE TABLE '"+fname+"'"();
						// con.close();
					} catch (Exception e1) {
						System.out.println(e1);
					}
					clear();

					displayt();
				}

			}
				
				
			
		});
		ubtn.setBackground(Color.ORANGE);
		ubtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		ubtn.setBounds(41, 308, 111, 36);
		frame.getContentPane().add(ubtn);
		table.getColumnModel().getColumn(0).setPreferredWidth(47);
		table.getColumnModel().getColumn(0).setMaxWidth(47);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setMinWidth(22);
		table.getColumnModel().getColumn(2).setPreferredWidth(77);
		table.getColumnModel().getColumn(3).setPreferredWidth(78);

	}
}
