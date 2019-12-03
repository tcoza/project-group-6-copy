package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.OneToMany;

/**
 * This auto-generated is our model class for ScheduledGroupSession, a
 * child-class of ScheduledSession. The primary key is the session'id'. Table
 * per class inheritance strategy is used for our JPA mapping to the persistence
 * layer. A ScheduledGroupSession has an integer value for the number of
 * registered students, and a classroom it will be held at, and can have many
 * GroupSessionRequest that it fulfills.
 */
@Entity
public class ScheduledGroupSession extends ScheduledSession {
	public ScheduledGroupSession() {
		super();
	}

	private ClassRoom room;

	@ManyToOne(optional = false)
	public ClassRoom getRoom() {
		return this.room;
	}

	public void setRoom(ClassRoom room) {
		this.room = room;
	}

	private int numRegisteredStudents;

	public void setNumRegisteredStudents(int value) {
		this.numRegisteredStudents = value;
	}

	public int getNumRegisteredStudents() {
		return this.numRegisteredStudents;
	}

	private Set<GroupRequest> groupRequest;

	@OneToMany(mappedBy = "scheduledGroupSession")
	public Set<GroupRequest> getGroupRequest() {
		return this.groupRequest;
	}

	public void setGroupRequest(Set<GroupRequest> groupRequests) {
		this.groupRequest = groupRequests;
	}

}
