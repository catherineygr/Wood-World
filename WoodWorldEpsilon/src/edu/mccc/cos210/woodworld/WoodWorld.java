package edu.mccc.cos210.woodworld;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
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
	private JPanel jp;
	private JTextField textfield;
	private JPanel3D jp3d;
	private Mannequin elliot, catherine, sergey, rich;
	private ScriptEngine se;
	final static String STARTPANEL = "Test start";
	final static String WOODWORLD = "Test WoodWorld";
	private JPanel wwjp;
	private JRadioButton s1;
	private JRadioButton s2;
	private JRadioButton s3;
	private JCheckBox rb;
	private JCheckBox eb;
	private JCheckBox sb;
	private JCheckBox cb;
	private JToggleButton tglbtnMrGreeen;
	private JToggleButton tglbtnProfPlum;
	private JToggleButton tglbtnMissScarlet;
	private JToggleButton tglbtnElCapitan;
	private JRadioButton rdbtnStage;
	private JRadioButton rdbtnStage_1;
	private JRadioButton rdbtnStage_2;

	WoodWorld() {
		ScriptEngineManager sem = new ScriptEngineManager();
		se = sem.getEngineByName("ECMAScript");
		elliot = new ManElliot();
		catherine = new ManCatherine();
		sergey = new ManSergey();
		rich = new ManRich();
		se.put("Elliot", elliot);
		se.put("Catherine", catherine);
		se.put("Sergey", sergey);
		se.put("Rich", rich);
		JFrame jf = new JFrame("Wood World");
		JPanel startjp = createStart();
		JPanel wwjp = createWoodWorld();
		jf.setLayout(new BorderLayout());
		init();
		jp= new JPanel(new CardLayout());
		jp.add(startjp, STARTPANEL);
		jp.add(wwjp, WOODWORLD);
		jp.setPreferredSize(startjp.getPreferredSize());
		jf.add(jp);
		jf.setJMenuBar(createMenu());
		jf.pack();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
		
	}
	private JPanel createWoodWorld() {
		wwjp = new JPanel();
		wwjp.setBackground(new Color(162, 42 ,42));
		wwjp.setLayout(null);

		jp3d = new JPanel3D(elliot, catherine, sergey, rich);
		se.put("canvas", jp3d);
		textfield = new JTextField();
		textfield.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textfield.setBounds(129, 604, 935, 60);
		wwjp.add(textfield);
		
		wwjp.setVisible(true);
		
		wwjp.setPreferredSize(new Dimension(1080, 725));
		jp3d.setBounds(115, 20, 930, 525);
		wwjp.add(jp3d);
		textfield.addActionListener(
				(ae) -> {
					try {
						System.out.println(textfield.getText());
						Object o = se.eval(textfield.getText());
						if (o != null) {
							System.out.println(o.toString());
						}
					} catch (Exception ex) {
						System.err.println("Script Exception");
					}
					textfield.setText("");
					jp3d.repaint();
				}
			);
		textfield.setColumns(12);
		jp3d.setVisible(true);
		
		JButton btnTutorial = new JButton("Tutorial");
		btnTutorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Tutorial.NewScreen();
			}
		});
		btnTutorial.setBounds(20, 553, 85, 40);
		wwjp.add(btnTutorial);
		
		JButton btnCredits = new JButton("Credits");
		btnCredits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Credits.NewScreen();
			}
		});
		btnCredits.setBounds(20, 614, 85, 40);
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
		btnDance.addActionListener(ae-> {
		
		});
		wwjp.add(btnDance);
		
		JToggleButton tglbtnColMustard = new JToggleButton("Col. Mustard");
		tglbtnColMustard.setBounds(170, 565, 121, 28);
		wwjp.add(tglbtnColMustard);
		
		JToggleButton tglbtnMissScarlet = new JToggleButton("Miss Scarlet");
		tglbtnMissScarlet.setBounds(404, 565, 121, 28);
		wwjp.add(tglbtnMissScarlet);
		
		JToggleButton tglbtnProfessorPlum = new JToggleButton("Professor Plum");
		tglbtnProfessorPlum.setBounds(631, 565, 121, 28);
		wwjp.add(tglbtnProfessorPlum);
		
		JToggleButton tglbtnMrGreen = new JToggleButton("Mr. Green");
		tglbtnMrGreen.setBounds(861, 565, 121, 28);
		wwjp.add(tglbtnMrGreen);
		return wwjp;
	}
	private JPanel createStart() {
		JPanel start = new JPanel();
		start.setLayout(null);
		start.setPreferredSize(new Dimension(1080, 725));
		start.setBackground(new Color(100, 20, 20));
		
		JButton btnStart = new JButton("Start");
		btnStart.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnStart.addActionListener(ae->{
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
				Tutorial.NewScreen();
			}
		});
		btnTutorial.setBounds(10, 630, 100, 40);
		start.add(btnTutorial);
		
		JButton btnCredits = new JButton("Credits");
		btnCredits.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnCredits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Credits.NewScreen();
			}
		});
		btnCredits.setBounds(954, 630, 100, 40);
		start.add(btnCredits);
		
