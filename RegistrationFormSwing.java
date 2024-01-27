package com.assignment;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

class Form implements ActionListener{
	JFrame frame;
	JLabel id, name, gender, address, contact;
	String Id, Name, Gender, Address, Contact ;
	JTextField idText, nameText, addressText, contactText;
	JTable table;
	DefaultTableModel tm1;
	JScrollPane s1;
	JRadioButton r1, r2;
	ButtonGroup bg;
	JButton exit, register, delete, update, reset, refresh;
	JPanel p1,p2;
	String[] str = {"ID", "Name", "Gender", "Address", "Contact"};
	String storeGender;
	
	public Form() {
		frame = new JFrame("Registration Form");
		frame.setLayout(null);
		frame.setSize(1000, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		p1 = new JPanel();
		p1.setBounds(0, 0, 300, 550);
		p1.setLayout(null);
		
		p2 = new JPanel();
		p2.setBounds(300, 0, 700, 550);
		p2.setLayout(null);
//		p2.setBackground(Color.black);
		
		id = new JLabel("ID");
		id.setBounds(10, 75, 80, 30);
		id.setFont(new Font("Times New Roman",Font.BOLD ,15));
		idText = new JTextField("");
		idText.setBounds(100, 80, 170, 30);
		p1.add(id);
		p1.add(idText);
		
		name = new JLabel("Name");
		name.setBounds(10, 120, 80, 30);
		name.setFont(new Font("Times New Roman",Font.BOLD ,15));
		nameText = new JTextField("");
		nameText.setBounds(100, 125, 170, 30);
		p1.add(name);
		p1.add(nameText);
		
		gender = new JLabel("Gender");
		gender.setBounds(10, 170, 80, 30);
		gender.setFont(new Font("Times New Roman",Font.BOLD ,15));
		
		r1 = new JRadioButton("Male");
		r1.setBounds(100, 170, 80, 30);
		r2 = new JRadioButton("Female");
		r2.setBounds(180, 170, 80, 30);
		
		bg = new ButtonGroup();
		bg.add(r1);
		bg.add(r2);
		p1.add(gender);
		p1.add(r1);
		p1.add(r2);
		
		address = new JLabel("Address");
		address.setBounds(10, 215, 80, 30);
		address.setFont(new Font("Times New Roman",Font.BOLD ,15));
		addressText = new JTextField("");
		addressText.setBounds(100, 215, 170, 30);
		p1.add(address);
		p1.add(addressText);
		
		
		contact = new JLabel("Contact");
		contact.setBounds(10, 260, 170, 30);
		contact.setFont(new Font("Times New Roman",Font.BOLD ,15));
		contactText = new JTextField("");
		contactText.setBounds(100, 260, 170, 30);
		p1.add(contact);
		p1.add(contactText);
		
		exit = new JButton("Exit");
		exit.setBounds(20, 350, 120, 30);
		p1.add(exit);
		
		register = new JButton("Register");
		register.addActionListener(this);
		register.setBounds(160, 350, 120, 30);
		p1.add(register);
		
		delete = new JButton("Delete");
		delete.addActionListener(this);
		delete.setBounds(20, 400, 120, 30);
		p1.add(delete);
		
		update = new JButton("Update");
		update.addActionListener(this);
		update.setBounds(160, 400, 120, 30);
		p1.add(update);
		
		reset = new JButton("Reset");
		reset.addActionListener(this);
		reset.setBounds(90, 450, 120, 30);
		p1.add(reset);
		
		tm1 = new DefaultTableModel();
		tm1.setColumnIdentifiers(str);
		
		table = new JTable();
		table.setModel(tm1);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.addMouseListener(null);
		s1 = new JScrollPane(table);
		s1.setBounds(0, 20, 680, 400);
		s1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		s1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		p2.add(s1);
		
		refresh = new JButton("Refresh Table");
		refresh.addActionListener(this);
		refresh.setBounds(250, 450, 150, 30);
		p2.add(refresh);
		
		
		frame.add(p1);
		frame.add(p2);
		frame.setVisible(true);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(r1.isSelected()) {
			storeGender = "Male";
		}else {
			storeGender = "Female";
		}
		if(e.getSource() == register) 
		{
			if(idText.getText().trim().equals("") ||nameText.getText().trim().equals("") || (!(r1.isSelected() || r2.isSelected()))|| addressText.getText().trim().equals("") || contactText.getText().trim().equals("")) 
			{
				if(idText.getText().trim().equals("")) 
				{
					JOptionPane.showMessageDialog(frame, "Please enter your id");
				}
				else if(nameText.getText().trim().equals("")) 
				{
					JOptionPane.showMessageDialog(frame, "Please enter your name");
				}
				else if(!(r1.isSelected() || r2.isSelected())) 
				{
					JOptionPane.showMessageDialog(frame, "Specify your gender");
				}
				else if(addressText.getText().trim().equals("")) 
				{
					JOptionPane.showMessageDialog(frame, "Please enter your address");
				}
				else if(contactText.getText().trim().equals("")) 
				{
					JOptionPane.showMessageDialog(frame, "Please enter your contact number..");
				}
			}
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment", "root", "");
//				System.out.println("Connection Establised...");
				Statement st = cn.createStatement();
				
				int x = st.executeUpdate("Insert into registrationform values ('"+idText.getText()+"','"+nameText.getText()+"','"+storeGender+"','"+addressText.getText()+"','"+contactText.getText()+"')");
				
				if(x>0) {
					JOptionPane.showMessageDialog(frame, "Record Updated Successfully");
				}else {
					JOptionPane.showMessageDialog(frame, "Record Not inserted...");
				}
				cn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
				
			}
		}
		
		else if(e.getSource() == refresh) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment", "root", "");
				Statement st = cn.createStatement();
				ResultSet rs = st.executeQuery("Select * from registrationform");
				tm1 = (DefaultTableModel) table.getModel();
				tm1.setRowCount(0);
				while(rs.next()) {
					Object[] row = {rs.getString(1),
									rs.getString("name"),rs.getString("gender"),rs.getString("address"),rs.getString("contact")
					};
					tm1.addRow(row);
				}
				cn.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		else if(e.getSource() == delete) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment", "root", "");
				Statement st = cn.createStatement();

				int x = st.executeUpdate("Delete from registrationform where id = '"+idText.getText()+"'");
				if(x>0) {
					JOptionPane.showMessageDialog(frame, "Record Deleted Succesfully");
				}else {
					JOptionPane.showMessageDialog(frame, "Record Not In Database");
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == update) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment", "root", "");
				Statement st = cn.createStatement();

				int x = st.executeUpdate("update registrationform set name = '"+nameText.getText()+"',gender = '"+storeGender+"', address = '"+addressText.getText()+"' where id='"+idText.getText()+"'");
				
				
				if(x>0) {
					JOptionPane.showMessageDialog(frame, "Record Updated");
				}else {
					JOptionPane.showMessageDialog(frame, "Record Not Updated");
				}
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
		}else if(e.getSource() == exit){
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment", "root", "");
				Statement st = cn.createStatement();
				cn.close();
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
}



public class RegistrationFormSwing 
{
	public static void main(String[] args) {
		new Form();
	}
}
