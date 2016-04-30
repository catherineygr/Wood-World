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
		frmTutorial.getContentPane().setBackground(new Color(128, 0, 0));
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
		
		JLabel lblInformationalText = new JLabel("INFORMATIONAL TEXT");
		lblInformationalText.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformationalText.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblInformationalText.setBounds(10, 84, 414, 316);
		frmTutorial.getContentPane().add(lblInformationalText);
	}
}
