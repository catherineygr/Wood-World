package edu.mccc.cos210.woodworld;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class TestFrame {
	public TestFrame() {
		JFrame jf = new JFrame("Hand Test");
	JMenuBar mb = new JMenuBar();
	JMenu m = new JMenu("Menu");
	testjp3d jp3d = new testjp3d();
	mb.add(m);
	jf.setJMenuBar(mb);
	jf.add(jp3d);
	jf.pack();
	jf.setResizable(false);
	jf.setLocationRelativeTo(null);
	jf.setVisible(true);
	}
	public static void main(String[] sa) {
		EventQueue.invokeLater(TestFrame::new);
	}
	
	
}
