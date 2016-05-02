package edu.mccc.cos210.woodworld;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;

public class Start {
	public Start() {
		JFrame jf = new JFrame("Wood World");
		JPanel jp= new JPanel();
		jp.setPreferredSize(new Dimension(500,500));
		jf.add(jp);
		jf.pack();
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
		
		
	}
	public static void main(String[] sa) {
		EventQueue.invokeLater(Start::new);
	}
}
