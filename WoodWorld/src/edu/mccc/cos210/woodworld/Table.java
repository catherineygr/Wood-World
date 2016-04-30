package edu.mccc.cos210.woodworld;
import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
public class Table extends QuadArray {
	public Table() {
		super(120, GeometryArray.COORDINATES | GeometryArray.NORMALS);
		setCoordinates(
			0,
			new double[] {
				-0.75,  0.025, -0.25,	// Top
				-0.75,  0.025,  0.25,
				 0.75,  0.025,  0.25,
				 0.75,  0.025, -0.25,

				-0.75, -0.025,  0.25,	// Bottom
				-0.75, -0.025, -0.25,
				 0.75, -0.025, -0.25,
				 0.75, -0.025,  0.25,

				-0.75,  0.025,  0.25,	// Front Side
				-0.75, -0.025,  0.25,
				 0.75, -0.025,  0.25,
				 0.75,  0.025,  0.25,

				 0.75,  0.025, -0.25,	// Back Side
				 0.75, -0.025, -0.25,
				-0.75, -0.025, -0.25,
				-0.75,  0.025, -0.25,

				-0.75,  0.025, -0.25,	// Left End
				-0.75, -0.025, -0.25,
				-0.75, -0.025,  0.25,
				-0.75,  0.025,  0.25,

				 0.75,  0.025,  0.25,	// Right End
				 0.75, -0.025,  0.25,
				 0.75, -0.025, -0.25,
				 0.75,  0.025, -0.25
			}
		);
		double[] leg = makeLeg();
		positionLeg(leg, -(0.75 - 0.0125), -(0.3 + 0.025), -(0.25 - 0.0125));
		setCoordinates(24, leg);
		leg = makeLeg();
		positionLeg(leg, -(0.75 - 0.0125), -(0.3 + 0.025), +(0.25 - 0.0125));
		setCoordinates(48, leg);
		leg = makeLeg();
		positionLeg(leg, +(0.75 - 0.0125), -(0.3 + 0.025), +(0.25 - 0.0125));
		setCoordinates(72, leg);
		leg = makeLeg();
		positionLeg(leg, +(0.75 - 0.0125), -(0.3 + 0.025), -(0.25 - 0.0125));
		setCoordinates(96, leg);
		GeometryInfo gi = new GeometryInfo(this);
		NormalGenerator ng = new NormalGenerator();
		ng.generateNormals(gi);
		setNormals(0, gi.getNormals());
	}
	private void positionLeg(double[] leg, double xDelta, double yDelta, double zDelta) {
		for (int i = 0; i < leg.length; i += 3) {
			leg[i] += xDelta;
			leg[i + 1] += yDelta;
			leg[i + 2] += zDelta;
		}
	}
	private double[] makeLeg() {
		return new double[] {
			-0.0125,  0.3, -0.0125,	// Top of Leg
			-0.0125,  0.3,  0.0125,
			 0.0125,  0.3,  0.0125,
			 0.0125,  0.3, -0.0125,

			-0.0125, -0.3,  0.0125,	// Bottom of Leg
			-0.0125, -0.3, -0.0125,
			 0.0125, -0.3, -0.0125,
			 0.0125, -0.3,  0.0125,

			-0.0125,  0.3,  0.0125,	// Front Side of Leg
			-0.0125, -0.3,  0.0125,
			 0.0125, -0.3,  0.0125,
			 0.0125,  0.3,  0.0125,

			 0.0125,  0.3, -0.0125,	// Back Side of Leg
			 0.0125, -0.3, -0.0125,
			-0.0125, -0.3, -0.0125,
			-0.0125,  0.3, -0.0125,

			-0.0125,  0.3, -0.0125,	// Left End of Leg
			-0.0125, -0.3, -0.0125,
			-0.0125, -0.3,  0.0125,
			-0.0125,  0.3,  0.0125,

			 0.0125,  0.3,  0.0125,	// Right End of Leg
			 0.0125, -0.3,  0.0125,
			 0.0125, -0.3, -0.0125,
			 0.0125,  0.3, -0.0125
		};
	}
}
