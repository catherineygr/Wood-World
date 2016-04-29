package edu.mccc.cos210.woodworld;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class WoodWorld {
	JFrame jf;
	JPanel jp;
	JPanel3D jp3d;
	WoodWorld() {
		JFrame jf = new JFrame("Wood World");
		JMenu m = new JMenu("Menu");
		jf.setJMenuBar(createMenu());
		jp= new JPanel();
		jp.setPreferredSize(new Dimension(500,500));
		jf.add(jp);
		jf.pack();
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
		
	}
	private JMenuBar createMenu(){
			JMenuBar mb = new JMenuBar();
			
			JMenu fm = new JMenu("File");
			JMenuItem startb = new JMenuItem("Start");
			startb.addActionListener(al->{
				jf.remove(jp);
				jp3d = new JPanel3D();
				jf.add(jp3d);
				jf.pack();
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
