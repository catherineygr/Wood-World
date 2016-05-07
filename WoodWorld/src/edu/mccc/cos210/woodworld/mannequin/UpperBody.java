package edu.mccc.cos210.woodworld.mannequin;

import javax.media.j3d.Appearance;
import javax.media.j3d.TransformGroup;
import com.sun.j3d.utils.geometry.Box;

public class UpperBody {
	Box torso;
	Arm rightArm;
	Arm leftArm;
	Segment head;
	double scalex, scaley, scalez;
	Appearance app;
	/*public UpperBody(double scalex, double scaley, double scalez, Appearance app) {
		this.scalex=scalex;
		this.scaley=scaley;
		this.scalez=scalez;
		this.app=app;
	}*/
	public UpperBody(float hightRatio, float thicknessRatio, Mannequin mannequin) {
		
	}
}
