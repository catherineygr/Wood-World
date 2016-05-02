package edu.mccc.cos210.woodworld;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;


public class WoodWorld {
	JPanel jp;
	final static String STARTPANEL = "Test start";
	final static String WOODWORLD = "Test WoodWorld";
	WoodWorld() {
		JFrame jf = new JFrame("Wood World");
		jf.setLayout(new BorderLayout());
		JTextField console = createConsole();
		jf.add(console, BorderLayout.SOUTH);
		
		JPanel startjp = createStart();
		JPanel wwjp = createWoodWorld();
		
		jp= new JPanel(new CardLayout());
		jp.add(startjp, STARTPANEL);
		jp.add(wwjp, WOODWORLD);
		jp.setPreferredSize(startjp.getPreferredSize());
		jf.add(jp);
		jf.setJMenuBar(createMenu());
		jf.pack();
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
		
	}
	private JPanel createWoodWorld() {
		JPanel wwjp = new JPanel();
		wwjp.setBackground(new Color(160, 82, 45));
		wwjp.setBounds(100, 100, 1080, 725);
		

		JPanel3D panel = new JPanel3D();
		panel.setBounds(115, 20, 930, 525);
		wwjp.add(panel);
		
		JButton btnTutorial = new JButton("Tutorial");
		btnTutorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Tutorial tut = new Tutorial();
				Tutorial.NewScreen();
			}
		});
		btnTutorial.setBounds(20, 555, 116, 42);
		wwjp.add(btnTutorial);
		
		JButton btnCredits = new JButton("Credits");
		btnCredits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Credits cred = new Credits();
				Credits.NewScreen();
			}
		});
		btnCredits.setBounds(20, 608, 116, 42);
		wwjp.add(btnCredits);

		JButton btnNewButton = new JButton("Walk");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(20, 20, 85, 40);
		wwjp.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Run");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.setBounds(20, 100, 85, 40);
		wwjp.add(btnNewButton_1);
		
		JButton btnSit = new JButton("Sit");
		btnSit.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSit.setBounds(20, 180, 85, 40);
		wwjp.add(btnSit);
		
		JButton btnStand = new JButton("Stand");
		btnStand.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnStand.setBounds(20, 260, 85, 40);
		wwjp.add(btnStand);
		
		JButton btnJump = new JButton("Jump");
		btnJump.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnJump.setBounds(20, 340, 85, 40);
		wwjp.add(btnJump);
		
		JButton btnDance = new JButton("Dance");
		btnDance.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDance.setBounds(20, 420, 85, 40);
		btnDance.addActionListener(ae->{panel.mannyDance();});
		wwjp.add(btnDance);
		
		
		return wwjp;
	}
	private JTextField createConsole() {
		// TODO Auto-generated method stub
		//put stuff for scripting here
		return new JTextField();
	}
	private JPanel createStart() {
		JPanel start = new JPanel();
		start.setLayout(null);
		start.setBounds(100, 100, 1080, 725);
		
		JButton btnStart = new JButton("Start");
		btnStart.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnStart.addActionListener(al->{
			CardLayout cl = (CardLayout)(jp.getLayout());
			cl.show(jp, WOODWORLD);
		});
		btnStart.setBounds(455, 569, 115, 45);
		start.add(btnStart);
		
		JLabel lblWelcomeToThe = new JLabel("Welcome to the Wonders of");
		lblWelcomeToThe.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));
		lblWelcomeToThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToThe.setBounds(10, 11, 1044, 44);
		start.add(lblWelcomeToThe);
		
		JLabel lblWoodworld = new JLabel("WOODWORLD");
		lblWoodworld.setForeground(new Color(139, 69, 19));
		lblWoodworld.setFont(new Font("Tahoma", Font.BOLD, 48));
		lblWoodworld.setHorizontalAlignment(SwingConstants.CENTER);
		lblWoodworld.setBounds(10, 56, 1044, 50);
		start.add(lblWoodworld);
		
		JButton btnTutorial = new JButton("Tutorial");
		btnTutorial.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnTutorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tutorial tut = new Tutorial();
				tut.NewScreen();
			}
		});
		btnTutorial.setBounds(10, 630, 100, 40);
		start.add(btnTutorial);
		
		JButton btnCredits = new JButton("Credits");
		btnCredits.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnCredits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Credits cred = new Credits();
				cred.NewScreen();
			}
		});
		btnCredits.setBounds(954, 630, 100, 40);
		start.add(btnCredits);
		
