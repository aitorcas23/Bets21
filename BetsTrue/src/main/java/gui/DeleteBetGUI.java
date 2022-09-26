package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Bet;
import domain.Movement;
import domain.MultiBet;
import domain.NotAdmin;
import domain.User;

import javax.swing.JList;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class DeleteBetGUI extends JFrame {

	private JPanel contentPane;
	DefaultComboBoxModel<MultiBet> l = new DefaultComboBoxModel<MultiBet>();
	DefaultListModel<MultiBet> model = new DefaultListModel<MultiBet>();
	private static NotAdmin user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteBetGUI frame = new DeleteBetGUI(user);
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
	public DeleteBetGUI(NotAdmin user) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		BLFacade facade = MainGUI.getBusinessLogic();
		ArrayList<MultiBet> list = ((NotAdmin) facade.getUserByUsername(user.getUsername())).getMultiBets();
		for(MultiBet m : list) {
			l.addElement(m);
			model.addElement(m);
		}
		
		JLabel lblNewLabel = new JLabel("Select bet");
		lblNewLabel.setBounds(156, 11, 224, 22);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 44, 414, 174);
		contentPane.add(scrollPane);
		
		JList deleteBetList = new JList();
		deleteBetList.setModel(model);
		scrollPane.setViewportView(deleteBetList);
		
		JButton btnNewButton = new JButton("Delete Bet");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MultiBet a= (MultiBet) deleteBetList.getSelectedValue();
				model.removeElement(a);
				BLFacade facade = MainGUI.getBusinessLogic();
				try {
					facade.removeMultiBet(a, true);
				}catch(NullPointerException exception){
					lblNewLabel.setText("No bets to select");
				}
				
				
			}
		});
		btnNewButton.setBounds(156, 229, 111, 21);
		contentPane.add(btnNewButton);
	}
}


