package edu.mccc.cos210.woodworld;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JToolBar;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;

public class StartScreen {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartScreen window = new StartScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StartScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Woodworld1 ww1 = new Woodworld1();
				ww1.NewScreen();
			}
		});
		btnStart.setBounds(345, 569, 89, 23);
		frame.getContentPane().add(btnStart);
		
		JLabel lblWelcomeToThe = new JLabel("Welcome to the Wonders of");
		lblWelcomeToThe.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));
		lblWelcomeToThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToThe.setBounds(10, 11, 764, 44);
		frame.getContentPane().add(lblWelcomeToThe);
		
		JLabel lblWoodworld = new JLabel("WOODWORLD");
		lblWoodworld.setForeground(new Color(139, 69, 19));
		lblWoodworld.setFont(new Font("Tahoma", Font.BOLD, 48));
		lblWoodworld.setHorizontalAlignment(SwingConstants.CENTER);
		lblWoodworld.setBounds(10, 56, 764, 50);
		frame.getContentPane().add(lblWoodworld);
		
		JButton btnTutorial = new JButton("Tutorial");
		btnTutorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tutorial tut = new Tutorial();
				tut.NewScreen();
			}
		});
		btnTutorial.setBounds(10, 627, 89, 23);
		frame.getContentPane().add(btnTutorial);
		
		JButton btnCredits = new JButton("Credits");
		btnCredits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Credits cred = new Credits();
				cred.NewScreen();
			}
		});
		btnCredits.setBounds(685, 627, 89, 23);
		frame.getContentPane().add(btnCredits);
		
		JToggleButton tglbtnElCapitan = new JToggleButton("Col. Mustard");
		tglbtnElCapitan.setBounds(30, 295, 121, 23);
		frame.getContentPane().add(tglbtnElCapitan);
		
		JToggleButton tglbtnMissScarlet = new JToggleButton("Miss Scarlet");
		tglbtnMissScarlet.setBounds(224, 295, 121, 23);
		frame.getContentPane().add(tglbtnMissScarlet);
		
		JToggleButton tglbtnProfPlum = new JToggleButton("Prof. Plum");
		tglbtnProfPlum.setBounds(433, 295, 121, 23);
		frame.getContentPane().add(tglbtnProfPlum);
		
		JToggleButton tglbtnMrGreeen = new JToggleButton("Mr. Green");
		tglbtnMrGreeen.setBounds(631, 295, 121, 23);
		frame.getContentPane().add(tglbtnMrGreeen);
		
		JRadioButton rdbtnStage = new JRadioButton("Stage 1");
		rdbtnStage.setBounds(73, 511, 109, 23);
		frame.getContentPane().add(rdbtnStage);
		
		JRadioButton rdbtnStage_1 = new JRadioButton("Stage 2");
		rdbtnStage_1.setBounds(353, 511, 70, 23);
		frame.getContentPane().add(rdbtnStage_1);
		
		JRadioButton rdbtnStage_2 = new JRadioButton("Stage 3");
		rdbtnStage_2.setBounds(646, 511, 70, 23);
		frame.getContentPane().add(rdbtnStage_2);
	}
}
