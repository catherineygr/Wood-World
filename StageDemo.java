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

import javax.swing.JPanel;

public class StageDemo extends JPanel {
	private static final long serialVersionUID = 1L;
	private AffineTransform gat = new AffineTransform();
	private AffineTransform at = new AffineTransform();
	private Shape s = null;
	public StageDemo() {
		setBackground(Color.WHITE);
	}	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		gat = AffineTransform.getTranslateInstance(
				getWidth() / 2.0,
				getHeight() / 2.0
		);
		gat.scale(1.0, -1.0);
		g2d.transform(gat);

		
		
		g2d.dispose();
		
	}
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(160, 175);
	}
}