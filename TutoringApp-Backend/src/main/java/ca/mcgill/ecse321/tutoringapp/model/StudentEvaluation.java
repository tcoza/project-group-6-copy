package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * This auto-generated is our model class for StudentEvaluation, a sub-class of
 * Evaluation. The primary key is the evaluation 'id'. Table per class
 * inheritance strategy is used for our JPA mapping to the persistence layer. A
 * StudentEvaluation has a Student as the recipient of the evaluation, and a
 * Tutor as the author of the evaluation.
 */
@Entity
public class StudentEvaluation extends Evaluation {

	public StudentEvaluation() {
		super();
	}

	private Student recipient;

	@ManyToOne(optional = false)
	public Student getRecipient() {
		return this.recipient;
	}

	public void setRecipient(Student recipient) {
		this.recipient = recipient;
	}

	private Tutor author;

	@ManyToOne(optional = false)
	public Tutor getAuthor() {
		return this.author;
	}

	public void setAuthor(Tutor author) {
		this.author = author;
	}

}
