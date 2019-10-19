package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


import java.sql.Date;
import java.util.Set;
import javax.persistence.ManyToMany;

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

	

}
