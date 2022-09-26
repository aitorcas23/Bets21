package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.NotAdmin;
import domain.User;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import businessLogic.BLFacade;
import domain.Movement;
import domain.NotAdmin;
import domain.User;

public class ErabiltzaileaJarraituGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private static NotAdmin user;
	/**
	 * Launch the application.
	 */
	private final JLabel lblNewLabelMessage = new JLabel(""); //$NON-NLS-1$ //$NON-NLS-2$
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErabiltzaileaJarraituGUI frame = new ErabiltzaileaJarraituGUI(user);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 * @param user 
	 */
	public ErabiltzaileaJarraituGUI(NotAdmin user) {
		BLFacade facade = MainGUI.getBusinessLogic();
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Aukeratu erabiltzailea:");
		lblNewLabel.setBounds(158, 41, 137, 16);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(145, 73, 116, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		lblNewLabelMessage.setBounds(93, 361, 130, 14);
		JButton btnNewButton = new JButton("Jarraitu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!facade.followUser(user, textField.getText(), Double.parseDouble(textField_1.getText()))) {
						lblNewLabelMessage.setText("User does not exist");
					}
					setVisible(false);
				}catch(NumberFormatException j) {
					lblNewLabelMessage.setText("Percentage must be a Number");
				}
			}
		});
		btnNewButton.setBounds(157, 204, 97, 25);
		contentPane.add(btnNewButton);
		
		textField_1 = new JTextField();
		textField_1.setBounds(145, 161, 116, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Apostatutako portzentaia:");
		lblNewLabel_2.setBounds(148, 135, 147, 16);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("Mugimenduak Ikusi");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new ShowMovementsGUI(facade.getUserByUserName(textField.getText()));
				a.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(127, 104, 147, 21);
		contentPane.add(btnNewButton_1);
	}
}
