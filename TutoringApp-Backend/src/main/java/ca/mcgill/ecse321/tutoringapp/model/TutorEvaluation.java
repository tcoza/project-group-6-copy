package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class TutorEvaluation extends Evaluation {
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
