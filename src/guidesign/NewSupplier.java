package guidesign;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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

public class NewSupplier {

	private JTextField fsnametxt;
	private JLabel lblAddress;
	private JTextField saddresstxt;
	private JLabel lblMobile;
	private JTextField smobiletxt;
	private JTable stable;
	JFrame frame;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */

	public void displayt() {
		DefaultTableModel sd = (DefaultTableModel) stable.getModel();
		int rc = sd.getRowCount();
		// delete the previous table data
		while (rc-- != 0) {
			sd.removeRow(rc);
		}
		try {

			String q1 = "SELECT * FROM newsupplier";
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

	public void clear1() {
		fsnametxt.setText("");
		saddresstxt.setText("");
		smobiletxt.setText("");
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewSupplier window = new NewSupplier();
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
	public NewSupplier() {
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
		JLabel nsuser = new JLabel("Create New Supplier");
		nsuser.setBounds(5, 5, 541, 29);
		nsuser.setVerticalAlignment(SwingConstants.TOP);
		nsuser.setHorizontalAlignment(SwingConstants.CENTER);
		nsuser.setFont(new Font("Tahoma", Font.BOLD, 24));
		frame.getContentPane().add(nsuser);

		// Name label
		JLabel fsname = new JLabel("Full Name");
		fsname.setFont(new Font("Tahoma", Font.BOLD, 20));
		fsname.setBounds(41, 103, 104, 29);
		frame.getContentPane().add(fsname);

		// Name textfield
		fsnametxt = new JTextField();
		fsnametxt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fsnametxt.setBounds(217, 103, 192, 28);
		frame.getContentPane().add(fsnametxt);
		fsnametxt.setColumns(10);

		// Address label
		lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAddress.setBounds(41, 164, 104, 29);
		frame.getContentPane().add(lblAddress);

		// Address textfield
		saddresstxt = new JTextField();
		saddresstxt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		saddresstxt.setColumns(10);
		saddresstxt.setBounds(217, 164, 192, 28);
		frame.getContentPane().add(saddresstxt);

		// Mobile no label
		lblMobile = new JLabel("Mobile");
		lblMobile.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMobile.setBounds(41, 219, 104, 29);
		frame.getContentPane().add(lblMobile);

		// Mobile no textfield
		smobiletxt = new JTextField(10);

		smobiletxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				String value = smobiletxt.getText();
				int l = value.length();
				if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					smobiletxt.setEditable(true);

				} else {
					smobiletxt.setEditable(false);

				}
				if (l > 9) {
					smobiletxt.setEditable(false);
				}
			}

		});
		smobiletxt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		smobiletxt.setColumns(10);
		smobiletxt.setBounds(217, 219, 192, 28);
		frame.getContentPane().add(smobiletxt);

		// Button for creating user.

		JButton createbtn = new JButton("Create");
		createbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fname = fsnametxt.getText();
				String faddress = saddresstxt.getText();
				String fmobile = smobiletxt.getText();
				if (fname.length() == 0 || faddress.length() == 0 || fmobile.length() == 0 && fmobile.length() > 10) {
					JFrame f = new JFrame();
					JOptionPane.showMessageDialog(f, "information Missing");
				} else {
					try {

						String sql = "INSERT INTO newsupplier (Name, Address,Mobile) VALUES ('" + fname + "','"
								+ faddress + "','" + fmobile + "')";

						Dbconn.s.executeUpdate(sql);
						// String sql2="CREATE TABLE '"+fname+"'"();
					
					} catch (Exception e1) {
						System.out.println(e1);
					}
					

					clear1();
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

		stable = new JTable();

		stable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TableModel md = stable.getModel();
				int i = stable.getSelectedRow();
				fsnametxt.setText(md.getValueAt(i, 1).toString());
				saddresstxt.setText(md.getValueAt(i, 2).toString());
				smobiletxt.setText(md.getValueAt(i, 3).toString());
			}

		});
		scrollPane.setViewportView(stable);
		stable.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "SN", "Full Name", "Address", "Mobile" }));

		JButton upbtn = new JButton("Update");
		upbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableModel md = stable.getModel();
				int i = stable.getSelectedRow();
				String b = md.getValueAt(i, 0).toString();
				String x = fsnametxt.getText();
				String y = saddresstxt.getText();
				String z = smobiletxt.getText();
				if (x.isEmpty() && y.isEmpty() && z.isEmpty()) {
					JFrame f = new JFrame();
					JOptionPane.showMessageDialog(f, "Information Missing");
				} else {
					try {

						String sql4 = "UPDATE newsupplier SET Name='" + x + "',Address='" + y + "',Mobile='" + z + "' WHERE ID='"+b+"'";

						Dbconn.s.executeUpdate(sql4);
						// String sql2="CREATE TABLE '"+fname+"'"();
						// con.close();
					} catch (Exception e1) {
						System.out.println(e1);
					}
					clear1();

					displayt();
				}

			}
		});
		upbtn.setFont(new Font("Dialog", Font.BOLD, 16));
		upbtn.setBackground(Color.ORANGE);
		upbtn.setBounds(54, 308, 101, 36);
		frame.getContentPane().add(upbtn);

		JButton dtbtn = new JButton("Delete");
		dtbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					TableModel md = stable.getModel();
					int i = stable.getSelectedRow();
					String a = md.getValueAt(i, 0).toString();

					String sql3 = "DELETE FROM newsupplier WHERE ID=" + a;

					Dbconn.s.executeUpdate(sql3);
					// String sql2="CREATE TABLE '"+fname+"'"();
					// con.close();
				} catch (Exception e1) {
					System.out.println(e1);
				}
				clear1();

				displayt();
			}

		});
		dtbtn.setFont(new Font("Dialog", Font.BOLD, 16));
		dtbtn.setBackground(Color.RED);
		dtbtn.setBounds(240, 395, 101, 37);
		frame.getContentPane().add(dtbtn);
		
		JButton bbcbtn = new JButton("Back");
		bbcbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dashboard dbas= new Dashboard();
				dbas.frmDashboard.setVisible(true);
				frame.dispose();
			}
		});
		bbcbtn.setBackground(Color.RED);
		bbcbtn.setFont(new Font("Dialog", Font.BOLD, 16));
		bbcbtn.setBounds(54, 399, 101, 33);
		frame.getContentPane().add(bbcbtn);
		stable.getColumnModel().getColumn(0).setPreferredWidth(47);
		stable.getColumnModel().getColumn(0).setMaxWidth(47);
		stable.getColumnModel().getColumn(1).setPreferredWidth(80);
		stable.getColumnModel().getColumn(1).setMinWidth(22);
		stable.getColumnModel().getColumn(2).setPreferredWidth(77);
		stable.getColumnModel().getColumn(3).setPreferredWidth(78);

	}
}