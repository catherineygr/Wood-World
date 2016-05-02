package edu.mccc.cos210.woodworld.mannequin;

import javax.media.j3d.Appearance;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Sphere;

public class Segment extends TransformGroup{
	/*double scalex, scaley, scalez;
	Sphere joint;
	Appearance app;
	public Segment(double scalex, double scaley,double scalez, Cylinder shape, Appearance app) {
	this.scalex = scalex;
	this.scaley = scaley;
	this.scalez = scalez;
	joint = new Sphere((float) (0.1f * scalex), app);
	TransformGroup ltg = new TransformGroup();
	Transform3D lt3d = new Transform3D();
	Vector3f v3d = new Vector3f(0.0f, (float)(-0.1f * scalex), 0.0f );
	lt3d.setTranslation(v3d);
	ltg.addChild(shape);
	ltg.setTransform(lt3d);
	addChild(joint);
	addChild(ltg);
	
	}
	public Segment(double scalex, double scaley,double scalez, Sphere shape, Appearance app) {
		this.scalex=scalex;
		this.scaley=scaley;
		this.scalez=scalez;
		joint= new Sphere((float) (1.0f * scalex), app);
		}
	public Segment(double scalex, double scaley,double scalez, Box shape, Appearance app) {
		this.scalex=scalex;
		this.scaley=scaley;
		this.scalez=scalez;
		joint= new Sphere((float) (1.0f * scalex), app);
		}
	public Segment() {
		this(1.0, 1.0, 1.0, new Cylinder(), new Appearance());
	}*/
	Sphere joint;
	Appearance app;
	public Segment(Leg leg) {
		
	}
	public Segment(Segment segment) {
		
	}
}
