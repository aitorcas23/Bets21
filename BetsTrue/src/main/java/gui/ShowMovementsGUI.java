package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Movement;
import domain.NotAdmin;
import domain.User;

import javax.swing.JList;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

public class ShowMovementsGUI extends JFrame {

	private JPanel contentPane;
	DefaultListModel<Movement> l2 = new DefaultListModel<Movement>();
	private static NotAdmin user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowMovementsGUI frame = new ShowMovementsGUI(user);
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
	public ShowMovementsGUI(NotAdmin user) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Movements");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		BLFacade facade = MainGUI.getBusinessLogic();
		ArrayList<Movement> list = ((NotAdmin) facade.getUserByUsername(user.getUsername())).getMovements();
		for(Movement m : list) {
			l2.addElement(m);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 414, 205);
		contentPane.add(scrollPane);
		
		JList list_1 = new JList();
		list_1.setModel(l2);
		scrollPane.setViewportView(list_1);
		
		JLabel lblNewLabel = new JLabel("Total money: " + ((NotAdmin) facade.getUserByUsername(user.getUsername())).getMoney() + "€");
		lblNewLabel.setBounds(31, 11, 362, 14);
		contentPane.add(lblNewLabel);
	}
}
