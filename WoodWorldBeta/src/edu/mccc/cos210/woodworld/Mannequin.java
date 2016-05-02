package edu.mccc.cos210.woodworld;

import javax.media.j3d.Alpha;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Material;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Texture;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Transform3D;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;

import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

abstract class Mannequin extends TransformGroup {
	Sphere waist;
	boolean visibility;
	Appearance app;
	UpperBody upperBody;
	LowerBody lowerBody;
	String name;
	double scalex, scaley, scalez;
	public Mannequin(double scalex, double scaley) {
		this.scalex=scalex;
		this.scaley=scaley;
		setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Appearance appearance = new Appearance();
		Material material = new Material(
			new Color3f(1.0f, 1.0f, 1.0f),
			new Color3f(0.0f, 0.0f, 0.0f),
			new Color3f(0.5f, 0.5f, 0.5f),
			new Color3f(1.0f, 1.0f, 1.0f),
			96.0f
		);
		appearance.setMaterial(material);
		TextureLoader textureLoader = new TextureLoader("floor.jpg", null);
		Texture texture = textureLoader.getTexture();
		texture.setBoundaryModeS(Texture.CLAMP);
		texture.setBoundaryModeT(Texture.CLAMP);
		appearance.setTexture(texture);
		Sphere waist= new Sphere(0.2f, Primitive.ENABLE_GEOMETRY_PICKING |
				Primitive.GENERATE_NORMALS |
				Primitive.GENERATE_TEXTURE_COORDS,
				appearance);
		UpperBody ub = new UpperBody(appearance);
		Transform3D t3dub = new Transform3D();
		t3dub.setTranslation(new Vector3f(0.0f, 0.4f, 0.0f));
		ub.setTransform(t3dub);
		addChild(ub);
		LowerBody lb = new LowerBody(appearance);
		Transform3D t3dlb = new Transform3D();
		t3dlb.setTranslation(new Vector3f(0.0f, -0.25f, 0.0f));
		lb.setTransform(t3dlb);
		addChild(lb);
		addChild(waist);		
	}
	public class Segment extends TransformGroup{
		double scalex, scaley;
		Sphere joint;
		Appearance app;
		public Segment(Appearance app) {
			joint= new Sphere(0.1f, Primitive.ENABLE_GEOMETRY_PICKING |
					Primitive.GENERATE_NORMALS |
					Primitive.GENERATE_TEXTURE_COORDS,
					app);
			Cylinder limb = new Cylinder(0.08f, 0.3f, Primitive.ENABLE_GEOMETRY_PICKING |
					Primitive.GENERATE_NORMALS |
					Primitive.GENERATE_TEXTURE_COORDS,
					app);
			TransformGroup tg = new TransformGroup();
			tg.addChild(limb);
			Transform3D t3d = new Transform3D();
			t3d.setTranslation(new Vector3f(0.0f, -0.2f, 0.0f));
			tg.setTransform(t3d);
			addChild(joint);
			addChild(tg);
		}
		
	}
	public class UpperBody extends TransformGroup {
		Box torso;
		Arm rArm;
		Arm lArm;
		Head head;
		double scalex, scaley;
		Appearance app;
		private BoundingSphere boundingSphere = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), Double.MAX_VALUE);
		public UpperBody(Appearance app) {
			setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
			setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			torso = new Box(0.25f, 0.25f, 0.3f, Primitive.ENABLE_GEOMETRY_PICKING |
					Primitive.GENERATE_NORMALS |
					Primitive.GENERATE_TEXTURE_COORDS, app);
			addChild(torso);
			rArm= new Arm(app);
			lArm= new Arm(app);
			head = new Head(app);
			Transform3D t3dr= new Transform3D();
			Transform3D t3dl= new Transform3D();
			Transform3D t3dh= new Transform3D();
			t3dr.setTranslation(new Vector3f(0.35f, 0.15f, 0.0f));
			t3dl.setTranslation(new Vector3f(-0.35f, 0.15f, 0.0f));
			t3dh.setTranslation(new Vector3f(0.0f, 0.2f, 0.0f));
			rArm.setTransform(t3dr);
			lArm.setTransform(t3dl);
			head.setTransform(t3dh);
			
			
//			RotationInterpolator ri = new RotationInterpolator(
//					new Alpha(
//						-1,
//						Alpha.INCREASING_ENABLE,
//						0,
//						0,
//						500,
//						0,
//						0,
//						0,
//						0,
//						0
//					),
//					this
//				);
//			ri.setSchedulingBounds(boundingSphere);
//			ri.setEnable(false);
//			Dance dance = new Dance(ri.hashCode(), 2500, ri, this); 
//			rArm.addChild(ri);	
			addChild(rArm);
			addChild(lArm);
			addChild(head);
					
		}
	}
	public class LowerBody extends TransformGroup {
		private Box pelvis;
		private Leg rleg;
		private Leg lleg;
		Transform3D t3d;
		double scalex, scaley, scalez;
		Appearance app;
		public LowerBody(Appearance app) { 
			setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
			setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			pelvis = new Box(0.25f, 0.2f, 0.2f, Primitive.ENABLE_GEOMETRY_PICKING |
					Primitive.GENERATE_NORMALS |
					Primitive.GENERATE_TEXTURE_COORDS, app);
			rleg = new Leg(app);
			lleg = new Leg(app);
			Transform3D t3dr= new Transform3D();
			Transform3D t3dl= new Transform3D();
			t3dr.setTranslation(new Vector3f(0.15f,-0.3f, 0.0f));
			t3dl.setTranslation(new Vector3f(-0.15f,-0.3f, 0.0f));
			rleg.setTransform(t3dr);
			lleg.setTransform(t3dl);
			addChild(pelvis);
			addChild(rleg);
			addChild(lleg);
		}
	}
	public class Arm extends TransformGroup{
		private BoundingSphere boundingSphere = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), Double.MAX_VALUE);
		double scalex, scaley, scalez;
		Segment upper, lower, hand;
		Appearance app;
		public Arm(Appearance app) {
			setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
			setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			this.app=app;
			upper= new Segment(app);
			
			lower= new Segment(app);
			Transform3D lt3d = new Transform3D();
			lt3d.setTranslation(new Vector3f(0.0f, -0.4f, 0.0f));
			hand= new Segment(app);
			Transform3D ht3d = new Transform3D();
			ht3d.setTranslation(new Vector3f(0.0f, -0.8f, 0.0f));
		
			lower.setTransform(lt3d);
			hand.setTransform(ht3d);
			
			addChild(upper);
			addChild(lower);
			addChild(hand);
		}
	}
	public class Leg extends TransformGroup {
		public static final int LEFT_LEG = 1;
		public static final int RIGHT_LEG = 2;
		double scalex, scaley, scalez;
		private Segment upper;  
		private Segment lower;
		private Segment foot;
		private Appearance app;
		public Leg(Appearance app) {
			setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
			setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			this.app=app;
			upper= new Segment(app);
			
			lower= new Segment(app);
			Transform3D lt3d = new Transform3D();
			lt3d.setTranslation(new Vector3f(0.0f, -0.4f, 0.0f));
			foot= new Segment(app);
			Transform3D ht3d = new Transform3D();
			ht3d.setTranslation(new Vector3f(0.0f, -0.8f, 0.0f));
		
			lower.setTransform(lt3d);
			foot.setTransform(ht3d);
			
			addChild(upper);
			addChild(lower);
			addChild(foot);
		}
	}
	public class Head extends TransformGroup {
		double scalex, scaley;
		Sphere joint;
		Appearance app;
		public Head(Appearance app) {
			joint= new Sphere(0.1f, Primitive.ENABLE_GEOMETRY_PICKING |
					Primitive.GENERATE_NORMALS |
					Primitive.GENERATE_TEXTURE_COORDS,
					app);
			Sphere head = new Sphere(0.2f, Primitive.ENABLE_GEOMETRY_PICKING |
					Primitive.GENERATE_NORMALS |
					Primitive.GENERATE_TEXTURE_COORDS,
					app);
			TransformGroup tg = new TransformGroup();
			tg.addChild(head);
			Transform3D t3d = new Transform3D();
			t3d.setTranslation(new Vector3f(0.0f, 0.25f, 0.0f));
			tg.setTransform(t3d);
			addChild(joint);
			addChild(tg);
		}
	}
	abstract void dance();
	abstract void walk();
	abstract void jump();
	abstract void sit();
	abstract void run();
	
}
