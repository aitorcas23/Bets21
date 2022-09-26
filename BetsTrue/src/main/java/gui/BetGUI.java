package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Bet;
import domain.MultiBet;
import domain.NotAdmin;
import domain.Question;
import domain.Quote;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class BetGUI extends JFrame {

	private JPanel contentPane;
	private static NotAdmin notAdmin;
	private static Question question;
	private static MultiBet multiBet;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private DefaultComboBoxModel<Quote> answerList = new DefaultComboBoxModel<Quote>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BetGUI frame = new BetGUI(notAdmin, question, multiBet);
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
	public BetGUI(NotAdmin notAdmin, Question question, MultiBet multiBet) {
		this.notAdmin = notAdmin;
		this.question = question;
		this.multiBet = multiBet;
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				JFrame a = new FindQuestionsGUIUser(notAdmin, multiBet);
				a.setVisible(true);
				setVisible(false);
			}
		});
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Bet");
		
		JLabel lblNewLabel = new JLabel("Event:");
		lblNewLabel.setBounds(31, 27, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Question:");
		lblNewLabel_1.setBounds(225, 27, 93, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabelEvent = new JLabel("");
		lblNewLabelEvent.setBounds(14, 52, 250, 14);
		lblNewLabelEvent.setText(question.getEvent().toString());
		contentPane.add(lblNewLabelEvent);
		
		JLabel lblNewLabelQuestion = new JLabel("");
		lblNewLabelQuestion.setBounds(208, 52, 216, 14);
		lblNewLabelQuestion.setText(question.toString());
		contentPane.add(lblNewLabelQuestion);
		
		JLabel lblNewLabelMessage = new JLabel("");
		lblNewLabelMessage.setBounds(119, 202, 164, 14);
		contentPane.add(lblNewLabelMessage);
		
		for(Quote q : question.getQuotes()) {
			answerList.addElement(q);
		}
		JComboBox comboBoxAnswers = new JComboBox();
		comboBoxAnswers.setBounds(119, 169, 164, 22);
		comboBoxAnswers.setModel(answerList);
		contentPane.add(comboBoxAnswers);
		
		JButton btnNewButton = new JButton("Bet");
		btnNewButton.setBounds(155, 227, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				BLFacade facade = MainGUI.getBusinessLogic();
				facade.bet(notAdmin,(Quote) comboBoxAnswers.getSelectedItem(), multiBet);
				JFrame a = new FindQuestionsGUIUser(notAdmin, multiBet);
				a.setVisible(true);
				setVisible(false);
			}
		});
		
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Select answer");
		lblNewLabel_2.setBounds(166, 144, 98, 14);
		contentPane.add(lblNewLabel_2);
	}
}
