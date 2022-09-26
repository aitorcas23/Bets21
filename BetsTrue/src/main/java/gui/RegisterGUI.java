package gui;

import javax.swing.JFrame;
import javax.swing.JTextField;

import businessLogic.BLFacade;
import domain.NotAdmin;

import javax.swing.JLabel;
import javax.swing.JPasswordField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;

public class RegisterGUI extends JFrame{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JTextField textField_3;
	private JTextField textField_4;
	public RegisterGUI() {
		getContentPane().setLayout(null);
		setSize(495, 290);
		setTitle("Register");
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
		
		textField = new JTextField();
		textField.setBounds(70, 36, 86, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(95, 11, 46, 14);
		getContentPane().add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(259, 36, 86, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Surname");
		lblNewLabel_1.setBounds(275, 11, 59, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Username");
		lblNewLabel_2.setBounds(80, 67, 78, 14);
		getContentPane().add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(70, 92, 86, 20);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Password");
		lblNewLabel_3.setBounds(269, 67, 78, 14);
		getContentPane().add(lblNewLabel_3);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(259, 92, 86, 20);
		getContentPane().add(passwordField);
		
		JLabel lblNewLabel_4 = new JLabel("Email");
		lblNewLabel_4.setBounds(95, 123, 46, 14);
		getContentPane().add(lblNewLabel_4);
		
		textField_3 = new JTextField();
		textField_3.setBounds(70, 148, 86, 20);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("CreditCard");
		lblNewLabel_5.setBounds(275, 123, 86, 14);
		getContentPane().add(lblNewLabel_5);
		
		textField_4 = new JTextField();
		textField_4.setBounds(259, 148, 86, 20);
		getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setBounds(132, 179, 229, 14);
		getContentPane().add(lblNewLabel_6);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.setBounds(166, 205, 89, 23);
		getContentPane().add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade facade = MainGUI.getBusinessLogic();
				try {
					if(null==facade.register(textField.getText(), textField_1.getText(), textField_2.getText(), passwordField.getText(), textField_3.getText(),Integer.parseInt(textField_4.getText()))) {
						lblNewLabel_6.setText("Username is being used");
					}else {
						JFrame a = new MainGUIUser((NotAdmin) facade.getUserByUsername(textField_2.getText()));
						a.setVisible(true);
						setVisible(false);
					}
				}catch(NumberFormatException exceprion) {
					lblNewLabel_6.setText("Credit card must be a number");
				}
				
			}
		});
	}
}
