package edu.mccc.cos210.woodworld;

import java.awt.event.KeyEvent;
import java.util.Enumeration;

import javax.media.j3d.Alpha;
import javax.media.j3d.Behavior;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupOnAWTEvent;
import javax.vecmath.Point3d;

public class Dance extends RotationInterpolator {
	public Dance(Alpha arg0, TransformGroup arg1) {
		super(arg0, arg1);
	}
	@Override
	public void initialize() {
		super.initialize();
		wakeupOn(new WakeupOnAWTEvent(KeyEvent.VK_D));
	}

	@Override
	public void processStimulus(Enumeration arg0) {
		this.processStimulus(arg0);
		wakeupOn(new WakeupOnAWTEvent(KeyEvent.VK_D));
		
	}

}
