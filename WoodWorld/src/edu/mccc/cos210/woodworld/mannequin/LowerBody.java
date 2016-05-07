package edu.mccc.cos210.woodworld.mannequin;

import javax.media.j3d.Appearance;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Sphere;

public class LowerBody extends TransformGroup {
	private Box pelvis;
	private Leg rightLeg;
	private Leg leftLeg;
	private Transform3D lowerBodyTransform3d;
	Vector3f shapesVector;
	//TransformGroup tg;
	Transform3D t3d;
	double scalex, scaley, scalez;
	Appearance app;
/*	public LowerBody(double scalex, double scaley, double scalez, Appearance app) {
		this.scalex=scalex;
		this.scaley=scaley;
		this.scalez=scalez;
		this.app=app;
	}*/
	public LowerBody(float hightRatio, float thicknessRatio, Mannequin mannequin) {
		this.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		shapesVector = mannequin.getShapesVector();
		shapesVector.setY((float) (shapesVector.getY() - (.095 * hightRatio)));
		//tg = new TransformGroup();
		t3d = new Transform3D();
		t3d.setTranslation(shapesVector);
		this.setTransform(t3d);
		pelvis = new Box(.2f * hightRatio, (.06f * hightRatio) * thicknessRatio, .12f * hightRatio, null);
		this.addChild(pelvis);
		mannequin.getWaist().addChild(this);
		setRightLeg(new Leg(hightRatio, thicknessRatio, this, Leg.RIGHT_LEG));
		setLeftLeg(new Leg(hightRatio, thicknessRatio, this, Leg.LEFT_LEG));
	}
	public Vector3f getShapesVector() {
		return this.shapesVector;
	}
	void setRightLeg(Leg rLeg) {
		rightLeg = rLeg;
	}
	void setLeftLeg(Leg lLeg) {
		leftLeg = lLeg;
	}
	public Leg getRightLeg() {
		return rightLeg;
	}
	public Leg getLeftLeg() {
		return leftLeg;
	}
	public Box getPelvis() {
		return pelvis;
	}
	public Transform3D getTransform3D() {
		return t3d;
	}
}
