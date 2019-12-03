package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * This auto-generated is our model class for TutorEvaluation, a sub-class of
 * Evaluation. The primary key is the evaluation 'id'. Table per class
 * inheritance strategy is used for our JPA mapping to the persistence layer. A
 * TutorEvaluation has a Tutor as the recipient of the evaluation, and a Student
 * as the author of the evaluation.
 */
@Entity
public class TutorEvaluation extends Evaluation {
	public TutorEvaluation() {
		super();
	}

	private Student author;

	@ManyToOne(optional = false)
	public Student getAuthor() {
		return this.author;
	}

	public void setAuthor(Student author) {
		this.author = author;
	}

	private Tutor recipient;

	@ManyToOne(optional = false)
	public Tutor getRecipient() {
		return this.recipient;
	}

	public void setRecipient(Tutor recipient) {
		this.recipient = recipient;
	}

}
