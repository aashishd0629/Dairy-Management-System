package guidesign;

import java.awt.EventQueue;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Detailsview {

	 JFrame frame;
	private JTable table;
	private JTextField blstxt;
	private JLabel slbl;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	
	
	public void searchk(String str) {
		DefaultTableModel model= (DefaultTableModel)table.getModel();
		TableRowSorter<DefaultTableModel> tr= new TableRowSorter<DefaultTableModel>(model);
		table.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(str));
	}
	
	public void displayact() {
		DefaultTableModel sd = (DefaultTableModel) table.getModel();
		int rc = sd.getRowCount();
		// delete the previous table data
		while (rc-- != 0) {
			sd.removeRow(rc);
		}
		try {

			String q1 = "SELECT * FROM accinfo";
			ResultSet rs = Dbconn.s.executeQuery(q1);
			while (rs.next()) {
				String id = rs.getString(1);
				String date = rs.getString(2);
				String acno = rs.getString(3);
				String name = rs.getString(4);
				String item = rs.getString(5);
				String qty = rs.getString(6);
				String rate = rs.getString(7);
				String amount = rs.getString(8);
				String[] data = { id, date, acno, name, item, qty, rate, amount };
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
					Detailsview window = new Detailsview();
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
	public Detailsview() {
		initialize();
		displayact();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1240, 623);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(560, 67, 629, 373);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
	//	table.setEditable(false);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Bill NO", "Date", "ACNO", "Name", "Item", "Quantity", "Rate", "Amount"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(62);
		
		blstxt = new JTextField();
		blstxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String skey =blstxt.getText();
				searchk(skey);
			}
		});
	
		blstxt.setBounds(560, 23, 96, 20);
		frame.getContentPane().add(blstxt);
		blstxt.setColumns(10);
		
		slbl = new JLabel("Search");
		slbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		slbl.setBounds(668, 23, 70, 17);
		frame.getContentPane().add(slbl);
		
		lblNewLabel = new JLabel("Bill Details");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(742, 41, 183, 26);
		frame.getContentPane().add(lblNewLabel);
		
		JButton bbtn = new JButton("Back");
		bbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Dashboard dbas = new Dashboard();
				dbas.frmDashboard.setVisible(true);
				frame.dispose();
			}
		});
		bbtn.setBackground(Color.RED);
		bbtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		bbtn.setBounds(27, 70, 170, 50);
		frame.getContentPane().add(bbtn);
	}
}
