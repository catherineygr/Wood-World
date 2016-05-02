package edu.mccc.cos210.woodworld;

import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.image.TextureLoader;

public class Stage extends TransformGroup {
	public Stage() {
		
		setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		
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
		Appearance app = new Appearance();
		app.setMaterial(material);
		app.setTexture(textureLoader.getTexture());
		app.setTextureAttributes(textureAttributes);
		Box stage = new Box(5.0f, 0.75f, 5.0f, Primitive.ENABLE_GEOMETRY_PICKING |
				Primitive.GENERATE_NORMALS |
				Primitive.GENERATE_TEXTURE_COORDS, app);
		addChild(stage);
	}
}
