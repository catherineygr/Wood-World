package edu.mccc.cos210.woodworld;

import javax.media.j3d.Appearance;
import javax.media.j3d.TransformGroup;

import com.sun.j3d.utils.geometry.Sphere;

public class Mannequin extends TransformGroup {
	Sphere waist;
	Boolean visibility;
	Appearance app;
	UpperBody ub;
	LowerBody lb;
	String name;
	Double scalex, scaley, scalez;
	public Mannequin(double scalex, double scaley,double scalez) {
		this.scalex=scalex;
		this.scaley=scaley;
		this.scalez=scalez;
		app= new Appearance();
		
	}
}
