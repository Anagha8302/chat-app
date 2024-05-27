package com.elitesoftwares.chatapp.views;
import java.lang.Exception;
import java.sql.SQLException;
import java.util.Arrays;  // Import for the Arrays class

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.elitesoftwares.chatapp.dao.UserDAO;
import com.elitesoftwares.chatapp.dto.UserDTO;
import com.elitesoftwares.chatapp.utils.UserInfo;

public class UserScreen extends JFrame {
	private JTextField useridtxt;
	private JPasswordField passwordField;

	
	public static void main(String[] args) {
		
					UserScreen window = new UserScreen();
					
				
	}
	UserDAO userDAO= new UserDAO();

	private void doLogin() {
		String userid = useridtxt.getText();
		char[] password= passwordField.getPassword();
		//UserDAO userDAO= new UserDAO();
		UserDTO userDTO= new UserDTO(userid,password);
		try {
			String message="";
			if(userDAO.isLogin(userDTO)) {
				message="Welcome "+userid+"!";
				UserInfo.USER_NAME=userid;
				JOptionPane.showMessageDialog(this,message);
				setVisible(false);
				dispose();
				DashBoard dashBoard=new DashBoard(message);
				dashBoard.setVisible(true);
				}
			else {
				message="Invalid UserId or Password";
				JOptionPane.showMessageDialog(this,message);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void register(){
		String userid = useridtxt.getText();
		char[] password= passwordField.getPassword();
		UserDTO userDTO= new UserDTO(userid,password);
		try {
		int result=userDAO.add(userDTO);
		if(result>0) {
			//System.out.println("Record added...");
			JOptionPane.showMessageDialog(this, "Registered Successfully!");		}
		else {
			JOptionPane.showMessageDialog(this, "Registration Failed!");		}
		
		}
		catch(ClassNotFoundException | SQLException ex) {
			System.out.println("DB issue...");
			ex.printStackTrace();
		}
		catch(Exception ex){
		  System.out.println("Some generic exception....");
		  ex.printStackTrace();
		}
		System.out.println("userid:"+userid+" password:"+password);
		
	}

	/**
	 * Create the application.
	 */
	public UserScreen() {
		setResizable(false);
		setTitle("LOGIN");
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(235, 49, 107, 17);
		getContentPane().add(lblNewLabel);
		
		useridtxt = new JTextField();
		useridtxt.setBounds(283, 106, 168, 17);
		getContentPane().add(useridtxt);
		useridtxt.setColumns(10);
		
		JLabel useridlbl = new JLabel("Userid");
		useridlbl.setFont(new Font("Arial", Font.BOLD, 12));
		useridlbl.setBounds(172, 107, 47, 17);
		getContentPane().add(useridlbl);
		
		JLabel pwdlbl = new JLabel("Password");
		pwdlbl.setFont(new Font("Arial", Font.BOLD, 12));
		pwdlbl.setBounds(172, 167, 91, 14);
		getContentPane().add(pwdlbl);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(283, 164, 168, 20);
		getContentPane().add(passwordField);
		
		JButton loginbt = new JButton("Login");
		loginbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doLogin();
			}
		});
		loginbt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		loginbt.setBounds(152, 240, 89, 23);
		getContentPane().add(loginbt);
		
		JButton registerbt = new JButton("Register");
		registerbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		registerbt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		registerbt.setBounds(333, 240, 107, 23);
		getContentPane().add(registerbt);
		setBackground(Color.WHITE);
		setSize(700,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
}
