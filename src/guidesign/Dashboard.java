package guidesign;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

public class Dashboard {

	JFrame frmDashboard;
	JTable table;
	private JTextField pstxt;
	private JTextField sstxt;
	private JTextField stotxt;
	private JTextField ssstxt;
	private JTextField ptxtfc;
	private JTextField sctctf;
	private JTextField ssctxtf;
	private JTextField pptxt;
	private JTextField psstxt;
	private JTextField pssstxt;
	private JTextField pbtxt;
	private JTextField sbtxt;
	private JTextField ssbtxt;

	/**
	 * Launch the application.
	 * 
	 */

	public void mpsum() {
		try {
			String sql2 = "SELECT SUM(bought) FROM strecord WHERE item='Milk'";

			ResultSet rs1 = Dbconn.s.executeQuery(sql2);

			if (rs1.next()) {
				String psum = rs1.getString("SUM(bought)");
				pstxt.setText(psum);

			}

		} catch (Exception e) {

		}
	}
	
	public void cpsum() {
		try {
			String sql3 = "SELECT SUM(bought) FROM strecord WHERE item='Curd'";

			ResultSet rs2 = Dbconn.s.executeQuery(sql3);

			if (rs2.next()) {
				String cppsum = rs2.getString("SUM(bought)");
				ptxtfc.setText(cppsum);

			}

		} catch (Exception e) {

		}
	}
	
	public void ppsum() {
		try {
			String sql4 = "SELECT SUM(bought) FROM strecord WHERE item='Paneer'";

			ResultSet rs3 = Dbconn.s.executeQuery(sql4);

			if (rs3.next()) {
				String pppsum = rs3.getString("SUM(bought)");
				pptxt.setText(pppsum);

			}

		} catch (Exception e) {

		}
	}
	
	public void bpsum() {
		try {
			String sql5 = "SELECT SUM(bought) FROM strecord WHERE item='Butter'";

			ResultSet rs4 = Dbconn.s.executeQuery(sql5);

			if (rs4.next()) {
				String bppsum = rs4.getString("SUM(bought)");
				pbtxt.setText(bppsum);

			}

		} catch (Exception e) {

		}
	}
	
	public void mssum() {
		try {
			String sql6 = "SELECT SUM(sold) FROM strecord WHERE item='Milk'";

			ResultSet rs5 = Dbconn.s.executeQuery(sql6);

		while(rs5.next()) {
				String ssum = rs5.getString("SUM(sold)");
				sstxt.setText(ssum);

			}

		} catch (Exception e) {

		}
	}
	
	public void cssum() {
		try {
			String sql6 = "SELECT SUM(sold) FROM strecord WHERE item='Curd'";

			ResultSet rs6 = Dbconn.s.executeQuery(sql6);

		while(rs6.next()) {
				String csssum = rs6.getString("SUM(sold)");
				sctctf.setText(csssum);

			}

		} catch (Exception e) {

		}
	}
	
	public void pssum() {
		try {
			String sql7 = "SELECT SUM(sold) FROM strecord WHERE item='Paneer'";

			ResultSet rs7 = Dbconn.s.executeQuery(sql7);

		while(rs7.next()) {
				String csssum = rs7.getString("SUM(sold)");
				psstxt.setText(csssum);

			}

		} catch (Exception e) {

		}
	}
	
	public void bssum() {
		try {
			String sql8 = "SELECT SUM(sold) FROM strecord WHERE item='Butter'";

			ResultSet rs8 = Dbconn.s.executeQuery(sql8);

		while(rs8.next()) {
				String csssum = rs8.getString("SUM(sold)");
				sbtxt.setText(csssum);

			}

		} catch (Exception e) {

		}
	}
	

