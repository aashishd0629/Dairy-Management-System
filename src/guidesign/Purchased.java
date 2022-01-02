package guidesign;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;

public class Purchased {

	JFrame frame;
	private JTextField dtxtf;
	private JTextField ntxt;
	private JTextField qtxtf;
	private JTextField rtxtf;
	private JTextField amtxtf;
	private JTable table;
	private JTextField sprtxt;

	/**
	 * Launch the application.
	 */

	public class txtf {
		String dt = dtxtf.getText();
		String nt = ntxt.getText();
		int qt = Integer.parseInt(qtxtf.getText());
		int rt = Integer.parseInt(rtxtf.getText());
		int amt = Integer.parseInt(amtxtf.getText());

	}

	public void searchpk(String str) {
		DefaultTableModel model1 = (DefaultTableModel) table.getModel();
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model1);
		table.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(str));
	}

	public void displaypds() {
		DefaultTableModel sd = (DefaultTableModel) table.getModel();
		int rc = sd.getRowCount();
		// delete the previous table data
		while (rc-- != 0) {
			sd.removeRow(rc);
		}
		try {

			String q1 = "SELECT * FROM precord";
			ResultSet rs = Dbconn.s.executeQuery(q1);
			while (rs.next()) {
				String id = rs.getString(1);
				String date = rs.getString(2);
				String name = rs.getString(3);
				String item = rs.getString(4);
				String quantity = rs.getString(5);
				String rate = rs.getString(6);
				String amount = rs.getString(7);
				String shift = rs.getString(8);
				String ttype = rs.getString(9);
				String[] data = { id, date, name, item, quantity, rate, amount, shift, ttype };
				sd.addRow(data);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	public void curDatetime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		dtxtf.setText(dtf.format(now));

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Purchased window = new Purchased();
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
	public Purchased() {
		initialize();
		displaypds();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1271, 647);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel hlblp = new JLabel("Purchase Record");
		hlblp.setFont(new Font("Tahoma", Font.BOLD, 24));
		hlblp.setBounds(56, 27, 334, 32);
		frame.getContentPane().add(hlblp);

		JLabel dlbl = new JLabel("Date");
		dlbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		dlbl.setBounds(56, 100, 78, 21);
		frame.getContentPane().add(dlbl);

		dtxtf = new JTextField();
		curDatetime();
		dtxtf.setEditable(false);
		dtxtf.setBounds(191, 102, 96, 20);
		frame.getContentPane().add(dtxtf);
		dtxtf.setColumns(10);

		JLabel nlbl = new JLabel("Name");
		nlbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		nlbl.setBounds(56, 132, 78, 29);
		frame.getContentPane().add(nlbl);

		ntxt = new JTextField();
		ntxt.setBounds(191, 133, 96, 20);
		frame.getContentPane().add(ntxt);
		ntxt.setColumns(10);

		JLabel qtylbl = new JLabel("Quantity");
		qtylbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		qtylbl.setBounds(56, 204, 78, 21);
		frame.getContentPane().add(qtylbl);

		qtxtf = new JTextField();
		qtxtf.setBounds(191, 206, 96, 20);
		frame.getContentPane().add(qtxtf);
		qtxtf.setColumns(10);

		JLabel rlbl = new JLabel("Rate");
		rlbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		rlbl.setBounds(56, 242, 78, 21);
		frame.getContentPane().add(rlbl);

		rtxtf = new JTextField();
		rtxtf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int qtt = Integer.parseInt(qtxtf.getText());
				int rtt = Integer.parseInt(rtxtf.getText());
				int tttt = (qtt * rtt);
				amtxtf.setText("" + tttt);
			}
		});
		rtxtf.setBounds(191, 242, 96, 20);
		frame.getContentPane().add(rtxtf);
		rtxtf.setColumns(10);

		JLabel amlbl = new JLabel("Amount");
		amlbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		amlbl.setBounds(56, 277, 78, 21);
		frame.getContentPane().add(amlbl);

		amtxtf = new JTextField();
		amtxtf.setEditable(false);
		amtxtf.setBounds(191, 277, 96, 20);
		frame.getContentPane().add(amtxtf);
		amtxtf.setColumns(10);

		JLabel shlbl = new JLabel("Shift");
		shlbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		shlbl.setBounds(56, 312, 78, 21);
		frame.getContentPane().add(shlbl);

		JComboBox<String> scombo = new JComboBox<String>();
		scombo.setModel(new DefaultComboBoxModel<String>(new String[] { "Morning", "Evening" }));
		scombo.setFont(new Font("Tahoma", Font.BOLD, 16));
		scombo.setBounds(191, 311, 96, 22);
		frame.getContentPane().add(scombo);

		JLabel tlblt = new JLabel("Transaction");
		tlblt.setFont(new Font("Tahoma", Font.BOLD, 16));
		tlblt.setBounds(56, 340, 96, 29);
		frame.getContentPane().add(tlblt);

		JComboBox<String> tcombo = new JComboBox<String>();
		tcombo.setModel(new DefaultComboBoxModel<String>(new String[] { "Cash", "Due", "Cheque" }));
		tcombo.setFont(new Font("Tahoma", Font.BOLD, 16));
		tcombo.setBounds(191, 343, 96, 22);
		frame.getContentPane().add(tcombo);

		JLabel itlbl = new JLabel("Item");
		itlbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		itlbl.setBounds(56, 172, 96, 21);
		frame.getContentPane().add(itlbl);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 16));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Milk", "Curd", "Butter", "Paneer" }));
		comboBox.setBounds(191, 164, 96, 22);
		frame.getContentPane().add(comboBox);

		JButton eprbtn = new JButton("Enter");
		eprbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					txtf pr = new txtf();
					String sh = scombo.getSelectedItem().toString();
					String ttf = tcombo.getSelectedItem().toString();
					String itt = comboBox.getSelectedItem().toString();
					String sql = "INSERT INTO precord (date, name, item, quantity, rate, amount, shift, ttypes) VALUES ('"
							+ pr.dt + "','" + pr.nt + "','" + itt + "', '" + pr.qt + "','" + pr.rt + "','" + pr.amt
							+ "','" + sh + "','" + ttf + "')";
					String sql1 = "INSERT INTO strecord (Date, item, bought) Values ('" + pr.dt + "', '" + itt + "', '"
							+ pr.qt + "')";
					Dbconn.s.executeUpdate(sql1);
					Dbconn.s.executeUpdate(sql);

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
				displaypds();
			}

		});
		eprbtn.setBackground(Color.MAGENTA);
		eprbtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		eprbtn.setBounds(191, 373, 89, 23);
		frame.getContentPane().add(eprbtn);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(447, 97, 776, 296);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Date", "Name", "Item", "Quantity", "Rate", "Amount", "Shift", "Trans" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(49);
		table.getColumnModel().getColumn(0).setMaxWidth(49);
		table.getColumnModel().getColumn(3).setPreferredWidth(53);

		sprtxt = new JTextField();
		sprtxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String spk = sprtxt.getText();
				searchpk(spk);
			}
		});
		sprtxt.setBounds(447, 66, 96, 20);
		frame.getContentPane().add(sprtxt);
		sprtxt.setColumns(10);
		
		JLabel slbl = new JLabel("Search Here");
		slbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		slbl.setBounds(558, 69, 96, 14);
		frame.getContentPane().add(slbl);
		
		JButton bcbtn = new JButton("Back");
		bcbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dashboard dbas = new Dashboard();
				dbas.frmDashboard.setVisible(true);
				frame.dispose();
			}
			
		});
		bcbtn.setBackground(Color.RED);
		bcbtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		bcbtn.setBounds(45, 375, 89, 23);
		frame.getContentPane().add(bcbtn);

	}
}
