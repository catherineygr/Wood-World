package edu.mccc.cos210.woodworld;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.PickSegment;
import javax.media.j3d.SceneGraphPath;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.GMatrix;
import javax.vecmath.Matrix3d;
import javax.vecmath.Matrix4d;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.picking.PickResult;
public class KeyCABehavior extends KeyBehavior {
	private TransformGroup tg;
	private BranchGroup bg;
	public KeyCABehavior(TransformGroup tg, BranchGroup bg) {
		super(tg);
		this.tg = tg;
		this.bg = bg;
	}
	protected boolean isCollision(Vector3d v3d) {
		Transform3D t3d = new Transform3D();
		tg.getTransform(t3d);
		Matrix4d m4d = new Matrix4d();
		t3d.get(m4d);
		GMatrix gm1 = new GMatrix(
			4,
			4,
			new double[] {
				1.0, 0.0, 0.0, v3d.x,
				0.0, 1.0, 0.0, v3d.y,
				0.0, 0.0, 1.0, v3d.z,
				0.0, 0.0, 0.0, 1.0
			}
		);
		Matrix3d m3d = new Matrix3d();
		m4d.get(m3d);
		GMatrix gm2 = new GMatrix(
			4,
			4,
			new double[] {
				m3d.m00, m3d.m01, m3d.m02, 0.0,
				m3d.m10, m3d.m11, m3d.m12, 0.0,
				m3d.m20, m3d.m21, m3d.m22, 0.0,
				0.0, 0.0, 0.0, 1.0
			}
		);
		gm2.mul(gm1);
		double[] d = new double[4];
		gm2.getColumn(3, d);
		Vector3d v3d0 = new Vector3d();
		v3d0.x = speed == SLOW ? d[0] * 20.0 : d[0] * 2.0;
		v3d0.y = speed == SLOW ? d[1] * 20.0 : d[1] * 2.0;
		v3d0.z = speed == SLOW ? d[2] * 20.0 : d[2] * 2.0;
		PickSegment ps = new PickSegment(
			new Point3d(m4d.m03, m4d.m13, m4d.m23),
			new Point3d(
				m4d.m03 + v3d0.x,
				m4d.m13 + v3d0.y,
				m4d.m23 + v3d0.z
			)
		);
		Point3d p3dstart = new Point3d();
		Point3d p3dend = new Point3d();
		ps.get(p3dstart, p3dend);
		SceneGraphPath sgp = bg.pickAny(ps);
		if (!(sgp == null)) {
			if (sgp.getObject() instanceof Shape3D) {
				@SuppressWarnings("unused")
				Shape3D s3d = (Shape3D) sgp.getObject();
				PickResult pr = new PickResult(sgp, ps);
				if (pr.numIntersections() > 0) {
					return true;
				}
			} else {
				return true;
			}
		}
		return false;
	}
}
