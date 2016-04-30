package edu.mccc.cos210.woodworld;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsConfigTemplate;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.media.j3d.Alpha;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Geometry;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.GraphicsConfigTemplate3D;
import javax.media.j3d.Group;
import javax.media.j3d.Locale;
import javax.media.j3d.Material;
import javax.media.j3d.PhysicalBody;
import javax.media.j3d.PhysicalEnvironment;
import javax.media.j3d.QuadArray;
import javax.media.j3d.RotPosPathInterpolator;
import javax.media.j3d.RotationInterpolator;
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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.vecmath.AxisAngle4d;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Quat4f;
import javax.vecmath.TexCoord2f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;





public class JPanel3D extends JPanel{

	private TransformGroup viewPlatformTransformGroup = new TransformGroup();
	private Transform3D transform3D = new Transform3D();
	private BoundingSphere boundingSphere = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), Double.MAX_VALUE);
	private TextureLoader textureLoader = new TextureLoader("block.jpg", null);
	private static final long serialVersionUID = 1L;
	public JPanel3D() {
		
		
		VirtualUniverse universe = new VirtualUniverse();
		
		VirtualUniverse virtualUniverse = new VirtualUniverse();
		Locale locale = new Locale(virtualUniverse);
		BranchGroup branchGroup = createViewBranch();
		viewPlatformTransformGroup.getTransform(transform3D);
		transform3D.set(new Vector3d(64.0, 0.0, 64.0));
		viewPlatformTransformGroup.setTransform(transform3D);
		locale.addBranchGraph(branchGroup);
		branchGroup = createSceneBranchGroup();
		KeyCABehavior4 keyCABehavior4 = new KeyCABehavior4(viewPlatformTransformGroup, branchGroup);
		keyCABehavior4.setSchedulingBounds(boundingSphere);
		branchGroup.addChild(keyCABehavior4);
		locale.addBranchGraph(branchGroup);
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
		canvas3D.setSize(new Dimension(500, 500));
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
						transform3D.set(new Vector3d(x, -1.0, z));
						viewPlatformTransformGroup.setTransform(transform3D);
					} else {
						if (map[z * xMax + x] == MazeMap.OBJECT1) {
							addObject1(transformGroup, x, z);
						} else {
							if (map[z * xMax + x] == MazeMap.OBJECT2) {
								addObject2(transformGroup, x, z);
							} else {
								if (map[z * xMax + x] == MazeMap.OBJECT3) {
									addObject3(transformGroup, x, z);
								} else {
									if (map[z * xMax + x] == MazeMap.OBJECT4) {
										addObject4(transformGroup, x, z);
									}
								}
							}
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
	private void addObject1(Group group, int x, int z) {
		TransformGroup transformGroup = new TransformGroup();
		Transform3D transform3D = new Transform3D();
		transformGroup.getTransform(transform3D);
		transform3D.setTranslation(new Vector3f(x, -1.375f, z));
		transformGroup.setTransform(transform3D);
		Shape3D shape3D = new Shape3D();
		TableT tableT = new TableT();
		tableT.setCapability(Geometry.ALLOW_INTERSECT);
		tableT.setCapability(GeometryArray.ALLOW_COUNT_READ);
		tableT.setCapability(GeometryArray.ALLOW_FORMAT_READ);
		tableT.setCapability(GeometryArray.ALLOW_COORDINATE_READ);
		shape3D.addGeometry(tableT);
		shape3D.setCapability(Shape3D.ALLOW_GEOMETRY_READ);
		Appearance appearance = new Appearance();
		Material material = new Material(
			new Color3f(1.0f, 1.0f, 1.0f),
			new Color3f(0.0f, 0.0f, 0.0f),
			new Color3f(0.5f, 0.5f, 0.5f),
			new Color3f(1.0f, 1.0f, 1.0f),
			96.0f
		);
		appearance.setMaterial(material);
		TextureLoader textureLoader = new TextureLoader("J3D10.jpg", null);
		Texture texture = textureLoader.getTexture();
		texture.setBoundaryModeS(Texture.CLAMP);
		texture.setBoundaryModeT(Texture.CLAMP);
		appearance.setTexture(texture);
		shape3D.setAppearance(appearance);
		transformGroup.addChild(shape3D);
		group.addChild(transformGroup);
	}
	private void addObject2(Group group, int x, int z) {
		TransformGroup transformGroup1 = new TransformGroup();
		Transform3D transform3D = new Transform3D();
		transformGroup1.getTransform(transform3D);
		transform3D.setTranslation(new Vector3f(x, -1.0f, z));
		transformGroup1.setTransform(transform3D);
		TransformGroup transformGroup2 = new TransformGroup();
		transformGroup2.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		transformGroup2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Appearance appearance = new Appearance();
		Material material = new Material(
			new Color3f(1.0f, 1.0f, 1.0f),
			new Color3f(0.0f, 0.0f, 0.0f),
			new Color3f(0.5f, 0.5f, 0.5f),
			new Color3f(1.0f, 1.0f, 1.0f),
			96.0f
		);
		appearance.setMaterial(material);
		Sphere sphere = new Sphere(
			1.0f,
			Primitive.ENABLE_GEOMETRY_PICKING |
				Primitive.GENERATE_NORMALS |
				Primitive.GENERATE_TEXTURE_COORDS,
				128,
				appearance
		);
		RotationInterpolator rotationInterpolator = new RotationInterpolator(
			new Alpha(
				-1,
				Alpha.INCREASING_ENABLE,
				0,
				0,
				500,
				0,
				0,
				0,
				0,
				0
			),
			transformGroup2
		);
		Shape3D shape3D = sphere.getShape(Sphere.BODY);
		shape3D.setUserData(
			new ApplicationBundle(
				"CollisionBehavior",
				new CollisionBehavior(
					shape3D.hashCode(),
					2500,
					rotationInterpolator,
					transformGroup2
				)
			)
		);
		rotationInterpolator.setEnable(false);
		rotationInterpolator.setSchedulingBounds(boundingSphere);
		transformGroup2.addChild(rotationInterpolator);
		TextureLoader textureLoader = new TextureLoader("J3D14.jpg", null);
		Texture texture = textureLoader.getTexture();
		texture.setBoundaryModeS(Texture.CLAMP);
		texture.setBoundaryModeT(Texture.CLAMP);
		appearance.setTexture(texture);
		transformGroup2.addChild(sphere);
		transformGroup1.addChild(transformGroup2);
		group.addChild(transformGroup1);
	}
	private void addObject3(Group group, int x, int z) {
		TransformGroup transformGroup1 = new TransformGroup();
		transformGroup1.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		transformGroup1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Quat4f q4f0 = new Quat4f();
		q4f0.set(new AxisAngle4d(0.0, 1.0, 0.0, 0.0));
		Quat4f q4f1 = new Quat4f();
		q4f1.set(new AxisAngle4d(0.0, 1.0, 0.0, Math.PI / 2.0));
		Quat4f q4f2 = new Quat4f();
		q4f2.set(new AxisAngle4d(0.0, 1.0, 0.0, Math.PI / 2.0 * 2.0));
		Quat4f q4f3 = new Quat4f();
		q4f3.set(new AxisAngle4d(0.0, 1.0, 0.0, Math.PI / 2.0 * 3.0));
		RotPosPathInterpolator rppi = new RotPosPathInterpolator(
			new Alpha(
				-1,
				24000
			),
			transformGroup1,
			new Transform3D(),
			new float[] {
				0.0f,
				0.24f,
				0.25f,
				0.49f,
				0.5f,
				0.74f,
				0.75f,
				0.99f,
				1.0f
			},
			new Quat4f[] {
				q4f0,
				q4f0,
				q4f1,
				q4f1,
				q4f2,
				q4f2,
				q4f3,
				q4f3,
				q4f0
			},
			new Point3f[] {
				new Point3f(0.0f, 0.0f, 0.0f),

				new Point3f(0.0f, 0.0f, 6.0f),
				new Point3f(0.0f, 0.0f, 6.0f),

				new Point3f(6.0f, 0.0f, 6.0f),
				new Point3f(6.0f, 0.0f, 6.0f),

				new Point3f(6.0f, 0.0f, 0.0f),
				new Point3f(6.0f, 0.0f, 0.0f),

				new Point3f(0.0f, 0.0f, 0.0f),
				new Point3f(0.0f, 0.0f, 0.0f)
			}
		);
		rppi.setSchedulingBounds(boundingSphere);
		transformGroup1.addChild(rppi);
		TransformGroup transformGroup2 = new TransformGroup();
		Transform3D transform3D2 = new Transform3D();
		transformGroup2.getTransform(transform3D2);
		transform3D2.setTranslation(new Vector3f(x, -2.025f, z));
		transformGroup2.setTransform(transform3D2);
		transformGroup1.addChild(new Beaver());
		transformGroup2.addChild(transformGroup1);
		group.addChild(transformGroup2);
	}
	private void addObject4(Group group, int x, int z) {
		TransformGroup transformGroup1 = new TransformGroup();
		Transform3D transform3D1 = new Transform3D();
		transformGroup1.getTransform(transform3D);
		transform3D1.setTranslation(new Vector3f(x, -0.65f, z));
		transformGroup1.setTransform(transform3D1);
		TransformGroup transformGroup2 = new TransformGroup();
		Transform3D transform3D2 = new Transform3D();
		transformGroup2.getTransform(transform3D2);
		transform3D2.setScale(0.5);
		transformGroup2.setTransform(transform3D2);
		transformGroup2.addChild(new Truck2());
		transformGroup1.addChild(transformGroup2);
		group.addChild(transformGroup1);
	}
	private void addBlock(Group group, int x, int z) {
		TransformGroup transformGroup = new TransformGroup();
		Transform3D transform3D = new Transform3D();
		transformGroup.getTransform(transform3D);
		transform3D.setTranslation(new Vector3f(x, 0.0f, z));
		transformGroup.setTransform(transform3D);
		Material material = new Material(
			new Color3f(1.0f, 1.0f, 0.0f),
			new Color3f(0.0f, 0.0f, 0.0f),
			new Color3f(1.0f, 1.0f, 0.0f),
			new Color3f(1.0f, 1.0f, 1.0f),
			80f
		);
		Appearance appearance = new Appearance();
		appearance.setMaterial(material);
		appearance.setTexCoordGeneration(new TexCoordGeneration());
		appearance.setTexture(textureLoader.getTexture());
		Box box = new Box(
				1.0f,
				2.0f,
				1.0f,
				Primitive.GENERATE_NORMALS |
					Primitive.ENABLE_GEOMETRY_PICKING,
				appearance
		);
		transformGroup.addChild(box);
		group.addChild(transformGroup);
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
		private final static int OBJECT1 = 3;
		private final static int OBJECT2 = 4;
		private final static int OBJECT3 = 5;
		private final static int OBJECT4 = 6;
		private final static int STAGE = 6;
		public MazeMap() {
			try {
				BufferedImage bufferedImage = ImageIO.read(new URL("file:map.bmp"));
				this.width = bufferedImage.getData().getWidth();
				this.height = bufferedImage.getData().getHeight();
				this.map = new int[width * height];
				int[] pixels = bufferedImage.getData().getPixels(0, 0, width, height, (int[]) null);
				for (int i = 0; i < pixels.length; i += 3) {
					int what = pixels[i] > 200 && pixels[i + 1] < 200 && pixels[i + 2] < 200 ? PLAYER :
						pixels[i] < 200 && pixels[i + 1] < 200 && pixels[i + 2] > 200 ? OBJECT1 :
							pixels[i] < 100 && pixels[i + 1] < 100 && pixels[i + 2] < 100 ? WALL :
								pixels[i] < 200 && pixels[i + 1] > 200 && pixels[i + 2] < 200 ? OBJECT2 :
									pixels[i] > 200 && pixels[i + 1] > 200 && pixels[i + 2] < 200 ? OBJECT3 :
										pixels[i] > 200 && pixels[i + 1] < 200 && pixels[i + 2] > 200 ? OBJECT4 :
											//pixels[i] > 200 && pixels[i + 1] > 200 && pixels[i + 2] > 200 ? STAGE:
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
				new Point3f(-1.0f, 2.0f, -1.0f),
				new Point3f(1.0f * xMax + 1.0f, 2.0f, -1.0f),
				new Point3f(1.0f * xMax + 1.0f, 2.0f, 1.0f * zMax + 1.0f),
				new Point3f(-1.0f, 2.0f, 1.0f * zMax + 1.0f)
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
		TextureLoader textureLoader = new TextureLoader("ceiling.jpg", null);
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

	private View createView(ViewPlatform vp) {
		View view = new View();
		Canvas3D canvas = createCanvas();
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
	    c3d.setSize(new Dimension(500, 500));
		return c3d;
	}
}