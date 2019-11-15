package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

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
