package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * This auto-generated is our model class for ScheduledPrivateSession, a
 * child-class of ScheduledSession. The primary key is the session'id'. Table
 * per class inheritance strategy is used for our JPA mapping to the persistence
 * layer. A ScheduledPrivateSession has a smallroom it will be held at, and a
 * PrivateSessionRequest that it fulfills.
 */
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

	@OneToOne
	public PrivateRequest getPrivateRequest() {
		return this.privateRequest;
	}

	public void setPrivateRequest(PrivateRequest privateRequest) {
		this.privateRequest = privateRequest;
	}

}
