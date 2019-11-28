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
	private String evaluationComment;	

	public String getEvaluationComment() {
		return this.evaluationComment;
	}

	public void setEvaluationComment(String evaluationComment) {
		this.evaluationComment = evaluationComment;
	}

	
	private boolean commentVisible;
	
	public boolean getCommentVisible() {
		return this.commentVisible;
	}

	public void setCommentVisible(boolean commentVisible) {
		this.commentVisible = commentVisible;
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
