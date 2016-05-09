package edu.mccc.cos210.woodworld;

import java.util.Vector;

import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.media.j3d.Texture;
import javax.media.j3d.Transform3D;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.image.TextureLoader;

public class ManRich extends Mannequin {
	Appearance app;
	static TextureLoader tl = new TextureLoader("textureR.jpg", null);
	public ManRich() {
		super(0.9f, 0.9f, tl);
		
	}
	@Override
	public void dance() {
		
		
	}

	@Override
	public void jump() {
		
		
		
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
