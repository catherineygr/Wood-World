package tutorials;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public class Finger extends TransformGroup {
	Phalanx upper = new Phalanx();
	Phalanx middle = new Phalanx();
	Phalanx lower = new Phalanx();
	public Finger() {
		
		TransformGroup tgu= new TransformGroup();
		TransformGroup tgm = new TransformGroup();
		Transform3D transformu = new Transform3D();
		Transform3D transformm = new Transform3D();
		transformu.setTranslation(new Vector3f(.0f, .035f, .0f));
		transformm.setTranslation(new Vector3f(.0f, .07f, .0f));
		tgu.setTransform(transformu);
		tgm.setTransform(transformm);
		tgu.addChild(upper);
		tgm.addChild(middle);
		addChild(tgm);
		addChild(tgu);
		addChild(lower);
		Alpha upRamp = new Alpha();
		upRamp.setAlphaAtOneDuration(1000);
		RotationInterpolator mySpinner = new RotationInterpolator(upRamp,this);
		mySpinner.setTransformAxis(new Transform3D());
		mySpinner.setMinimumAngle( 0.0f );
		mySpinner.setMaximumAngle( (float)(Math.PI * 2.0) );
		mySpinner.setSchedulingBounds(new BoundingSphere());
		addChild(mySpinner);
		
	}
	public void FoldDown() {
//		removeAllChildren();
//		
//		setTransform(curl1);
//		addChild(tgm);
//		addChild(tgu);
//		addChild(lower);
		
	}

}
