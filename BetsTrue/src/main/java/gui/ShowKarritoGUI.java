package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Movement;
import domain.NotAdmin;
import domain.Question;
import domain.User;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class ShowKarritoGUI extends JFrame {

	private JPanel contentPane;
	DefaultListModel<Question> l2 = new DefaultListModel<Question>();
	private static NotAdmin user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowKarritoGUI frame = new ShowKarritoGUI(user);
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
	public ShowKarritoGUI(NotAdmin user) {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		BLFacade facade = MainGUI.getBusinessLogic();
		ArrayList<Question> list = ((NotAdmin) facade.getUserByUsername(user.getUsername())).getCart();
		for(Question m : list) {
			if(m.getQuestion()!=null) {
			l2.addElement(m);
			}
		}
		
		JLabel lblNewLabel = new JLabel("Karritoko apustuak:");
		lblNewLabel.setBounds(27, 25, 144, 16);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 64, 366, 163);
		contentPane.add(scrollPane);
		
		JList list_1 = new JList();
		list_1.setModel(l2);
		scrollPane.setViewportView(list_1);
	}
}
