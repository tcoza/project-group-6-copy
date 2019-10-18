package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;

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

	@OneToMany(mappedBy = "requestor")
	public Set<SessionRequest> getRequests() {
		return this.requests;
	}

	public void setRequests(Set<SessionRequest> requestss) {
		this.requests = requestss;
	}

	private Set<TutorEvaluation> tutorEvaluation;

	@OneToMany(mappedBy = "author")
	public Set<TutorEvaluation> getTutorEvaluation() {
		return this.tutorEvaluation;
	}

	public void setTutorEvaluation(Set<TutorEvaluation> tutorEvaluations) {
		this.tutorEvaluation = tutorEvaluations;
	}

	private Set<StudentEvaluation> studentEvaluation;

	@OneToMany(mappedBy = "recipient")
	public Set<StudentEvaluation> getStudentEvaluation() {
		return this.studentEvaluation;
	}

	public void setStudentEvaluation(Set<StudentEvaluation> studentEvaluations) {
		this.studentEvaluation = studentEvaluations;
	}

}
