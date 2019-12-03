package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * This auto-generated is our model class for PrivateRequest, a sub-class of
 * SessionRequest. The primary key is the request 'id'. Table per class
 * inheritance strategy is used for our JPA mapping to the persistence layer. A
 * PrivateRequest can have a scheduled session for when the request is fulfilled.
 */
@Entity
public class PrivateRequest extends SessionRequest {
	public PrivateRequest() {
		super();
	}
	private ScheduledPrivateSession scheduledPrivateSession;

	@OneToOne(mappedBy = "privateRequest")
	public ScheduledPrivateSession getScheduledPrivateSession() {
		return this.scheduledPrivateSession;
	}

	public void setScheduledPrivateSession(ScheduledPrivateSession scheduledPrivateSession) {
		this.scheduledPrivateSession = scheduledPrivateSession;
	}

}
