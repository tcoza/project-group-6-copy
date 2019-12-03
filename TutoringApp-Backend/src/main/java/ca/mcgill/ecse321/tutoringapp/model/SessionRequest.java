package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import java.sql.Date;

/**
 * This auto-generated is our model class for SessionRequest, a super-class of
 * PrivateRequest and GroupRequest. The primary key is the request 'id'. Table
 * per class inheritance strategy is used for our JPA mapping to the persistence
 * layer. A SessionRequest has a Student requestor, a date created, a boolean
 * value for whether it was scheduled or not, and an associated course or
 * subject for the request.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class SessionRequest {
	public SessionRequest() {

	}

	private Date dateCreated;

	public void setDateCreated(Date value) {
		this.dateCreated = value;
	}

	@Column
	public Date getDateCreated() {
		return this.dateCreated;
	}

	private boolean isScheduled = false;

	public void setIsScheduled(boolean value) {
		this.isScheduled = value;
	}

	public boolean isIsScheduled() {
		return this.isScheduled;
	}

	private int id;

	public void setId(int value) {
		this.id = value;
	}

	@Id
	public int getId() {
		return this.id;
	}

	private Student requestor;

	@ManyToOne
	public Student getRequestor() {
		return this.requestor;
	}

	public void setRequestor(Student requestor) {
		this.requestor = requestor;
	}

	private Subject requestedSubject;

	@ManyToOne
	public Subject getRequestedSubject() {
		return this.requestedSubject;
	}

	public void setRequestedSubject(Subject requestedSubject) {
		this.requestedSubject = requestedSubject;
	}

	private Course requestedCourse;

	@ManyToOne
	public Course getRequestedCourse() {
		return this.requestedCourse;
	}

	public void setRequestedCourse(Course requestedCourse) {
		this.requestedCourse = requestedCourse;
	}

}
