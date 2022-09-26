package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Message;
import domain.User;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class MezuakIrakurriGUI extends JFrame {

	private JPanel contentPane;
	private static User user;
	private DefaultListModel<Message> messages = new DefaultListModel<Message>();
	private DefaultComboBoxModel<User> users = new DefaultComboBoxModel<User>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MezuakIrakurriGUI frame = new MezuakIrakurriGUI(user);
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
	public MezuakIrakurriGUI(User user) {
		BLFacade facade = MainGUI.getBusinessLogic();
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		for(User u : facade.getUsers(user)) {
			users.addElement(u);
		}
		comboBox.setModel(users);
		comboBox.setBounds(257, 13, 163, 22);
		contentPane.add(comboBox);
		
		
		
		JButton btnNewButton_1 = new JButton("Atzera");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(300, 215, 97, 25);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Mezuaren igorlea:");
		lblNewLabel.setBounds(132, 16, 113, 16);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 410, 158);
		contentPane.add(scrollPane);
		
		JList list = new JList();
		User selectedUser = (User) comboBox.getSelectedItem();
		for(Message m : facade.getMessages(user)) {
			if(m.getSender().getUsername().equals(selectedUser.getUsername()) || m.getReceiver().getUsername().equals(selectedUser.getUsername())) {
				messages.addElement(m);
			}
		}
		list.setModel(messages);
		scrollPane.setViewportView(list);
		
		JButton btnNewButton = new JButton("Mezuak idatzi");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new MezuakBidaliGUI(user);
				a.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setBounds(61, 215, 208, 25);
		contentPane.add(btnNewButton);
		
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				User su = (User) comboBox.getSelectedItem();
				messages.clear();
				for(Message m : facade.getMessages(user)) {
					if(m.getSender().getUsername().equals(su.getUsername()) || m.getReceiver().getUsername().equals(su.getUsername())) {
						messages.addElement(m);
					}
				}
				list.setModel(messages);
			}
		});
	}
}
