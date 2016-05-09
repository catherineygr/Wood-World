package edu.mccc.cos210.woodworld;
import java.awt.event.KeyEvent;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.TransformGroup;
public class KeyCABehavior3 extends KeyCABehavior2 {
	protected static final double HYPER = 1.00;
	public KeyCABehavior3(TransformGroup tg, BranchGroup bg) {
		super(tg, bg);
	}
	protected void processKeyEvent(KeyEvent ke) {
		int kc = ke.getKeyCode();
		if (ke.isShiftDown()) {
			speed = HYPER;
			rotAmount = Math.PI / 60.0;
		} else {
			speed = NORMAL;
			rotAmount = Math.PI / 120.0;
		}
		if (ke.isAltDown()) {
			altTransform(kc);
		} else {
			if (ke.isControlDown()) {
				controlTransform(kc);
			} else {
				standardTransform(kc);
			}
		}
	}
}
