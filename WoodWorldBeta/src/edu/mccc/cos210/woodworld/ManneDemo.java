package edu.mccc.cos210.woodworld;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JPanel;

public class ManneDemo extends JPanel {
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	public ManneDemo() {
		       try {                
		          image = ImageIO.read(new File("manny.PNG"));
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
		return new Dimension(160, 175);
	}
}