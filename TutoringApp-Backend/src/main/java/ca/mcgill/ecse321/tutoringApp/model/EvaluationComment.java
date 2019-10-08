package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class EvaluationComment{
   private String comment;

public void setComment(String value) {
    this.comment = value;
}
public String getComment() {
    return this.comment;
}
   private Evaluation evaluation;
   
   @OneToOne
   public Evaluation getEvaluation() {
      return this.evaluation;
   }
   
   public void setEvaluation(Evaluation evaluation) {
      this.evaluation = evaluation;
   }
   
   }
