package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.NotAdmin;
import domain.User;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddMoneyGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private static NotAdmin user;
	private JLabel lblNewLabelMessage;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMoneyGUI frame = new AddMoneyGUI(user);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddMoneyGUI(NotAdmin user) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
			}
		});
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Add money");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(161, 61, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(161, 134, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		lblNewLabelMessage = new JLabel("");
		lblNewLabelMessage.setBounds(161, 173, 237, 14);
		contentPane.add(lblNewLabelMessage);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setBounds(86, 210, 89, 23);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				BLFacade facade = MainGUI.getBusinessLogic();
				double money = 0;
				int creditCard = -1;
				try {
					creditCard = Integer.parseInt(textField.getText());
				}catch(NumberFormatException exception) {
					lblNewLabelMessage.setText("Credit card must be a number");
				}
				
				try {
					money = Double.parseDouble(textField_1.getText());
				}catch(NumberFormatException exception){
					lblNewLabelMessage.setText("Money must be a number");
				}
				int i = facade.addMoney(money, user, creditCard);
				switch(i) {
				case 0: lblNewLabelMessage.setText("Money added");
				break;
				case 1: lblNewLabelMessage.setText("Wrong credit card");
				break;
				case 2: lblNewLabelMessage.setText("Money must be positive");
				break;
				}
			}
		});
		
		
		JLabel lblNewLabel = new JLabel("Credit card");
		lblNewLabel.setBounds(161, 36, 86, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Money amount");
		lblNewLabel_1.setBounds(161, 109, 86, 14);
		contentPane.add(lblNewLabel_1);
		
		btnNewButton_1 = new JButton("Remove");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade facade = MainGUI.getBusinessLogic();
				double money = 0;
				int creditCard = -1;
				try {
					creditCard = Integer.parseInt(textField.getText());
				}catch(NumberFormatException exception) {
					lblNewLabelMessage.setText("Credit card must be a number");
				}
				
				try {
					money = Double.parseDouble(textField_1.getText());
				}catch(NumberFormatException exception){
					lblNewLabelMessage.setText("Money must be a number");
				}
				int i = facade.removeMoney(money, user, creditCard);
				switch(i) {
				case 0: lblNewLabelMessage.setText("Money removed");
				break;
				case 1: lblNewLabelMessage.setText("Wrong credit card");
				break;
				case 2: lblNewLabelMessage.setText("Money must be positive");
				break;
				case 3: lblNewLabelMessage.setText("Not enough money");
				}
				
			}
		});
		btnNewButton_1.setBounds(229, 210, 89, 23);
		contentPane.add(btnNewButton_1);
	}
}
