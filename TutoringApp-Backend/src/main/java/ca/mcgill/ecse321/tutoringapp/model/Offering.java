package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import java.sql.Time;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Offering {
	public Offering() {
		
	}
	private Weekday weekday;

	public void setWeekday(Weekday value) {
		this.weekday = value;
	}

	public Weekday getWeekday() {
		return this.weekday;
	}

	private Time endTime;

	public void setEndTime(Time value) {
		this.endTime = value;
	}

	@Column
	public Time getEndTime() {
		return this.endTime;
	}

	private Set<SessionRequest> sessionRequest;

	@ManyToMany
	public Set<SessionRequest> getSessionRequest() {
		return this.sessionRequest;
	}

	public void setSessionRequest(Set<SessionRequest> sessionRequests) {
		this.sessionRequest = sessionRequests;
	}

	private Time startTime;

	public void setStartTime(Time value) {
		this.startTime = value;
	}

	@Column
	public Time getStartTime() {
		return this.startTime;
	}

	private int id;

	public void setId(int value) {
		this.id = value;
	}

	@Id
	public int getId() {
		return this.id;
	}


}
