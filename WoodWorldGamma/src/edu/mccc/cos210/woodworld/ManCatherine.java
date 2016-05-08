package edu.mccc.cos210.woodworld;

import java.util.Vector;

import javax.media.j3d.Alpha;
import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.media.j3d.Texture;
import javax.media.j3d.Transform3D;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.image.TextureLoader;

public class ManCatherine extends Mannequin {
	Appearance app;
	static TextureLoader tl = new TextureLoader("textureC.jpg", null);
	public ManCatherine() {
		super(0.8f, 0.8f, tl);
		
	}
	@Override
	public void dance() {
		
		if (ri.getEnable()) {
			ri.setEnable(false);
		} else {
			Alpha alpha= new Alpha(5, 2000);
			alpha.setStartTime(System.currentTimeMillis());
			ri.setAlpha(alpha);			
			ri.setEnable(true);
		}	
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
