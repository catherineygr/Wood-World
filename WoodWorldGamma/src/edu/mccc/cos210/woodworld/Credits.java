package edu.mccc.cos210.woodworld;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.Color;

public class Credits {

	private JFrame frmCredits;

	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Credits window = new Credits();
					window.frmCredits.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Credits() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCredits = new JFrame();
		frmCredits.getContentPane().setBackground(new Color(255, 255, 255));
		frmCredits.setTitle("CREDITS");
		frmCredits.setBounds(100, 100, 450, 450);
		//frmCredits.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCredits.getContentPane().setLayout(null);
		frmCredits.setLocationRelativeTo(null);
		
		JLabel lblCreditWhereCredit = new JLabel("Credit where credit is due");
		lblCreditWhereCredit.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreditWhereCredit.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblCreditWhereCredit.setBounds(10, 11, 414, 60);
		frmCredits.getContentPane().add(lblCreditWhereCredit);
		
		JLabel lblProjectManager = new JLabel("Project Manager:  Elliot");
		lblProjectManager.setHorizontalAlignment(SwingConstants.CENTER);
		lblProjectManager.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblProjectManager.setBounds(10, 110, 414, 40);
		frmCredits.getContentPane().add(lblProjectManager);
		
		JLabel lblSoftwareArchitectCatherine = new JLabel("Software Architect:  Catherine");
		lblSoftwareArchitectCatherine.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSoftwareArchitectCatherine.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoftwareArchitectCatherine.setBounds(10, 160, 414, 40);
		frmCredits.getContentPane().add(lblSoftwareArchitectCatherine);
		
		JLabel lblSoftwareDeveloperSergey = new JLabel("Software Developer:  Sergey");
		lblSoftwareDeveloperSergey.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSoftwareDeveloperSergey.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoftwareDeveloperSergey.setBounds(10, 210, 414, 40);
		frmCredits.getContentPane().add(lblSoftwareDeveloperSergey);
		
		JLabel lblUserExpDesigner = new JLabel("User Exp. Designer:  Richard");
		lblUserExpDesigner.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserExpDesigner.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUserExpDesigner.setBounds(10, 260, 414, 40);
		frmCredits.getContentPane().add(lblUserExpDesigner);
	}
}
