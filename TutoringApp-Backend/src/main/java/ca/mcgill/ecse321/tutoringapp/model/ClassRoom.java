package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;

/**
 * This auto-generated is our model class for ClassRoom, a child-class of Room.
 * The primary key is the room 'id'. Table per class inheritance strategy is
 * used for our JPA mapping to the persistence layer. A Classroom has a max
 * studentCapacity of 15 students, and a Classroom can have many scheduled group
 * tutoring sessions booked for it.
 */
@Entity
public class ClassRoom extends Room {
	public ClassRoom() {
		super();
	}

	private Set<ScheduledGroupSession> scheduledGroupSession;

	@OneToMany(mappedBy = "room")
	public Set<ScheduledGroupSession> getScheduledGroupSession() {
		return this.scheduledGroupSession;
	}

	public void setScheduledGroupSession(Set<ScheduledGroupSession> scheduledGroupSessions) {
		this.scheduledGroupSession = scheduledGroupSessions;
	}

	private int studentCapacity = 15;

	public void setStudentCapacity(int value) {
		this.studentCapacity = value;
	}

	public int getStudentCapacity() {
		return this.studentCapacity;
	}
}
