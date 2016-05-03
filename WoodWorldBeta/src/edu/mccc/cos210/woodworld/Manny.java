package edu.mccc.cos210.woodworld;

import javax.media.j3d.Transform3D;
import javax.vecmath.Vector3f;

public class Manny extends Mannequin {
	//private RotationInterpolator ri;

	public Manny(double scalex, double scaley) {
		super(scalex, scaley);
	}
	@Override
	public void dance() {
		
		
	}

	@Override
	public void jump() {
		Vector3f vector = new Vector3f(0.0f, 0.5f, 0.0f);
		Transform3D t3d = new Transform3D();
		t3d.setTranslation(vector);
		setTransform(t3d);
		
	}
	@Override
	void walk() {
		// TODO Auto-generated method stub
		
	}
	@Override
	void sit() {
		// TODO Auto-generated method stub
		
	}
	@Override
	void run() {
		// TODO Auto-generated method stub
		
	}
}
