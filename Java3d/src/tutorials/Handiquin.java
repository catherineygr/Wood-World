package tutorials;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class Handiquin {
	public Handiquin() {
		JFrame jf = new JFrame("Hand Test");
		JMenuBar mb = new JMenuBar();
		JMenu m = new JMenu("Menu");
		JPanel3D jp3d = new JPanel3D();
		mb.add(m);
		jf.setJMenuBar(mb);
		jf.add(jp3d);
		jf.pack();
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
		
	}
	public static void main(String[] sa) {
		EventQueue.invokeLater(Handiquin::new);
	}
}
