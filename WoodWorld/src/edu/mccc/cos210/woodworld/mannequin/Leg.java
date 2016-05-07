package edu.mccc.cos210.woodworld.mannequin;

import javax.media.j3d.Appearance;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Sphere;

public class Leg extends TransformGroup {
	public static final int LEFT_LEG = 1;
	public static final int RIGHT_LEG = 2;
	double scalex, scaley, scalez;
	private Segment upperSegment;  
	private Segment lowerSegment;
	private Segment footSegment;
	private Appearance app;
	private Vector3f shapesVector;
	/*
	public Leg(double scalex,double scaley, double scalez, Appearance app) {
		this.scalex=scalex;
		this.scaley=scaley;
		this.scalez=scalez;
		this.app=app;
	}*/
	public Leg(float hightRatio, float thicknessRatio, LowerBody lowerBody, int leg) {
		this.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		this.shapesVector = calculateVector(lowerBody.getShapesVector(), leg, lowerBody, hightRatio);
		setUpperSegment(new Segment(this));
		setLowerSegment(new Segment(upperSegment));
		setFootSegment(new Segment(lowerSegment));
		this.addChild(upperSegment);
	}
	private Vector3f calculateVector(Vector3f parent, int leg, LowerBody lowerBody, float hightRatio) {
		Vector3f v3f = parent;
		if (leg == LEFT_LEG) {
			v3f.setX((float) (v3f.getX() + (lowerBody.getPelvis().getXdimension() / 2)));
		} else {
			v3f.setX((float) (v3f.getX() - (lowerBody.getPelvis().getXdimension() / 2)));
		}
		v3f.setY((float) (v3f.getY() - lowerBody.getPelvis().getYdimension() - (.02 * hightRatio)));
		return v3f;
	}
	private void setUpperSegment(Segment us) {
		this.upperSegment = us;
	}
	private void setLowerSegment(Segment ls) {
		this.lowerSegment = ls;
	}
	private void setFootSegment(Segment fs) {
		this.footSegment = fs;
	}
	public Vector3f getShapesVector() {
		return this.shapesVector;
	}
	public Segment getUpperSegment() {
		return this.upperSegment;
	}
	public Segment getLowerSegment() {
		return this.lowerSegment;
	}
	public Segment getFootSegment() {
		return this.footSegment;
	}
}
