package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import java.sql.Date;
import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class SessionRequest {
	private Date dateCreated;

	public void setDateCreated(Date value) {
		this.dateCreated = value;
	}

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

	@ManyToOne(optional = false)
	public Student getRequestor() {
		return this.requestor;
	}

	public void setRequestor(Student requestor) {
		this.requestor = requestor;
	}

	private Set<Offering> offering;

	@ManyToMany(mappedBy = "sessionRequest")
	public Set<Offering> getOffering() {
		return this.offering;
	}

	public void setOffering(Set<Offering> offerings) {
		this.offering = offerings;
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

	private TutoringApplicationModel tutoringApplication;

	@ManyToOne(optional = false)
	public TutoringApplicationModel getTutoringApplication() {
		return this.tutoringApplication;
	}

	public void setTutoringApplication(TutoringApplicationModel tutoringApplication) {
		this.tutoringApplication = tutoringApplication;
	}

}
