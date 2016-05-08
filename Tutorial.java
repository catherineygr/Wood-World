package edu.mccc.cos210.woodworld;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

public class Tutorial {

	private JFrame frmTutorial;

	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tutorial window = new Tutorial();
					window.frmTutorial.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Tutorial() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTutorial = new JFrame();
		frmTutorial.setTitle("Tutorial");
		frmTutorial.getContentPane().setBackground(new Color(255, 255, 255));
		frmTutorial.setBackground(new Color(255, 255, 255));
		frmTutorial.setBounds(100, 100, 450, 450);
		//frmTutorial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTutorial.getContentPane().setLayout(null);
		frmTutorial.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("WoodWorld Tutorial");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(10, 11, 414, 62);
		frmTutorial.getContentPane().add(lblNewLabel);
		
		JLabel tutorialtext = new JLabel("To Start; choose 1-4 Mannequin(s)");
		tutorialtext.setHorizontalAlignment(SwingConstants.CENTER);
		tutorialtext.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tutorialtext.setBounds(10, 84, 414, 35);
		frmTutorial.getContentPane().add(tutorialtext);
		
		JLabel tutorialtext1 = new JLabel("Next; Choose a stage");
		tutorialtext1.setHorizontalAlignment(SwingConstants.CENTER);
		tutorialtext1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tutorialtext1.setBounds(10, 119, 414, 35);
		frmTutorial.getContentPane().add(tutorialtext1);
		
		JLabel tutorialtext2 = new JLabel("Then; Press START");
		tutorialtext2.setHorizontalAlignment(SwingConstants.CENTER);
		tutorialtext2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tutorialtext2.setBounds(10, 154, 414, 35);
		frmTutorial.getContentPane().add(tutorialtext2);
		
		JLabel tutorialtext3 = new JLabel("Finally; Use the 'action' buttons to control the Mannequin(s)");
		tutorialtext3.setHorizontalAlignment(SwingConstants.CENTER);
		tutorialtext3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tutorialtext3.setBounds(10, 189, 414, 35);
		frmTutorial.getContentPane().add(tutorialtext3);
		
		JLabel tutorialtext4 = new JLabel("Use; 'Menu' + 'LoadScript' to load your own Script");
		tutorialtext4.setHorizontalAlignment(SwingConstants.CENTER);
		tutorialtext4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tutorialtext4.setBounds(10, 224, 414, 35);
		frmTutorial.getContentPane().add(tutorialtext4);
		
		JLabel tutorialtext5 = new JLabel("Use; The 'Text Bar' to write your own Script");
		tutorialtext5.setHorizontalAlignment(SwingConstants.CENTER);
		tutorialtext5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tutorialtext5.setBounds(10, 259, 414, 35);
		frmTutorial.getContentPane().add(tutorialtext5);
	}
}