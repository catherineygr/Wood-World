package edu.mccc.cos210.woodworld;

import javax.media.j3d.Appearance;

import com.sun.j3d.utils.geometry.Box;

public class UpperBody {
	Box torso;
	Arm rarm, larm;
	Segment head;
	double scalex, scaley, scalez;
	Appearance app;
	public UpperBody(double scalex, double scaley, double scalez, Appearance app) {
		this.scalex=scalex;
		this.scaley=scaley;
		this.scalez=scalez;
		this.app=app;
	}
}
