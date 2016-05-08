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
	private int stagevalue;
	private Box stage;
	private TextureLoader tl;
	private Appearance app;
	public Stage(int sv) {
		stagevalue=sv;
		setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
		app = new Appearance();
		app.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_READ);
        app.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
		app.setCapability(Appearance.ALLOW_TEXTURE_WRITE);
		app.setCapability(Appearance.ALLOW_TEXTURE_READ);
		app.setCapability(Appearance.ALLOW_MATERIAL_READ);
		app.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
		app.setCapability(Appearance.ALLOW_TEXTURE_ATTRIBUTES_READ);
		app.setCapability(Appearance.ALLOW_TEXTURE_ATTRIBUTES_WRITE);
		Material material = new Material(
			new Color3f(1.0f, 1.0f, 1.0f),
			new Color3f(0.0f, 0.0f, 0.0f),
			new Color3f(1.0f, 1.0f, 1.0f),
			new Color3f(1.0f, 1.0f, 1.0f),
			128f
		);
		
		app.setMaterial(material);
		tl= new TextureLoader("stagestandard.jpg", null);
		TextureAttributes textureAttributes = new TextureAttributes();
		textureAttributes.setTextureMode(
			TextureAttributes.MODULATE
		);
		app.setTexture(tl.getTexture());
		app.setTextureAttributes(textureAttributes);
		stage = new Box(5.0f, 0.75f, 5.0f, Primitive.ENABLE_GEOMETRY_PICKING |
				Primitive.GENERATE_NORMALS |
				Primitive.GENERATE_TEXTURE_COORDS, app);
		stage.setCapability(Box.ENABLE_APPEARANCE_MODIFY);
		stage.setCapability(Box.ALLOW_PICKABLE_READ);
        stage.setCapability(Box.ALLOW_PICKABLE_WRITE);
        stage.setCapability(Box.ENABLE_PICK_REPORTING);
		addChild(stage);
	}
	public void setScene(int sv) {
		this.stagevalue=sv;
		switch (stagevalue) { 
			case 1: 
				tl= new TextureLoader("stagestandard.jpg", null);
				break;
			case 2: 
				tl= new TextureLoader("stageirish.jpg", null);
				break;
			case 3:  
				tl= new TextureLoader("stageballet.jpg", null);
				break;
			default : 
				tl= new TextureLoader("floor.jpg", null);
				break;
		}
		TextureAttributes textureAttributes = new TextureAttributes();
		textureAttributes.setTextureMode(
			TextureAttributes.MODULATE
		);
		app.setTexture(tl.getTexture());
		app.setTextureAttributes(textureAttributes);
		stage.setAppearance(app);
	}
}