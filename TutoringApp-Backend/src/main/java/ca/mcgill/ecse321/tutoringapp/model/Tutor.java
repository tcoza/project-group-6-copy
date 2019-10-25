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

	private Set<Course> course;

	@ManyToMany
	public Set<Course> getCourse() {
		return this.course;
	}

	public void setCourse(Set<Course> courses) {
		this.course = courses;
	}
	
	private Set<TutorEvaluation> tutorEvaluation;

	@OneToMany(mappedBy = "recipient", cascade = { CascadeType.ALL }, orphanRemoval=true)
	public Set<TutorEvaluation> getTutorEvaluation() {
		return this.tutorEvaluation;
	}

	public void setTutorEvaluation(Set<TutorEvaluation> tutorEvaluations) {
		this.tutorEvaluation = tutorEvaluations;
	}

	private Set<StudentEvaluation> studentEvaluation;

	@OneToMany(mappedBy = "author", cascade = { CascadeType.ALL })
	public Set<StudentEvaluation> getStudentEvaluation() {
		return this.studentEvaluation;
	}

	public void setStudentEvaluation(Set<StudentEvaluation> studentEvaluations) {
		this.studentEvaluation = studentEvaluations;
	}

	private Set<Subject> subject;

	@ManyToMany(mappedBy = "qualifiedTutor")
	public Set<Subject> getSubject() {
		return this.subject;
	}

	public void setSubject(Set<Subject> subjects) {
		this.subject = subjects;
	}

	private Set<ScheduledSession> scheduledSession;

	@OneToMany(mappedBy = "assignedTutor")
	public Set<ScheduledSession> getScheduledSession() {
		return this.scheduledSession;
	}

	public void setScheduledSession(Set<ScheduledSession> scheduledSessions) {
		this.scheduledSession = scheduledSessions;
	}

}
