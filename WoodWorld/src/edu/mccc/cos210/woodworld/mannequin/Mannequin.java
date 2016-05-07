package edu.mccc.cos210.woodworld.mannequin;

import javax.media.j3d.Appearance;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Transform3D;
import com.sun.j3d.utils.geometry.Sphere;

import javax.vecmath.Vector3f;

public class Mannequin extends TransformGroup {
	Sphere waist;
	boolean visibility;
	Appearance app;
	UpperBody upperBody;
	LowerBody lowerBody;
	String name;
	Transform3D mannequinTransform3d;
	Vector3f shapesVector;
//	TransformGroup tg;
//	double scalex, scaley, scalez;
	/*public Mannequin(double scalex, double scaley, double scalez) {
	this.scalex=scalex;
	this.scaley=scaley;
	this.scalez=scalez;
	app = new Appearance();
}*/
	public Mannequin() {
		new Mannequin(1f, 1f);
	}
	protected Mannequin(float hightRatio, float thicknessRatio) {
		//super();
		//tg = new TransformGroup();
		this.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		waist = new Sphere(.08f * hightRatio);
		mannequinTransform3d = new Transform3D();
		shapesVector = new Vector3f(0f, .625f * hightRatio, 0f);
		mannequinTransform3d.setTranslation(shapesVector);
		this.setTransform(mannequinTransform3d);
		this.addChild(waist);
		setUpperBody(new UpperBody(hightRatio, thicknessRatio, this));
		setLowerBody(new LowerBody(hightRatio, thicknessRatio, this));
		System.out.println("Done executing constructor");
	}
	private void setUpperBody(UpperBody up) {
		upperBody = up;
	}
	private void setLowerBody(LowerBody lw) {
		lowerBody = lw;
	} 
	public UpperBody getUpperBody() {
		return this.upperBody;
	}
	public LowerBody getLowerBody() {
		return this.lowerBody;
	}
	public Transform3D getTransform3D() {
		return this.mannequinTransform3d;
	}
	public Vector3f getShapesVector() {
		return this.shapesVector;
	}
	public Sphere getWaist() {
		return waist;
	}
	/*
	public TransformGroup getTransformGroup() {
		return this.tg;
	}*/
	public void dance() {
		
	}
	public void walk() {
		
	}
	public void jump() {
		
	}
	public void sit() {
		 
	}
	public void run() {
		
	}
	
}
