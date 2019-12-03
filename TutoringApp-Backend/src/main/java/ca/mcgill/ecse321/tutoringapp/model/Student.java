package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;

/**
 * This auto-generated is our model class for Student, a child-class of AppUser.
 * The primary key is the username. Table per class inheritance strategy is used
 * for our JPA mapping to the persistence layer. A student can have many session
 * requests, be the recipient of many StudentEvaluations, and be the author of
 * many TutorEvaluations
 */
@Entity
public class Student extends AppUser {

	public Student() {
		super();
	}

	private Boolean isActiveAccount = true;

	public void setIsActiveAccount(Boolean value) {
		this.isActiveAccount = value;
	}

	public Boolean getIsActiveAccount() {
		return this.isActiveAccount;
	}

	private Set<SessionRequest> requests;

	@OneToMany(mappedBy = "requestor", cascade = { CascadeType.ALL }, orphanRemoval = true)
	public Set<SessionRequest> getRequests() {
		return this.requests;
	}

	public void setRequests(Set<SessionRequest> requests) {
		this.requests = requests;
	}

	private Set<TutorEvaluation> tutorEvaluation;

	@OneToMany(mappedBy = "author", cascade = { CascadeType.ALL }, orphanRemoval = true)
	public Set<TutorEvaluation> getTutorEvaluation() {
		return this.tutorEvaluation;
	}

	public void setTutorEvaluation(Set<TutorEvaluation> tutorEvaluations) {
		this.tutorEvaluation = tutorEvaluations;
	}

	private Set<StudentEvaluation> studentEvaluation;

	@OneToMany(mappedBy = "recipient", cascade = { CascadeType.ALL }, orphanRemoval = true)
	public Set<StudentEvaluation> getStudentEvaluation() {
		return this.studentEvaluation;
	}

	public void setStudentEvaluation(Set<StudentEvaluation> studentEvaluations) {
		this.studentEvaluation = studentEvaluations;
	}

}