	public void displayst() {
		DefaultTableModel sd = (DefaultTableModel) table.getModel();
		int rc = sd.getRowCount();
		// delete the previous table data
		while (rc-- != 0) {
			sd.removeRow(rc);
		}
		try {

			String q1 = "SELECT * FROM strecord";
			ResultSet rs = Dbconn.s.executeQuery(q1);
			while (rs.next()) {
				String date = rs.getString(1);
				String item = rs.getString(2);
				String bought = rs.getString(3);
				String sold = rs.getString(4);

				String[] data = { date, item, bought, sold };
				sd.addRow(data);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}
	
	public void mstock() {
		
		int ps= Integer.parseInt(pstxt.getText());
		int ss= Integer.parseInt(sstxt.getText());
		int stt= ps-ss;
		stotxt.setText(""+stt);
	}
	
	public void cstock() {
		int cp= Integer.parseInt(ptxtfc.getText());
		int css= Integer.parseInt(sctctf.getText());
		int cst= cp-css;
		ssctxtf.setText(""+cst);
	}
	public void pstock() {
		int pp= Integer.parseInt(pptxt.getText());
		int pss= Integer.parseInt(psstxt.getText());
		int pst= pp-pss;
		pssstxt.setText(""+pst);
	}
	public void bstock() {
		int bp= Integer.parseInt(pbtxt.getText());
		int bs= Integer.parseInt(sbtxt.getText());
		int bst= bp-bs;
		ssbtxt.setText(""+bst);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard window = new Dashboard();
					window.frmDashboard.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Dashboard() {
		initialize();
		displayst();
		mpsum();
		mssum();
		mstock();
		cpsum();
		ppsum();
		bpsum();
		cssum();
		pssum();
		bssum();
		cstock();
		pstock();
		bstock();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDashboard = new JFrame();
		frmDashboard.setTitle("Dashboard");
		frmDashboard.setBounds(100, 100, 1342, 629);
		frmDashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDashboard.getContentPane().setLayout(null);

		JButton dsbtn = new JButton("Daily sales");
		dsbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmDashboard.dispose();
				DairySoftware das = new DairySoftware();
				das.frmNgask.setVisible(true);

			}
		});
		dsbtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		dsbtn.setBounds(57, 54, 141, 33);
		frmDashboard.getContentPane().add(dsbtn);

		JButton nsbtn = new JButton("Suppliers");
		nsbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmDashboard.dispose();
				NewSupplier ns = new NewSupplier();
				ns.frame.setVisible(true);
			}
		});
		nsbtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		nsbtn.setBounds(57, 190, 141, 33);
		frmDashboard.getContentPane().add(nsbtn);

		JButton csbtn = new JButton("Consumers");
		csbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmDashboard.dispose();
				Nuser nu = new Nuser();
				nu.frame.setVisible(true);
			}
		});
		csbtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		csbtn.setBounds(57, 263, 141, 33);
		frmDashboard.getContentPane().add(csbtn);

		JButton pybtn = new JButton("Payment");
		pybtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmDashboard.dispose();
				Mpage pp = new Mpage();
				pp.frmPaymentPortal.setVisible(true);
			}
		});
		pybtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		pybtn.setBounds(57, 333, 141, 33);
		frmDashboard.getContentPane().add(pybtn);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(363, 58, 602, 449);
		frmDashboard.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Date", "Item", "Bought", "Sold" }));

		JLabel mlbl = new JLabel("Milk");
		mlbl.setHorizontalAlignment(SwingConstants.CENTER);
		mlbl.setFont(new Font("Tahoma", Font.BOLD, 18));
		mlbl.setBounds(1079, 25, 89, 25);
		frmDashboard.getContentPane().add(mlbl);

		JLabel mplbl = new JLabel("Purchased");
		mplbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		mplbl.setBounds(1008, 53, 89, 34);
		frmDashboard.getContentPane().add(mplbl);

		JLabel mslbl = new JLabel("Sold");
		mslbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		mslbl.setBounds(1008, 92, 89, 25);
		frmDashboard.getContentPane().add(mslbl);

		pstxt = new JTextField();
		pstxt.setEditable(false);
		pstxt.setFont(new Font("Tahoma", Font.BOLD, 16));
		pstxt.setBounds(1118, 62, 96, 20);
		frmDashboard.getContentPane().add(pstxt);
		pstxt.setColumns(10);

		sstxt = new JTextField();
		sstxt.setEditable(false);
		sstxt.setFont(new Font("Tahoma", Font.BOLD, 16));
		sstxt.setBounds(1118, 96, 96, 20);
		frmDashboard.getContentPane().add(sstxt);
		sstxt.setColumns(10);
		
		JLabel ssslbl = new JLabel("Stock");
		ssslbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		ssslbl.setBounds(1008, 127, 89, 20);
		frmDashboard.getContentPane().add(ssslbl);
		
		stotxt = new JTextField();
		stotxt.setEditable(false);
		stotxt.setFont(new Font("Tahoma", Font.BOLD, 16));
		stotxt.setBounds(1118, 127, 96, 20);
		frmDashboard.getContentPane().add(stotxt);
		stotxt.setColumns(10);
		
		JLabel culbl = new JLabel("Curd");
		culbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		culbl.setHorizontalAlignment(SwingConstants.CENTER);
		culbl.setBounds(1089, 165, 79, 25);
		frmDashboard.getContentPane().add(culbl);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(1008, 158, 252, 2);
		frmDashboard.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(254, 54, 17, 469);
		frmDashboard.getContentPane().add(separator_1);
		
		ssstxt = new JTextField();
		ssstxt.setBounds(362, 27, 96, 20);
		frmDashboard.getContentPane().add(ssstxt);
		ssstxt.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Search Here");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(468, 25, 89, 22);
		frmDashboard.getContentPane().add(lblNewLabel);
		
		JButton pcbtn = new JButton("Purchased");
		pcbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmDashboard.dispose();
				Purchased pcs = new Purchased();
				pcs.frame.setVisible(true);
			}
		});
		pcbtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		pcbtn.setBounds(57, 118, 141, 33);
		frmDashboard.getContentPane().add(pcbtn);
		
		JButton binbtn = new JButton("Bill Info");
		binbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmDashboard.dispose();
				Detailsview dv = new Detailsview();
				dv.frame.setVisible(true);
			}
		});
		binbtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		binbtn.setBounds(57, 398, 141, 33);
		frmDashboard.getContentPane().add(binbtn);
		
		JLabel clbl = new JLabel("Purchased");
		clbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		clbl.setBounds(1008, 201, 89, 22);
		frmDashboard.getContentPane().add(clbl);
		
		JLabel cslbl = new JLabel("Sold");
		cslbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		cslbl.setBounds(1008, 239, 89, 14);
		frmDashboard.getContentPane().add(cslbl);
		
		JLabel csslbl = new JLabel("Stock");
		csslbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		csslbl.setBounds(1008, 268, 93, 14);
		frmDashboard.getContentPane().add(csslbl);
		
		ptxtfc = new JTextField();
		ptxtfc.setEditable(false);
		ptxtfc.setBounds(1118, 203, 96, 20);
		frmDashboard.getContentPane().add(ptxtfc);
		ptxtfc.setColumns(10);
		
		sctctf = new JTextField();
		sctctf.setEditable(false);
		sctctf.setBounds(1118, 238, 96, 20);
		frmDashboard.getContentPane().add(sctctf);
		sctctf.setColumns(10);
		
		ssctxtf = new JTextField();
		ssctxtf.setEditable(false);
		ssctxtf.setBounds(1118, 268, 96, 20);
		frmDashboard.getContentPane().add(ssctxtf);
		ssctxtf.setColumns(10);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(997, 305, 252, 2);
		frmDashboard.getContentPane().add(separator_2);
		
		JLabel plbl = new JLabel("Paneer");
		plbl.setHorizontalAlignment(SwingConstants.CENTER);
		plbl.setFont(new Font("Tahoma", Font.BOLD, 18));
		plbl.setBounds(1089, 311, 96, 25);
		frmDashboard.getContentPane().add(plbl);
		
		JLabel pplbl = new JLabel("Purchased");
		pplbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		pplbl.setBounds(1008, 337, 89, 25);
		frmDashboard.getContentPane().add(pplbl);
		
		JLabel pslbl = new JLabel("Sold");
		pslbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		pslbl.setBounds(1008, 363, 89, 20);
		frmDashboard.getContentPane().add(pslbl);
		
		JLabel psslbl = new JLabel("Stock");
		psslbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		psslbl.setBounds(1008, 394, 89, 16);
		frmDashboard.getContentPane().add(psslbl);
		
		pptxt = new JTextField();
		pptxt.setEditable(false);
		pptxt.setBounds(1118, 341, 96, 20);
		frmDashboard.getContentPane().add(pptxt);
		pptxt.setColumns(10);
		
		psstxt = new JTextField();
		psstxt.setEditable(false);
		psstxt.setBounds(1118, 365, 96, 20);
		frmDashboard.getContentPane().add(psstxt);
		psstxt.setColumns(10);
		
		pssstxt = new JTextField();
		pssstxt.setEditable(false);
		pssstxt.setBounds(1118, 394, 96, 20);
		frmDashboard.getContentPane().add(pssstxt);
		pssstxt.setColumns(10);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(997, 443, 252, 2);
		frmDashboard.getContentPane().add(separator_3);
		
		JLabel blbl = new JLabel("Butter");
		blbl.setHorizontalAlignment(SwingConstants.CENTER);
		blbl.setFont(new Font("Tahoma", Font.BOLD, 18));
		blbl.setBounds(1089, 447, 96, 25);
		frmDashboard.getContentPane().add(blbl);
		
		JLabel pblbl = new JLabel("Purchased\r\n");
		pblbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		pblbl.setBounds(1008, 474, 89, 25);
		frmDashboard.getContentPane().add(pblbl);
		
		JLabel sblbl = new JLabel("Sold");
		sblbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		sblbl.setBounds(1008, 499, 89, 25);
		frmDashboard.getContentPane().add(sblbl);
		
		JLabel ssblbl = new JLabel("Stock");
		ssblbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		ssblbl.setBounds(1008, 528, 89, 25);
		frmDashboard.getContentPane().add(ssblbl);
		
		pbtxt = new JTextField();
		pbtxt.setEditable(false);
		pbtxt.setBounds(1118, 478, 96, 20);
		frmDashboard.getContentPane().add(pbtxt);
		pbtxt.setColumns(10);
		
		sbtxt = new JTextField();
		sbtxt.setEditable(false);
		sbtxt.setBounds(1118, 503, 96, 20);
		frmDashboard.getContentPane().add(sbtxt);
		sbtxt.setColumns(10);
		
		ssbtxt = new JTextField();
		ssbtxt.setEditable(false);	
		ssbtxt.setBounds(1118, 532, 96, 20);
		frmDashboard.getContentPane().add(ssbtxt);
		ssbtxt.setColumns(10);

	}
}
