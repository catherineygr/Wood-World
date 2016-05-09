package edu.mccc.cos210.woodworld;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Stage1 extends JPanel {
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	public Stage1() {
		try {
			image = ImageIO.read(new File("stage1.png"));
		} catch (IOException ex) {
			System.out.println("Can't read file");
		}
	}	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(300, 175);
	}
}