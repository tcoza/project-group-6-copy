package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;

/**
 * This auto-generated is our model class for Subject. The primary key is the
 * subject's name. A Subject has an associated school that it is taught at, and
 * can be many tutors that are qualified to teach it, as well as many
 * SessionRequests for tutoring sessions for this subject.
 */
@Entity
public class Subject {
	public Subject() {

	}

	private String name;

	public void setName(String value) {
		this.name = value;
	}

	@Id
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

	private Set<Tutor> qualifiedTutor;

	@ManyToMany
	public Set<Tutor> getQualifiedTutor() {
		return this.qualifiedTutor;
	}

	public void setQualifiedTutor(Set<Tutor> qualifiedTutors) {
		this.qualifiedTutor = qualifiedTutors;
	}

	private Set<SessionRequest> request;

	@OneToMany(mappedBy = "requestedSubject", cascade = { CascadeType.ALL }, orphanRemoval = true)
	public Set<SessionRequest> getRequest() {
		return this.request;
	}

	public void setRequest(Set<SessionRequest> requests) {
		this.request = requests;
	}

}
