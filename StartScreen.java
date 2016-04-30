package edu.mccc.cos210.woodworld;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;

public class StartScreen {

	private JFrame frame;
	private ButtonGroup bg = new ButtonGroup();

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
	public StartScreen() {
		initialize();
	}
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1080, 725);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JButton btnStart = new JButton("Start");
		btnStart.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Woodworld1 ww1 = new Woodworld1();
				ww1.NewScreen();
			}
		});
		btnStart.setBounds(455, 569, 115, 45);
		frame.getContentPane().add(btnStart);
		
		JLabel lblWelcomeToThe = new JLabel("Welcome to the Wonders of");
		lblWelcomeToThe.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));
		lblWelcomeToThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToThe.setBounds(10, 11, 1044, 44);
		frame.getContentPane().add(lblWelcomeToThe);
		
		JLabel lblWoodworld = new JLabel("WOODWORLD");
		lblWoodworld.setForeground(new Color(139, 69, 19));
		lblWoodworld.setFont(new Font("Tahoma", Font.BOLD, 48));
		lblWoodworld.setHorizontalAlignment(SwingConstants.CENTER);
		lblWoodworld.setBounds(10, 56, 1044, 50);
		frame.getContentPane().add(lblWoodworld);
		
		JButton btnTutorial = new JButton("Tutorial");
		btnTutorial.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnTutorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tutorial tut = new Tutorial();
				tut.NewScreen();
			}
		});
		btnTutorial.setBounds(10, 630, 100, 40);
		frame.getContentPane().add(btnTutorial);
		
		JButton btnCredits = new JButton("Credits");
		btnCredits.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnCredits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Credits cred = new Credits();
				cred.NewScreen();
			}
		});
		btnCredits.setBounds(954, 630, 100, 40);
		frame.getContentPane().add(btnCredits);
		
		ManneDemo man1 = new ManneDemo();
		man1.setBounds(56, 110, 160, 175);
		frame.getContentPane().add(man1);
		
		ManneDemo man2 = new ManneDemo();
		man2.setBounds(310, 110, 160, 175);
		frame.getContentPane().add(man2);
		
		ManneDemo man3 = new ManneDemo();
		man3.setBounds(588, 110, 160, 175);
		frame.getContentPane().add(man3);
		
		ManneDemo man4 = new ManneDemo();
		man4.setBounds(848, 110, 160, 175);
		frame.getContentPane().add(man4);
		
		JToggleButton tglbtnElCapitan = new JToggleButton("Col. Mustard");
		tglbtnElCapitan.setBounds(75, 296, 121, 23);
		frame.getContentPane().add(tglbtnElCapitan);
		
		JToggleButton tglbtnMissScarlet = new JToggleButton("Miss Scarlet");
		tglbtnMissScarlet.setBounds(331, 296, 121, 23);
		frame.getContentPane().add(tglbtnMissScarlet);
		
		JToggleButton tglbtnProfPlum = new JToggleButton("Prof. Plum");
		tglbtnProfPlum.setBounds(608, 296, 121, 23);
		frame.getContentPane().add(tglbtnProfPlum);
		
		JToggleButton tglbtnMrGreeen = new JToggleButton("Mr. Green");
		tglbtnMrGreeen.setBounds(869, 296, 121, 23);
		frame.getContentPane().add(tglbtnMrGreeen);
		
		JRadioButton rdbtnStage = new JRadioButton("Stage 1");
		rdbtnStage.setBounds(75, 510, 75, 25);
		frame.getContentPane().add(rdbtnStage);
		
		JRadioButton rdbtnStage_1 = new JRadioButton("Stage 2");
		rdbtnStage_1.setBounds(478, 510, 75, 25);
		frame.getContentPane().add(rdbtnStage_1);

		JRadioButton rdbtnStage_2 = new JRadioButton("Stage 3");
		rdbtnStage_2.setBounds(926, 510, 75, 25);
		frame.getContentPane().add(rdbtnStage_2);

		bg.add(rdbtnStage);
		bg.add(rdbtnStage_1);
		bg.add(rdbtnStage_2);
	}
}