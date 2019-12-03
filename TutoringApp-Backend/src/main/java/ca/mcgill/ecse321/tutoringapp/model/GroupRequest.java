package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * This auto-generated is our model class for GroupRequest, a sub-class of
 * SessionRequest. The primary key is the request 'id'. Table per class
 * inheritance strategy is used for our JPA mapping to the persistence layer. A
 * GroupRequest can have a scheduled session for when the request is fulfilled.
 */
@Entity
public class GroupRequest extends SessionRequest {
	public GroupRequest() {
		super();
	}

	private ScheduledGroupSession scheduledGroupSession;

	@ManyToOne
	public ScheduledGroupSession getScheduledGroupSession() {
		return this.scheduledGroupSession;
	}

	public void setScheduledGroupSession(ScheduledGroupSession scheduledGroupSession) {
		this.scheduledGroupSession = scheduledGroupSession;
	}

}
