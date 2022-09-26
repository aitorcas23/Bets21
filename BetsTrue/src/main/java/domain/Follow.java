package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Follow {
	@Id @GeneratedValue
	private int id;
	private NotAdmin follower;
	private NotAdmin followed;
	private double percentage;
	
	public Follow(NotAdmin follower, NotAdmin followed, double percentage) {
		this.follower = follower;
		this.followed = followed;
		this.percentage = percentage;
	}
	
	public NotAdmin getFollower() {
		return follower;
	}
	
	public NotAdmin getFollowed() {
		return followed;
	}
	
	public double getPercentage() {
		return percentage;
	}
}
