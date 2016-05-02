package edu.mccc.cos210.woodworld.mannequin;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GraphicsConfiguration;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.media.j3d.Alpha;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.ImageComponent;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class MannequinTest extends JFrame {
	private static final long serialVersionUID = 1L;
	private BoundingSphere bs = new BoundingSphere(
			new Point3d(0.0, 0.0, 0.0),
			Double.MAX_VALUE
	);
	public MannequinTest() {
		super("Mannequin_Test");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
		Canvas3D c3d = new Canvas3D(gc);
		SimpleUniverse su = new SimpleUniverse(c3d);
		getContentPane().add(c3d, BorderLayout.CENTER);
		JButton jb = new JButton("ClickMe");
		jb.addActionListener(this::doStuff);
		getContentPane().add(jb, BorderLayout.NORTH);
		su.getViewingPlatform().setNominalViewingTransform();
		/*
		Transform3D t3d = new Transform3D();
		t3d.set(new Vector3d(0.0, 0.0, 5.0));
		TransformGroup tg = su.getViewingPlatform().getViewPlatformTransform();
		tg.setTransform(t3d);
		KeyNavigatorBehavior knb = new KeyNavigatorBehavior(tg);
		knb.setSchedulingBounds(bs);*/
		BranchGroup bg = createScene(su);
		//bg.addChild(knb);
		/*
		Background b = new Background(getIC2D("J3D11.jpg"));
		b.setImageScaleMode(Background.SCALE_FIT_MAX);
		b.setApplicationBounds(bs);
		bg.addChild(b);*/
		bg.compile();
		
		su.addBranchGraph(bg);
		setSize(640, 480);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	private BranchGroup createScene(SimpleUniverse su) {
		BranchGroup bg = new BranchGroup();
		TransformGroup major = new TransformGroup();
		major.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
////////////////////Building Lower Body/////////////////////////////
		TransformGroup absTG = new TransformGroup();
		Transform3D absTransform = new Transform3D();
		Vector3f absVector = new Vector3f(0f, 0f, 0f);//(0f, 0.625f, 0f);
		absTransform.setTranslation(absVector);
		absTG.setTransform(absTransform);
		absTG.addChild(new Sphere(.07f));
		Transform3D pelvisTransform = new Transform3D();
		TransformGroup pelvisTG = new TransformGroup();
		Vector3f pelvisVector = new Vector3f(absVector);
		pelvisVector.setY(pelvisVector.getY() - .095f);
		pelvisTransform.setTranslation(pelvisVector);
		pelvisTG.setTransform(pelvisTransform);
		Box pelvis = new Box(.11f, .06f, .07f, null);
		pelvisTG.addChild(pelvis);
		major.addChild(pelvisTG);//add pelvis to the branch group
		major.addChild(absTG);// add abs sphere to the branch group
		createLeg(pelvis, new Vector3f(pelvisVector), major, "left");
		createLeg(pelvis, new Vector3f(pelvisVector), major, "right");
/////////////////////////////////////////////////////////////////////////////
///////////////////////////Building Upper Body///////////////////////////////
		Box torso = new Box(.12f, .14f, .07f, null);
		TransformGroup torsoTG = new TransformGroup();
		Transform3D torsoTransform = new Transform3D();
		Vector3f torsoVector = new Vector3f(absVector);
		torsoVector.setY(torsoVector.getY() + .17f);
		torsoTransform.setTranslation(torsoVector);
		torsoTG.setTransform(torsoTransform);
		torsoTG.addChild(torso);
		
		createArm(torso, new Vector3f(torsoVector), major, "left");
		createArm(torso, new Vector3f(torsoVector), major, "right");
		
		Sphere head = new Sphere(.08f);
		TransformGroup headTG = new TransformGroup();
		Transform3D headTransform = new Transform3D();
		Vector3f headVector = new Vector3f(torsoVector);
		headVector.setY(headVector.getY() + .14f + head.getRadius());
		headTransform.setTranslation(headVector);
		headTG.setTransform(headTransform);
		headTG.addChild(head);
		
		/*Box oneMeter = new Box(.01f, 1, .01f, null);
		TransformGroup meterTG = new TransformGroup();
		Transform3D meterTransform = new Transform3D();
		Vector3f meterVector = new Vector3f(absVector);
		meterVector.setX(meterVector.getX() + 0.5f);
		meterTransform.setTranslation(meterVector);
		meterTG.setTransform(meterTransform);
		meterTG.addChild(oneMeter);*/
/////////////////////////////////////////////////////////////////////////////
		Transform3D t3 = new Transform3D();
		//t3.rotY((float) Math.PI / 2);
		major.setTransform(t3);	
		major.addChild(torsoTG);
		major.addChild(headTG);
		//major.addChild(meterTG);
		bg.addChild(major);
		
		
		Color3f lightcolor =  new Color3f(1.4f, .1f, .2f);
		BoundingSphere bounds = new BoundingSphere(new Point3d(0, 0, 0), 100);
		Vector3f vector1 = new Vector3f(4f, -7f, -12f);
		DirectionalLight light1 = new DirectionalLight(lightcolor, vector1);
		light1.setInfluencingBounds(bounds);
		bg.addChild(light1);
		Alpha a = new Alpha(-1, 5000);
		RotationInterpolator ri = new RotationInterpolator(
			a,
			major,
			t3,
			0.0f,
			(float) Math.PI * 2.0f
		);
		ri.setSchedulingBounds(bs);
		bg.addChild(ri);
		bg.compile();
		return bg;
	}
	void createArm(Box torso, Vector3f torsoVector, TransformGroup major, String s) {
		Sphere shoulder = new Sphere(.05f);
		Vector3f upperArmSphereVector = torsoVector;
		if (s.equalsIgnoreCase("left")) {
			upperArmSphereVector.setX(upperArmSphereVector.getX() + torso.getXdimension() + shoulder.getRadius());
		} else {
			upperArmSphereVector.setX(upperArmSphereVector.getX() - torso.getXdimension() - shoulder.getRadius());
		}
		upperArmSphereVector.setY((float) (upperArmSphereVector.getY() + torso.getYdimension()));
		Transform3D upperArmSphereTransform = new Transform3D();
		upperArmSphereTransform.setTranslation(upperArmSphereVector);
		TransformGroup upperArmSphereTG = new TransformGroup();
		upperArmSphereTG.setTransform(upperArmSphereTransform);
		upperArmSphereTG.addChild(shoulder);
		
		Cylinder upperCylinder = new Cylinder(.035f, .17f);
		Vector3f upperArmCylinderVector = upperArmSphereVector;
		upperArmCylinderVector.setY(upperArmCylinderVector.getY() - (upperCylinder.getHeight() / 2) - shoulder.getRadius());
		Transform3D upperArmCylinderTransform = new Transform3D();
		upperArmCylinderTransform.setTranslation(upperArmCylinderVector);
		TransformGroup upperArmCylinderTG = new TransformGroup();
		upperArmCylinderTG.setTransform(upperArmCylinderTransform);
		upperArmCylinderTG.addChild(upperCylinder);
		
		Sphere elbow = new Sphere(.03f);
		Vector3f lowerArmSphereVector = upperArmCylinderVector;
		lowerArmSphereVector.setY((float) (upperArmCylinderVector.getY() - (upperCylinder.getHeight() / 2) - elbow.getRadius()));
		Transform3D lowerArmSphereTransform = new Transform3D();
		lowerArmSphereTransform.setTranslation(lowerArmSphereVector);
		TransformGroup lowerArmSphereTG = new TransformGroup();
		lowerArmSphereTG.setTransform(lowerArmSphereTransform);
		lowerArmSphereTG.addChild(elbow);
		
		Cylinder lowerCylinder = new Cylinder(.035f, .10f);
		Vector3f lowerArmCylinderVector = lowerArmSphereVector;
		lowerArmCylinderVector.setY(lowerArmCylinderVector.getY() - (lowerCylinder.getHeight() / 2) - elbow.getRadius());
		Transform3D lowerArmCylinderTransform = new Transform3D();
		lowerArmCylinderTransform.setTranslation(lowerArmCylinderVector);
		TransformGroup lowerArmCylinderTG = new TransformGroup();
		lowerArmCylinderTG.setTransform(lowerArmCylinderTransform);
		lowerArmCylinderTG.addChild(lowerCylinder);
		
		Sphere wrist = new Sphere(.03f);
		Vector3f wristVector = lowerArmCylinderVector;
		wristVector.setY((float) (lowerArmCylinderVector.getY() - (lowerCylinder.getHeight() / 2) - wrist.getRadius()));
		Transform3D wristTransform = new Transform3D();
		wristTransform.setTranslation(wristVector);
		TransformGroup wristTG = new TransformGroup();
		wristTG.setTransform(wristTransform);
		wristTG.addChild(wrist);
		
		Box hand = new Box(lowerCylinder.getRadius() - .02f, .03f, .02f, null);
		Vector3f handVector = wristVector;
		handVector.setY(handVector.getY() - wrist.getRadius() - (hand.getYdimension() / 2));
		//wristVector.setZ(wristVector.getZ() - .02f);
		Transform3D handTransform = new Transform3D();
		handTransform.setTranslation(handVector);
		TransformGroup handTG = new TransformGroup();
		handTG.setTransform(handTransform);
		handTG.addChild(hand);
		
		major.addChild(upperArmCylinderTG);
		major.addChild(upperArmSphereTG);
		major.addChild(lowerArmSphereTG);
		major.addChild(lowerArmCylinderTG);
		major.addChild(wristTG);
		major.addChild(handTG);
	}
	void createLeg(Box pelvis, Vector3f v2, TransformGroup major, String s){
		/* Upper Left Leg*/
		Sphere hip = new Sphere(.03f);
		Vector3f upperLegSphereVector = v2;
		if (s.equalsIgnoreCase("left")) {
			upperLegSphereVector.setX((float) (upperLegSphereVector.getX() + (pelvis.getXdimension() / 2)));
		} else {
			upperLegSphereVector.setX((float) (upperLegSphereVector.getX() - (pelvis.getXdimension() / 2)));
		}
		upperLegSphereVector.setY((float) (upperLegSphereVector.getY() - pelvis.getYdimension() - .03));
		Transform3D upperLegSphereTransform = new Transform3D();
		upperLegSphereTransform.setTranslation(upperLegSphereVector);
		TransformGroup upperLegSphereTG = new TransformGroup();
		upperLegSphereTG.setTransform(upperLegSphereTransform);
		upperLegSphereTG.addChild(hip);
		
		Cylinder upperCylinder = new Cylinder(.05f, .19f);
		Vector3f upperLegCylinderVector = upperLegSphereVector;
		upperLegCylinderVector.setY(upperLegCylinderVector.getY() - (upperCylinder.getHeight() / 2) - hip.getRadius());
		Transform3D upperLegCylinderTransform = new Transform3D();
		upperLegCylinderTransform.setTranslation(upperLegCylinderVector);
		TransformGroup upperLegCylinderTG = new TransformGroup();
		upperLegCylinderTG.setTransform(upperLegCylinderTransform);
		upperLegCylinderTG.addChild(upperCylinder);	
		/*Lower Left Leg */
		Sphere knee = new Sphere(.03f);
		Vector3f lowerLegSphereVector = upperLegCylinderVector;
		lowerLegSphereVector.setY((float) (upperLegCylinderVector.getY() - (upperCylinder.getHeight() / 2) - knee.getRadius()));
		Transform3D lowerLegSphereTransform = new Transform3D();
		lowerLegSphereTransform.setTranslation(lowerLegSphereVector);
		TransformGroup lowerLegSphereTG = new TransformGroup();
		lowerLegSphereTG.setTransform(lowerLegSphereTransform);
		lowerLegSphereTG.addChild(knee);
		
		Cylinder lowerCylinder = new Cylinder(.05f, .10f);
		Vector3f lowerLegCylinderVector = lowerLegSphereVector;
		lowerLegCylinderVector.setY(lowerLegCylinderVector.getY() - (lowerCylinder.getHeight() / 2) - 0.03f);
		Transform3D lowerLegCylinderTransform = new Transform3D();
		lowerLegCylinderTransform.setTranslation(lowerLegCylinderVector);
		TransformGroup lowerLegCylinderTG = new TransformGroup();
		lowerLegCylinderTG.setTransform(lowerLegCylinderTransform);
		lowerLegCylinderTG.addChild(lowerCylinder);
		/*Foot*/
		Sphere ancle = new Sphere(.03f);
		Vector3f ancleVector = lowerLegCylinderVector;
		ancleVector.setY((float) (lowerLegCylinderVector.getY() - (lowerCylinder.getHeight() / 2) - ancle.getRadius()));
		Transform3D ancleTransform = new Transform3D();
		ancleTransform.setTranslation(ancleVector);
		TransformGroup ancleTG = new TransformGroup();
		ancleTG.setTransform(ancleTransform);
		ancleTG.addChild(ancle);
		
		Box foot = new Box(lowerCylinder.getRadius() - .02f, .03f, .065f, null);
		Vector3f footVector = ancleVector;
		footVector.setY(footVector.getY() - ancle.getRadius() - (foot.getYdimension() / 2));
		footVector.setZ(footVector.getZ() - .02f);
		Transform3D footTransform = new Transform3D();
		footTransform.setTranslation(footVector);
		TransformGroup footTG = new TransformGroup();
		footTG.setTransform(footTransform);
		footTG.addChild(foot);
		
		major.addChild(upperLegSphereTG);//add leg upper sphere
		major.addChild(upperLegCylinderTG);//add leg upper cylinder
		major.addChild(lowerLegSphereTG);
		major.addChild(lowerLegCylinderTG);
		major.addChild(ancleTG);
		major.addChild(footTG);
	}
	void doStuff(ActionEvent e) {
		
	}
	private ImageComponent2D getIC2D(String s) {
		ImageComponent2D ic2d = new ImageComponent2D(ImageComponent.FORMAT_RGB, 1, 1);
		try {
			BufferedImage bi = ImageIO.read(new URL("file:" + s));
			ic2d = new ImageComponent2D(
				ImageComponent.FORMAT_RGB,
				bi
			);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ic2d;
	}
	public static void main(String[] sa) {
		EventQueue.invokeLater(MannequinTest::new);
	}

}