//Mannequin images		
		Manny1 man1 = new Manny1();
		man1.setBounds(56, 110, 160, 175);
		start.add(man1);

		Manny2 man2 = new Manny2();
		man2.setBounds(310, 110, 160, 175);
		start.add(man2);
		
		Manny3 man3 = new Manny3();
		man3.setBounds(588, 110, 160, 175);
		start.add(man3);
		
		Manny4 man4 = new Manny4();
		man4.setBounds(848, 110, 160, 175);
		start.add(man4);
//Linking Toggle Buttons to JMenu		
		tglbtnElCapitan = new JToggleButton("Col. Mustard");
		tglbtnElCapitan.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (tglbtnElCapitan.isSelected()) {
					eb.setSelected(true);
				} else {
					eb.setSelected(false);
				}
			}
		});
		tglbtnElCapitan.setBounds(75, 296, 121, 23);
		start.add(tglbtnElCapitan);
		
		tglbtnMissScarlet = new JToggleButton("Miss Scarlet");
		tglbtnMissScarlet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tglbtnMissScarlet.isSelected()) {
					cb.setSelected(true);
				} else {
					cb.setSelected(false);
				}
			}
		});
		tglbtnMissScarlet.setBounds(331, 296, 121, 23);
		start.add(tglbtnMissScarlet);
		
		tglbtnProfPlum = new JToggleButton("Prof. Plum");
		tglbtnProfPlum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tglbtnProfPlum.isSelected()) {
					sb.setSelected(true);
				} else {
					sb.setSelected(false);
				}
			}
		});
		tglbtnProfPlum.setBounds(608, 296, 121, 23);
		start.add(tglbtnProfPlum);
		
		tglbtnMrGreeen = new JToggleButton("Mr. Green");
		tglbtnMrGreeen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tglbtnMrGreeen.isSelected()) {
					rb.setSelected(true);
				} else {
					rb.setSelected(false);
				}
			}
		});
		tglbtnMrGreeen.setBounds(869, 296, 121, 23);
		start.add(tglbtnMrGreeen);
//Stage Images		
		Stage1 stage1 = new Stage1();
		stage1.setBounds(30, 335, 300, 175);
		start.add(stage1);
				
		Stage2 stage2 = new Stage2();
		stage2.setBounds(395, 335, 300, 175);
		start.add(stage2);
				
		Stage3 stage3 = new Stage3();
		stage3.setBounds(760, 335, 300, 175);
		start.add(stage3);		
