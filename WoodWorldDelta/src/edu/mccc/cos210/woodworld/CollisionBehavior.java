package edu.mccc.cos210.woodworld;
import java.util.Enumeration;

import javax.media.j3d.Behavior;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Group;
import javax.media.j3d.WakeupOnBehaviorPost;
import javax.media.j3d.WakeupOnElapsedTime;
import javax.vecmath.Point3d;
public class CollisionBehavior extends Behavior {
	protected int postId;
	protected int duration;
	protected Behavior behavior;
	protected Group group;
	protected BoundingSphere bs = new BoundingSphere(
		new Point3d(0.0, 0.0, 0.0),
		Double.MAX_VALUE
	);
	public CollisionBehavior(int postId, int duration, Behavior behavior, Group group) {
		this.postId = postId;
		setDuration(duration);
		setBehavior(behavior);
		setSchedulingBounds(bs);
		setGroup(group);
		getGroup().addChild(this);
	}
	public void initialize() {
		wakeupOn(new WakeupOnBehaviorPost(null, getPostId()));
	}
	@SuppressWarnings("rawtypes")
	public void processStimulus(Enumeration e) {
		if (e.nextElement() instanceof WakeupOnBehaviorPost) {
			getBehavior().setEnable(true);
			wakeupOn(new WakeupOnElapsedTime(duration));
		} else {
			getBehavior().setEnable(false);
			wakeupOn(new WakeupOnBehaviorPost(null, getPostId()));
		}
	}
	public int getPostId() {
		return this.postId;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getDuration() {
		return this.duration;
	}
	public void setBehavior(Behavior behavior) {
		this.behavior = behavior;
	}
	public Behavior getBehavior() {
		return this.behavior;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public Group getGroup() {
		return this.group;
	}
}
