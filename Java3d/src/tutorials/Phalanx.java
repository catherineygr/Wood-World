package tutorials;
import com.sun.j3d.utils.geometry.*;

import com.sun.j3d.utils.universe.*;

import javax.media.j3d.*;

import javax.vecmath.*;

public class Phalanx extends TransformGroup {
	public Phalanx() {
		Sphere sphere = new Sphere(0.01f, new Appearance());
		Cylinder cylinder = new Cylinder(0.01f, 0.03f, new Appearance());
		TransformGroup tg = new TransformGroup();
		Transform3D transform = new Transform3D();
		Vector3f vector = new Vector3f(.0f, .02f, .0f);
		transform.setTranslation(vector);
		tg.setTransform(transform);
		tg.addChild(cylinder);
		addChild(sphere);
		addChild(tg);

	}

}
