package tutorials;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GraphicsConfigTemplate;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.GraphicsConfigTemplate3D;
import javax.media.j3d.Group;
import javax.media.j3d.Locale;
import javax.media.j3d.PhysicalBody;
import javax.media.j3d.PhysicalEnvironment;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.media.j3d.ViewPlatform;
import javax.media.j3d.VirtualUniverse;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.universe.SimpleUniverse;


public class testjp3d extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public testjp3d() {
		
		Canvas3D canvas = createCanvas();
		VirtualUniverse universe = new VirtualUniverse();
		Hand hand = new Hand();
		BranchGroup sgroup = createSceneBranchGroup();
		//BranchGroup vgroup = new BranchGroup();
		
		Locale locale = new Locale(universe);
		BranchGroup vpBranchGroup = new BranchGroup();
		
		//create a TransformGroup to scale the ViewPlatform
		//(and hence View)
		TransformGroup tg = new TransformGroup();

		//create the ViewPlatform
		ViewPlatform vp = new ViewPlatform();
		vp.setViewAttachPolicy( View.RELATIVE_TO_FIELD_OF_VIEW );

		//attach the ViewPlatform to the TransformGroup
		tg.addChild( vp );

		//attach the TransformGroup to the BranchGroup
		vpBranchGroup.addChild( tg );

		//finally, add the ViewPlatform BranchGroup to the Locale
		
		locale.addBranchGraph(sgroup);
		locale.addBranchGraph(vpBranchGroup);
		
//		sgroup.addBranchGraph(vgroup);
		//create the View object
		View view = new View();
		//create the PhysicalBody and PhysicalEnvironment for the View
		//and attach to the View
		PhysicalBody pb = new PhysicalBody();
		PhysicalEnvironment pe = new PhysicalEnvironment();
		view.setPhysicalEnvironment( pe );
		view.setPhysicalBody( pb );

		//attach the View to the ViewPlatform
		view.attachViewPlatform(vp);

		//set the near and far clipping planes for the View
		view.setBackClipDistance(110); 
		view.addCanvas3D(canvas);
		add(canvas, BorderLayout.CENTER);
		
	}
	private BranchGroup createSceneBranchGroup() {
	    BranchGroup objRoot = new BranchGroup();
	    BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),
	        100.0);

	    BranchGroup sceneBranchGroup = new BranchGroup();

	    
	    sceneBranchGroup.setCapability(Group.ALLOW_CHILDREN_EXTEND);
	    sceneBranchGroup.setCapability(Group.ALLOW_CHILDREN_READ);
	    sceneBranchGroup.setCapability(Group.ALLOW_CHILDREN_WRITE);

	    Color3f lColor1 = new Color3f(0.7f, 0.7f, 0.7f);
	    Vector3f lDir1 = new Vector3f(-1.0f, -1.0f, -1.0f);
	    Color3f alColor = new Color3f(0.2f, 0.2f, 0.2f);

	    AmbientLight aLgt = new AmbientLight(alColor);
	    aLgt.setInfluencingBounds(bounds);
	    DirectionalLight lgt1 = new DirectionalLight(lColor1, lDir1);
	    lgt1.setInfluencingBounds(bounds);
	    
	   
	    Hand hand = new Hand();
	    sceneBranchGroup.addChild(hand);
	    
	    objRoot.addChild(aLgt);
	    objRoot.addChild(lgt1);
	    objRoot.addChild(sceneBranchGroup);
	    
	    return objRoot;
	}
	private View createView(ViewPlatform vp) {
		View view = new View();

	    PhysicalBody pb = new PhysicalBody();
	    PhysicalEnvironment pe = new PhysicalEnvironment();

	    view.setPhysicalEnvironment(pe);
	    view.setPhysicalBody(pb);

	    if (vp != null)
	      view.attachViewPlatform(vp);

	    view.setBackClipDistance(100.0);
	    view.setFrontClipDistance(1.0);
	    	   
		return view;
	}
	private ViewPlatform createViewPlatform() {
		ViewPlatform vp = new ViewPlatform();
	    vp.setViewAttachPolicy(View.RELATIVE_TO_FIELD_OF_VIEW);
	    vp.setActivationRadius(100);
	    return vp;
	}
	private Canvas3D createCanvas() {
		GraphicsConfigTemplate3D gc3D = new GraphicsConfigTemplate3D();
	    gc3D.setSceneAntialiasing(GraphicsConfigTemplate.PREFERRED);
	    GraphicsDevice gd[] = GraphicsEnvironment.getLocalGraphicsEnvironment()
	        .getScreenDevices();

	    Canvas3D c3d = new Canvas3D(gd[0].getBestConfiguration(gc3D));
	    c3d.setSize(500, 500);
		return c3d;
	}
}
