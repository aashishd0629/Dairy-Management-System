package guidesign;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DairySoftware {

	JFrame frmNgask;
	private JTextField namet;
	private JTextField quantityt;
	private JTextField amountt;
	private JTextField datt;

	private JTable table1;
	private JTextField sftxt;
	private JTextField rtxt;

	/**
	 * Launch the application.
	 */

	public class txtff {
		String nt = namet.getText();
		int qty = Integer.parseInt(quantityt.getText());
		int rt = Integer.parseInt(rtxt.getText());
		int amtt = Integer.parseInt(amountt.getText());
	}

	public void searchk(String str) {
		DefaultTableModel model1 = (DefaultTableModel) table1.getModel();
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model1);
		table1.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(str));
	}

	public void displayds() {
		DefaultTableModel sd = (DefaultTableModel) table1.getModel();
		int rc = sd.getRowCount();
		// delete the previous table data
		while (rc-- != 0) {
			sd.removeRow(rc);
		}
		try {

			String q1 = "SELECT * FROM salesrecord";
			ResultSet rs = Dbconn.s.executeQuery(q1);
			while (rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				String item = rs.getString(3);
				String Quantity = rs.getString(4);
				String rts = rs.getString(5);
				String amount = rs.getString(6);
				String type = rs.getString(7);
				// type.setFont(new Font("preeti", Font.BOLD,12));
				String date = rs.getString(8);
				String shift = rs.getString(9);
				String[] data = { id, name, item, Quantity, rts, amount, type, date, shift };
				sd.addRow(data);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	public void curDatetime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		datt.setText(dtf.format(now));
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DairySoftware window = new DairySoftware();
					window.frmNgask.setVisible(true);
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DairySoftware() {
		initialize();
		displayds();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Frame of Application

		frmNgask = new JFrame();
		frmNgask.setTitle("NGASK");
		frmNgask.setBounds(100, 100, 1256, 629);
		frmNgask.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNgask.getContentPane().setLayout(null);

		// Title Label

		JLabel head = new JLabel();
		head.setText("Sales Record");
		head.setFont(new Font("Tahoma", Font.PLAIN, 36));
		head.setHorizontalAlignment(SwingConstants.CENTER);
		head.setBounds(25, 28, 350, 56);
		frmNgask.getContentPane().add(head);

		// Tile of sub heading
		JLabel record = new JLabel("Enter Records");
		record.setFont(new Font("Tahoma", Font.BOLD, 24));
		record.setHorizontalAlignment(SwingConstants.CENTER);
		record.setBounds(35, 95, 178, 45);
		frmNgask.getContentPane().add(record);

		// Label of name
		JLabel name = new JLabel("Name");
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setFont(new Font("Tahoma", Font.BOLD, 24));
		name.setBounds(25, 132, 121, 32);
		frmNgask.getContentPane().add(name);

		// Textfield for name
		namet = new JTextField();
		namet.setBounds(146, 135, 137, 29);
		frmNgask.getContentPane().add(namet);
		namet.setColumns(10);

		// Label of Quantity
		JLabel quantity = new JLabel("Quantity\r\n");
		quantity.setHorizontalAlignment(SwingConstants.CENTER);
		quantity.setFont(new Font("Tahoma", Font.BOLD, 24));
		quantity.setBounds(35, 212, 92, 29);
		frmNgask.getContentPane().add(quantity);

		// Textfield for Quantity
		quantityt = new JTextField();
		quantityt.setBounds(146, 216, 137, 27);
		frmNgask.getContentPane().add(quantityt);
		quantityt.setColumns(10);

		// label of amount
		JLabel amount = new JLabel("Amount");
		amount.setHorizontalAlignment(SwingConstants.CENTER);
		amount.setFont(new Font("Tahoma", Font.BOLD, 24));
		amount.setBounds(35, 306, 92, 32);
		frmNgask.getContentPane().add(amount);

		// Textfield for amount
		amountt = new JTextField();
		amountt.setEditable(false);
		amountt.setBounds(146, 309, 137, 32);
		frmNgask.getContentPane().add(amountt);
		amountt.setColumns(10);

		// Label of Transaction Type
		JLabel trans = new JLabel("Transaction");
		trans.setHorizontalAlignment(SwingConstants.CENTER);
		trans.setFont(new Font("Tahoma", Font.BOLD, 24));
		trans.setBounds(31, 349, 96, 32);
		frmNgask.getContentPane().add(trans);

		// Combo box of transaction type
		JComboBox<String> transtype = new JComboBox<String>();
		transtype.setModel(new DefaultComboBoxModel<String>(new String[] { "Cash", "Due" }));
		transtype.setFont(new Font("Tahoma", Font.BOLD, 24));
		transtype.setBounds(146, 352, 137, 32);
		frmNgask.getContentPane().add(transtype);

		// Label of Date
		JLabel datet = new JLabel("Date");
		datet.setHorizontalAlignment(SwingConstants.CENTER);
		datet.setFont(new Font("Tahoma", Font.BOLD, 24));
		datet.setBounds(35, 403, 92, 22);
		frmNgask.getContentPane().add(datet);

		// Textfield for date
		datt = new JTextField();
		curDatetime();
		datt.setEditable(false);
		datt.setBounds(146, 403, 137, 29);
		frmNgask.getContentPane().add(datt);
		datt.setColumns(10);

		// Label of Shift
		JLabel shiftl = new JLabel("Shift");
		shiftl.setHorizontalAlignment(SwingConstants.CENTER);
		shiftl.setFont(new Font("Tahoma", Font.BOLD, 24));
		shiftl.setBounds(35, 456, 92, 22);
		frmNgask.getContentPane().add(shiftl);

		// combo for Shift
		JComboBox<String> shift = new JComboBox<String>();
		shift.setModel(new DefaultComboBoxModel<String>(new String[] { "Morning", "Evening" }));
		shift.setFont(new Font("Tanhoma", Font.BOLD, 24));
		shift.setBounds(146, 449, 137, 32);
		frmNgask.getContentPane().add(shift);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(471, 103, 737, 445);
		frmNgask.getContentPane().add(scrollPane);

		table1 = new JTable();
		table1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollPane.setViewportView(table1);
		table1.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Name", "Item", "Quantity", "Rate", "Amount", "Transaction", "Date", "Shift" }));
		table1.getColumnModel().getColumn(0).setPreferredWidth(44);
		table1.getColumnModel().getColumn(0).setMaxWidth(44);
		table1.getColumnModel().getColumn(1).setMaxWidth(2000);
		table1.getColumnModel().getColumn(2).setPreferredWidth(60);
		table1.getColumnModel().getColumn(3).setPreferredWidth(53);
		table1.getColumnModel().getColumn(3).setMaxWidth(200);
		table1.getColumnModel().getColumn(5).setPreferredWidth(56);
		table1.getColumnModel().getColumn(5).setMaxWidth(200);
		table1.getColumnModel().getColumn(6).setMaxWidth(200);
		table1.getColumnModel().getColumn(7).setMaxWidth(100);
		table1.getColumnModel().getColumn(8).setMaxWidth(100);

		JButton bcbtn = new JButton("Back");
		bcbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dashboard dbas = new Dashboard();
				dbas.frmDashboard.setVisible(true);
				frmNgask.dispose();
			}
		});
		bcbtn.setBackground(Color.RED);
		bcbtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		bcbtn.setBounds(35, 505, 89, 30);
		frmNgask.getContentPane().add(bcbtn);

		sftxt = new JTextField();
		sftxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String ske = sftxt.getText();
				searchk(ske);
			}
		});
		sftxt.setBounds(471, 56, 96, 20);
		frmNgask.getContentPane().add(sftxt);
		sftxt.setColumns(10);

		JLabel lblrt = new JLabel("Rate");
		lblrt.setHorizontalAlignment(SwingConstants.CENTER);
		lblrt.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblrt.setBounds(35, 266, 92, 29);
		frmNgask.getContentPane().add(lblrt);

		rtxt = new JTextField();
		rtxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				//txtff txf = new txtff();
				int qty = Integer.parseInt(quantityt.getText());
				int rt = Integer.parseInt(rtxt.getText());
				int ttt = (qty * rt);
				amountt.setText("" + ttt);
			}
		});
		rtxt.setBounds(146, 266, 137, 27);
		frmNgask.getContentPane().add(rtxt);
		rtxt.setColumns(10);

		JLabel itmlbl = new JLabel("Item");
		itmlbl.setHorizontalAlignment(SwingConstants.CENTER);
		itmlbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		itmlbl.setBounds(35, 175, 92, 26);
		frmNgask.getContentPane().add(itmlbl);

		JComboBox<String> itemt = new JComboBox<String>();
		itemt.setFont(new Font("Tahoma", Font.BOLD, 16));
		itemt.setModel(new DefaultComboBoxModel<String>(new String[] { "Milk", "Curd", "Butter", "Paneer" }));
		itemt.setBounds(146, 175, 137, 30);
		frmNgask.getContentPane().add(itemt);

		JButton btnNewButton = new JButton("Enter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				txtff tf = new txtff();
				String dt = datt.getText();
				String vs = shift.getSelectedItem().toString();
				String vtt = transtype.getSelectedItem().toString();
				String itm = itemt.getSelectedItem().toString();
				if (tf.nt.length() == 0) {
					JFrame f = new JFrame();
					JOptionPane.showMessageDialog(f, "information Missing");
				} else {
					try {

						String sql = "INSERT INTO salesrecord (Name, item, Quantity, Rate, Amount, Ttype, Date, Shift) VALUES ('"
								+ tf.nt + "','" + itm + "','" + tf.qty + "','" + tf.rt + "','" + tf.amtt + "','" + vtt
								+ "','" + dt + "','" + vs + "')";
						String sql1= "INSERT INTO strecord (Date, item, sold) Values ('"+dt+"', '"+itm+"', '"+tf.qty+"')";
						Dbconn.s.executeUpdate(sql1);
						Dbconn.s.executeUpdate(sql);

					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1);
					}
					displayds();
				}
			}
		});
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(146, 505, 137, 32);
		frmNgask.getContentPane().add(btnNewButton);
		
		JLabel slbl = new JLabel("Search Here");
		slbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		slbl.setBounds(577, 59, 113, 14);
		frmNgask.getContentPane().add(slbl);

		// Ok button

	}
}
