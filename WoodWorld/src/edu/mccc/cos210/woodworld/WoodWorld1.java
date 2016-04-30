package edu.mccc.cos210.woodworld;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class WoodWorld1 {

	private JFrame frmWoodworld;

	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WoodWorld1 window = new WoodWorld1();
					window.frmWoodworld.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WoodWorld1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWoodworld = new JFrame();
		frmWoodworld.getContentPane().setBackground(new Color(160, 82, 45));
		frmWoodworld.setTitle("WoodWorld");
		frmWoodworld.setBounds(100, 100, 800, 725);
		frmWoodworld.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWoodworld.getContentPane().setLayout(null);
		frmWoodworld.setLocationRelativeTo(null);
		
		JButton btnTutorial = new JButton("Tutorial");
		btnTutorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Tutorial tut = new Tutorial();
				tut.NewScreen();
			}
		});
		btnTutorial.setBounds(658, 555, 116, 42);
		frmWoodworld.getContentPane().add(btnTutorial);
		
		JButton btnCredits = new JButton("Credits");
		btnCredits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Credits cred = new Credits();
				cred.NewScreen();
			}
		});
		btnCredits.setBounds(658, 608, 116, 42);
		frmWoodworld.getContentPane().add(btnCredits);
		
		JButton btnStage = new JButton("Stage 1");
		btnStage.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnStage.setBounds(680, 20, 85, 40);
		frmWoodworld.getContentPane().add(btnStage);
		
		JButton btnStage_1 = new JButton("Stage 2");
		btnStage_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnStage_1.setBounds(680, 100, 85, 40);
		frmWoodworld.getContentPane().add(btnStage_1);
		
		JButton btnStage_2 = new JButton("Stage 3");
		btnStage_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnStage_2.setBounds(680, 180, 85, 40);
		frmWoodworld.getContentPane().add(btnStage_2);
		
		JButton btnNewButton = new JButton("Walk");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(20, 20, 85, 40);
		frmWoodworld.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Run");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.setBounds(20, 100, 85, 40);
		frmWoodworld.getContentPane().add(btnNewButton_1);
		
		JButton btnSit = new JButton("Sit");
		btnSit.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSit.setBounds(20, 180, 85, 40);
		frmWoodworld.getContentPane().add(btnSit);
		
		JButton btnStand = new JButton("Stand");
		btnStand.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnStand.setBounds(20, 260, 85, 40);
		frmWoodworld.getContentPane().add(btnStand);
		
		JButton btnJump = new JButton("Jump");
		btnJump.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnJump.setBounds(20, 340, 85, 40);
		frmWoodworld.getContentPane().add(btnJump);
		
		JButton btnDance = new JButton("Dance");
		btnDance.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDance.setBounds(20, 420, 85, 40);
		frmWoodworld.getContentPane().add(btnDance);
		
		
		
		JPanel3D panel = new JPanel3D();
		panel.setBounds(115, 20, 555, 525);
		frmWoodworld.getContentPane().add(panel);
	}
	
}