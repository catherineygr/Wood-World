package edu.mccc.cos210.woodworld;

import javax.media.j3d.Appearance;

public class Arm {
	double scalex, scaley, scalez;
	Segment upper, lower, hand;
	Appearance app;
	public Arm(double scalex,double scaley, double scalez, Appearance app) {
		this.scalex=scalex;
		this.scaley=scaley;
		this.scalez=scalez;
		this.app=app;
	}

}
