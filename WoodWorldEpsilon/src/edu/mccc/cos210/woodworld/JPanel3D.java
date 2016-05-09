package edu.mccc.cos210.woodworld;

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Geometry;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.GraphicsConfigTemplate3D;
import javax.media.j3d.Group;
import javax.media.j3d.Locale;
import javax.media.j3d.Material;
import javax.media.j3d.PhysicalBody;
import javax.media.j3d.PhysicalEnvironment;
import javax.media.j3d.QuadArray;
import javax.media.j3d.Screen3D;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TexCoordGeneration;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.media.j3d.ViewPlatform;
import javax.media.j3d.VirtualUniverse;
import javax.swing.JPanel;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.TexCoord2f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.image.TextureLoader;


public class JPanel3D extends JPanel{

	private TransformGroup viewPlatformTransformGroup = new TransformGroup();
	private Transform3D transform3D = new Transform3D();
	private BoundingSphere boundingSphere = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), Double.MAX_VALUE);
	private TextureLoader tl = new TextureLoader("curtain.jpg", null);
	public 	Mannequin elliot, catherine, sergey, rich;
	private Stage stage;
	private ArrayList<Box> curtain= new ArrayList<Box>();
	private static final long serialVersionUID = 1L;
	private Appearance blockapp;
	public JPanel3D(Mannequin e, Mannequin c, Mannequin s, Mannequin r) {
		elliot = e;
		catherine = c;
		sergey = s;
		rich = r;
		VirtualUniverse virtualUniverse = new VirtualUniverse();
		Locale locale = new Locale(virtualUniverse);
		BranchGroup branchGroup = createViewBranch();
		viewPlatformTransformGroup.getTransform(transform3D);
		transform3D.set(new Vector3d(64.0, 0.0, 64.0));
		viewPlatformTransformGroup.setTransform(transform3D);
		locale.addBranchGraph(branchGroup);
		branchGroup = createSceneBranchGroup();
		locale.addBranchGraph(branchGroup);
	}
	public void toggleVisability(int mannvalue) {
	}
 	private BranchGroup createViewBranch() {
		BranchGroup viewBranchGroup = new BranchGroup();
		ViewPlatform viewPlatform = new ViewPlatform();
		viewPlatformTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		viewPlatformTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		viewPlatformTransformGroup.addChild(viewPlatform);
		viewBranchGroup.addChild(viewPlatformTransformGroup);
		GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] graphicsDeviceArray = graphicsEnvironment.getScreenDevices();
		GraphicsConfiguration[] graphicsConfigurationArray = graphicsDeviceArray[0].getConfigurations();
		GraphicsConfigTemplate3D graphicsConfigTemplate3D = new GraphicsConfigTemplate3D();
		GraphicsConfiguration graphicsConfiguration = graphicsConfigTemplate3D.getBestConfiguration(graphicsConfigurationArray);
		Canvas3D canvas3D = new Canvas3D(graphicsConfiguration);
		@SuppressWarnings("unused")
		Screen3D screen3D = canvas3D.getScreen3D();
		View view = new View();
		view.addCanvas3D(canvas3D);
		view.setFrontClipDistance(0.001);
		view.setBackClipDistance(500.0);
		PhysicalBody physicalBody = new PhysicalBody();
		view.setPhysicalBody(physicalBody);
		PhysicalEnvironment physicalEnvironment = new PhysicalEnvironment();
		view.setPhysicalEnvironment(physicalEnvironment);
		view.attachViewPlatform(viewPlatform);
		canvas3D.setSize(new Dimension(915, 515));
		canvas3D.requestFocus();
		add(canvas3D);
		return viewBranchGroup;
	}
	private BranchGroup createSceneBranchGroup() {
		BranchGroup branchGroup = new BranchGroup();
		TransformGroup transformGroup = new TransformGroup();
		MazeMap mazeMap = new MazeMap();
		int xMax = mazeMap.getWidth();
		int zMax = mazeMap.getHeight();
		int[] map = mazeMap.getMap();
		for (int z = 0; z < zMax; z++) {
			for (int x = 0; x < xMax; x++) {
				if (map[z * xMax + x] == MazeMap.WALL) {
					addBlock(transformGroup, x, z);
				} else {
					if (map[z * xMax + x] == MazeMap.PLAYER) {
						viewPlatformTransformGroup.getTransform(transform3D);
						transform3D.set(new Vector3d(x, 0.0, z));
						viewPlatformTransformGroup.setTransform(transform3D);
					} else {
						if (map[z * xMax + x] == MazeMap.STAGE) {
							addStage(transformGroup, x, z);
						} 
					}
				}
			}
		}
		addCeiling(transformGroup, xMax, zMax);
		addFloor(transformGroup, xMax, zMax);
		addOutsideWalls(transformGroup, xMax, zMax);
		branchGroup.addChild(transformGroup);
		setupLights(branchGroup);
		return branchGroup;
	}
	private void addStage(Group group, int x, int z) {
		TransformGroup transformGroup1 = new TransformGroup();
		Transform3D transform3D = new Transform3D();
		stage = new Stage(3);
		stage.getTransform(transform3D);
		transform3D.setTranslation(new Vector3f(x, -1.5f, z));
		stage.setTransform(transform3D);
		stage.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		stage.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
//add Elliot Mannequin
		Transform3D transformmanE = new Transform3D();
		elliot.getTransform(transformmanE);
		transformmanE.setTranslation(new Vector3f(x-3.0f, 0.7f,z - 0.5f));
		elliot.setTransform(transformmanE);
		transformGroup1.addChild(elliot);
//add Catherine Mannequin
		Transform3D transformmanC = new Transform3D();
		catherine.getTransform(transformmanC);
		transformmanC.setTranslation(new Vector3f(x-1.0f, 0.4f,z - 0.5f));
		catherine.setTransform(transformmanC);
		transformGroup1.addChild(catherine);
//add Sergey Mannequin
		Transform3D transformmanS = new Transform3D();
		sergey.getTransform(transformmanS);
		transformmanS.setTranslation(new Vector3f(x+1.0f, 0.7f,z - 0.5f));
		sergey.setTransform(transformmanS);
		transformGroup1.addChild(sergey);
//add Rich Mannequin
		Transform3D transformmanR = new Transform3D();
		rich.getTransform(transformmanR);
		transformmanR.setTranslation(new Vector3f(x+3.0f, 0.5f,z - 0.5f));
		rich.setTransform(transformmanR);
		transformGroup1.addChild(rich);
		
		transformGroup1.addChild(stage);
		group.addChild(transformGroup1);
	}
	public void setStage(int stagevalue){
		stage.setScene(stagevalue);
		setCurtain(stagevalue);
	}
	private void addBlock(Group group, int x, int z) {
		TransformGroup transformGroup = new TransformGroup();
		Transform3D transform3D = new Transform3D();
		transformGroup.getTransform(transform3D);
		transform3D.setTranslation(new Vector3f(x, 0.0f, z));
		transformGroup.setTransform(transform3D);
		Material material = new Material(
			new Color3f(1.0f, 1.0f, 1.0f),
			new Color3f(0.0f, 0.0f, 0.0f),
			new Color3f(1.0f, 1.0f, 1.0f),
			new Color3f(1.0f, 1.0f, 1.0f),
			80f
		);
		blockapp = new Appearance();
		blockapp.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_READ);
        blockapp.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
		blockapp.setCapability(Appearance.ALLOW_TEXTURE_WRITE);
		blockapp.setCapability(Appearance.ALLOW_TEXTURE_READ);
		blockapp.setCapability(Appearance.ALLOW_MATERIAL_READ);
		blockapp.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
		blockapp.setCapability(Appearance.ALLOW_TEXTURE_ATTRIBUTES_READ);
		blockapp.setCapability(Appearance.ALLOW_TEXTURE_ATTRIBUTES_WRITE);
		
		blockapp.setMaterial(material);
		blockapp.setTexCoordGeneration(new TexCoordGeneration());
		blockapp.setTexture(tl.getTexture());
		//app.setTexture(new TextureLoader("ceiling.jpg", null).getTexture());
		Box box = new Box(
				1.0f,
				4.0f,
				1.0f,
				Primitive.GENERATE_NORMALS |
				Primitive.ENABLE_GEOMETRY_PICKING |
				Primitive.GENERATE_TEXTURE_COORDS,
				blockapp
		);
		box.setCapability(Box.ENABLE_APPEARANCE_MODIFY);
		box.setCapability(Box.ALLOW_PICKABLE_READ);
        box.setCapability(Box.ALLOW_PICKABLE_WRITE);
        box.setCapability(Box.ENABLE_PICK_REPORTING);
        box.setCapability(Box.ENABLE_APPEARANCE_MODIFY);
		box.setCapability(Box.ALLOW_PICKABLE_READ);
        box.setCapability(Box.ALLOW_PICKABLE_WRITE);
        box.setCapability(Box.ENABLE_PICK_REPORTING);
        box.setCapability(Box.ALLOW_CHILDREN_EXTEND);
        box.setCapability(Box.ALLOW_CHILDREN_READ);
        box.setCapability(Box.ALLOW_CHILDREN_WRITE);
        Shape3D shape3d = box.getShape(Box.BACK);
        shape3d.setCapability(Shape3D.ALLOW_APPEARANCE_OVERRIDE_WRITE);
        shape3d.setCapability(Shape3D.ALLOW_APPEARANCE_OVERRIDE_READ);
        shape3d.setCapability(Shape3D.ALLOW_APPEARANCE_READ);
        shape3d.setCapability(Shape3D.ALLOW_APPEARANCE_WRITE);
        shape3d = box.getShape(Box.TOP);
        shape3d.setCapability(Shape3D.ALLOW_APPEARANCE_OVERRIDE_WRITE);
        shape3d.setCapability(Shape3D.ALLOW_APPEARANCE_OVERRIDE_READ);
        shape3d.setCapability(Shape3D.ALLOW_APPEARANCE_READ);
        shape3d.setCapability(Shape3D.ALLOW_APPEARANCE_WRITE);
        shape3d = box.getShape(Box.FRONT);
        shape3d.setCapability(Shape3D.ALLOW_APPEARANCE_OVERRIDE_WRITE);
        shape3d.setCapability(Shape3D.ALLOW_APPEARANCE_OVERRIDE_READ);
        shape3d.setCapability(Shape3D.ALLOW_APPEARANCE_READ);
        shape3d.setCapability(Shape3D.ALLOW_APPEARANCE_WRITE);
        shape3d = box.getShape(Box.BOTTOM);
        shape3d.setCapability(Shape3D.ALLOW_APPEARANCE_OVERRIDE_WRITE);
        shape3d.setCapability(Shape3D.ALLOW_APPEARANCE_OVERRIDE_READ);
        shape3d.setCapability(Shape3D.ALLOW_APPEARANCE_READ);
        shape3d.setCapability(Shape3D.ALLOW_APPEARANCE_WRITE);
        shape3d = box.getShape(Box.LEFT);
        shape3d.setCapability(Shape3D.ALLOW_APPEARANCE_OVERRIDE_WRITE);
        shape3d.setCapability(Shape3D.ALLOW_APPEARANCE_OVERRIDE_READ);
        shape3d.setCapability(Shape3D.ALLOW_APPEARANCE_READ);
        shape3d.setCapability(Shape3D.ALLOW_APPEARANCE_WRITE);
        shape3d = box.getShape(Box.RIGHT);
        shape3d.setCapability(Shape3D.ALLOW_APPEARANCE_OVERRIDE_WRITE);
        shape3d.setCapability(Shape3D.ALLOW_APPEARANCE_OVERRIDE_READ);
        shape3d.setCapability(Shape3D.ALLOW_APPEARANCE_READ);
        shape3d.setCapability(Shape3D.ALLOW_APPEARANCE_WRITE);
		transformGroup.addChild(box);
		group.addChild(transformGroup);
		curtain.add(box);
	}
	public void setCurtain(int sv) {
		switch (sv) { 
			case 1: 
				tl = new TextureLoader("curtain.jpg", null);;
				break;
			case 2: 
				tl = new TextureLoader("curtainirish.jpg", null);
				break;
			case 3:  
				tl = new TextureLoader("curtainballet.jpg", null);
				break;
			default : 
				break;
		}
		TextureAttributes textureAttributes = new TextureAttributes();
		textureAttributes.setTextureMode(
				TextureAttributes.MODULATE
		);
		blockapp.setTexture(tl.getTexture());
		blockapp.setTextureAttributes(textureAttributes);
		//app.setTexture(new TextureLoader("ceiling.jpg", null).getTexture());
		for (int i = 0; i < curtain.size(); i++) {
			//curtain.get(i).setAlternateCollisionTarget(arg0);
			curtain.get(i).setAppearance(blockapp);
			//System.out.println(curtain.get(i).hashCode());
		}
	}
	private void setupLights(BranchGroup branchGroup) {
		AmbientLight ambientLight = new AmbientLight(
			true,
			new Color3f(1.0f, 1.0f, 1.0f)
		);
		ambientLight.setInfluencingBounds(boundingSphere);
		branchGroup.addChild(ambientLight);
	}
	private class MazeMap {
		private int width = 0;
		private int height = 0;
		private int[] map = null;
		private final static int NOOBJECT = 0;
		private final static int WALL = 1;
		private final static int PLAYER = 2;
		private final static int STAGE = 3;
		public MazeMap() {
			try {
				BufferedImage bufferedImage = ImageIO.read(new URL("file:mapww.bmp"));
				this.width = bufferedImage.getData().getWidth();
				this.height = bufferedImage.getData().getHeight();
				this.map = new int[width * height];
				int[] pixels = bufferedImage.getData().getPixels(0, 0, width, height, (int[]) null);
				for (int i = 0; i < pixels.length; i += 3) {
					int what = pixels[i] > 200 && pixels[i + 1] < 200 && pixels[i + 2] < 200 ? PLAYER : //red
						pixels[i] < 200 && pixels[i + 1] < 200 && pixels[i + 2] > 200 ? STAGE : //blue
							pixels[i] < 100 && pixels[i + 1] < 100 && pixels[i + 2] < 100 ? WALL :
								NOOBJECT;
					this.map[i / 3] = what;
				}
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
		public int getWidth() {
			return this.width;
		}
		public int getHeight() {
			return this.height;
		}
		public int[] getMap() {
			return this.map;
		}
	}
	private void addCeiling(Group group, int xMax, int zMax) {
		QuadArray quadArray = new QuadArray(
			4,
			GeometryArray.COORDINATES |
			GeometryArray.NORMALS |
			GeometryArray.TEXTURE_COORDINATE_2
		);
		quadArray.setCapability(Geometry.ALLOW_INTERSECT);
		quadArray.setCapability(GeometryArray.ALLOW_COUNT_READ);
		quadArray.setCapability(GeometryArray.ALLOW_FORMAT_READ);
		quadArray.setCapability(GeometryArray.ALLOW_COORDINATE_READ);
		quadArray.setCoordinates(
			0,
			new Point3f[] {
				new Point3f(-1.0f, 4.0f, -1.0f),
				new Point3f(1.0f * xMax + 1.0f, 4.0f, -1.0f),
				new Point3f(1.0f * xMax + 1.0f, 4.0f, 1.0f * zMax + 1.0f),
				new Point3f(-1.0f, 4.0f, 1.0f * zMax + 1.0f)
			}
		);
		quadArray.setNormals(
			0,
			new Vector3f[] {
				new Vector3f(0.0f, -1.0f, 0.0f),
				new Vector3f(0.0f, -1.0f, 0.0f),
				new Vector3f(0.0f, -1.0f, 0.0f),
				new Vector3f(0.0f, -1.0f, 0.0f)
			}
		);
		quadArray.setTextureCoordinates(
			0,
			0,
			new TexCoord2f[] {
				new TexCoord2f(0.0f, 1.0f),
				new TexCoord2f(0.0f, 0.0f),
				new TexCoord2f(1.0f, 0.0f),
				new TexCoord2f(1.0f, 1.0f)
			}
		);
		TextureLoader textureLoader = new TextureLoader("stageballet.jpg", null);
		TextureAttributes textureAttributes = new TextureAttributes();
		textureAttributes.setTextureMode(
			TextureAttributes.MODULATE
		);
		Material material = new Material(
			new Color3f(1.0f, 1.0f, 1.0f),
			new Color3f(0.0f, 0.0f, 0.0f),
			new Color3f(1.0f, 1.0f, 1.0f),
			new Color3f(1.0f, 1.0f, 1.0f),
			128f
		);
		Appearance appearance = new Appearance();
		appearance.setMaterial(material);
		appearance.setTexture(textureLoader.getTexture());
		appearance.setTextureAttributes(textureAttributes);
		Shape3D ceiling = new Shape3D();
		ceiling.setCapability(Shape3D.ALLOW_GEOMETRY_READ);
		ceiling.addGeometry(quadArray);
		ceiling.setAppearance(appearance);
		group.addChild(ceiling);
	}
	private void addFloor(Group group, int xMax, int zMax) {
		QuadArray quadArray = new QuadArray(
			4,
			GeometryArray.COORDINATES |
			GeometryArray.NORMALS |
			GeometryArray.TEXTURE_COORDINATE_2
		);
		quadArray.setCapability(Geometry.ALLOW_INTERSECT);
		quadArray.setCapability(GeometryArray.ALLOW_COUNT_READ);
		quadArray.setCapability(GeometryArray.ALLOW_FORMAT_READ);
		quadArray.setCapability(GeometryArray.ALLOW_COORDINATE_READ);
		quadArray.setCoordinates(
			0,
			new Point3f[] {
				new Point3f(-1.0f, -2.0f, -1.0f),
				new Point3f(-1.0f, -2.0f, 1.0f * zMax + 1.0f),
				new Point3f(1.0f * xMax + 1.0f, -2.0f, 1.0f * zMax + 1.0f),
				new Point3f(1.0f * xMax + 1.0f, -2.0f, -1.0f)
			}
		);
		quadArray.setNormals(
			0,
			new Vector3f[] {
				new Vector3f(0.0f, 1.0f, 0.0f),
				new Vector3f(0.0f, 1.0f, 0.0f),
				new Vector3f(0.0f, 1.0f, 0.0f),
				new Vector3f(0.0f, 1.0f, 0.0f)
			}
		);
		quadArray.setTextureCoordinates(
			0,
			0,
			new TexCoord2f[] {
				new TexCoord2f(0.0f, 1.0f),
				new TexCoord2f(0.0f, 0.0f),
				new TexCoord2f(1.0f, 0.0f),
				new TexCoord2f(1.0f, 1.0f)
			}
		);
		TextureLoader textureLoader = new TextureLoader("floor.jpg", null);
		TextureAttributes textureAttributes = new TextureAttributes();
		textureAttributes.setTextureMode(
			TextureAttributes.MODULATE
		);
		Material material = new Material(
			new Color3f(1.0f, 1.0f, 1.0f),
			new Color3f(0.0f, 0.0f, 0.0f),
			new Color3f(1.0f, 1.0f, 1.0f),
			new Color3f(1.0f, 1.0f, 1.0f),
			128f
		);
		Appearance appearance = new Appearance();
		appearance.setMaterial(material);
		appearance.setTexture(textureLoader.getTexture());
		appearance.setTextureAttributes(textureAttributes);
		Shape3D floor = new Shape3D();
		floor.setCapability(Shape3D.ALLOW_GEOMETRY_READ);
		floor.addGeometry(quadArray);
		floor.setAppearance(appearance);
		group.addChild(floor);
	}
	private void addOutsideWalls(Group group, int xMax, int zMax) {
		QuadArray quadArray = new QuadArray(
			16,
			GeometryArray.COORDINATES |
			GeometryArray.NORMALS |
			GeometryArray.TEXTURE_COORDINATE_2
		);
		quadArray.setCapability(Geometry.ALLOW_INTERSECT);
		quadArray.setCapability(GeometryArray.ALLOW_COUNT_READ);
		quadArray.setCapability(GeometryArray.ALLOW_FORMAT_READ);
		quadArray.setCapability(GeometryArray.ALLOW_COORDINATE_READ);
		quadArray.setCoordinates(
			0,
			new Point3f[] {
				new Point3f(-1.0f, 2.0f, 1.0f * zMax + 1.0f),
				new Point3f(-1.0f, -2.0f, 1.0f * zMax + 1.0f),
				new Point3f(-1.0f, -2.0f,-1.0f),
				new Point3f(-1.0f, 2.0f, -1.0f)
			}
		);
		quadArray.setCoordinates(
			4,
			new Point3f[] {
				new Point3f(1.0f * xMax, 2.0f, -1.0f),
				new Point3f(1.0f * xMax, -2.0f, -1.0f),
				new Point3f(1.0f * xMax, -2.0f, 1.0f * zMax + 1.0f),
				new Point3f(1.0f * xMax, 2.0f, 1.0f * zMax + 1.0f)
			}
		);
		quadArray.setCoordinates(
			8,
			new Point3f[] {
				new Point3f(-1.0f, 2.0f, -1.0f),
				new Point3f(-1.0f, -2.0f, -1.0f),
				new Point3f(1.0f * xMax + 1.0f, -2.0f, -1.0f),
				new Point3f(1.0f * xMax + 1.0f, 2.0f, -1.0f)
			}
		);
		quadArray.setCoordinates(
			12,
			new Point3f[] {
				new Point3f(1.0f * xMax + 1.0f, 2.0f, 1.0f * zMax),
				new Point3f(1.0f * xMax + 1.0f, -2.0f, 1.0f * zMax),
				new Point3f(-1.0f, -2.0f, 1.0f * zMax),
				new Point3f(-1.0f, 2.0f, 1.0f * zMax)
			}
		);
		quadArray.setNormals(
			0,
			new Vector3f[] {
				new Vector3f(1.0f, 0.0f, 0.0f),
				new Vector3f(1.0f, 0.0f, 0.0f),
				new Vector3f(1.0f, 0.0f, 0.0f),
				new Vector3f(1.0f, 0.0f, 0.0f)
			}
		);
		quadArray.setNormals(
			4,
			new Vector3f[] {
				new Vector3f(-1.0f, 0.0f, 0.0f),
				new Vector3f(-1.0f, 0.0f, 0.0f),
				new Vector3f(-1.0f, 0.0f, 0.0f),
				new Vector3f(-1.0f, 0.0f, 0.0f)
			}
		);
		quadArray.setNormals(
			8,
			new Vector3f[] {
				new Vector3f(0.0f, 0.0f, 1.0f),
				new Vector3f(0.0f, 0.0f, 1.0f),
				new Vector3f(0.0f, 0.0f, 1.0f),
				new Vector3f(0.0f, 0.0f, 1.0f)
			}
		);
		quadArray.setNormals(
			12,
			new Vector3f[] {
				new Vector3f(0.0f, 0.0f, -1.0f),
				new Vector3f(0.0f, 0.0f, -1.0f),
				new Vector3f(0.0f, 0.0f, -1.0f),
				new Vector3f(0.0f, 0.0f, -1.0f)
			}
		);
		quadArray.setTextureCoordinates(
			0,
			0,
			new TexCoord2f[] {
				new TexCoord2f(0.0f, 1.0f),
				new TexCoord2f(0.0f, 0.0f),
				new TexCoord2f(50.0f, 0.0f),
				new TexCoord2f(50.0f, 1.0f)
			}
		);
		quadArray.setTextureCoordinates(
			0,
			4,
			new TexCoord2f[] {
				new TexCoord2f(0.0f, 1.0f),
				new TexCoord2f(0.0f, 0.0f),
				new TexCoord2f(50.0f, 0.0f),
				new TexCoord2f(50.0f, 1.0f)
			}
		);
		quadArray.setTextureCoordinates(
			0,
			8,
			new TexCoord2f[] {
				new TexCoord2f(0.0f, 1.0f),
				new TexCoord2f(0.0f, 0.0f),
				new TexCoord2f(50.0f, 0.0f),
				new TexCoord2f(50.0f, 1.0f)
			}
		);
		quadArray.setTextureCoordinates(
			0,
			12,
			new TexCoord2f[] {
				new TexCoord2f(0.0f, 1.0f),
				new TexCoord2f(0.0f, 0.0f),
				new TexCoord2f(50.0f, 0.0f),
				new TexCoord2f(50.0f, 1.0f)
			}
		);
		TextureLoader textureLoader = new TextureLoader("owall.jpg", null);
		TextureAttributes textureAttributes = new TextureAttributes();
		textureAttributes.setTextureMode(
			TextureAttributes.MODULATE
		);
		Texture texture = textureLoader.getTexture();
		texture.setBoundaryModeS(Texture.WRAP);
		texture.setBoundaryModeT(Texture.WRAP);
		Material material = new Material(
			new Color3f(1.0f, 1.0f, 1.0f),
			new Color3f(0.0f, 0.0f, 0.0f),
			new Color3f(1.0f, 1.0f, 1.0f),
			new Color3f(1.0f, 1.0f, 1.0f),
			128f
		);
		Appearance appearance = new Appearance();
		appearance.setMaterial(material);
		appearance.setTexture(texture);
		appearance.setTextureAttributes(textureAttributes);
		Shape3D owalls = new Shape3D();
		owalls.setCapability(Shape3D.ALLOW_GEOMETRY_READ);
		owalls.addGeometry(quadArray);
		owalls.setAppearance(appearance);
		group.addChild(owalls);
	}
}