package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class ScheduledGroupSession extends ScheduledSession {
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
