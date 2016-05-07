package edu.mccc.cos210.woodworld;

import javax.media.j3d.Alpha;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Material;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Texture;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;

import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;

import edu.mccc.cos210.woodworld.mannequin.Mannequin;

public class Manny extends Mannequin {
	private RotationInterpolator ri;

	public Manny() {
		setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Appearance appearance = new Appearance();
		Material material = new Material(
			new Color3f(1.0f, 1.0f, 1.0f),
			new Color3f(0.0f, 0.0f, 0.0f),
			new Color3f(0.5f, 0.5f, 0.5f),
			new Color3f(1.0f, 1.0f, 1.0f),
			96.0f
		);
		appearance.setMaterial(material);
		TextureLoader textureLoader = new TextureLoader("floor.jpg", null);
		Texture texture = textureLoader.getTexture();
		texture.setBoundaryModeS(Texture.CLAMP);
		texture.setBoundaryModeT(Texture.CLAMP);
		appearance.setTexture(texture);
		Cylinder cylinder = new Cylinder(0.5f, 1.0f, Primitive.ENABLE_GEOMETRY_PICKING |
				Primitive.GENERATE_NORMALS |
				Primitive.GENERATE_TEXTURE_COORDS,
				appearance);
		addChild(cylinder);
		ri = new RotationInterpolator(
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
				this
			);
			ri.setEnable(false);
			BoundingSphere boundingSphere = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), Double.MAX_VALUE);
			ri.setSchedulingBounds(boundingSphere);
			addChild(new Dance(new Alpha(
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
				this));
	}

	@Override
	public void dance() {
		
		
	}

	@Override
	public void jump() {
		// TODO Auto-generated method stub
		
	}
}
