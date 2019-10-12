package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.persistence.Id;

@Entity
public class Evaluation{
   private EvaluationComment evaluationComment;
   
   @OneToOne(mappedBy="evaluation" , cascade={CascadeType.ALL})
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
private String/*No type specified!*/ date;

public void setDate(String/*No type specified!*/ value) {
    this.date = value;
}
public String/*No type specified!*/ getDate() {
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
