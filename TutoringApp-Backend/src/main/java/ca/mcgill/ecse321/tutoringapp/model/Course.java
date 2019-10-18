package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Course {
	public Course() {
		
	}
	private String courseCode;

	public void setCourseCode(String value) {
		this.courseCode = value;
	}

	@Id
	public String getCourseCode() {
		return this.courseCode;
	}

	private String name;

	public void setName(String value) {
		this.name = value;
	}

	public String getName() {
		return this.name;
	}

	private TeachingInstitution school;

	@ManyToOne(optional = false)
	public TeachingInstitution getSchool() {
		return this.school;
	}

	public void setSchool(TeachingInstitution school) {
		this.school = school;
	}

	//private Subject subject;

	//@ManyToOne(optional = false)
	//public Subject getSubject() {
	//	return this.subject;
	//}

	//public void setSubject(Subject subject) {
		//this.subject = subject;
	//}

	private Set<Tutor> qualifiedTutor;

	@ManyToMany(mappedBy = "course")
	public Set<Tutor> getQualifiedTutor() {
		return this.qualifiedTutor;
	}

	public void setQualifiedTutor(Set<Tutor> qualifiedTutors) {
		this.qualifiedTutor = qualifiedTutors;
	}

	private Set<SessionRequest> request;

	@OneToMany(mappedBy = "requestedCourse")
	public Set<SessionRequest> getRequest() {
		return this.request;
	}

	public void setRequest(Set<SessionRequest> requests) {
		this.request = requests;
	}

	

}
