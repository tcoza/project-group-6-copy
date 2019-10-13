package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;

@Entity
public class Subject {
	private String name;

	public void setName(String value) {
		this.name = value;
	}

	@Id
	public String getName() {
		return this.name;
	}

	private Set<Course> course;

	@OneToMany(mappedBy = "subject")
	public Set<Course> getCourse() {
		return this.course;
	}

	public void setCourse(Set<Course> courses) {
		this.course = courses;
	}

	private TeachingInstitution school;

	@ManyToOne(optional = false)
	public TeachingInstitution getSchool() {
		return this.school;
	}

	public void setSchool(TeachingInstitution school) {
		this.school = school;
	}

	private Set<Tutor> qualifiedTutor;

	@ManyToMany
	public Set<Tutor> getQualifiedTutor() {
		return this.qualifiedTutor;
	}

	public void setQualifiedTutor(Set<Tutor> qualifiedTutors) {
		this.qualifiedTutor = qualifiedTutors;
	}

	private Set<SessionRequest> request;

	@OneToMany(mappedBy = "requestedSubject")
	public Set<SessionRequest> getRequest() {
		return this.request;
	}

	public void setRequest(Set<SessionRequest> requests) {
		this.request = requests;
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
