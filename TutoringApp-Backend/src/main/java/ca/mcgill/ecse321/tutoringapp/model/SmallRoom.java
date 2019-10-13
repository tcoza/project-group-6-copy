package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class SmallRoom extends Room {
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
