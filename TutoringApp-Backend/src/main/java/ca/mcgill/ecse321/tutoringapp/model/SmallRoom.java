package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;

/**
 * This auto-generated is our model class for SmallRoom, a child-class of Room.
 * The primary key is the room 'id'. Table per class inheritance strategy is
 * used for our JPA mapping to the persistence layer. A SmallRoom has a max
 * studentCapacity of 1 student, and a SmallRoom can have many scheduled private
 * tutoring sessions booked for it.
 */
@Entity
public class SmallRoom extends Room {
	public SmallRoom() {
		super();
	}

	private Set<ScheduledPrivateSession> scheduledPrivateSession;

	@OneToMany(mappedBy = "room")
	public Set<ScheduledPrivateSession> getScheduledPrivateSession() {
		return this.scheduledPrivateSession;
	}

	public void setScheduledPrivateSession(Set<ScheduledPrivateSession> scheduledPrivateSessions) {
		this.scheduledPrivateSession = scheduledPrivateSessions;
	}

	private int studentCapacity = 1;

	public void setStudentCapacity(int value) {
		this.studentCapacity = value;
	}

	public int getStudentCapacity() {
		return this.studentCapacity;
	}
}