//		ManneDemo man1 = new ManneDemo();
//		man1.setBounds(56, 110, 160, 175);
//		start.add(man1);
//		
//		ManneDemo man2 = new ManneDemo();
//		man2.setBounds(310, 110, 160, 175);
//		start.add(man2);
//		
//		ManneDemo man3 = new ManneDemo();
//		man3.setBounds(588, 110, 160, 175);
//		start.add(man3);
//		
//		ManneDemo man4 = new ManneDemo();
//		man4.setBounds(848, 110, 160, 175);
//		start.add(man4);
		
		JToggleButton tglbtnElCapitan = new JToggleButton("Col. Mustard");
		tglbtnElCapitan.setBounds(75, 296, 121, 23);
		start.add(tglbtnElCapitan);
		
		JToggleButton tglbtnMissScarlet = new JToggleButton("Miss Scarlet");
		tglbtnMissScarlet.setBounds(331, 296, 121, 23);
		start.add(tglbtnMissScarlet);
		
		JToggleButton tglbtnProfPlum = new JToggleButton("Prof. Plum");
		tglbtnProfPlum.setBounds(608, 296, 121, 23);
		start.add(tglbtnProfPlum);
		
		JToggleButton tglbtnMrGreeen = new JToggleButton("Mr. Green");
		tglbtnMrGreeen.setBounds(869, 296, 121, 23);
		start.add(tglbtnMrGreeen);
		
		JRadioButton rdbtnStage = new JRadioButton("Stage 1");
		rdbtnStage.setBounds(75, 510, 75, 25);
		start.add(rdbtnStage);
		
		JRadioButton rdbtnStage_1 = new JRadioButton("Stage 2");
		rdbtnStage_1.setBounds(478, 510, 75, 25);
		start.add(rdbtnStage_1);

		JRadioButton rdbtnStage_2 = new JRadioButton("Stage 3");
		rdbtnStage_2.setBounds(926, 510, 75, 25);
		start.add(rdbtnStage_2);
		
		ButtonGroup bg = new ButtonGroup();

		bg.add(rdbtnStage);
		bg.add(rdbtnStage_1);
		bg.add(rdbtnStage_2);
		return start;
	}
	private JMenuBar createMenu(){
			JMenuBar mb = new JMenuBar();
			
			JMenu fm = new JMenu("File");
			JMenuItem startb = new JMenuItem("Start Menu");
			startb.addActionListener(al->{
				CardLayout cl = (CardLayout)(jp.getLayout());
				cl.show(jp, STARTPANEL);
			});
			fm.add(startb);
			JMenuItem lb = new JMenuItem("Load Script");
			fm.add(lb);
			mb.add(fm);
			
			JMenu mm = new JMenu("Mannequin");
			JCheckBox rb = new JCheckBox("Rich");
			JCheckBox eb = new JCheckBox("Elliot");
			JCheckBox sb = new JCheckBox("Sergey");
			JCheckBox cb = new JCheckBox("Catherine");
			mm.add(rb);
			mm.add(eb);
			mm.add(sb);
			mm.add(cb);
			mb.add(mm);
			
			
			JMenu sm = new JMenu("Scene");
			JRadioButton s1 = new JRadioButton("Irish", true);
			JRadioButton s2 = new JRadioButton("Ballet");
			JRadioButton s3 = new JRadioButton("Ninties");
			ButtonGroup sbg = new ButtonGroup();
			
			sbg.add(s1);
			sbg.add(s2);
			sbg.add(s3);
			sm.add(s1);
			sm.add(s2);
			sm.add(s3);	
			
			mb.add(mm);
			mb.add(sm);		
			
			return mb;
		}	
	
	public static void main(String[] sa) {
		EventQueue.invokeLater(WoodWorld::new);
	}

}
