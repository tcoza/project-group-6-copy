package ca.mcgill.ecse321.tutoringApp.model;

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
   
   @OneToOne(optional=false)
   public Evaluation getEvaluation() {
      return this.evaluation;
   }
   
   public void setEvaluation(Evaluation evaluation) {
      this.evaluation = evaluation;
   }
   
   }