//Linking Radio Buttons	to JMenu	
		rdbtnStage = new JRadioButton("Stage 1", true);
		rdbtnStage.addActionListener(ae -> {
			s1.setSelected(true);
			jp3d.setStage(1);
		});
		rdbtnStage.setBounds(140, 510, 75, 25);
		start.add(rdbtnStage);
		
		rdbtnStage_1 = new JRadioButton("Stage 2");
		rdbtnStage_1.addActionListener(ea -> {
			s2.setSelected(true);
			jp3d.setStage(2);
		});
		rdbtnStage_1.setBounds(510, 510, 75, 25);
		start.add(rdbtnStage_1);

		rdbtnStage_2 = new JRadioButton("Stage 3");
		rdbtnStage_2.addActionListener(ae -> {
			s3.setSelected(true);
			jp3d.setStage(3);
		});
		rdbtnStage_2.setBounds(875, 510, 75, 25);
		start.add(rdbtnStage_2);
//Grouping of Buttons		
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
		lb.addActionListener(ae -> {loadScript();});
		fm.add(lb);
		mb.add(fm);
		
		JMenu mm = new JMenu("Mannequin");
		eb = new JCheckBox("Elliot");		
		cb = new JCheckBox("Catherine");
		sb = new JCheckBox("Sergey");
		rb = new JCheckBox("Rich");
//JMenu and Toggle button Linking
		eb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (eb.isSelected()) {
					tglbtnElCapitan.setSelected(true);
				} else {
					tglbtnElCapitan.setSelected(false);
				}
			}
		});		
		cb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (cb.isSelected()) {
					tglbtnMissScarlet.setSelected(true);
				} else {
					tglbtnMissScarlet.setSelected(false);
				}
			}
		});
		sb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (sb.isSelected()) {
					tglbtnProfPlum.setSelected(true);
				} else {
					tglbtnProfPlum.setSelected(false);
				}
			}
		});
		rb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (rb.isSelected()) {
					tglbtnMrGreeen.setSelected(true);
				} else {
					tglbtnMrGreeen.setSelected(false);
				}
			}
		});

		mm.add(eb);
		mm.add(cb);
		mm.add(sb);
		mm.add(rb);
		mb.add(mm);
		
		JMenu sm = new JMenu("Scene");
		s1 = new JRadioButton("Standard", true);
		s1.addActionListener(ae -> {
			jp3d.setStage(1);
		});
		s2 = new JRadioButton("Irish");
		s2.addActionListener(ae -> {
			jp3d.setStage(2);
		});
		s3 = new JRadioButton("Ballet");
		s3.addActionListener(ae -> {
			jp3d.setStage(3);
		});
		ButtonGroup sbg = new ButtonGroup();
		sbg.add(s1);
		sbg.add(s2);
		sbg.add(s3);
		sm.add(s1);
		sm.add(s2);
		sm.add(s3);	
		
		s1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (s1.isSelected()) {
					rdbtnStage.setSelected(true);
				}
			}
		});
		s2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (s2.isSelected()) {
					rdbtnStage_1.setSelected(true);
				}
			}
		});
		s3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (s3.isSelected()) {
					rdbtnStage_2.setSelected(true);
				}
			}
		});		
		mb.add(mm);
		mb.add(sm);		
		return mb;
	}	
	private void loadScript() {
		JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(null);
		File sf = fc.getSelectedFile();
		String fn = sf.getAbsolutePath();
		try {
			FileReader reader = new FileReader(fn);
			BufferedReader br = new BufferedReader(reader);
			textfield.read(br, null);
			br.close();
			textfield.requestFocus();
		} catch (Exception e){
			System.err.println("TXT file type required");
		}
		
	}
	private void init() {
		try (BufferedReader br = new BufferedReader(new FileReader("./init.js"))) {
			String s;
			while ((s = br.readLine()) != null) {
				se.eval(s);
			}
		} catch (Exception ex) {
			System.err.println("Unable to initialize");
		}
	}
	public static void main(String[] sa) {
		EventQueue.invokeLater(WoodWorld::new);
	}
}