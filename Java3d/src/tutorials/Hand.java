package tutorials;
import com.sun.j3d.utils.geometry.*;

import com.sun.j3d.utils.universe.*;

import javax.media.j3d.*;

import javax.vecmath.*;

public class Hand extends TransformGroup{
	public Hand() {
		Appearance app = new Appearance();
	    Color3f objColor = new Color3f(1.0f, 0.7f, 0.8f);
	    Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
	    app.setMaterial(new Material(objColor, black, objColor, black, 80.0f));
		Box box= new Box(0.04f, 0.05f, 0.01f, app);
		Finger index = new Finger();
		Finger middle = new Finger();
		Finger ring = new Finger();
		Finger pinky = new Finger();
		TransformGroup tgi = new TransformGroup();
		TransformGroup tgm = new TransformGroup();
		TransformGroup tgr = new TransformGroup();
		TransformGroup tgp = new TransformGroup();
		Transform3D transformi = new Transform3D();
		Transform3D transformm = new Transform3D();
		Transform3D transformr = new Transform3D();
		Transform3D transformp = new Transform3D();
		transformi.setTranslation(new Vector3f(-.0375f, .06f, .0f));
		transformr.setTranslation(new Vector3f(-.0125f, .06f, .0f));
		transformm.setTranslation(new Vector3f(.0125f, .06f, .0f));
		transformp.setTranslation(new Vector3f(.0375f, .06f, .0f));
		tgi.addChild(index);
		tgm.addChild(middle);
		tgr.addChild(ring);
		tgp.addChild(pinky);
		tgi.setTransform(transformi);
		tgm.setTransform(transformm);
		tgr.setTransform(transformr);
		tgp.setTransform(transformp);
		addChild(box);
		addChild(tgi);
		addChild(tgm);
		addChild(tgr);
		addChild(tgp);
	
	}

}
