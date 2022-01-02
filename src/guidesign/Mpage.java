package guidesign;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.sql.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Mpage {

	JFrame frmPaymentPortal;
	private JPanel contentPane;

	private JTextField datxt;
	private JTextField aidtxt;
	private JTextField anametxt;
	private JTextField aqttxt;
	private JTextField aamounttxt;
	private JTextField rtxt;
	String psum;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mpage window = new Mpage();
					window.frmPaymentPortal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */

	public void cleartxt() {
		aidtxt.setText("");
		anametxt.setText("");
		aamounttxt.setText("");
		aqttxt.setText("");
		rtxt.setText("");
	}

	public class textf {
		int j = Integer.parseInt(aidtxt.getText());
		String k = anametxt.getText();
		String l = datxt.getText();
		int m = Integer.parseInt(aamounttxt.getText());
		int n = Integer.parseInt(aqttxt.getText());
		int o = Integer.parseInt(rtxt.getText());
	}

	public void curDatetime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		datxt.setText(dtf.format(now));
	}

	public Mpage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPaymentPortal = new JFrame();
		frmPaymentPortal.setTitle("Payment Portal");
		frmPaymentPortal.setBounds(100, 100, 1188, 621);
		frmPaymentPortal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPaymentPortal.getContentPane().setLayout(null);
		
		JLabel lblhpp = new JLabel("Payment System");
		lblhpp.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblhpp.setHorizontalAlignment(SwingConstants.CENTER);
		lblhpp.setBounds(169, 53, 853, 56);
		frmPaymentPortal.getContentPane().add(lblhpp);
		
		JLabel lbld = new JLabel("Date");
		lbld.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbld.setBounds(67, 146, 86, 14);
		frmPaymentPortal.getContentPane().add(lbld);
		
		JLabel lblid = new JLabel("ID");
		lblid.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblid.setBounds(67, 184, 86, 14);
		frmPaymentPortal.getContentPane().add(lblid);
		
		JLabel lblname = new JLabel("Name");
		lblname.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblname.setBounds(67, 214, 86, 14);
		frmPaymentPortal.getContentPane().add(lblname);
		
		JLabel lblqt = new JLabel("Quantity");
		lblqt.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblqt.setBounds(67, 293, 73, 17);
		frmPaymentPortal.getContentPane().add(lblqt);
		
		JLabel lblamt = new JLabel("Amount");
		lblamt.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblamt.setBounds(67, 376, 86, 17);
		frmPaymentPortal.getContentPane().add(lblamt);
		
		datxt = new JTextField();
		curDatetime();
		datxt.setEditable(false);
		datxt.setBounds(169, 143, 96, 20);
		frmPaymentPortal.getContentPane().add(datxt);
		datxt.setColumns(10);
		
		aidtxt = new JTextField();
		aidtxt.setBounds(169, 178, 96, 20);
		frmPaymentPortal.getContentPane().add(aidtxt);
		aidtxt.setColumns(10);
		
		anametxt = new JTextField();
		anametxt.setBounds(169, 213, 96, 20);
		frmPaymentPortal.getContentPane().add(anametxt);
		anametxt.setColumns(10);
		
		aqttxt = new JTextField();
		aqttxt.setBounds(169, 290, 96, 20);
		frmPaymentPortal.getContentPane().add(aqttxt);
		aqttxt.setColumns(10);
		
		aamounttxt = new JTextField();
		aamounttxt.setEditable(false);
		aamounttxt.setBounds(169, 376, 96, 20);
		frmPaymentPortal.getContentPane().add(aamounttxt);
		aamounttxt.setColumns(10);
		
		JLabel ilbl = new JLabel("Item");
		ilbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		ilbl.setBounds(67, 242, 86, 26);
		frmPaymentPortal.getContentPane().add(ilbl);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Milk", "Curd", "Paneer", "Butter"}));
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox.setBounds(169, 244, 96, 22);
		frmPaymentPortal.getContentPane().add(comboBox);
		
		JTextArea rtarea = new JTextArea();
		rtarea.setBounds(575, 141, 447, 314);
		frmPaymentPortal.getContentPane().add(rtarea);
		
		JButton rbtn = new JButton("Receipt");
		rbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textf a = new textf();
				
				try {
				String itmm= comboBox.getSelectedItem().toString();
				String rq= "INSERT INTO accinfo (date,acno,name,item,qtty,rate,amtt) VALUES('"+a.l+"','"+a.j+"','"+a.k+"','"+itmm+"','"+a.n+"','"+a.o+"','"+a.m+"')";
				Dbconn.s.executeUpdate(rq);
				String sql2 = "SELECT ida FROM accinfo ORDER BY ida DESC LIMIT 1";
				ResultSet rs1 = Dbconn.s.executeQuery(sql2);
				if (rs1.next()) {
				String	psum = rs1.getString(1);
				
				
							
				rtarea.append("\n Receipt \t\t Bill No:"+psum+" \n" + "ID:" + a.j + "\t" + "Name:" + a.k + "\t" + "Date:" + a.l + "\n"
						+ "Item:"+itmm+"\t"+ "Quantity:" + a.n + "\t"+"Rate:"+a.o+"\t" + "Amt:" + a.m);
				}
				}
				catch(Exception e6) {
					
				}

				cleartxt();
			}
		});
		rbtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		rbtn.setBounds(169, 413, 96, 29);
		frmPaymentPortal.getContentPane().add(rbtn);
		
		JButton pbtn = new JButton("Print");
		pbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				boolean doPrint=rtarea.print();
				if (doPrint) {
					JOptionPane.showMessageDialog(null,"Completed");
					rtarea.setText("");	
					}
				}catch (PrinterException e5) {

					}
				
			}
			
			
		});
		pbtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		pbtn.setBounds(169, 471, 96, 29);
		frmPaymentPortal.getContentPane().add(pbtn);
		
		JLabel lblrt = new JLabel("Rate");
		lblrt.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblrt.setBounds(67, 337, 86, 17);
		frmPaymentPortal.getContentPane().add(lblrt);
		
		rtxt = new JTextField();
		rtxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int qt=  Integer.parseInt(aqttxt.getText());
				int rtt= Integer.parseInt(rtxt.getText());
				int tt= (qt*rtt);
				aamounttxt.setText("" +  tt); 
			}
		});
		rtxt.setBounds(169, 337, 96, 20);
		frmPaymentPortal.getContentPane().add(rtxt);
		rtxt.setColumns(10);
		
		JButton bacbtn = new JButton("Back");
		bacbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dashboard dbas= new Dashboard();
				dbas.frmDashboard.setVisible(true);
				frmPaymentPortal.dispose();
			}
		});
		bacbtn.setBackground(Color.RED);
		bacbtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		bacbtn.setBounds(29, 471, 89, 28);
		frmPaymentPortal.getContentPane().add(bacbtn);

	}
}
