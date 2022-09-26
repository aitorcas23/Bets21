package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.User;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class MezuakBidaliGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private static User user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MezuakBidaliGUI frame = new MezuakBidaliGUI(user);
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
	public MezuakBidaliGUI(User user) {
		BLFacade facade = MainGUI.getBusinessLogic();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				JFrame a = new MezuakIrakurriGUI(user);
				a.setVisible(true);
				setVisible(false);
			}
		});
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(12, 64, 408, 144);
		contentPane.add(textArea);
		
		JButton btnNewButton = new JButton("Atzera");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new MezuakIrakurriGUI(user);
				a.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setBounds(12, 13, 97, 25);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(170, 14, 250, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("User:");
		lblNewLabel.setBounds(126, 18, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabelMessage = new JLabel("");
		lblNewLabelMessage.setBounds(136, 47, 126, 14);
		contentPane.add(lblNewLabelMessage);
		
		JButton btnNewButton_1 = new JButton("Bidali");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!facade.sendMessage(user.getUsername(), textField.getText(), textArea.getText())) {
					lblNewLabelMessage.setText("User not found");
				}else {
					lblNewLabelMessage.setText("Message sent");
				}
			}
		});
		btnNewButton_1.setBounds(116, 215, 166, 25);
		contentPane.add(btnNewButton_1);
	}
}
