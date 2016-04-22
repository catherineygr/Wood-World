package tutorials;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.geometry.ColorCube;
import javax.media.j3d.BranchGroup;
public class Hello3d {
		public  Hello3d() {
			SimpleUniverse universe = new SimpleUniverse();
			BranchGroup group = new BranchGroup();
			ColorCube cube1 = new ColorCube(0.3);
			group.addChild(cube1);
			universe.getViewingPlatform().setNominalViewingTransform();
			universe.addBranchGraph(group);
			
			
		}
		public static void main(String[] sa) {
			new Hello3d();
		}
	}
