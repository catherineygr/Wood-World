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
	BoundingSphere boundingSphere = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), Double.MAX_VALUE);
	String name;
	float x, y;
	public Mannequin(float scalex, float scaley, TextureLoader tl) {
		this.x = scalex;
		this.y = scaley;
		setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		app = new Appearance();
		Material material = new Material(
			new Color3f(1.0f, 1.0f, 1.0f),
			new Color3f(0.0f, 0.0f, 0.0f),
			new Color3f(0.5f, 0.5f, 0.5f),
			new Color3f(1.0f, 1.0f, 1.0f),
			96.0f
		);
		app.setMaterial(material);
		TextureLoader textureLoader = tl;
		Texture texture = textureLoader.getTexture();
		texture.setBoundaryModeS(Texture.CLAMP);
		texture.setBoundaryModeT(Texture.CLAMP);
		app.setTexture(texture);
		Sphere waist= new Sphere(0.2f * x, Primitive.ENABLE_GEOMETRY_PICKING |
				Primitive.GENERATE_NORMALS |
				Primitive.GENERATE_TEXTURE_COORDS,
				app);
		UpperBody ub = new UpperBody();
		Transform3D t3dub = new Transform3D();
		t3dub.setTranslation(new Vector3f(0.0f * x, 0.4f * y, 0.0f));
		ub.setTransform(t3dub);
		addChild(ub);
		LowerBody lb = new LowerBody();
		Transform3D t3dlb = new Transform3D();
		t3dlb.setTranslation(new Vector3f(0.0f * x, -0.25f * y, 0.0f));
		lb.setTransform(t3dlb);
		addChild(lb);
		addChild(waist);		
	}
	public class Segment extends TransformGroup{
		double scalex, scaley;
		Sphere joint;
		public Segment() {
			joint= new Sphere(0.1f * x, Primitive.ENABLE_GEOMETRY_PICKING |
					Primitive.GENERATE_NORMALS |
					Primitive.GENERATE_TEXTURE_COORDS,
					app);
			Cylinder limb = new Cylinder(0.08f * x, 0.3f * y, Primitive.ENABLE_GEOMETRY_PICKING |
					Primitive.GENERATE_NORMALS |
					Primitive.GENERATE_TEXTURE_COORDS,
					app);
			TransformGroup tg = new TransformGroup();
			tg.addChild(limb);
			Transform3D t3d = new Transform3D();
			t3d.setTranslation(new Vector3f(0.0f * x, -0.2f * y, 0.0f));
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
		public UpperBody() {
			setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
			setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			torso = new Box(0.25f * x, 0.25f * y, 0.3f, Primitive.ENABLE_GEOMETRY_PICKING |
					Primitive.GENERATE_NORMALS |
					Primitive.GENERATE_TEXTURE_COORDS, app);
			addChild(torso);
			rArm= new Arm();
			lArm= new Arm();
			head = new Head();
			Transform3D t3dr= new Transform3D();
			rArm.getTransform(t3dr);
			Transform3D t3dl= new Transform3D();
			lArm.getTransform(t3dl);
			Transform3D t3dh= new Transform3D();
			head.getTransform(t3dh);
			t3dr.setTranslation(new Vector3f(0.35f * x, 0.15f * y, 0.0f));
			t3dl.setTranslation(new Vector3f(-0.35f * x, 0.15f * y, 0.0f));
			t3dh.setTranslation(new Vector3f(0.0f * x, 0.2f * y, 0.0f));
			rArm.setTransform(t3dr);
			lArm.setTransform(t3dl);
			head.setTransform(t3dh);
			
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
		public LowerBody() { 
			setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
			setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			pelvis = new Box(0.25f * x, 0.2f * y, 0.2f, Primitive.ENABLE_GEOMETRY_PICKING |
					Primitive.GENERATE_NORMALS |
					Primitive.GENERATE_TEXTURE_COORDS, app);
			rleg = new Leg();
			lleg = new Leg();
			Transform3D t3dr= new Transform3D();
			Transform3D t3dl= new Transform3D();
			t3dr.setTranslation(new Vector3f(0.15f * x,-0.3f * y, 0.0f));
			t3dl.setTranslation(new Vector3f(-0.15f * x,-0.3f * y, 0.0f));
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
		Segment upper, lower;
		Extreme hand;
		RotationInterpolator ri;
		public Arm() {
			setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
			setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			upper= new Segment();
			
			lower= new Segment();
			Transform3D lt3d = new Transform3D();
			lt3d.setTranslation(new Vector3f(0.0f * x, -0.4f * y, 0.0f));
			hand= new Extreme();
			Transform3D ht3d = new Transform3D();
			ht3d.setTranslation(new Vector3f(0.0f * x, -0.8f * y, 0.0f));
		
			lower.setTransform(lt3d);
			hand.setTransform(ht3d);
			TransformGroup tg = new TransformGroup();
			tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
			tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			ri = new RotationInterpolator(
					new Alpha(
						-1,
						Alpha.INCREASING_ENABLE,
						0,
						0,
						500,
						0,
						0,
						0,
						0,
						0
					),
					tg
				);
			
			ri.setSchedulingBounds(boundingSphere);
			ri.setEnable(true);
			
			tg.addChild(ri);
			tg.addChild(upper);
			tg.addChild(lower);
			tg.addChild(hand);
			addChild(tg);
		}
	}
	public class Leg extends TransformGroup {
		private Segment upper, lower;
		private Extreme foot;
		public Leg() {
			setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
			setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			upper= new Segment();
			lower= new Segment();
			Transform3D lt3d = new Transform3D();
			lt3d.setTranslation(new Vector3f(0.0f * x, -0.4f * y, 0.0f));
			foot= new Extreme();
			Transform3D ft3d = new Transform3D();
			ft3d.setTranslation(new Vector3f(0.0f * x, -0.8f * y, 0.0f));
			ft3d.rotX(Math.PI/2);
			lower.setTransform(lt3d);
			foot.setTransform(ft3d);
			
			addChild(upper);
			addChild(lower);
			addChild(foot);
		}
	}
	public class Head extends TransformGroup {
		double scalex, scaley;
		Sphere joint;
		public Head() {
			joint= new Sphere(0.1f * x, Primitive.ENABLE_GEOMETRY_PICKING |
					Primitive.GENERATE_NORMALS |
					Primitive.GENERATE_TEXTURE_COORDS,
					app);
			Sphere head = new Sphere(0.2f * x, Primitive.ENABLE_GEOMETRY_PICKING |
					Primitive.GENERATE_NORMALS |
					Primitive.GENERATE_TEXTURE_COORDS,
					app);
			TransformGroup tg = new TransformGroup();
			tg.addChild(head);
			Transform3D t3d = new Transform3D();
			t3d.setTranslation(new Vector3f(0.0f * x, 0.25f * y, 0.0f));
			tg.setTransform(t3d);
			addChild(joint);
			addChild(tg);
		}
	}
	public class Extreme extends TransformGroup {
		double scalex, scaley;
		Sphere joint;
		public Extreme() {
			joint= new Sphere(0.1f * x, Primitive.ENABLE_GEOMETRY_PICKING |
					Primitive.GENERATE_NORMALS |
					Primitive.GENERATE_TEXTURE_COORDS,
					app);
			Box limb = new Box(0.1f * x, 0.1f * y, 0.08f, Primitive.ENABLE_GEOMETRY_PICKING |
					Primitive.GENERATE_NORMALS |
					Primitive.GENERATE_TEXTURE_COORDS,
					app);
			TransformGroup tg = new TransformGroup();
			tg.addChild(limb);
			Transform3D t3d = new Transform3D();
			t3d.setTranslation(new Vector3f(0.0f * x, -0.2f * y, 0.0f));
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
