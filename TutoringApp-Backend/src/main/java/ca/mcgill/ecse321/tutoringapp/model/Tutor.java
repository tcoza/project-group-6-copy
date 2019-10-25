package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Tutor extends AppUser {
	public Tutor() {
		super();
	}
	private TutorStatus status = TutorStatus.PENDING;

	public void setStatus(TutorStatus value) {
		this.status = value;
	}

	public TutorStatus getStatus() {
		return this.status;
	}

	private Set<Course> courses;

	@ManyToMany
	public Set<Course> getCourses() {
		return this.courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	
	private Set<TutorEvaluation> tutorEvaluations;

	@OneToMany(mappedBy = "recipient", cascade = { CascadeType.ALL }, orphanRemoval=true)
	public Set<TutorEvaluation> getTutorEvaluations() {
		return this.tutorEvaluations;
	}

	public void setTutorEvaluations(Set<TutorEvaluation> tutorEvaluations) {
		this.tutorEvaluations = tutorEvaluations;
	}

	private Set<StudentEvaluation> studentEvaluations;

	@OneToMany(mappedBy = "author", cascade = { CascadeType.ALL })
	public Set<StudentEvaluation> getStudentEvaluations() {
		return this.studentEvaluations;
	}

	public void setStudentEvaluations(Set<StudentEvaluation> studentEvaluations) {
		this.studentEvaluations = studentEvaluations;
	}

	private Set<Subject> subjects;

	@ManyToMany(mappedBy = "qualifiedTutor")
	public Set<Subject> getSubjects() {
		return this.subjects;
	}

	public void setSubjects(Set<Subject> subjects) {
		this.subjects = subjects;
	}

	private Set<ScheduledSession> scheduledSessions;

	@OneToMany(mappedBy = "assignedTutor")
	public Set<ScheduledSession> getScheduledSessions() {
		return this.scheduledSessions;
	}

	public void setScheduledSessions(Set<ScheduledSession> scheduledSessions) {
		this.scheduledSessions = scheduledSessions;
	}

}
