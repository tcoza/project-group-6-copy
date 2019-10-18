package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class ScheduledPrivateSession extends ScheduledSession {
	public ScheduledPrivateSession() {
		super();
	}
	private SmallRoom room;

	@ManyToOne(optional = false)
	public SmallRoom getRoom() {
		return this.room;
	}

	public void setRoom(SmallRoom room) {
		this.room = room;
	}

	private PrivateRequest privateRequest;

	@OneToOne(optional = false)
	public PrivateRequest getPrivateRequest() {
		return this.privateRequest;
	}

	public void setPrivateRequest(PrivateRequest privateRequest) {
		this.privateRequest = privateRequest;
	}

}
