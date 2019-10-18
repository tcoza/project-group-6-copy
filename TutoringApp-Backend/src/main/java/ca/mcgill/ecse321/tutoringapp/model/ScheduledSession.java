package ca.mcgill.ecse321.tutoringapp.model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ScheduledSession {
	public ScheduledSession() {
		
	}
	private int id;

	public void setId(int value) {
		this.id = value;
	}

	@Id
	public int getId() {
		return this.id;
	}

	private Boolean isFull;

	public void setIsFull(Boolean value) {
		this.isFull = value;
	}

	public Boolean getIsFull() {
		return this.isFull;
	}

	private Date date;

	public void setDate(Date value) {
		this.date = value;
	}

	@Column
	public Date getDate() {
		return this.date;
	}

	private Time startTime;

	public void setStartTime(Time value) {
		this.startTime = value;
	}

	@Column
	public Time getStartTime() {
		return this.startTime;
	}

	private Time endTime;

	public void setEndTime(Time value) {
		this.endTime = value;
	}

	@Column
	public Time getEndTime() {
		return this.endTime;
	}

	private Tutor assignedTutor;

	@ManyToOne(optional = false)
	public Tutor getAssignedTutor() {
		return this.assignedTutor;
	}

	public void setAssignedTutor(Tutor assignedTutor) {
		this.assignedTutor = assignedTutor;
	}


}
