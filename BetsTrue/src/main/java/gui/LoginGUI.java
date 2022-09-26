package gui;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import businessLogic.BLFacade;
import domain.NotAdmin;
import domain.User;
import domain.Admin;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class LoginGUI extends JFrame{
	private JTextField textField;
	private JPasswordField passwordField;
	
	public LoginGUI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				JFrame a = new MainGUI();
				a.setVisible(true);
				setVisible(false);
			}
		});
		
		getContentPane().setLayout(null);
		setSize(495, 290);
		setTitle("Login");
		
		textField = new JTextField();
		textField.setBounds(168, 53, 86, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(187, 36, 61, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(184, 96, 70, 14);
		getContentPane().add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(168, 121, 86, 20);
		getContentPane().add(passwordField);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(168, 186, 89, 23);
		getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(168, 161, 205, 14);
		getContentPane().add(lblNewLabel_2);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade facade = MainGUI.getBusinessLogic();
				User u = facade.isLoginCorrect(textField.getText(), passwordField.getText());
				if( u == null) {
					lblNewLabel_2.setText("Username or password is incorrect");
				}else {
					if(u.isAdmin()) {
						JFrame a = new MainGUIAdmin((Admin) u);
						a.setVisible(true);
						setVisible(false);
					}else {
						JFrame a = new MainGUIUser((NotAdmin) u);
						a.setVisible(true);
						setVisible(false);
					}
				}
			}
		});
				
	}
}
