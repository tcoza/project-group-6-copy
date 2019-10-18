package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Id;

@Entity
public class EvaluationComment {
	public EvaluationComment() {
		
	}
	private String comment;

	public void setComment(String value) {
		this.comment = value;
	}

	public String getComment() {
		return this.comment;
	}

	private Evaluation evaluation;

	@OneToOne(optional = false)
	public Evaluation getEvaluation() {
		return this.evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

	private int id;

	public void setId(int value) {
		this.id = value;
	}

	@Id
	public int getId() {
		return this.id;
	}
}
