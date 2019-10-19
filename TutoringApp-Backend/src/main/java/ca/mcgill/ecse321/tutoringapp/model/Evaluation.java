package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Evaluation {
	
	public Evaluation() {
		
	}
	private EvaluationComment evaluationComment;

	@OneToOne(mappedBy = "evaluation", cascade = { CascadeType.ALL }, orphanRemoval = true)
	public EvaluationComment getEvaluationComment() {
		return this.evaluationComment;
	}

	public void setEvaluationComment(EvaluationComment evaluationComment) {
		this.evaluationComment = evaluationComment;
	}

	private int rating;

	public void setRating(int value) {
		this.rating = value;
	}

	public int getRating() {
		return this.rating;
	}

	private Date date;

	public void setDate(Date value) {
		this.date = value;
	}

	@Column
	public Date getDate() {
		return this.date;
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
