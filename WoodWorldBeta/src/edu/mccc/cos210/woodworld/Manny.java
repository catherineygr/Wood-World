package edu.mccc.cos210.woodworld;

import java.util.Vector;

import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.media.j3d.Texture;
import javax.media.j3d.Transform3D;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.image.TextureLoader;

public class Manny extends Mannequin {
	Appearance app;
	static TextureLoader tl = new TextureLoader("floor.jpg", null);
	public Manny() {
		super(1.5f, 1.0f, tl);
		
	}
	@Override
	public void dance() {
		
		
	}

	@Override
	public void jump() {
		Vector3f vector = new Vector3f(0.0f, 0.5f, 0.0f);
		Transform3D t3d = new Transform3D();
		getTransform(t3d);
		t3d.setTranslation(vector);
		
		
		setTransform(t3d);
		
		
	}
	@Override
	void walk() {
		// TODO Auto-generated method stub
		
	}
	@Override
	void sit() {
		// TODO Auto-generated method stub
		
	}
	@Override
	void run() {
		// TODO Auto-generated method stub
		
	}
}
