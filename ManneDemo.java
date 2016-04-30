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

public class ManneDemo extends JPanel {
	private static final long serialVersionUID = 1L;
	private AffineTransform gat = new AffineTransform();
	private AffineTransform at = new AffineTransform();
	private Shape s = null;
	public ManneDemo() {
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

		Path2D legs = new Path2D.Double();
		legs.moveTo(-15.0, -40.0);
		legs.lineTo(-10.0, -40.0);
		legs.lineTo(-10.0, -20.0);
		legs.lineTo(0.0, 0.0);
		legs.lineTo(10.0, -20.0);
		legs.lineTo(10.0, -40.0);
		legs.lineTo(15.0, -40.0);

		at.setToIdentity();
		s = at.createTransformedShape(legs);
		g2d.setPaint(Color.BLACK);
		g2d.draw(s);
		
		Path2D body = new Path2D.Double();
		body.moveTo(0.0, 0.0);
		body.lineTo(0.0, 25.0);
		body.lineTo(-10.0, 25.0);
		body.lineTo(-10.0, 10.0);
		body.lineTo(0.0, 0.0);
		body.lineTo(10.0, 10.0);
		body.lineTo(10.0, 25.0);
		body.lineTo(0.0, 25.0);
		
		at.setToIdentity();
		s = at.createTransformedShape(body);
		g2d.setPaint(Color.BLACK);
		g2d.draw(s);
		
		Ellipse2D head = new Ellipse2D.Double(
				-7,
				27.0,
				15.0,
				15.0
		);
		at.setToIdentity();
		s = at.createTransformedShape(head);
		g2d.setPaint(Color.BLACK);
		g2d.draw(s);
				
		g2d.dispose();
		
	}
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(160, 175);
	}
}